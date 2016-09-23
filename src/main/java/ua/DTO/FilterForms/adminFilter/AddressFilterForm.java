package ua.DTO.FilterForms.adminFilter;

public class AddressFilterForm {

	private String streetSearch;
	private Integer postcodeSearch;
	private String citySearch;

	public String getStreetSearch() {
		return streetSearch;
	}

	public void setStreetSearch(String streetSearch) {
		this.streetSearch = streetSearch;
	}

	public Integer getPostcodeSearch() {
		return postcodeSearch;
	}

	public void setPostcodeSearch(Integer postcodeSearch) {
		this.postcodeSearch = postcodeSearch;
	}

	public String getCitySearch() {
		return citySearch;
	}

	public void setCitySearch(String citySearch) {
		this.citySearch = citySearch;
	}
}
