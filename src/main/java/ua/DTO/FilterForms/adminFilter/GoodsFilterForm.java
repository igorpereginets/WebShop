package ua.DTO.FilterForms.adminFilter;

public class GoodsFilterForm {

	private String nameSearch;
	private Double minPrice;
	private Double maxPrice;
	private Integer minAmount;
	private Integer maxAmount;
	private String beginSearch;
	private String endSearch;
	private Boolean activeSearch;

	private String userSearch;
	private String categorySearch;
	private String brandSearch;
	private String stateSearch;

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

	public String getBeginSearch() {
		return beginSearch;
	}

	public void setBeginSearch(String beginSearch) {
		this.beginSearch = beginSearch;
	}

	public String getEndSearch() {
		return endSearch;
	}

	public void setEndSearch(String endSearch) {
		this.endSearch = endSearch;
	}

	public Boolean getActiveSearch() {
		return activeSearch;
	}

	public void setActiveSearch(Boolean activeSearch) {
		this.activeSearch = activeSearch;
	}

	public String getUserSearch() {
		return userSearch;
	}

	public void setUserSearch(String userSearch) {
		this.userSearch = userSearch;
	}

	public String getCategorySearch() {
		return categorySearch;
	}

	public void setCategorySearch(String categorySearch) {
		this.categorySearch = categorySearch;
	}

	public String getBrandSearch() {
		return brandSearch;
	}

	public void setBrandSearch(String brandSearch) {
		this.brandSearch = brandSearch;
	}

	public String getStateSearch() {
		return stateSearch;
	}

	public void setStateSearch(String stateSearch) {
		this.stateSearch = stateSearch;
	}

}
