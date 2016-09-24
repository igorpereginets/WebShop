package ua.service.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.DTO.SaveForms.UserSaveForm;
import ua.entity.User;
import ua.service.UserService;

public class UserValidator implements Validator {

	private UserService userService;
	
	public UserValidator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return UserSaveForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "", "Login must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "First name must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password name must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Last name must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "E-mail must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telephone", "", "Telephone must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "", "Birthday must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "money", "", "Money must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rate", "", "Rate must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "", "Address must not be empty");
		
		UserSaveForm user = (UserSaveForm) target;
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
		if(user.getBirthday().length() > 10)
			errors.rejectValue("birthday", "", "Birthday must be less than 10 characters");
		if(user.getMoney() > 1_000_000.0)
			errors.rejectValue("money", "", "Money must be less than 1 000 000");
		if(user.getRate() > 10.0)
			errors.rejectValue("rate", "", "Rate must be less than 10");
		
		Pattern pattern = Pattern.compile("^[0-9]{4,4}-[0-9]{2,2}-[0-9]{2,2}$");
		Matcher matcher = pattern.matcher(user.getBirthday());
		if(!matcher.matches())
			errors.rejectValue("birthday", "", "Bithday format: YYYY-MM-DD");
		
		User userFromDB = userService.findByLogin(user.getLogin());
		if(userFromDB != null && userFromDB.getId() != user.getId())
			errors.rejectValue("login", "", "User with such login already exists");
		userFromDB = userService.findByEmail(user.getEmail());
		if(userFromDB != null && userFromDB.getId() != user.getId())
			errors.rejectValue("email", "", "User with such e-mail already exists");
	}

}
