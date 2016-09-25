package ua.service.specification.adminFilterAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.DTO.FilterForms.adminFilter.GoodsFilterForm;
import ua.entity.Goods;

public class GoodsFilterAdapter implements Specification<Goods> {

	private final GoodsFilterForm form;
	private final List<Specification<Goods>> filters;

	public GoodsFilterAdapter(GoodsFilterForm form) {
		if (form != null)
			this.form = form;
		else
			this.form = new GoodsFilterForm();
		this.filters = new ArrayList<>();
	}

	@Override
	public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		findByName();
		findByPrice();
		findByAmount();
		findByBegin();
		findByEnd();
		findByActive();
		findByUser();
		findByCategory();
		findByBrand();
		findByState();
		if (!filters.isEmpty()) {
			Specifications<Goods> specifications = Specifications.where(filters.get(0));
			for (Specification<Goods> filter : filters) {
				specifications = specifications.and(filter);
			}
			return specifications.toPredicate(root, query, cb);
		}
		return null;
	}

	private void findByState() {
		String state = form.getStateSearch();
		if (state != null && !state.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> stateExp = root.get("state").<String> get("name");
				return cb.like(cb.lower(stateExp), state.toLowerCase() + "%");
			});
		}
	}

	private void findByBrand() {
		String brand = form.getBrandSearch();
		if (brand != null && !brand.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> brandExp = root.get("brand").<String> get("name");
				return cb.like(cb.lower(brandExp), brand.toLowerCase() + "%");
			});
		}
	}

	private void findByCategory() {
		String category = form.getCategorySearch();
		if (category != null && !category.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> categoryExp = root.get("category").<String> get("name");
				return cb.like(cb.lower(categoryExp), category.toLowerCase() + "%");
			});
		}
	}

	private void findByUser() {
		String user = form.getUserSearch();
		if (user != null && !user.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> userExp = root.get("user").<String> get("login");
				return cb.like(cb.lower(userExp), user.toLowerCase() + "%");
			});
		}
	}

	private void findByActive() {
		Boolean active = form.getActiveSearch();
		if (active != null) {
			if (active)
				filters.add((root, query, cb) -> {
					return cb.isTrue(root.get("active"));
				});
			else
				filters.add((root, query, cb) -> {
					return cb.isFalse(root.get("active"));
				});
		}
	}

	private void findByEnd() {
		String endSearch = form.getEndSearch();
		if (endSearch != null && !endSearch.isEmpty()) {
			LocalDate end = LocalDate.parse(endSearch);
			filters.add((root, query, cb) -> {
				Expression<LocalDate> endExp = root.<LocalDate> get("end");
				return cb.greaterThanOrEqualTo(endExp, end);
			});
		}
	}

	private void findByBegin() {
		String beginSearch = form.getEndSearch();
		if (beginSearch != null && !beginSearch.isEmpty()) {
			LocalDate begin = LocalDate.parse(beginSearch);
			filters.add((root, query, cb) -> {
				Expression<LocalDate> beginExp = root.<LocalDate> get("begin");
				return cb.greaterThanOrEqualTo(beginExp, begin);
			});
		}
	}

	private void findByAmount() {
		Integer maxAmount = form.getMaxAmount();
		Integer minAmount = form.getMinAmount();
		if (maxAmount != null && minAmount != null) {
			filters.add((root, query, cb) -> {
				Expression<Integer> amountExp = root.<Integer> get("amount");
				return cb.between(amountExp, minAmount, maxAmount);
			});
		} else if (minAmount != null) {
			filters.add((root, query, cb) -> {
				Expression<Integer> amountExp = root.<Integer> get("amount");
				return cb.greaterThanOrEqualTo(amountExp, minAmount);
			});
		} else if (maxAmount != null) {
			filters.add((root, query, cb) -> {
				Expression<Integer> amountExp = root.<Integer> get("amount");
				return cb.lessThanOrEqualTo(amountExp, maxAmount);
			});
		}
	}

	private void findByPrice() {
		Double maxPrice = form.getMaxPrice();
		Double minPrice = form.getMinPrice();
		if (maxPrice != null && minPrice != null) {
			filters.add((root, query, cb) -> {
				Expression<Double> priceExp = root.<Double> get("price");
				return cb.between(priceExp, minPrice, maxPrice);
			});
		} else if (minPrice != null) {
			filters.add((root, query, cb) -> {
				Expression<Double> priceExp = root.<Double> get("price");
				return cb.greaterThanOrEqualTo(priceExp, minPrice);
			});
		} else if (maxPrice != null) {
			filters.add((root, query, cb) -> {
				Expression<Double> priceExp = root.<Double> get("price");
				return cb.lessThanOrEqualTo(priceExp, maxPrice);
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
