package ua.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.DTO.SaveForms.GoodsSaveForm;
import ua.entity.Brand;
import ua.entity.Category;
import ua.entity.State;
import ua.entity.Tag;
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
import ua.service.editors.TagCollectionEditor;
import ua.service.editors.TagEditor;

@Controller
public class CreateGoodsController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private TagService tagService;
	@Autowired
	private UserService userService;
	@Autowired
	private StateService stateService;
	@Autowired
	private GoodsService goodsService;

	@ModelAttribute("goodsSaveForm")
	public GoodsSaveForm getSaveForm() {
		return new GoodsSaveForm();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
		binder.registerCustomEditor(Brand.class, new BrandEditor(brandService));
		binder.registerCustomEditor(Tag.class, new TagEditor(tagService));
		binder.registerCustomEditor(State.class, new StateEditor(stateService));
		binder.registerCustomEditor(List.class, "tags", new TagCollectionEditor(List.class, tagService));
	}

	@RequestMapping("/createGoods")
	public String createGoods(Model model) {
		model.addAttribute("categories", categoryService.findAllWithGoodsCount());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("tags", tagService.findAll());
		model.addAttribute("states", stateService.findAll());
		return "createGoods";
	}

	@RequestMapping(value = "/createGoods", method = RequestMethod.POST)
	public String saveGoods(@ModelAttribute("goodsSaveForm") GoodsSaveForm goodsSaveForm, Principal principal) {
		User user = userService.findByLogin(principal.getName());
		goodsSaveForm.setUser(user);
		goodsService.save(goodsSaveForm);
		return "redirect:/";
	}
}
