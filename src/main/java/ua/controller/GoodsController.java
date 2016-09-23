package ua.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
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

import ua.DTO.FilterForms.adminFilter.GoodsFilterForm;
import ua.DTO.SaveForms.GoodsSaveForm;
import ua.entity.Brand;
import ua.entity.Category;
import ua.entity.Goods;
import ua.entity.State;
import ua.entity.User;
import ua.service.BrandService;
import ua.service.CategoryService;
import ua.service.GoodsService;
import ua.service.StateService;
import ua.service.TagService;
import ua.service.UserService;
import ua.service.editors.BrandEditor;
import ua.service.editors.CategoryEditor;
import ua.service.editors.StateEditor;
import ua.service.editors.UserEditor;
import ua.service.validators.GoodsValidator;

@Controller
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	@Autowired
	private UserService userService;
	@Autowired
	private StateService stateService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private TagService tagService;

	@ModelAttribute("goodsSaveForm")
	public GoodsSaveForm getForm() {
		return new GoodsSaveForm();
	}

	@ModelAttribute("filter")
	public GoodsFilterForm getFilter() {
		return new GoodsFilterForm();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
		binder.registerCustomEditor(Brand.class, new BrandEditor(brandService));
		binder.registerCustomEditor(State.class, new StateEditor(stateService));
		binder.registerCustomEditor(User.class, new UserEditor(userService));
		binder.registerCustomEditor(List.class, "tags", new CustomCollectionEditor(List.class) {

			@Override
			protected Object convertElement(Object element) {
				String tagId = (String) element;
				if(tagId == null || tagId.isEmpty())
					return null;
				else
					return tagService.findOne(Integer.parseInt(tagId));
			}
			
		});
	}

	@InitBinder("goodsSaveForm")
	public void initValidator(WebDataBinder binder) {
		binder.setValidator(new GoodsValidator());
	}

	@RequestMapping("/admin/goods")
	public String showGoods(Model model, @PageableDefault(10) Pageable pageable, @ModelAttribute("filter") GoodsFilterForm filter) {
		Page<Goods> page = goodsService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		model.addAttribute("users", userService.findAll());
		model.addAttribute("states", stateService.findAll());
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("tags", tagService.findAll());
		return "goods";
	}

	@RequestMapping(value = "/admin/goods", method = RequestMethod.POST)
	public String saveGoods(@Valid @ModelAttribute("goodsSaveForm") GoodsSaveForm goodsSaveForm, BindingResult bindingResult, HttpServletRequest request, Model model,
			Pageable pageable, @ModelAttribute("filter") GoodsFilterForm filter) {
		if (bindingResult.hasErrors()) {
			Page<Goods> page = goodsService.findAll(pageable, filter);
			model.addAttribute("page", page);
			model.addAttribute("countPages", page.getTotalPages());
			model.addAttribute("users", userService.findAll());
			model.addAttribute("states", stateService.findAll());
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("brands", brandService.findAll());
			return "goods";
		}
		goodsService.save(goodsSaveForm);
		return "redirect:/admin/goods?" + request.getQueryString();
	}

	@RequestMapping("/admin/goods/update/{id}")
	public String updateGoods(@PathVariable int id, Model model, Pageable pageable, @ModelAttribute("filter") GoodsFilterForm filter) {
		model.addAttribute("goodsSaveForm", goodsService.findWithSaveForm(id));
		Page<Goods> page = goodsService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		model.addAttribute("users", userService.findAll());
		model.addAttribute("states", stateService.findAll());
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("tags", tagService.findAll());
		return "goods";
	}

	@RequestMapping("/admin/goods/delete/{id}")
	public String deleteGoods(@PathVariable int id, HttpServletRequest request) {
		goodsService.delete(id);
		return "redirect:/admin/goods?" + request.getQueryString();
	}
}
