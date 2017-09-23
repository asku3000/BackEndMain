package com.niit.ecommerce.Backend.dao;

import com.niit.ecommerce.Backend.entity.Cart;

public interface CartDao {
	public boolean updateCart(Cart cart);

	public Cart getCartBycart_Id(Long cart_Id);

	public boolean deleteAllCartItems(Cart cart);

}
