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

import ua.DTO.FilterForms.adminFilter.StatusFilterForm;
import ua.DTO.SaveForms.StatusSaveForm;
import ua.entity.Status;
import ua.service.StatusService;
import ua.service.validators.StatusValidator;

@Controller
public class StatusController {

	@Autowired
	private StatusService statusService;

	@ModelAttribute("statusSaveForm")
	public StatusSaveForm getForm() {
		return new StatusSaveForm();
	}

	@ModelAttribute("filter")
	public StatusFilterForm getFilter() {
		return new StatusFilterForm();
	}

	@InitBinder("statusSaveForm")
	public void setValidator(WebDataBinder binder) {
		binder.setValidator(new StatusValidator(statusService));
	}

	@RequestMapping("/admin/status")
	public String showStatuses(Model model, @PageableDefault(10) Pageable pageable, @ModelAttribute("filter") StatusFilterForm filter) {
		Page<Status> page = statusService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		return "status";
	}

	@RequestMapping(value = "/admin/status", method = RequestMethod.POST)
	public String saveStatus(@Valid @ModelAttribute("statusSaveForm") StatusSaveForm statusSaveForm, BindingResult bindingResult, HttpServletRequest request, Pageable pageable,
			@ModelAttribute("filter") StatusFilterForm filter, Model model) {
		if (bindingResult.hasErrors()) {
			Page<Status> page = statusService.findAll(pageable, filter);
			model.addAttribute("page", page);
			model.addAttribute("countPages", page.getTotalPages());
			return "status";
		}
		statusService.save(statusSaveForm);
		return "redirect:/admin/status?" + request.getQueryString();
	}

	@RequestMapping("/admin/status/delete/{id}")
	public String deleteStatus(@PathVariable int id, HttpServletRequest request) {
		statusService.delete(id);
		return "redirect:/admin/status?" + request.getQueryString();
	}

	@RequestMapping("/admin/status/update/{id}")
	public String updateStatus(@PathVariable int id, Model model, Pageable pageable, @ModelAttribute("filter") StatusFilterForm filter) {
		Page<Status> page = statusService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		model.addAttribute("statusSaveForm", statusService.findWithSaveForm(id));
		return "status";
	}
}
