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

import ua.DTO.FilterForms.adminFilter.CategoryFilterForm;
import ua.DTO.SaveForms.CategorySaveForm;
import ua.entity.Category;
import ua.service.CategoryService;
import ua.service.editors.CategoryEditor;
import ua.service.validators.adminValidators.CategoryValidator;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@ModelAttribute("categorySaveForm")
	public CategorySaveForm getForm() {
		return new CategorySaveForm();
	}

	@ModelAttribute("filter")
	public CategoryFilterForm getFilter() {
		return new CategoryFilterForm();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
	}
	
	@InitBinder("categorySaveForm")
	public void setValidators(WebDataBinder binder) {
		binder.setValidator(new CategoryValidator(categoryService));
	}

	@RequestMapping("/admin/categories")
	public String showCategories(Model model, @PageableDefault(10) Pageable pageable, @ModelAttribute("filter") CategoryFilterForm filter) {
		Page<Category> page = categoryService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		model.addAttribute("categories", categoryService.findAll());
		return "categories";
	}

	@RequestMapping(value = "/admin/categories", method = RequestMethod.POST)
	public String saveCategory(@Valid @ModelAttribute("categorySaveForm") CategorySaveForm categorySaveForm, BindingResult bindingResult, HttpServletRequest request, Model model,
			@PageableDefault(10) Pageable pageable, @ModelAttribute("filter") CategoryFilterForm filter) {
		if (bindingResult.hasErrors()) {
			Page<Category> page = categoryService.findAll(pageable, filter);
			model.addAttribute("page", page);
			model.addAttribute("countPages", page.getTotalPages());
			model.addAttribute("categories", categoryService.findAll());
			return "categories";
		}
		categoryService.save(categorySaveForm);
		return "redirect:/admin/categories?" + request.getQueryString();
	}

	@RequestMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable int id, Model model, Pageable pageable, @ModelAttribute("filter") CategoryFilterForm filter) {
		Page<Category> page = categoryService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("categorySaveForm", categoryService.findWithSaveForm(id));
		return "categories";
	}

	@RequestMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id, HttpServletRequest request) {
		categoryService.delete(id);
		return "redirect:/admin/categories?" + request.getQueryString();
	}
}
