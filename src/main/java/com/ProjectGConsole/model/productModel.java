package com.ProjectGConsole.model;

public class productModel {

	private int product_id;
	private String product_name;
	private int product_stock;
	private int product_price;
	private int product_quantity;
	public productModel(int product_id, String product_name, int product_stock, int product_price,
			int product_quantity) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_stock = product_stock;
		this.product_price = product_price;
		this.product_quantity = product_quantity;
	}
	public productModel() {
		super();
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_stock() {
		return product_stock;
	}
	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getProduct_quantity() {
		return product_quantity;
	}
	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}
	
	
}