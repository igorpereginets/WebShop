package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.DTO.FilterForms.IndexGoodsFilterForm;
import ua.entity.Goods;
import ua.service.BrandService;
import ua.service.CategoryService;
import ua.service.GoodsService;

@Controller
public class IndexController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private GoodsService goodsService;
	
	@ModelAttribute("filter")
	public IndexGoodsFilterForm getFilter() {
		return new IndexGoodsFilterForm();
	}
	
	@RequestMapping("/")
	public String index(Model model, @PageableDefault(10) Pageable pageable, @ModelAttribute("filter") IndexGoodsFilterForm filter) {
		model.addAttribute("categories", categoryService.findAllWithGoodsCount());
		model.addAttribute("brands", brandService.findAll());
		Page<Goods> page = goodsService.findAll(pageable, filter);
		model.addAttribute("page", page);
		model.addAttribute("countPages", page.getTotalPages());
		return "index";
	}
}
