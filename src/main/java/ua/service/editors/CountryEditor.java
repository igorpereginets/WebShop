package ua.service.editors;

import java.beans.PropertyEditorSupport;

import ua.entity.Country;
import ua.service.CountryService;

public class CountryEditor extends PropertyEditorSupport {

	private CountryService countryService;

	public CountryEditor(CountryService countryService) {
		this.countryService = countryService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Country country = countryService.findOne(Integer.valueOf(text));
		setValue(country);
	}

}
