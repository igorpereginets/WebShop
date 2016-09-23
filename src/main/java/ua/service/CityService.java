package ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.DTO.FilterForms.adminFilter.CityFilterForm;
import ua.DTO.SaveForms.CitySaveForm;
import ua.entity.City;
import ua.repository.CityRepository;
import ua.service.specification.adminFilterAdapter.CityFilterAdapter;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	public Iterable<City> findAll() {
		return cityRepository.findAll();
	}

	public City save(CitySaveForm citySaveForm) {
		City city = new City();
		city.setId(citySaveForm.getId());
		city.setName(citySaveForm.getName());
		city.setCountry(citySaveForm.getCountry());
		return cityRepository.save(city);
	}
	
	public CitySaveForm findWithSaveForm(int id) {
		City city = cityRepository.findOneWithCountry(id);
		CitySaveForm citySaveForm = new CitySaveForm();
		citySaveForm.setId(city.getId());
		citySaveForm.setName(city.getName());
		citySaveForm.setCountry(city.getCountry());
		return citySaveForm;
	}

	public City findOne(int id) {
		return cityRepository.findOne(id);
	}

	public void delete(int id) {
		cityRepository.delete(id);
	}

	public Page<City> findAllWithCountries(Pageable pageable, CityFilterForm filter) {
		return cityRepository.findAll(new CityFilterAdapter(filter), pageable);
	}

	public City findByName(String name) {
		return cityRepository.findByName(name);
	}

}
