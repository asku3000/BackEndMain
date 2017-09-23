package com.niit.ecommerce.Backend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ecommerce.Backend.dao.CartItemDao;
import com.niit.ecommerce.Backend.entity.Cart;
import com.niit.ecommerce.Backend.entity.CartItem;
import com.niit.ecommerce.Backend.entity.Product;

@Repository("cartItemDao")
@Transactional
public class CartItemDaoImpl implements CartItemDao {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	public boolean searchCartItemByCartIdAndProductId(Cart cart, Product product) {
		String selectCartId = "FROM CartItem where cart=:parameter1 and product=:parameter2";
		Query<CartItem> query = sessionFactory.getCurrentSession().createQuery(selectCartId, CartItem.class);
		query.setParameter("parameter1", cart);
		query.setParameter("parameter2", product);
		try {
			query.getSingleResult();
			return false;
		} catch (Exception ex) {
			return true;
		}
	}

	public CartItem getCartItemByCartIdAndProductId(Cart cart, Product product) {
		String selectCartId = "FROM CartItem where cart=:parameter1 and product=:parameter2";
		Query<CartItem> query = sessionFactory.getCurrentSession().createQuery(selectCartId, CartItem.class);
		query.setParameter("parameter1", cart);
		query.setParameter("parameter2", product);
		try {
			return query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}

	public boolean addCartItem(CartItem cartItem) {
		try {
			// add cart item to the database
			sessionFactory.getCurrentSession().saveOrUpdate(cartItem);
			// sessionFactory.getCurrentSession().evict(cartItem);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}
	}

	public List<CartItem> cartItemGetByCart(Cart cart) {
		String selectCartId = "FROM CartItem where cart=:parameter";
		@SuppressWarnings("unchecked")
		Query<CartItem> query = sessionFactory.getCurrentSession().createQuery(selectCartId);
		query.setParameter("parameter", cart);
		try {
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}

	public boolean updateCartItem(CartItem cartItem) {

		try {
			// sessionFactory.getCurrentSession().refresh(cartItem);
			// sessionFactory.getCurrentSession().refresh(user);
			// update the User to the database
			sessionFactory.getCurrentSession().update(cartItem);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}
	}

	public boolean deleteCartItem(Long cartItem_Id) {
		String deleteCartItem = "DELETE FROM CartItem WHERE cartitem_id=:parameter";
		@SuppressWarnings("unchecked")
		Query<CartItem> query = sessionFactory.getCurrentSession().createQuery(deleteCartItem);
		query.setParameter("parameter", cartItem_Id);
		try {
			// Delete the cartItem to the database
			query.executeUpdate();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public CartItem getCartItemByCartItem_Id(Long cartItem_Id) {
		String selectCartId = "FROM CartItem where cartItem_Id=:parameter";
		Query<CartItem> query = sessionFactory.getCurrentSession().createQuery(selectCartId, CartItem.class);
		query.setParameter("parameter", cartItem_Id);
		try {
			return query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public long getGrandTotal(Cart cart) {
		String selectTotalPrice = "From CartItem where cart_cart_id=:parameter";
		Query<CartItem> query = sessionFactory.getCurrentSession().createQuery(selectTotalPrice, CartItem.class);
		query.setParameter("parameter", cart.getCart_Id());
		try {
			long total_price = 0;
			long total_quantity = 0;
			List<CartItem> list = query.getResultList();
			for (CartItem c : list) {
				total_price = total_price + c.getTotal_price();
				total_quantity = total_quantity + c.getSell_quantity();
			}
			return total_price;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int getTotalQuantity(Cart cart) {
		String selectTotalPrice = "From CartItem where cart_cart_id=:parameter";
		Query<CartItem> query = sessionFactory.getCurrentSession().createQuery(selectTotalPrice, CartItem.class);
		query.setParameter("parameter", cart.getCart_Id());
		try {
			int total_quantity = 0;
			List<CartItem> list = query.getResultList();
			for (CartItem c : list) {
				total_quantity = total_quantity + c.getSell_quantity();
			}
			return total_quantity;
		} catch (Exception e) {
			return 0;
		}
	}

}
