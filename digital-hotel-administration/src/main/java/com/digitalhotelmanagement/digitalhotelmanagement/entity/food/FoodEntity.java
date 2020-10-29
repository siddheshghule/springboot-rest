package com.digitalhotelmanagement.digitalhotelmanagement.entity.food;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Food")
public class FoodEntity {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable = false, length = 30)
	private String item;
	@Column(nullable = false, length = 30)
	private Integer amount;
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
}
