package com.niit.ecommerce.Backend.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Reviews")
public class Reviews implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long review_id;

	private boolean review_enabled = true;

	private String review_message;

	private int review_stars;

	@JsonManagedReference
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "product_Id")
	private Product product;

	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	public boolean isReview_enabled() {
		return review_enabled;
	}

	public void setReview_enabled(boolean review_enabled) {
		this.review_enabled = review_enabled;
	}

	public long getReview_id() {
		return review_id;
	}

	public void setReview_id(long review_id) {
		this.review_id = review_id;
	}

	public String getReview_message() {
		return review_message;
	}

	public void setReview_message(String review_message) {
		this.review_message = review_message;
	}

	public int getReview_stars() {
		return review_stars;
	}

	public void setReview_stars(int review_stars) {
		this.review_stars = review_stars;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Reviews [review_id=" + review_id + ", review_enabled=" + review_enabled + ", review_message="
				+ review_message + ", review_stars=" + review_stars + ", product=" + product.getProduct_bookName() + ", user=" + user.getUser_firstName() + "]";
	}
	
}