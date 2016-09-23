package ua.service.editors;

import java.beans.PropertyEditorSupport;

import ua.entity.Brand;
import ua.service.BrandService;

public class BrandEditor extends PropertyEditorSupport {

	private BrandService brandService;

	public BrandEditor(BrandService brandService) {
		this.brandService = brandService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Brand brand = brandService.findOne(Integer.parseInt(text));
		setValue(brand);
	}
	
	
}
