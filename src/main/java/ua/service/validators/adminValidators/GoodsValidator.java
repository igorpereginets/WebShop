package ua.service.validators.adminValidators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.DTO.SaveForms.GoodsSaveForm;

public class GoodsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return GoodsSaveForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Goods name must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "", "Price must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "", "Amount must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "", "User must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "brand", "", "Brand must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "", "Category must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "", "State must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "", "Description must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shortDescription", "", "Short description must not be empty");
		
		GoodsSaveForm goodsForm = (GoodsSaveForm) target;
		if(goodsForm.getName().length() > 150)
			errors.rejectValue("name", "", "Goods name must be less than 150 characters");
		if(goodsForm.getPrice() == null || goodsForm.getPrice() > 1_000_000.0 || goodsForm.getPrice() <= 0)
			errors.rejectValue("price", "", "Price must be in range from 0 to 1 000 000");
		if(goodsForm.getAmount() == null || goodsForm.getAmount() < 1 || goodsForm.getAmount() > 2_000_000)
			errors.rejectValue("amount", "", "Amount must be in range from 0 to 2 000 000");
		if(goodsForm.getShortDescription().length() > 255)
			errors.rejectValue("shortDescription", "", "Short Description must be less then 255 characters");
		if(goodsForm.getDescription().length() > 2000)
			errors.rejectValue("shortDescription", "", "Description must be less then 2000 characters");
	}

}
