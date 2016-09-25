package ua.service.editors;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.math.NumberUtils;

import ua.entity.Brand;
import ua.service.BrandService;

public class BrandEditor extends PropertyEditorSupport {

	private final BrandService brandService;

	public BrandEditor(BrandService brandService) {
		if (brandService == null)
			throw new IllegalArgumentException("brandService = null");
		this.brandService = brandService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && NumberUtils.isDigits(text)) {
			Brand brand = brandService.findOne(Integer.parseInt(text));
			setValue(brand);
		} else
			setValue(null);
	}

}
