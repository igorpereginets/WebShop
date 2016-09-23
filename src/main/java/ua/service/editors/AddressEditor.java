package ua.service.editors;

import java.beans.PropertyEditorSupport;

import ua.entity.Address;
import ua.service.AddressService;

public class AddressEditor extends PropertyEditorSupport {

	private AddressService addressService;
	
	public AddressEditor(AddressService addressService) {
		this.addressService = addressService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Address address = addressService.findOne(Integer.parseInt(text));
		setValue(address);
	}

	
}
