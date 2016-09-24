package ua.service.validators.adminValidators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.DTO.SaveForms.TagSaveForm;
import ua.entity.Tag;
import ua.service.TagService;

public class TagValidator implements Validator {

	private TagService tagService;
	
	public TagValidator(TagService tagService) {
		this.tagService = tagService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return TagSaveForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Name must not be empty");
		
		TagSaveForm tag = (TagSaveForm) target;
		if(tag.getName().length() > 15)
			errors.rejectValue("name", "", "Name must be less than 15 characters");
		
		Tag tagFromDB = tagService.findByName(tag.getName());
		if(tagFromDB != null && tagFromDB.getId() != tag.getId())
			errors.rejectValue("name", "", "Such tag already exists");
	}

}
