package ua.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bucket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition = "INT DEFAULT 1")
	private int goodsAmount;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	@ManyToOne(fetch = FetchType.EAGER)
	private Goods good;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(int goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Goods getGood() {
		return good;
	}

	public void setGood(Goods good) {
		this.good = good;
	}

}
