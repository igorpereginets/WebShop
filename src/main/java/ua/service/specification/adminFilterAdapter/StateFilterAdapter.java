package ua.service.specification.adminFilterAdapter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.DTO.FilterForms.adminFilter.StateFilterForm;
import ua.entity.State;

public class StateFilterAdapter implements Specification<State> {

	private final StateFilterForm form;

	public StateFilterAdapter(StateFilterForm form) {
		if (form != null)
			this.form = form;
		else
			this.form = new StateFilterForm();
	}

	@Override
	public Predicate toPredicate(Root<State> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		String name = form.getName();
		if (name != null && !name.isEmpty()) {
			Expression<String> nameExp = root.<String> get("name");
			return cb.like(cb.lower(nameExp), name.toLowerCase() + "%");
		}
		return null;
	}

}
