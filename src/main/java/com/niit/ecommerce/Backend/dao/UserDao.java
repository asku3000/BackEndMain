package com.niit.ecommerce.Backend.dao;

import com.niit.ecommerce.Backend.entity.User;

public interface UserDao {

	public User getUserByUsername(String email);

	boolean add(User user);

	boolean update(User user);

	boolean delete(String email);

	public User getUserById(Long user_id);

	public User checkForLogin(String email, String password);

}
