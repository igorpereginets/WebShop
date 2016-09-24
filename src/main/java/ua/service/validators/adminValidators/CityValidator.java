package ua.service.validators.adminValidators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.DTO.SaveForms.CitySaveForm;
import ua.entity.City;
import ua.service.CityService;

public class CityValidator implements Validator {

	private CityService cityService;
	
	public CityValidator(CityService cityService) {
		this.cityService = cityService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return CitySaveForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "City name must not be empty");
		
		
		CitySaveForm form = (CitySaveForm) target;
		if(form.getName().length() > 50)
			errors.rejectValue("name", "", "City name must be less than 50 characters");
		
		City cityFromDB = cityService.findByName(form.getName());
		if(cityFromDB != null && cityFromDB.getId() != form.getId())
			errors.rejectValue("name", "", "Such city already exists");
	}

	
}
