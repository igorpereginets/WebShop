package ua.DTO.FilterForms.adminFilter;

public class UserFilterForm {

	private String firstNameSearch;
	private String lastNameSearch;
	private String emailSearch;
	private String loginSearch;
	private String telephoneSearch;
	private String passwordSearch;
	private String genderSearch;
	private String birthdaySearch;
	private Double minMoney;
	private Double maxMoney;
	private Double minRate;
	private Double maxRate;
	private String statusSearch;

	public String getFirstNameSearch() {
		return firstNameSearch;
	}

	public void setFirstNameSearch(String firstNameSearch) {
		this.firstNameSearch = firstNameSearch;
	}

	public String getLastNameSearch() {
		return lastNameSearch;
	}

	public void setLastNameSearch(String lastNameSearch) {
		this.lastNameSearch = lastNameSearch;
	}

	public String getEmailSearch() {
		return emailSearch;
	}

	public void setEmailSearch(String emailSearch) {
		this.emailSearch = emailSearch;
	}

	public String getLoginSearch() {
		return loginSearch;
	}

	public void setLoginSearch(String loginSearch) {
		this.loginSearch = loginSearch;
	}

	public String getTelephoneSearch() {
		return telephoneSearch;
	}

	public void setTelephoneSearch(String telephoneSearch) {
		this.telephoneSearch = telephoneSearch;
	}

	public String getPasswordSearch() {
		return passwordSearch;
	}

	public void setPasswordSearch(String passwordSearch) {
		this.passwordSearch = passwordSearch;
	}

	public String getGenderSearch() {
		return genderSearch;
	}

	public void setGenderSearch(String genderSearch) {
		this.genderSearch = genderSearch;
	}

	public String getBirthdaySearch() {
		return birthdaySearch;
	}

	public void setBirthdaySearch(String birthdaySearch) {
		this.birthdaySearch = birthdaySearch;
	}

	public String getStatusSearch() {
		return statusSearch;
	}

	public void setStatusSearch(String statusSearch) {
		this.statusSearch = statusSearch;
	}

	
	public Double getMinMoney() {
		return minMoney;
	}

	
	public void setMinMoney(Double minMoney) {
		this.minMoney = minMoney;
	}

	
	public Double getMaxMoney() {
		return maxMoney;
	}

	
	public void setMaxMoney(Double maxMoney) {
		this.maxMoney = maxMoney;
	}

	
	public Double getMinRate() {
		return minRate;
	}

	
	public void setMinRate(Double minRate) {
		this.minRate = minRate;
	}

	
	public Double getMaxRate() {
		return maxRate;
	}

	
	public void setMaxRate(Double maxRate) {
		this.maxRate = maxRate;
	}
}
