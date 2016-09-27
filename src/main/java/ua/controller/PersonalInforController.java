package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.service.BrandService;
import ua.service.CategoryService;
import ua.service.TagService;
import ua.service.UserService;

@Controller
public class PersonalInforController {

	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private TagService tagService;
	
	@RequestMapping("/myInfo")
	public String showInfo(Principal principal, Model model) {
		model.addAttribute("categories", categoryService.findAllWithGoodsCount());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("tags", tagService.findAll());
		model.addAttribute("user", userService.findByLoginWithAddress(principal.getName()));
		return "personalInfo";
	}
}
