package com.niit.ecommerce.Backend.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;

	private String user_firstName;

	private String user_lastName;

	private String supplier_companyName;

	@Column(unique = true)
	private String supplier_brandName;

	public String getSupplier_companyName() {
		return supplier_companyName;
	}

	public void setSupplier_companyName(String supplier_companyName) {
		this.supplier_companyName = supplier_companyName;
	}

	public String getSupplier_brandName() {
		return supplier_brandName;
	}

	public void setSupplier_brandName(String supplier_brandName) {
		this.supplier_brandName = supplier_brandName;
	}

	@Column(unique = true)
	private String email;

	private String password;
	private String address;
	private String contact;
	private String role;

	private boolean enabled = true;
	private String user_dob;
	private String user_gender;
	private String user_state;
	private int user_status = 1;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Cart cart;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUser_firstName() {
		return user_firstName;
	}

	public void setUser_firstName(String user_firstName) {
		this.user_firstName = user_firstName;
	}

	public String getUser_lastName() {
		return user_lastName;
	}

	public void setUser_lastName(String user_lastName) {
		this.user_lastName = user_lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getUser_dob() {
		return user_dob;
	}

	public void setUser_dob(String user_dob) {
		this.user_dob = user_dob;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_state() {
		return user_state;
	}

	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}

	public int getUser_status() {
		return user_status;
	}

	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_firstName=" + user_firstName + ", user_lastName=" + user_lastName
				+ ", supplier_companyName=" + supplier_companyName + ", supplier_brandName=" + supplier_brandName
				+ ", email=" + email + ", password=" + password + ", address=" + address + ", contact=" + contact
				+ ", role=" + role + ", enabled=" + enabled + ", user_dob=" + user_dob + ", user_gender=" + user_gender
				+ ", user_state=" + user_state + ", user_status=" + user_status + ", cart=" + cart + "]";
	}

}
