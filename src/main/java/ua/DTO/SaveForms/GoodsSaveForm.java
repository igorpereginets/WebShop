package ua.DTO.SaveForms;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import ua.entity.Brand;
import ua.entity.Category;
import ua.entity.State;
import ua.entity.User;

public class GoodsSaveForm {

	private int id;
	private String name;
	private Double price;
	private Integer amount;
	private String description;
	private String shortDescription;
	private User user;
	private State state;
	private Brand brand;
	private Category category;

	private LocalDate begin;
	private LocalDate end;
	private Boolean active;
	private MultipartFile file;
	private String pathToFile;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public LocalDate getBegin() {
		return begin;
	}

	public void setBegin(LocalDate begin) {
		this.begin = begin;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	
	public String getPathToFile() {
		return pathToFile;
	}

	
	public void setPathToFile(String pathToFile) {
		this.pathToFile = pathToFile;
	}

}
