package ua.service.editors;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.math.NumberUtils;

import ua.entity.Address;
import ua.service.AddressService;

public class AddressEditor extends PropertyEditorSupport {

	private final AddressService addressService;

	public AddressEditor(AddressService addressService) {
		if (addressService == null)
			throw new IllegalArgumentException("addressService = null");
		this.addressService = addressService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && NumberUtils.isDigits(text)) {
			Address address = addressService.findOne(Integer.parseInt(text));
			setValue(address);
		} else
			setValue(null);
	}

}
