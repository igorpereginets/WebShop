package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.DTO.RegistrationForm;
import ua.service.UserService;
import ua.service.validators.RegistrationValidator;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public RegistrationForm getForm() {
		return new RegistrationForm();
	}
	
	@InitBinder("user")
	public void initValidator(WebDataBinder binder) {
		binder.setValidator(new RegistrationValidator(userService));
	}
	
	@RequestMapping("/registration")
	public String registration() {
		return "registration";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute("user") RegistrationForm user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "registration";
		}
		userService.save(user);
		return "redirect:/login";
	}
}
