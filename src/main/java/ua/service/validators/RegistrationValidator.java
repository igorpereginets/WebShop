package ua.service.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.DTO.RegistrationForm;
import ua.entity.User;
import ua.service.UserService;

public class RegistrationValidator implements Validator {

	private UserService userService;
	
	public RegistrationValidator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return RegistrationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "", "Login must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "First Name must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Last Name must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telephone", "", "Telephone must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "E-mail must not be empty");
		
		RegistrationForm user = (RegistrationForm) target;
		if(user.getEmail().length() > 40)
			errors.rejectValue("email", "", "E-mail must be less than 40 characters");
		if(user.getLogin().length() > 20)
			errors.rejectValue("login", "", "Login must be less than 20 characters");
		if(user.getPassword().length() > 20)
			errors.rejectValue("password", "", "Password must be less than 20 characters");
		if(user.getFirstName().length() > 20)
			errors.rejectValue("firstName", "", "First name must be less than 20 characters");
		if(user.getLastName().length() > 25)
			errors.rejectValue("lastName", "", "Last name must be less than 25 characters");
		if(user.getTelephone().length() > 15)
			errors.rejectValue("telephone", "", "Telephone must be less than 15 characters");
		if(user.getPassword().length() < 9)
			errors.rejectValue("password", "", "Password must be greater than 9 characters");
		if(!user.getPassword().equals(user.getConfirm())) {
			errors.rejectValue("password", "", "Password doesn't match");
		}
		
		User userFromDB = userService.findByLogin(user.getLogin());
		if(userFromDB != null)
			errors.rejectValue("login", "", "Such login is already occupied");
		
		userFromDB = userService.findByEmail(user.getEmail());
		if(userFromDB != null)
			errors.rejectValue("email", "", "Such e-mail is already occupied");
	}

}
