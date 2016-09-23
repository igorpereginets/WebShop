package ua.service.specification.adminFilterAdapter;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.DTO.SaveForms.BrandSaveForm;
import ua.entity.Brand;
import ua.service.BrandService;


public class BrandValidator implements Validator {

	private final BrandService brandService;
	
	public BrandValidator(BrandService brandService) {
		this.brandService = brandService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return BrandSaveForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Name must not be empty");
		
		BrandSaveForm brand = (BrandSaveForm) target;
		if(brand.getName().length() > 15)
			errors.rejectValue("name", "", "Name must be less than 15 characters");
		
		Brand brandFromDB = brandService.findByName(brand.getName());
		if(brandFromDB != null && brandFromDB.getId() != brand.getId())
			errors.rejectValue("name", "", "Such brand already exists");
	}

}
