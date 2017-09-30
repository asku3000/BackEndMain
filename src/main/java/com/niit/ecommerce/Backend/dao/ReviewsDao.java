package com.niit.ecommerce.Backend.dao;

import java.util.List;

import com.niit.ecommerce.Backend.entity.Product;
import com.niit.ecommerce.Backend.entity.Reviews;
import com.niit.ecommerce.Backend.entity.User;

public interface ReviewsDao {

	public boolean addReviews(Reviews reviews);

	public boolean deleteReviews(Long review_id);

	public boolean updateReviews(Reviews reviews);
	
	public Reviews getReviewById(Long review_id);
	
	public List<Reviews> getAllReviews();

	public List<Reviews> getAllReviewsByProductId(Product product);

	public List<Reviews> getAllReviewsOfUser(User user);
	
	public List<Reviews> getAverageRatingOfProduct(Product product);
	
	public Reviews getReviewsByUserAndProduct(Product product,User user);

}
