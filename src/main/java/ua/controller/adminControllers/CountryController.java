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

import ua.DTO.FilterForms.adminFilter.CountryFilterForm;
import ua.DTO.SaveForms.CountrySaveForm;
import ua.entity.Country;
import ua.service.CountryService;
import ua.service.validators.adminValidators.CountryValidator;

@Controller
public class CountryController {

	@Autowired
	private CountryService countryService;

	@ModelAttribute("countrySaveForm")
	public CountrySaveForm getCountrySaveForm() {
		return new CountrySaveForm();
	}
	
	@ModelAttribute("filter")
	public CountryFilterForm getCountryFilterForm() {
		return new CountryFilterForm();
	}

	@InitBinder("countrySaveForm")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new CountryValidator(countryService));
	}

	@RequestMapping("/admin/countries")
	public String showCountries(Model model, @PageableDefault(10) Pageable pageable, @ModelAttribute("filter") CountryFilterForm filter) {
		Page<Country> pageCountry = countryService.findAll(pageable, filter);
		model.addAttribute("page", pageCountry);
		model.addAttribute("countPages", pageCountry.getTotalPages());
		return "countries";
	}

	@RequestMapping(value = "/admin/countries", method = RequestMethod.POST)
	public String saveCountry(@Valid @ModelAttribute("countrySaveForm") CountrySaveForm countrySaveForm,
			BindingResult bindingResult, Model model, Pageable pageable, HttpServletRequest request, @ModelAttribute("filter") CountryFilterForm filter) {
		if (bindingResult.hasErrors()) {
			Page<Country> pageCountry = countryService.findAll(pageable, filter);
			model.addAttribute("page", pageCountry);
			model.addAttribute("countPages", pageCountry.getTotalPages());
			return "countries";
		}
		countryService.save(countrySaveForm);
		return "redirect:/admin/countries?" + request.getQueryString();
	}

	@RequestMapping("/admin/countries/delete/{id}")
	public String deleteCountry(@PathVariable int id, Model model, HttpServletRequest request) {
		countryService.delete(id);
		return "redirect:/admin/countries?" + request.getQueryString();
	}

	@RequestMapping("/admin/countries/update/{id}")
	public String updateCountry(@PathVariable int id, Model model, Pageable pageable, @ModelAttribute("filter") CountryFilterForm filter) {
		model.addAttribute("countrySaveForm", countryService.findWithSaveForm(id));
		Page<Country> pageCountry = countryService.findAll(pageable, filter);
		model.addAttribute("page", pageCountry);
		model.addAttribute("countPages", pageCountry.getTotalPages());
		return "countries";
	}
}
