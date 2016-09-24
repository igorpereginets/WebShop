package ua.service.validators.adminValidators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.DTO.SaveForms.CountrySaveForm;
import ua.entity.Country;
import ua.service.CountryService;

public class CountryValidator implements Validator {

	private final CountryService countryService;
	
	public CountryValidator(CountryService countryService) {
		if(countryService != null)
			this.countryService = countryService;
		else
			this.countryService = new CountryService();
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CountrySaveForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Country name must not be empty");

		CountrySaveForm countrySaveForm = (CountrySaveForm) target;
		if (countrySaveForm.getName().length() > 50)
			errors.rejectValue("name", "", "Country name must be less than 50 characters");

		Country countryFromDB = countryService.findByName(countrySaveForm.getName());
		if (countryFromDB != null && countryFromDB.getId() != countrySaveForm.getId())
			errors.rejectValue("name", "", "Such country already exists");
	}

}
