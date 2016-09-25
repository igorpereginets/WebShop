package ua.service.specification.adminFilterAdapter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.DTO.FilterForms.adminFilter.CountryFilterForm;
import ua.entity.Country;

public class CountryFilterAdapter implements Specification<Country> {

	private final CountryFilterForm form;

	public CountryFilterAdapter(CountryFilterForm form) {
		if (form != null)
			this.form = form;
		else
			this.form = new CountryFilterForm();
	}

	@Override
	public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		String name = form.getName();
		if (name != null && !name.isEmpty())
			return cb.like(cb.lower(root.get("name")), name.toLowerCase() + "%");
		return null;
	}

}
