package com.niit.ecommerce.Backend.dao;

import java.util.List;

import com.niit.ecommerce.Backend.entity.Cart;
import com.niit.ecommerce.Backend.entity.CartItem;
import com.niit.ecommerce.Backend.entity.Product;

public interface CartItemDao {

	public boolean searchCartItemByCartIdAndProductId(Cart cart, Product product);

	public CartItem getCartItemByCartIdAndProductId(Cart cart, Product product);

	public boolean addCartItem(CartItem cartItem);

	public List<CartItem> cartItemGetByCart(Cart cart);

	public boolean updateCartItem(CartItem cartItem);

	public boolean deleteCartItem(Long cartItem_Id);

	public CartItem getCartItemByCartItem_Id(Long cartItem_Id);

	public long getGrandTotal(Cart cart);

	public int getTotalQuantity(Cart cart);

}
