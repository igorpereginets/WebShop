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

import ua.DTO.FilterForms.adminFilter.AddressFilterForm;
import ua.entity.Address;

public class AddressFilterAdapter implements Specification<Address> {

	private final AddressFilterForm addressFilterForm;
	private final List<Specification<Address>> filters;

	public AddressFilterAdapter(AddressFilterForm addressFilterForm) {
		if (addressFilterForm != null)
			this.addressFilterForm = addressFilterForm;
		else
			this.addressFilterForm = new AddressFilterForm();
		this.filters = new ArrayList<>();
	}

	@Override
	public Predicate toPredicate(Root<Address> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		findByStreet();
		findByCity();
		findByPostcode();
		if (!filters.isEmpty()) {
			Specifications<Address> specifications = Specifications.where(filters.get(0));
			for (Specification<Address> filter : filters) {
				specifications = specifications.and(filter);
			}
			return specifications.toPredicate(root, query, cb);
		}
		return null;
	}

	private void findByPostcode() {
		Integer postcode = addressFilterForm.getPostcodeSearch();
		if (postcode != null && postcode >= 0) {
			filters.add((root, query, cb) -> {
				Expression<Integer> postcodeExp = root.<Integer> get("postcode");
				return cb.ge(postcodeExp, postcode);
			});
		}
	}

	private void findByCity() {
		String city = addressFilterForm.getCitySearch();
		if (city != null && !city.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> cityExp = root.get("city").<String> get("name");
				return cb.like(cb.lower(cityExp), city.toLowerCase() + "%");
			});
		}
	}

	private void findByStreet() {
		String street = addressFilterForm.getStreetSearch();
		if (street != null && !street.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> streetExp = root.<String> get("street");
				return cb.like(cb.lower(streetExp), street.toLowerCase() + "%");
			});
		}
	}

}
