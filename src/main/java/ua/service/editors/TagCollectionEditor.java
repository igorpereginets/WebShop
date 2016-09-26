package ua.service.editors;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import ua.entity.Tag;
import ua.service.TagService;

public class TagCollectionEditor extends CustomCollectionEditor {

	private final TagService tagService;

	public TagCollectionEditor(@SuppressWarnings("rawtypes") Class<? extends Collection> collectionType, TagService tagService) {
		super(collectionType);
		if (tagService == null)
			throw new IllegalArgumentException("tagService = null");
		this.tagService = tagService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.isEmpty()) {
			setValue(null);
			return;
		}
		String[] tags = text.split(" ");
		setValue(Arrays.asList(tags));
	}

	@Override
	protected Object convertElement(Object element) {
		if (element == null || !String.class.equals(element.getClass()))
			return null;

		String tagField = (String) element;

		if (NumberUtils.isDigits(tagField)) {
			return tagService.findOne(Integer.parseInt(tagField));
		} else {
			Tag tag = tagService.findByName(tagField);
			if (tag == null)
				return tagService.save(new Tag(tagField));
			return tag;
		}

	}

}
