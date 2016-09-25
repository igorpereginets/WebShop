package ua.service.editors;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.math.NumberUtils;

import ua.entity.Category;
import ua.service.CategoryService;

public class CategoryEditor extends PropertyEditorSupport {

	private final CategoryService categoryService;

	public CategoryEditor(CategoryService categoryService) {
		if(categoryService == null)
			throw new IllegalArgumentException("categoryService = null");
		this.categoryService = categoryService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && NumberUtils.isDigits(text)) {
			Category category = categoryService.findOne(Integer.parseInt(text));
			setValue(category);
		} else
			setValue(null);
	}

}
