package ua.service.editors;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.math.NumberUtils;

import ua.entity.Tag;
import ua.service.TagService;

public class TagEditor extends PropertyEditorSupport {

	private final TagService tagService;

	public TagEditor(TagService tagService) {
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
		Tag tag = null;
		
		if (NumberUtils.isDigits(text)) {
			tag = tagService.findOne(Integer.parseInt(text));
		} else {
			tag = tagService.findByName(text);
			if(tag == null)
				tag = tagService.save(new Tag(text));
		}
			
		setValue(tag);
	}

}
