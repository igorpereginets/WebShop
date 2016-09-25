package ua.DTO.FilterForms;

import ua.entity.Brand;
import ua.entity.Category;

public class IndexGoodsFilterForm {

	private String nameSearch;
	private Double minPrice;
	private Double maxPrice;
	private Integer minAmount;
	private Integer maxAmount;
	private Brand brandSearch;
	private Category categorySearch;
	private String tagSearch;

	public String getNameSearch() {
		return nameSearch;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Integer getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Integer minAmount) {
		this.minAmount = minAmount;
	}

	public Integer getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Integer maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Brand getBrandSearch() {
		return brandSearch;
	}

	public void setBrandSearch(Brand brandSearch) {
		this.brandSearch = brandSearch;
	}

	public Category getCategorySearch() {
		return categorySearch;
	}

	public void setCategorySearch(Category categorySearch) {
		this.categorySearch = categorySearch;
	}

	
	public String getTagSearch() {
		return tagSearch;
	}

	
	public void setTagSearch(String tagSearch) {
		this.tagSearch = tagSearch;
	}

}
