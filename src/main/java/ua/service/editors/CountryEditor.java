package ua.service.editors;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.math.NumberUtils;

import ua.entity.Country;
import ua.service.CountryService;

public class CountryEditor extends PropertyEditorSupport {

	private final CountryService countryService;

	public CountryEditor(CountryService countryService) {
		if (countryService == null)
			throw new IllegalArgumentException("countryService = null");
		this.countryService = countryService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && NumberUtils.isDigits(text)) {
			Country country = countryService.findOne(Integer.valueOf(text));
			setValue(country);
		} else
			setValue(null);
	}

}
