package com.niit.ecommerce.Backend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ecommerce.Backend.dao.CategoryDao;
import com.niit.ecommerce.Backend.entity.Category;

@Repository("categoryDao")
@Transactional

public class CategoryDaoImpl implements CategoryDao {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	@Override
	public boolean add(Category category) {
		try {
			sessionFactory.getCurrentSession().save(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}

	}

	@Override
	public boolean update(Category category) {
		try {
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}
	}

	@Override
	public Category getCategoryById(Long category_id) {
		try {
			return sessionFactory.getCurrentSession().get(Category.class, Long.valueOf(category_id));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public List<Category> getCategoryByCategoty_type(String category_type) {
		category_type = category_type.toLowerCase();
		String selectCategoryName = "FROM Category where category_type=:parameter";
		Query<Category> query = sessionFactory.getCurrentSession().createQuery(selectCategoryName, Category.class);
		query.setParameter("parameter", category_type);
		try {

			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}

	}

	@Override
	public Category getCategoryByCategory_level(String category_level) {
		String selectCategoryName = "FROM Category where category_level=:parameter";
		Query<Category> query = sessionFactory.getCurrentSession().createQuery(selectCategoryName, Category.class);
		query.setParameter("parameter", category_level);
		try {

			return query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public List<Category> getAllCategoryLevel() {
		String selectCategory = "FROM Category";
		Query<Category> query = sessionFactory.getCurrentSession().createQuery(selectCategory, Category.class);
		try {

			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}

}
