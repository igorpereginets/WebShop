package ua.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class Goods implements Serializable {

	private static final long serialVersionUID = 4395021971342041035L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 150)
	private String name;
	@Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0")
	private double price;
	@Column(columnDefinition = "INT DEFAULT 1")
	private int amount;
	private LocalDate begin;
	private LocalDate end;
	@Column(columnDefinition = "CHAR(1) DEFAULT 'Y'")
	@Type(type = "yes_no")
	private boolean active;
	private String pathToFile;
	@Column(columnDefinition = "MEDIUMTEXT")
	private String description;
	private String shortDescription;

	@ManyToOne
	private User user;
	@ManyToOne
	private State state;
	@ManyToOne
	private Brand brand;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Tag> tags;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "goods")
	private List<Bucket> buckets;

	@ManyToOne
	private Category category;
	@ManyToMany(mappedBy = "wishlist", fetch = FetchType.LAZY)
	private List<User> usersWishlist;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPathToFile() {
		return pathToFile;
	}

	public void setPathToFile(String pathToFile) {
		this.pathToFile = pathToFile;
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

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Bucket> getBuckets() {
		return buckets;
	}

	public void setBuckets(List<Bucket> buckets) {
		this.buckets = buckets;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<User> getUsersWishlist() {
		return usersWishlist;
	}

	public void setUsersWishlist(List<User> usersWishlist) {
		this.usersWishlist = usersWishlist;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Goods other = (Goods) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
