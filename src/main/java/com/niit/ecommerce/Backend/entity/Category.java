package com.niit.ecommerce.Backend.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Category")
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long category_id;
	
	private String category_type;
	
	private String category_level;
	
	private boolean category_enabled;

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getCategory_type() {
		return category_type;
	}

	public void setCategory_type(String category_type) {
		this.category_type = category_type;
	}

	public String getCategory_level() {
		return category_level;
	}

	public void setCategory_level(String category_level) {
		this.category_level = category_level;
	}

	public boolean isCategory_enabled() {
		return category_enabled;
	}

	public void setCategory_enabled(boolean category_enabled) {
		this.category_enabled = category_enabled;
	}

	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", category_type=" + category_type + ", category_level="
				+ category_level + ", category_enabled=" + category_enabled + "]";
	}

	
	
	
	

}
