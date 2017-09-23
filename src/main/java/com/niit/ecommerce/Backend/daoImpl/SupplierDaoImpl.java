package com.niit.ecommerce.Backend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ecommerce.Backend.dao.SupplierDao;
import com.niit.ecommerce.Backend.entity.Supplier;

@Repository("supplierDao")
@Transactional
public class SupplierDaoImpl implements SupplierDao {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	@Override
	public boolean addSupplier(Supplier supplier) {
		try {
			sessionFactory.getCurrentSession().persist(supplier);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}
	}

	@Override
	public boolean updateSupplier(Supplier supplier) {
		try {
			sessionFactory.getCurrentSession().update(supplier);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}
	}

	@Override
	public List<Supplier> getSupplierByCompanyName(String companyName) {
		String selectsupplier = "From Supplier where supplier_companyName like lower(:parameter)";
		Query<Supplier> query = sessionFactory.getCurrentSession().createQuery(selectsupplier, Supplier.class);
		query.setParameter("parameter", '%' + companyName + '%');
		try {

			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}

	}

	@Override
	public Supplier getSupplierByBrandName(String brandName) {
		String selectsupplier = "From Supplier where supplier_brandName like lower(:parameter)";
		Query<Supplier> query = sessionFactory.getCurrentSession().createQuery(selectsupplier, Supplier.class);
		query.setParameter("parameter", '%' + brandName + '%');
		try {

			return query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public boolean deleteSupplierByBrandName(String brandName) {
		Supplier supplier = getSupplierByBrandName(brandName);
		supplier.setSupplier_enabled(false);
		try {
			sessionFactory.getCurrentSession().update(supplier);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}
	}

	@Override
	public List<Supplier> getAllSupplier() {
		String givesupplier = "FROM Supplier";
		Query<Supplier> query = sessionFactory.getCurrentSession().createQuery(givesupplier, Supplier.class);
		try {
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}

	}

	@Override
	public List<Supplier> getAllActiveSupplier() {
		String givesupplier = "FROM Supplier where supplier_enabled = 'true'";
		Query<Supplier> query = sessionFactory.getCurrentSession().createQuery(givesupplier, Supplier.class);
		try {
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}

	}

}
