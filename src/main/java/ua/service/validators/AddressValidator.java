package ua.service.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.DTO.SaveForms.AddressSaveForm;

public class AddressValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AddressSaveForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "", "Street must not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "postcode", "", "Postcode must not be empty");
		
		AddressSaveForm form = (AddressSaveForm) target;
		String street = form.getStreet();
		if(street != null && street.length() > 60)
			errors.rejectValue("street", "", "Street must be less than 60 characters");
		Integer postcode = form.getPostcode();
		if(postcode != null && postcode > 1_000_000)
			errors.rejectValue("postcode", "", "Postcode must be less than 1 000 000");
	}

}
