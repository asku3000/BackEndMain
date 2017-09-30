package com.niit.ecommerce.Backend.dao;

import java.util.List;

import com.niit.ecommerce.Backend.entity.Category;

public interface CategoryDao {
	boolean add(Category category);

	boolean update(Category category);

	public Category getCategoryById(Long category_id);

	public List<Category> getCategoryByCategoty_type(String category_type);

	public Category getCategoryByCategory_level(String category_level);

	public List<Category> getAllCategoryLevel();

}
