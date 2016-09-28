package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.DTO.FilterForms.IndexGoodsFilterForm;
import ua.entity.Brand;
import ua.entity.Category;
import ua.entity.Goods;
import ua.entity.Tag;
import ua.service.BrandService;
import ua.service.BucketService;
import ua.service.CategoryService;
import ua.service.GoodsService;
import ua.service.TagService;
import ua.service.UserService;
import ua.service.editors.BrandEditor;
import ua.service.editors.CategoryEditor;
import ua.service.editors.TagEditor;

@Controller
public class IndexController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private TagService tagService;
	@Autowired
	private UserService userService;
	@Autowired
	private BucketService bucketService;

	@ModelAttribute("filter")
	public IndexGoodsFilterForm getFilter() {
		return new IndexGoodsFilterForm();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Brand.class, new BrandEditor(brandService));
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
		binder.registerCustomEditor(Tag.class, new TagEditor(tagService));
	}

	@RequestMapping("/")
	public String index(Model model, @PageableDefault(10) Pageable pageable, @ModelAttribute("filter") IndexGoodsFilterForm filter, Principal principal) {
		model.addAttribute("categories", categoryService.findAllWithGoodsCountNotSold());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("tags", tagService.findAll());
		model.addAttribute("bucketCount", bucketService.getGoodsCount(principal));
		Page<Goods> page = goodsService.findAllNotSold(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		return "index";
	}

	@RequestMapping("/showGoods/{id}")
	public String showGoods(@PathVariable int id, Model model, Principal principal) {
		model.addAttribute("categories", categoryService.findAllWithGoodsCountNotSold());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("bucketCount", bucketService.getGoodsCount(principal));
		model.addAttribute("good", goodsService.findOne(id));
		model.addAttribute("tags", tagService.findAll());
		return "showGoods";
	}

	@RequestMapping("/myInfo")
	public String showInfo(Principal principal, Model model) {
		model.addAttribute("categories", categoryService.findAllWithGoodsCountNotSold());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("tags", tagService.findAll());
		model.addAttribute("bucketCount", bucketService.getGoodsCount(principal));
		model.addAttribute("user", userService.findByLoginWithAddress(principal.getName()));
		return "personalInfo";
	}

}
