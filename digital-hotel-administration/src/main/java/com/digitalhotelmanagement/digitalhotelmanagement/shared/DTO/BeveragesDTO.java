package com.digitalhotelmanagement.digitalhotelmanagement.shared.DTO;

import java.io.Serializable;

public class BeveragesDTO implements Serializable {

	private static final long serialVersionUID = -6487866079782557508L;
	private Integer id;
	private String item;
	private Integer amount;
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
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
