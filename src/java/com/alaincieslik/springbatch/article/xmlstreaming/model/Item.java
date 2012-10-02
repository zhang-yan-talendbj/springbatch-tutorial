package com.alaincieslik.springbatch.article.xmlstreaming.model;

public class Item {

	private String data;
	private Integer type;
	
	public Item(){
		System.out.println("Item");
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
