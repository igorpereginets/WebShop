package ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.DTO.FilterForms.adminFilter.AddressFilterForm;
import ua.DTO.SaveForms.AddressSaveForm;
import ua.entity.Address;
import ua.repository.AddressRepository;
import ua.service.specification.adminFilterAdapter.AddressFilterAdapter;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	public Iterable<Address> findAll() {
		return addressRepository.findAll();
	}

	public Address save(AddressSaveForm addressSaveForm) {
		Address address = new Address();
		address.setId(addressSaveForm.getId());
		address.setStreet(addressSaveForm.getStreet());
		address.setPostcode(addressSaveForm.getPostcode());
		address.setCity(addressSaveForm.getCity());
		return addressRepository.save(address);
	}
	
	public AddressSaveForm findWithSaveForm(int id) {
		Address address = addressRepository.findOne(id);
		AddressSaveForm addressSaveForm = new AddressSaveForm();
		addressSaveForm.setId(address.getId());
		addressSaveForm.setStreet(address.getStreet());
		addressSaveForm.setPostcode(address.getPostcode());
		addressSaveForm.setCity(address.getCity());
		return addressSaveForm;
	}

	public void delete(int id) {
		addressRepository.delete(id);
	}

	public Page<Address> findAll(Pageable pageable, AddressFilterForm filter) {
		return addressRepository.findAll(new AddressFilterAdapter(filter), pageable);
	}

	public Address findByStreet(String street) {
		return addressRepository.findByStreet(street);
	}

	public Address findOne(int id) {
		return addressRepository.findOne(id);
	}
	
}
