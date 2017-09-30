package com.niit.ecommerce.Backend.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long product_Id;

	private String product_bookName;

	private String product_author;

	private String product_publisher;

	private String product_description;

	private String product_language;

	private String product_imgUrl;

	private Long product_price;

	private int product_quantity;

	private boolean product_activeIs;

	private String product_status;

	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;

	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getProduct_Id() {
		return product_Id;
	}

	public void setProduct_Id(Long product_Id) {
		this.product_Id = product_Id;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public String getProduct_imgUrl() {
		return product_imgUrl;
	}

	public void setProduct_imgUrl(String product_imgUrl) {
		this.product_imgUrl = product_imgUrl;
	}

	public Long getProduct_price() {
		return product_price;
	}

	public void setProduct_price(Long product_price) {
		this.product_price = product_price;
	}

	public int getProduct_quantity() {
		return product_quantity;
	}

	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}

	public boolean isProduct_activeIs() {
		return product_activeIs;
	}

	public void setProduct_activeIs(boolean product_activeIs) {
		this.product_activeIs = product_activeIs;
	}

	public String getProduct_bookName() {
		return product_bookName;
	}

	public void setProduct_bookName(String product_bookName) {
		this.product_bookName = product_bookName;
	}

	public String getProduct_author() {
		return product_author;
	}

	public void setProduct_author(String product_author) {
		this.product_author = product_author;
	}

	public String getProduct_language() {
		return product_language;
	}

	public void setProduct_language(String product_language) {
		this.product_language = product_language;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProduct_status() {
		return product_status;
	}

	public void setProduct_status(String product_status) {
		this.product_status = product_status;
	}

	public String getProduct_publisher() {
		return product_publisher;
	}

	public void setProduct_publisher(String product_publisher) {
		this.product_publisher = product_publisher;
	}

	@Override
	public String toString() {
		return "Product [product_Id=" + product_Id + ", product_bookName=" + product_bookName + ", product_author="
				+ product_author + ", product_publisher=" + product_publisher + ", product_description="
				+ product_description + ", product_language=" + product_language + ", product_imgUrl=" + product_imgUrl
				+ ", product_price=" + product_price + ", product_quantity=" + product_quantity + ", product_activeIs="
				+ product_activeIs + ", product_status=" + product_status + ", category=" + category.getCategory_level() + ", user="
				+ user.getUser_firstName() + "]";
	}

}
