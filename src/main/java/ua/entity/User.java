package ua.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false, length = 20)
	private String login;
	@Column(nullable = false, length = 20)
	private String password;
	@Column(nullable = false, length = 20)
	private String firstName;
	@Column(nullable = false, length = 25)
	private String lastName;
	@Type(type = "numeric_boolean")
	private boolean gender;
	@Column(unique = true, nullable = false, length = 40)
	private String email;
	@Column(unique = true, nullable = false, length = 15)
	private String telephone;
	private LocalDate birthday;
	@Column(columnDefinition = "DECIMAL(15,2) DEFAULT 0")
	private double money;
	@Column(nullable = false, columnDefinition = "DOUBLE DEFAULT 0")
	private double rate;

	@ManyToOne
	private Status status;
	@OneToOne(fetch = FetchType.LAZY)
	private Address address;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Goods> goods;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Bucket> buckets;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Goods> wishlist;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

	public List<Bucket> getBuckets() {
		return buckets;
	}

	public void setBuckets(List<Bucket> buckets) {
		this.buckets = buckets;
	}

	public List<Goods> getWishlist() {
		return wishlist;
	}

	public void setWishlist(List<Goods> wishlist) {
		this.wishlist = wishlist;
	}

}