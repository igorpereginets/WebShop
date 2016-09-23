package ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.DTO.FilterForms.adminFilter.TagFilterForm;
import ua.DTO.SaveForms.TagSaveForm;
import ua.entity.Tag;
import ua.repository.TagRepository;
import ua.service.specification.adminFilterAdapter.TagFilterAdapter;

@Service
public class TagService {

	@Autowired
	private TagRepository tagRepository;

	public Iterable<Tag> findAll() {
		return tagRepository.findAll();
	}

	public Tag save(TagSaveForm tagSaveForm) {
		Tag tag = new Tag();
		tag.setId(tagSaveForm.getId());
		tag.setName(tagSaveForm.getName());
		return tagRepository.save(tag);
	}
	
	public TagSaveForm findWithSaveForm(int id) {
		Tag tag = tagRepository.findOne(id);
		TagSaveForm tagSaveForm = new TagSaveForm();
		tagSaveForm.setId(tag.getId());
		tagSaveForm.setName(tag.getName());
		return tagSaveForm;
	}

	public void delete(int id) {
		tagRepository.delete(id);
	}

	public Page<Tag> findAll(Pageable pageable) {
		return tagRepository.findAll(pageable);
	}

	public Page<Tag> findAll(Pageable pageable, TagFilterForm filter) {
		return tagRepository.findAll(new TagFilterAdapter(filter), pageable);
	}

	public Tag findByName(String name) {
		return tagRepository.findByName(name);
	}
}
