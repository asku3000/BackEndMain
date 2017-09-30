package com.niit.ecommerce.Backend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ecommerce.Backend.dao.UserDao;
import com.niit.ecommerce.Backend.entity.Cart;
import com.niit.ecommerce.Backend.entity.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	@Override
	public User getUserByUsername(String email) {
		String selectUserId = "FROM User where email=:parameter";
		Query<User> query = sessionFactory.getCurrentSession().createQuery(selectUserId, User.class);
		query.setParameter("parameter", email);
		try {

			return query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public boolean add(User user) {
		try {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
			// add the User to the database
			sessionFactory.getCurrentSession().persist(user);
			return true;

		} catch (Exception ex) {

			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}

	}

	@Override
	public boolean update(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}

	}

	@Override
	public boolean delete(String email) {
		User user = getUserByUsername(email);
		user.setEnabled(false);
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}

	}

	@Override
	public User getUserById(Long user_id) {

		try {
			return sessionFactory.getCurrentSession().get(User.class, Long.valueOf(user_id));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public User checkForLogin(String email, String password) {
		String getlogin = "FROM User where email = :paramter1 and password = :parameter2";
		Query<User> query = sessionFactory.getCurrentSession().createQuery(getlogin, User.class);
		query.setParameter("parameter1", email);
		query.setParameter("parameter2", password);
		try {

			return query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}

	}

	@Override
	public List<User> getAllUsers() {
		String getusers = "FROM User";
		Query<User> query = sessionFactory.getCurrentSession().createQuery(getusers, User.class);
		try {
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public List<User> getSupplierByCompanyName(String companyName) {
		String selectsupplier = "From User where supplier_companyName like lower(:parameter)";
		Query<User> query = sessionFactory.getCurrentSession().createQuery(selectsupplier, User.class);
		query.setParameter("parameter", '%' + companyName + '%');
		try {

			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}

	}

	@Override
	public User getSupplierByBrandName(String brandName) {
		String selectsupplier = "From User where supplier_brandName like lower(:parameter)";
		Query<User> query = sessionFactory.getCurrentSession().createQuery(selectsupplier, User.class);
		query.setParameter("parameter", '%' + brandName + '%');
		try {

			return query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public List<User> getAllSupplier() {
		String givesupplier = "FROM Supplier where role = supplier";
		Query<User> query = sessionFactory.getCurrentSession().createQuery(givesupplier, User.class);
		try {
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public boolean deleteSupplierByBrandName(String brandName) {
		User user = getSupplierByBrandName(brandName);
		user.setEnabled(false);
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}
	}

}
