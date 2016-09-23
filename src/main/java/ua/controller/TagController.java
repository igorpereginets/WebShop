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

import ua.DTO.FilterForms.adminFilter.TagFilterForm;
import ua.DTO.SaveForms.TagSaveForm;
import ua.entity.Tag;
import ua.service.TagService;
import ua.service.validators.TagValidator;

@Controller
public class TagController {

	@Autowired
	private TagService tagService;

	@ModelAttribute("tagSaveForm")
	public TagSaveForm getForm() {
		return new TagSaveForm();
	}

	@ModelAttribute("filter")
	public TagFilterForm getFilter() {
		return new TagFilterForm();
	}

	@InitBinder("tagSaveForm")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new TagValidator(tagService));
	}

	@RequestMapping("/admin/tags")
	public String showTags(Model model, @PageableDefault(10) Pageable pageable, @ModelAttribute("filter") TagFilterForm filter) {
		Page<Tag> page = tagService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		return "tags";
	}

	@RequestMapping(value = "/admin/tags", method = RequestMethod.POST)
	public String saveTag(@Valid @ModelAttribute("tagSaveForm") TagSaveForm tagSaveForm, BindingResult bindingResult, HttpServletRequest request, Model model,
			@PageableDefault(10) Pageable pageable, @ModelAttribute("filter") TagFilterForm filter) {
		if (bindingResult.hasErrors()) {
			Page<Tag> page = tagService.findAll(pageable, filter);
			model.addAttribute("page", page);
			model.addAttribute("countPages", page.getTotalPages());
			return "tags";
		}
		tagService.save(tagSaveForm);
		return "redirect:/admin/tags?" + request.getQueryString();
	}

	@RequestMapping("/admin/tags/update/{id}")
	public String updateTag(@PathVariable int id, Model model, Pageable pageable, @ModelAttribute("filter") TagFilterForm filter) {
		Page<Tag> page = tagService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		model.addAttribute("tagSaveForm", tagService.findWithSaveForm(id));
		return "tags";
	}

	@RequestMapping("/admin/tags/delete/{id}")
	public String deleteTag(@PathVariable int id, HttpServletRequest request) {
		tagService.delete(id);
		return "redirect:/admin/tags?" + request.getQueryString();
	}
}
