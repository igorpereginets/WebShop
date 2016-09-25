package ua.service.validators.adminValidators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.DTO.SaveForms.StateSaveForm;
import ua.entity.State;
import ua.service.StateService;

public class StateValidator implements Validator {

	private final StateService stateService;

	public StateValidator(StateService stateService) {
		if (stateService == null)
			throw new IllegalArgumentException("stateService = null");
		this.stateService = stateService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return StateSaveForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "State must not be empty");

		StateSaveForm form = (StateSaveForm) target;
		if (form.getName() != null && form.getName().length() > 25)
			errors.rejectValue("name", "", "State must be less than 25 characters");

		State stateFromDB = stateService.findByName(form.getName());
		if (stateFromDB != null && stateFromDB.getId() != form.getId())
			errors.rejectValue("name", "", "Such state already exists");
	}

}
