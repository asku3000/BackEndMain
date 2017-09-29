package com.niit.ecommerce.Backend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ecommerce.Backend.dao.ProductDao;
import com.niit.ecommerce.Backend.entity.Category;
import com.niit.ecommerce.Backend.entity.Product;

@Repository("productDao")
@Transactional
public class ProductDaoImpl implements ProductDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product getProductById(Long product_id) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Long.valueOf(product_id));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return null;
		}

	}

	@Override
	public List<Product> getAllProductList() {
		String getproduct = "FROM Product";
		Query<Product> query = sessionFactory.getCurrentSession().createQuery(getproduct, Product.class);
		try {
			return query.getResultList();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().save(product);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}

	}

	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}

	}

	@Override
	public boolean delete(Long product_id) {
		Product product = getProductById(product_id);
		product.setProduct_activeIs(false);
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return false;
		}

	}

	@Override
	public List<Product> getProductByCategoryType(Category category) {
		String selectproduct = "FROM Product where Category_id = :parameter";
		Query<Product> query = sessionFactory.getCurrentSession().createQuery(selectproduct, Product.class);
		query.setParameter("parameter", category);
		try {
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public List<Product> getProductByCategoryLevel(Category category) {
		String selectproduct = "FROM Product where Category_id = :parameter";
		Query<Product> query = sessionFactory.getCurrentSession().createQuery(selectproduct, Product.class);
		query.setParameter("parameter", category);
		try {
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public List<Product> getAllActiveProductList() {
		String givesupplier = "FROM Product where product_activeis = 'TRUE'";
		Query<Product> query = sessionFactory.getCurrentSession().createQuery(givesupplier, Product.class);
		try {
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return null;
		}

	}

	@Override
	public List<Product> getAllInActiveProductList() {
		String givesupplier = "FROM Product where product_activeis = 'FALSE'";
		Query<Product> query = sessionFactory.getCurrentSession().createQuery(givesupplier, Product.class);
		try {
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public List<Product> searchProductByProductName(String name) {
		name = name.toLowerCase();
		String search = "FROM Product where PRODUCT_BOOKNAME like lower(:parameter)";
		Query<Product> query = sessionFactory.getCurrentSession().createQuery(search, Product.class);
		query.setParameter("parameter", '%' + name + '%');
		try {
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public Product getProductByName(String product_name) {
		String search = "FROM Product where PRODUCT_BOOKNAME = :parameter";
		Query<Product> query = sessionFactory.getCurrentSession().createQuery(search, Product.class);
		query.setParameter("parameter", product_name);
		try {
			return query.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public List<Product> getProductBySupplierId(Long supplier_id) {
		String search = "FROM Product where SUPPLIER_ID = :parameter";
		Query<Product> query = sessionFactory.getCurrentSession().createQuery(search, Product.class);
		query.setParameter("parameter", supplier_id);
		try {
			return query.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return null;
		}

	}

}
