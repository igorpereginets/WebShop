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

import ua.DTO.FilterForms.adminFilter.BrandFilterForm;
import ua.DTO.SaveForms.BrandSaveForm;
import ua.entity.Brand;
import ua.service.BrandService;
import ua.service.specification.adminFilterAdapter.BrandValidator;

@Controller
public class BrandController {

	@Autowired
	private BrandService brandService;

	@ModelAttribute("brandSaveForm")
	public BrandSaveForm getForm() {
		return new BrandSaveForm();
	}

	@ModelAttribute("filter")
	public BrandFilterForm getFilter() {
		return new BrandFilterForm();
	}

	@InitBinder("brandSaveForm")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new BrandValidator(brandService));
	}

	@RequestMapping("/admin/brands")
	public String showBrand(Model model, @PageableDefault(10) Pageable pageable, @ModelAttribute("filter") BrandFilterForm filter) {
		Page<Brand> page = brandService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		return "brands";
	}

	@RequestMapping(value = "/admin/brands", method = RequestMethod.POST)
	public String saveBrand(@Valid @ModelAttribute("brandSaveForm") BrandSaveForm brandSaveForm, BindingResult bindingResult, HttpServletRequest request, Model model,
			@PageableDefault(10) Pageable pageable, @ModelAttribute("filter") BrandFilterForm filter) {
		if (bindingResult.hasErrors()) {
			Page<Brand> page = brandService.findAll(pageable, filter);
			model.addAttribute("page", page);
			model.addAttribute("countPages", page.getTotalPages());
			return "brands";
		}
		brandService.save(brandSaveForm);
		return "redirect:/admin/brands?" + request.getQueryString();
	}

	@RequestMapping("/admin/brands/update/{id}")
	public String updateBrand(@PathVariable int id, Model model, Pageable pageable, @ModelAttribute("filter") BrandFilterForm filter) {
		Page<Brand> page = brandService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		model.addAttribute("brandSaveForm", brandService.findWithSaveForm(id));
		return "brands";
	}

	@RequestMapping("/admin/brands/delete/{id}")
	public String deleteBrand(@PathVariable int id, HttpServletRequest request) {
		brandService.delete(id);
		return "redirect:/admin/brands?" + request.getQueryString();
	}
}
