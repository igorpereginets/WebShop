package ua.service.specification.adminFilterAdapter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.DTO.FilterForms.adminFilter.BrandFilterForm;
import ua.entity.Brand;

public class BrandFilterAdapter implements Specification<Brand> {

	private final BrandFilterForm form;
	
	public BrandFilterAdapter(BrandFilterForm form) {
		if(form != null)
			this.form = form;
		else
			this.form = new BrandFilterForm();
	}

	@Override
	public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		String name = form.getNameSearch();
		if(name != null && !name.isEmpty())
			return cb.like(cb.lower(root.get("name")), name.toLowerCase() + "%");
		return null;
	}

}
