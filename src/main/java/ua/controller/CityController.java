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

import ua.DTO.FilterForms.adminFilter.CityFilterForm;
import ua.DTO.SaveForms.CitySaveForm;
import ua.entity.City;
import ua.entity.Country;
import ua.service.CityService;
import ua.service.CountryService;
import ua.service.editors.CountryEditor;
import ua.service.validators.CityValidator;

@Controller
public class CityController {

	@Autowired
	private CityService cityService;
	@Autowired
	private CountryService countryService;

	@ModelAttribute("citySaveForm")
	public CitySaveForm getCitySaveForm() {
		return new CitySaveForm();
	}

	@ModelAttribute("filter")
	public CityFilterForm getCityFilterForm() {
		return new CityFilterForm();
	}

	@InitBinder("citySaveForm")
	public void setValidator(WebDataBinder binder) {
		binder.setValidator(new CityValidator(cityService));
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
	}

	@RequestMapping("/admin/cities")
	public String showCities(Model model, @PageableDefault(10) Pageable pageable,
			@ModelAttribute("filter") CityFilterForm filter) {
		Page<City> page = cityService.findAllWithCountries(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		model.addAttribute("countries", countryService.findAll());
		return "cities";
	}

	@RequestMapping(value = "/admin/cities", method = RequestMethod.POST)
	public String saveCity(@Valid @ModelAttribute("citySaveForm") CitySaveForm citySaveForm, BindingResult bindingResult,
			HttpServletRequest request, Pageable pageable, @ModelAttribute("filter") CityFilterForm filter, Model model) {
		if (bindingResult.hasErrors()) {
			Page<City> page = cityService.findAllWithCountries(pageable, filter);
			model.addAttribute("page", page);
			model.addAttribute("countPages", page.getTotalPages());
			model.addAttribute("countries", countryService.findAll());
			return "cities";
		}
		cityService.save(citySaveForm);
		return "redirect:/admin/cities?" + request.getQueryString();
	}

	@RequestMapping("/admin/cities/update/{id}")
	public String updateCity(@PathVariable int id, Model model, Pageable pageable, HttpServletRequest request,
			@ModelAttribute("filter") CityFilterForm filter) {
		Page<City> page = cityService.findAllWithCountries(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		model.addAttribute("citySaveForm", cityService.findWithSaveForm(id));
		model.addAttribute("countries", countryService.findAll());
		return "cities";
	}

	@RequestMapping("/admin/cities/delete/{id}")
	public String deleteCity(@PathVariable int id, Model model, HttpServletRequest request) {
		cityService.delete(id);
		return "redirect:/admin/cities?" + request.getQueryString();
	}
}
