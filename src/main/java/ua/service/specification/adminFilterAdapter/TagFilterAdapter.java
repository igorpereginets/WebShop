package ua.service.specification.adminFilterAdapter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.DTO.FilterForms.adminFilter.TagFilterForm;
import ua.entity.Tag;

public class TagFilterAdapter implements Specification<Tag> {

	private final TagFilterForm form;

	public TagFilterAdapter(TagFilterForm form) {
		if (form != null)
			this.form = form;
		else
			this.form = new TagFilterForm();
	}

	@Override
	public Predicate toPredicate(Root<Tag> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		String name = form.getNameSearch();
		if (name != null && !name.isEmpty()) {
			Expression<String> nameExp = root.<String> get("name");
			return cb.like(cb.lower(nameExp), name.toLowerCase() + "%");
		}
		return null;
	}

}
