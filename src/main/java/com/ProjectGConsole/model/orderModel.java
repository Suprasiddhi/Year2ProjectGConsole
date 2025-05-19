package com.ProjectGConsole.model;

public class orderModel {
	private int order_id;
	private String customer_username;
	private int order_quantity;
	private int order_price;
	private String order_deliverytype;
	private int order_status;
	public orderModel(int order_id, String customer_username, int order_quantity, int order_price,
			String order_deliverytype, int order_status) {
		super();
		this.order_id = order_id;
		this.customer_username = customer_username;
		this.order_quantity = order_quantity;
		this.order_price = order_price;
		this.order_deliverytype = order_deliverytype;
		this.order_status = order_status;
	}
	public orderModel() {
		super();
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}
	public int getOrder_price() {
		return order_price;
	}
	public void setOrder_price(int order_price) {
		this.order_price = order_price;
	}
	public String getOrder_deliverytype() {
		return order_deliverytype;
	}
	public void setOrder_deliverytype(String order_deliverytype) {
		this.order_deliverytype = order_deliverytype;
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}
	public String getCustomer_username() {
		return customer_username;
	}
	public void setCustomer_username(String customer_username) {
		this.customer_username = customer_username;
	}
	

	
}
