package ua.DTO.FilterForms;

public class IndexGoodsFilterForm {

	private String nameSearch;
	private Double minPrice;
	private Double maxPrice;
	private Integer minAmount;
	private Integer maxAmount;
	private String brandSearch;
	private String categorySearch;

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

	public String getBrandSearch() {
		return brandSearch;
	}

	public void setBrandSearch(String brandSearch) {
		this.brandSearch = brandSearch;
	}

	public String getCategorySearch() {
		return categorySearch;
	}

	public void setCategorySearch(String categorySearch) {
		this.categorySearch = categorySearch;
	}

}
