package ua.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.DTO.FilterForms.IndexGoodsFilterForm;
import ua.entity.Goods;

public class IndexGoodsFilterAdapter implements Specification<Goods> {

	private final IndexGoodsFilterForm form;
	private List<Specification<Goods>> filters;
	
	public IndexGoodsFilterAdapter(IndexGoodsFilterForm form) {
		if(form != null)
			this.form = form;
		else
			this.form = new IndexGoodsFilterForm();
		this.filters = new ArrayList<>();
	}

	@Override
	public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		filterByName();
		filterByPrice();
		filterByAmount();
		filterByBrand();
		filterByCategory();
		if (!filters.isEmpty()) {
			Specifications<Goods> specifications = Specifications.where(filters.get(0));
			for (Specification<Goods> filter : filters) {
				specifications = specifications.and(filter);
			}
			return specifications.toPredicate(root, query, cb);
		}
		return null;
	}

	private void filterByCategory() {
		String category = form.getCategorySearch();
		if (category != null && !category.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> categoryExp = root.get("category").<String> get("name");
				return cb.like(cb.lower(categoryExp), category.toLowerCase() + "%");
			});
		}
	}

	private void filterByBrand() {
		String brand = form.getBrandSearch();
		if (brand != null && !brand.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> brandExp = root.get("brand").<String> get("name");
				return cb.like(cb.lower(brandExp), brand.toLowerCase() + "%");
			});
		}
	}

	private void filterByAmount() {
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

	private void filterByPrice() {
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

	private void filterByName() {
		String name = form.getNameSearch();
		if (name != null && !name.isEmpty()) {
			filters.add((root, query, cb) -> {
				Expression<String> nameExp = root.<String> get("name");
				return cb.like(cb.lower(nameExp), name.toLowerCase() + "%");
			});
		}
	}

}
