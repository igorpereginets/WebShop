package ua.service.specification.adminFilterAdapter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.DTO.FilterForms.adminFilter.StatusFilterForm;
import ua.entity.Status;

public class StatusFilterAdapter implements Specification<Status> {

	private StatusFilterForm form;

	public StatusFilterAdapter(StatusFilterForm form) {
		this.form = form;
	}

	@Override
	public Predicate toPredicate(Root<Status> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		String status = form.getStatusSearch();
		if (status != null && !status.isEmpty())
			return cb.like(cb.lower(root.<String> get("status")), status.toLowerCase() + "%");
		return null;
	}

}
