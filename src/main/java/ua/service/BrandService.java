package ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.DTO.FilterForms.adminFilter.BrandFilterForm;
import ua.DTO.SaveForms.BrandSaveForm;
import ua.entity.Brand;
import ua.repository.BrandRepository;
import ua.service.specification.adminFilterAdapter.BrandFilterAdapter;

@Service
public class BrandService {

	@Autowired
	private BrandRepository brandRepository;

	public Iterable<Brand> findAll() {
		return brandRepository.findAll();
	}

	public Brand save(BrandSaveForm brandSaveForm) {
		if (brandSaveForm == null)
			return null;

		Brand brand = new Brand();
		brand.setId(brandSaveForm.getId());
		brand.setName(brandSaveForm.getName());
		return brandRepository.save(brand);
	}

	public BrandSaveForm findWithSaveForm(int id) {
		Brand brand = brandRepository.findOne(id);
		if (brand == null)
			return null;

		BrandSaveForm brandSaveForm = new BrandSaveForm();
		brandSaveForm.setId(brand.getId());
		brandSaveForm.setName(brand.getName());
		return brandSaveForm;
	}

	public void delete(int id) {
		brandRepository.delete(id);
	}

	public Page<Brand> findAll(Pageable pageable) {
		return brandRepository.findAll(pageable);
	}

	public Page<Brand> findAll(Pageable pageable, BrandFilterForm filter) {
		return brandRepository.findAll(new BrandFilterAdapter(filter), pageable);
	}

	public Brand findByName(String name) {
		if (name == null || name.isEmpty())
			return null;
		return brandRepository.findByName(name);
	}

	public Brand findOne(int id) {
		return brandRepository.findOne(id);
	}

	public Brand save(Brand brand) {
		if(brand != null)
			return brandRepository.save(brand);
		return null;
	}
}
