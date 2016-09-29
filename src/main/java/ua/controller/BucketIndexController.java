package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.entity.Brand;
import ua.entity.Bucket;
import ua.entity.Category;
import ua.entity.Tag;
import ua.service.BrandService;
import ua.service.BucketService;
import ua.service.CategoryService;
import ua.service.TagService;
import ua.service.editors.BrandEditor;
import ua.service.editors.CategoryEditor;
import ua.service.editors.TagEditor;

@Controller
public class BucketIndexController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private TagService tagService;
	@Autowired
	private BucketService bucketService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Brand.class, new BrandEditor(brandService));
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
		binder.registerCustomEditor(Tag.class, new TagEditor(tagService));
	}

	@RequestMapping("/toBucket/{id}")
	public String toBucket(@PathVariable int id, Principal principal) {
		bucketService.addGoods(id, principal.getName());
		return "redirect:/";
	}

	@RequestMapping("/myBucket")
	public String showBucket(Principal principal, Model model) {
		Bucket bucket = bucketService.findByUserLogin(principal.getName());
		model.addAttribute("goods", bucket.getGoods());
		model.addAttribute("categories", categoryService.findAllWithGoodsCountNotSold());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("bucketCount", bucketService.getGoodsCount(principal));
		model.addAttribute("tags", tagService.findAll());
		return "bucket";
	}

	@RequestMapping("/removeFromBucket/{id}")
	public String removeFromBucket(@PathVariable int id, Principal principal) {
		bucketService.removeFromBucket(id, principal.getName());
		return "redirect:/myBucket";
	}

	@RequestMapping("/buy/{id}")
	public String buyGoods(@PathVariable int id, Principal principal) {
		bucketService.buyGoods(id, principal.getName());
		return "redirect:/myBucket";
	}
}
