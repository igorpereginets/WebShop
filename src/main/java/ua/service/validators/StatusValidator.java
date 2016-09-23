package ua.service.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.DTO.SaveForms.StatusSaveForm;
import ua.entity.Status;
import ua.service.StatusService;

public class StatusValidator implements Validator {

	private StatusService statusService;
	
	public StatusValidator(StatusService statusService) {
		this.statusService = statusService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return StatusSaveForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "", "Status must not be empty");
		
		StatusSaveForm form = (StatusSaveForm) target;
		if(form.getStatus() != null && form.getStatus().length() > 20)
			errors.rejectValue("status", "", "Status must be less than 20 characters");
		
		Status statusFromDB = statusService.findByStatus(form.getStatus());
		if(statusFromDB != null && statusFromDB.getId() != form.getId())
			errors.rejectValue("status", "", "Such status already exists");
	}

}
