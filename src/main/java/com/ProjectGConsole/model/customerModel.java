package com.ProjectGConsole.model;

public class customerModel {
	private String customer_name;
	private String customer_email;
	private String customer_phonenumber;
	private String customer_address;
	private String customer_gender;
	private String customer_dob;
	private String customer_username;
	private String customer_password;
	private String customer_image;
	
	public customerModel() {
		
	}
	
	

	public customerModel(String customer_name, String customer_email, String customer_phonenumber,
			String customer_address, String customer_gender, String customer_dob, String customer_username,
			String customer_password,String customer_image) {
		super();
		this.customer_name = customer_name;
		this.customer_email = customer_email;
		this.customer_phonenumber = customer_phonenumber;
		this.customer_address = customer_address;
		this.customer_gender = customer_gender;
		this.customer_dob = customer_dob;
		this.customer_username = customer_username;
		this.customer_password = customer_password;
		this.customer_image = customer_image;
	}



	public customerModel(String customer_username, String customer_password) {
		super();
		this.customer_username = customer_username;
		this.customer_password = customer_password;
	}



	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_phonenumber() {
		return customer_phonenumber;
	}

	public void setCustomer_phonenumber(String customer_phonenumber) {
		this.customer_phonenumber = customer_phonenumber;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public String getCustomer_gender() {
		return customer_gender;
	}

	public void setCustomer_gender(String customer_gender) {
		this.customer_gender = customer_gender;
	}

	public String getCustomer_dob() {
		return customer_dob;
	}

	public void setCustomer_dob(String customer_dob) {
		this.customer_dob = customer_dob;
	}

	public String getCustomer_username() {
		return customer_username;
	}

	public void setCustomer_username(String customer_username) {
		this.customer_username = customer_username;
	}

	public String getCustomer_password() {
		return customer_password;
	}

	public void setCustomer_password(String customer_password) {
		this.customer_password = customer_password;
	}

	public String getCustomer_image() {
		return customer_image;
	}

	public void setCustomer_image(String customer_image) {
		this.customer_image = customer_image;
	}



	public customerModel(String customer_name, String customer_email, String customer_phonenumber,
			String customer_address, String customer_gender, String customer_dob, String customer_username,
			String customer_image) {
		super();
		this.customer_name = customer_name;
		this.customer_email = customer_email;
		this.customer_phonenumber = customer_phonenumber;
		this.customer_address = customer_address;
		this.customer_gender = customer_gender;
		this.customer_dob = customer_dob;
		this.customer_username = customer_username;
		this.customer_image = customer_image;
	}
	
	
	
	
}
