package ua.service.validators.adminValidators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.DTO.SaveForms.CategorySaveForm;
import ua.entity.Category;
import ua.service.CategoryService;

public class CategoryValidator implements Validator {

	private CategoryService categoryService;
	
	public CategoryValidator(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return CategorySaveForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Name must not be empty");
		
		CategorySaveForm category = (CategorySaveForm) target;
		if(category.getName().length() > 25)
			errors.rejectValue("name", "", "Name must be less than 25 characters");
		
		Category categoryFromDB = categoryService.findByName(category.getName());
		if(categoryFromDB != null && categoryFromDB.getId() != category.getId())
			errors.rejectValue("name", "", "Such category already exists");
	}

}
