package com.niit.ecommerce.Backend.dao;

import java.util.List;

import com.niit.ecommerce.Backend.entity.User;

public interface UserDao {

	public User getUserByUsername(String email);

	boolean add(User user);

	boolean update(User user);

	boolean delete(String email);

	public User getUserById(Long user_id);

	public User checkForLogin(String email, String password);

	public List<User> getAllUsers();

	public List<User> getSupplierByCompanyName(String companyName);

	public User getSupplierByBrandName(String brandName);

	public List<User> getAllSupplier();

	boolean deleteSupplierByBrandName(String brandName);

}
