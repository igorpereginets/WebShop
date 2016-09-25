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
		if (text != null && !text.isEmpty()) {
			Category category = categoryService.findOne(Integer.parseInt(text));
			setValue(category);
		} else
			setValue(null);
	}

}
