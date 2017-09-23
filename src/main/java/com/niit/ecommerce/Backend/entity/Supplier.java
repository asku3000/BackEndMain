package com.niit.ecommerce.Backend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Supplier")
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long supplier_id;

	private String supplier_companyName;

	@Column(unique = true)
	private String supplier_brandName;

	@Column(unique = true)
	private String supplier_email;

	private String supplier_phoneNo;

	private boolean supplier_enabled;

	private String supplier_address;

	private String supplier_password;

	public Long getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(Long supplier_id) {
		this.supplier_id = supplier_id;
	}

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

	public String getSupplier_email() {
		return supplier_email;
	}

	public void setSupplier_email(String supplier_email) {
		this.supplier_email = supplier_email;
	}

	public String getSupplier_phoneNo() {
		return supplier_phoneNo;
	}

	public void setSupplier_phoneNo(String supplier_phoneNo) {
		this.supplier_phoneNo = supplier_phoneNo;
	}

	public boolean isSupplier_enabled() {
		return supplier_enabled;
	}

	public void setSupplier_enabled(boolean supplier_enabled) {
		this.supplier_enabled = supplier_enabled;
	}

	public String getSupplier_address() {
		return supplier_address;
	}

	public void setSupplier_address(String supplier_address) {
		this.supplier_address = supplier_address;
	}

	public String getSupplier_password() {
		return supplier_password;
	}

	public void setSupplier_password(String supplier_password) {
		this.supplier_password = supplier_password;
	}

	@Override
	public String toString() {
		return "Supplier [supplier_id=" + supplier_id + ", supplier_companyName=" + supplier_companyName
				+ ", supplier_brandName=" + supplier_brandName + ", supplier_email=" + supplier_email
				+ ", supplier_phoneNo=" + supplier_phoneNo + ", supplier_enabled=" + supplier_enabled
				+ ", supplier_address=" + supplier_address + "]";
	}

}
