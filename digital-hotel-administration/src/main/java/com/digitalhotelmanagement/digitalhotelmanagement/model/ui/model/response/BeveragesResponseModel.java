package com.digitalhotelmanagement.digitalhotelmanagement.model.ui.model.response;

import org.springframework.hateoas.RepresentationModel;

public class BeveragesResponseModel extends RepresentationModel<BeveragesResponseModel>{

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
