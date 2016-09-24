package ua.controller.adminControllers;

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

import ua.DTO.FilterForms.adminFilter.AddressFilterForm;
import ua.DTO.SaveForms.AddressSaveForm;
import ua.entity.Address;
import ua.entity.City;
import ua.service.AddressService;
import ua.service.CityService;
import ua.service.editors.CityEditor;
import ua.service.validators.adminValidators.AddressValidator;

@Controller
public class AddressController {

	@Autowired
	private AddressService addressService;
	@Autowired
	private CityService cityService;

	@ModelAttribute("addressSaveForm")
	public AddressSaveForm getForm() {
		return new AddressSaveForm();
	}

	@ModelAttribute("filter")
	public AddressFilterForm getFilter() {
		return new AddressFilterForm();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(City.class, new CityEditor(cityService));
	}

	@InitBinder("addressSaveForm")
	public void setValidator(WebDataBinder binder) {
		binder.setValidator(new AddressValidator());
	}

	@RequestMapping("/admin/address")
	public String showAddresses(Model model, @PageableDefault(10) Pageable pageable, @ModelAttribute("filter") AddressFilterForm filter) {
		Page<Address> page = addressService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		model.addAttribute("cities", cityService.findAll());
		return "address";
	}

	@RequestMapping(value = "/admin/address", method = RequestMethod.POST)
	public String saveAddress(@Valid @ModelAttribute("addressSaveForm") AddressSaveForm addressSaveForm, BindingResult bindingResult, HttpServletRequest request, Model model,
			Pageable pageable, @ModelAttribute("filter") AddressFilterForm filter) {
		if (bindingResult.hasErrors()) {
			Page<Address> page = addressService.findAll(pageable, filter);
			model.addAttribute("page", page);
			model.addAttribute("countPages", page.getTotalPages());
			model.addAttribute("cities", cityService.findAll());
			return "address";
		}
		addressService.save(addressSaveForm);
		return "redirect:/admin/address?" + request.getQueryString();
	}

	@RequestMapping("/admin/address/delete/{id}")
	public String deleteAddress(@PathVariable int id, HttpServletRequest request) {
		addressService.delete(id);
		return "redirect:/admin/address?" + request.getQueryString();
	}

	@RequestMapping("/admin/address/update/{id}")
	public String updateAddress(@PathVariable int id, Model model, Pageable pageable, @ModelAttribute("filter") AddressFilterForm filter) {
		Page<Address> page = addressService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		model.addAttribute("addressSaveForm", addressService.findWithSaveForm(id));
		model.addAttribute("cities", cityService.findAll());
		return "address";
	}
}
