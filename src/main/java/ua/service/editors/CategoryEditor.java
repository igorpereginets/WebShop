package ua.service.editors;

import java.beans.PropertyEditorSupport;

import ua.entity.Category;
import ua.service.CategoryService;

public class CategoryEditor extends PropertyEditorSupport {

	private CategoryService categoryService;
	
	public CategoryEditor(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Category category = categoryService.findOne(Integer.parseInt(text));
		setValue(category);
	}
	
}
