package com.niit.ecommerce.Backend.dao;

import com.niit.ecommerce.Backend.entity.Payment;
import com.niit.ecommerce.Backend.entity.User;

public interface PaymentDao {
	public Payment getPaymentDetails(User user);
	public boolean add(Payment payment);

}
