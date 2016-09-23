package ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.DTO.FilterForms.adminFilter.CountryFilterForm;
import ua.DTO.SaveForms.CountrySaveForm;
import ua.entity.Country;
import ua.repository.CountryRepository;
import ua.service.specification.adminFilterAdapter.CountryFilterAdapter;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	public Iterable<Country> findAll() {
		return countryRepository.findAll();
	}
	
	public void delete(int id) {
		countryRepository.delete(id);
	}
	
	public Country findByName(String name) {
		return countryRepository.findByName(name);
	}
	
	public Page<Country> findAll(Pageable pageable, CountryFilterForm filter) {
		return countryRepository.findAll(new CountryFilterAdapter(filter), pageable);
	}

	public Country save(CountrySaveForm countrySaveForm) {
		Country country = new Country();
		country.setId(countrySaveForm.getId());
		country.setName(countrySaveForm.getName());
		return countryRepository.save(country);
	}

	public CountrySaveForm findWithSaveForm(int id) {
		Country country = countryRepository.findOne(id);
		CountrySaveForm countrySaveForm = new CountrySaveForm();
		countrySaveForm.setId(country.getId());
		countrySaveForm.setName(country.getName());
		return countrySaveForm;
	}

	public Country findOne(Integer id) {
		return countryRepository.findOne(id);
	}
}
