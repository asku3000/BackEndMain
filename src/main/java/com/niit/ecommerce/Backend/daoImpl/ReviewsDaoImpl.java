package com.niit.ecommerce.Backend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ecommerce.Backend.dao.ReviewsDao;
import com.niit.ecommerce.Backend.entity.Product;
import com.niit.ecommerce.Backend.entity.Reviews;
import com.niit.ecommerce.Backend.entity.User;

@Repository("reviewsDao")
@Transactional
public class ReviewsDaoImpl implements ReviewsDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addReviews(Reviews reviews) {
		try {
			sessionFactory.getCurrentSession().save(reviews);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}
	}

	@Override
	public boolean deleteReviews(Long review_id) {
		Reviews reviews = getReviewById(review_id);
		reviews.setReview_enabled(false);
		try {
			sessionFactory.getCurrentSession().update(reviews);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}
	}

	@Override
	public boolean updateReviews(Reviews reviews) {
		try {
			sessionFactory.getCurrentSession().update(reviews);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}
	}

	@Override
	public List<Reviews> getAllReviewsByProductId(Product product) {
		String getreview = "FROM Reviews where product_Id=:parameter";
		Query<Reviews> query = sessionFactory.getCurrentSession().createQuery(getreview, Reviews.class);
		query.setParameter("parameter", product);
		try {
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public List<Reviews> getAllReviewsOfUser(User user) {
		String getreview = "FROM Reviews where user_id=:parameter";
		Query<Reviews> query = sessionFactory.getCurrentSession().createQuery(getreview, Reviews.class);
		query.setParameter("parameter", user);
		try {
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return null;
		}

	}

	@Override
	public List<Reviews> getAverageRatingOfProduct(Product product) {
		String getreview = "FROM Reviews where product_Id=:parameter";
		Query<Reviews> query = sessionFactory.getCurrentSession().createQuery(getreview, Reviews.class);
		query.setParameter("parameter", product);
		try {
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public Reviews getReviewById(Long review_id) {
		String getreview = "FROM Reviews where review_id=:parameter";
		Query<Reviews> query = sessionFactory.getCurrentSession().createQuery(getreview, Reviews.class);
		query.setParameter("parameter", review_id);
		try {
			return query.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public Reviews getReviewsByUserAndProduct(Product product, User user) {
		String getreview = "FROM Reviews where 	PRODUCT_ID = :parameter1 and USER_ID = :parameter2";
		Query<Reviews> query = sessionFactory.getCurrentSession().createQuery(getreview, Reviews.class);
		query.setParameter("parameter1", product);
		query.setParameter("parameter2", user);
		try {
			return query.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public List<Reviews> getAllReviews() {
		String getReviews = "FROM Reviews";
		Query<Reviews> query = sessionFactory.getCurrentSession().createQuery(getReviews, Reviews.class);
		try {
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}

}
