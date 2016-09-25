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

import ua.DTO.FilterForms.adminFilter.CityFilterForm;
import ua.entity.City;

public class CityFilterAdapter implements Specification<City> {

	private final CityFilterForm form;
	private final List<Specification<City>> filters;

	public CityFilterAdapter(CityFilterForm form) {
		if (form != null)
			this.form = form;
		else
			this.form = new CityFilterForm();
		this.filters = new ArrayList<>();
	}

	@Override
	public Predicate toPredicate(Root<City> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		findByName();
		findByCountry();
		if (!filters.isEmpty()) {
			Specifications<City> specifications = Specifications.where(filters.get(0));
			for (Specification<City> specification : filters) {
				specifications = specifications.and(specification);
			}
			return specifications.toPredicate(root, query, cb);
		}
		return null;
	}

	private void findByCountry() {
		String countryName = form.getCountrySearch();
		if (countryName != null && !countryName.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> countryNameExp = root.get("country").<String> get("name");
				return cb.like(cb.lower(countryNameExp), countryName.toLowerCase() + "%");
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
