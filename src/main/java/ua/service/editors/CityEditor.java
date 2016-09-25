package ua.service.editors;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.math.NumberUtils;

import ua.entity.City;
import ua.service.CityService;

public class CityEditor extends PropertyEditorSupport {

	private final CityService cityService;

	public CityEditor(CityService cityService) {
		if(cityService == null)
			throw new IllegalArgumentException("cityService = null");
		this.cityService = cityService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && NumberUtils.isDigits(text)) {
			City city = cityService.findOne(Integer.valueOf(text));
			setValue(city);
		} else
			setValue(null);
	}

}
