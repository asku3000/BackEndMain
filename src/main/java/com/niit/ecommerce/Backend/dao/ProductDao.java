package com.niit.ecommerce.Backend.dao;

import java.util.List;

import com.niit.ecommerce.Backend.entity.Category;
import com.niit.ecommerce.Backend.entity.Product;
import com.niit.ecommerce.Backend.entity.Supplier;

public interface ProductDao {

	public Product getProductById(Long product_id);

	public Product getProductByName(String product_name);

	public List<Product> searchProductByProductName(String name);

	public List<Product> getAllProductList();

	public List<Product> getAllActiveProductList();

	public List<Product> getAllInActiveProductList();

	boolean add(Product product);

	boolean update(Product product);

	boolean delete(Long product_id);

	public List<Product> getProductByCategoryType(Category category);

	public List<Product> getProductByCategoryLevel(Category category);

	public List<Product> getProductBySupplierId(Long supplier_id);
}
