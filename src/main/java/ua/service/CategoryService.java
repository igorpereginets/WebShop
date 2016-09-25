package ua.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.DTO.CategoryAndCount;
import ua.DTO.FilterForms.adminFilter.CategoryFilterForm;
import ua.DTO.SaveForms.CategorySaveForm;
import ua.entity.Category;
import ua.repository.CategoryRepository;
import ua.service.specification.adminFilterAdapter.CategoryFilterAdapter;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Iterable<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category save(CategorySaveForm categorySaveForm) {
		if (categorySaveForm == null)
			return null;

		Category category = new Category();
		category.setId(categorySaveForm.getId());
		category.setName(categorySaveForm.getName());
		category.setParent(categorySaveForm.getParent());
		return categoryRepository.save(category);
	}

	public CategorySaveForm findWithSaveForm(int id) {
		Category category = categoryRepository.findOne(id);
		if (category == null)
			return null;

		CategorySaveForm categorySaveForm = new CategorySaveForm();
		categorySaveForm.setId(category.getId());
		categorySaveForm.setName(category.getName());
		categorySaveForm.setParent(category.getParent());
		return categorySaveForm;
	}

	public void delete(int id) {
		categoryRepository.delete(id);
	}

	public Category findOne(int id) {
		return categoryRepository.findOne(id);
	}

	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	public Page<Category> findAll(Pageable pageable, CategoryFilterForm filter) {
		return categoryRepository.findAll(new CategoryFilterAdapter(filter), pageable);
	}

	public Category findByName(String name) {
		if (name == null || name.isEmpty())
			return null;
		return categoryRepository.findByName(name);
	}

	public List<CategoryAndCount> findAllWithGoodsCount() {
		return categoryRepository.findAllWithGoodsCount();
	}
}
