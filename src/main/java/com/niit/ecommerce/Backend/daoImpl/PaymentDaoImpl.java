package com.niit.ecommerce.Backend.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ecommerce.Backend.dao.PaymentDao;
import com.niit.ecommerce.Backend.entity.Payment;
import com.niit.ecommerce.Backend.entity.User;

@Repository("PaymentDao")
@Transactional
public class PaymentDaoImpl implements PaymentDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Payment getPaymentDetails(User user) {
		return (Payment) sessionFactory.getCurrentSession().createQuery("from Payment where user=:user")
				.setParameter("user", user).getSingleResult();

	}

	@Override
	public boolean add(Payment payment) {
		try {
			sessionFactory.getCurrentSession().save(payment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
