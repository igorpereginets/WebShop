package ua.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.DTO.FilterForms.adminFilter.UserFilterForm;
import ua.DTO.SaveForms.UserSaveForm;
import ua.entity.Address;
import ua.entity.Status;
import ua.entity.User;
import ua.service.AddressService;
import ua.service.StatusService;
import ua.service.UserService;
import ua.service.editors.AddressEditor;
import ua.service.editors.StatusEditor;
import ua.service.validators.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private AddressService addressService;

	@ModelAttribute("userSaveForm")
	public UserSaveForm getForm() {
		return new UserSaveForm();
	}

	@ModelAttribute("filter")
	public UserFilterForm getFilter() {
		return new UserFilterForm();
	}

	@InitBinder("userSaveForm")
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Address.class, new AddressEditor(addressService));
		binder.registerCustomEditor(Status.class, new StatusEditor(statusService));
		binder.setValidator(new UserValidator(userService));
	}

	@RequestMapping("/admin/users")
	public String showUsers(Model model, @PageableDefault(10) Pageable pageable, @ModelAttribute("filter") UserFilterForm filter) {
		Page<User> page = userService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		model.addAttribute("statuses", statusService.findAll());
		model.addAttribute("addresses", addressService.findAll());
		return "users";
	}

	@RequestMapping(value = "/admin/users", method = RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute("userSaveForm") UserSaveForm userSaveForm, BindingResult bindingResult, HttpServletRequest request,
			@PageableDefault(10) Pageable pageable, @ModelAttribute("filter") UserFilterForm filter, Model model) {
		if (bindingResult.hasErrors()) {
			Page<User> page = userService.findAll(pageable, filter);
			model.addAttribute("page", page);
			model.addAttribute("countPages", page.getTotalPages());
			model.addAttribute("statuses", statusService.findAll());
			model.addAttribute("addresses", addressService.findAll());
			return "users";
		}
		userService.save(userSaveForm);
		return "redirect:/admin/users?" + request.getQueryString();
	}

	@RequestMapping("/admin/users/update/{id}")
	public String updateUser(@PathVariable int id, Model model, @PageableDefault(10) Pageable pageable, @ModelAttribute("filter") UserFilterForm filter) {
		Page<User> page = userService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		model.addAttribute("statuses", statusService.findAll());
		model.addAttribute("addresses", addressService.findAll());
		model.addAttribute("userSaveForm", userService.findWithSaveForm(id));
		return "users";
	}

	@RequestMapping("/admin/users/delete/{id}")
	public String deleteUser(@PathVariable int id, HttpServletRequest request) {
		userService.delete(id);
		return "redirect:/admin/users?" + request.getQueryString();
	}
}
