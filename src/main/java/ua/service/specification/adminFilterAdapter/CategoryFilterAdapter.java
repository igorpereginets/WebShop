package ua.service.specification.adminFilterAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.DTO.FilterForms.adminFilter.CategoryFilterForm;
import ua.entity.Category;

public class CategoryFilterAdapter implements Specification<Category> {

	private final CategoryFilterForm form;
	private final List<Specification<Category>> filters;

	public CategoryFilterAdapter(CategoryFilterForm form) {
		if (form != null)
			this.form = form;
		else
			this.form = new CategoryFilterForm();
		this.filters = new ArrayList<>();
	}

	@Override
	public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		findByName();
		findByParent();
		if (!filters.isEmpty()) {
			Specifications<Category> specifications = Specifications.where(filters.get(0));
			for (Specification<Category> filter : filters) {
				specifications = specifications.and(filter);
			}
			return specifications.toPredicate(root, query, cb);
		}
		return null;
	}

	private void findByParent() {
		String parent = form.getParentSearch();
		if (parent != null && !parent.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> parentExp = root.get("parent").<String> get("name");
				return cb.like(cb.lower(parentExp), parent.toLowerCase() + "%");
			});
		}
	}

	private void findByName() {
		String name = form.getNameSearch();
		if (name != null && !name.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> nameExp = root.<String> get("name");
				return cb.like(cb.lower(nameExp), name.toLowerCase() + "%");
			});
		}
	}

}
