package com.niit.ecommerce.Backend.testcase;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.ecommerce.Backend.dao.CartDao;
import com.niit.ecommerce.Backend.dao.CartItemDao;
import com.niit.ecommerce.Backend.dao.CategoryDao;
import com.niit.ecommerce.Backend.dao.ProductDao;
import com.niit.ecommerce.Backend.dao.ReviewsDao;
import com.niit.ecommerce.Backend.dao.SupplierDao;
import com.niit.ecommerce.Backend.dao.UserDao;
import com.niit.ecommerce.Backend.entity.Cart;
import com.niit.ecommerce.Backend.entity.CartItem;
import com.niit.ecommerce.Backend.entity.Category;
import com.niit.ecommerce.Backend.entity.Product;
import com.niit.ecommerce.Backend.entity.Reviews;
import com.niit.ecommerce.Backend.entity.Supplier;
import com.niit.ecommerce.Backend.entity.User;

public class TestCaseOne {

	private static AnnotationConfigApplicationContext context;

	static UserDao userDao;
	static ProductDao productDao;
	static CartItemDao cartItemDao;
	static CartDao cartDao;
	static CategoryDao categoryDao;
	static SupplierDao supplierDao;
	static ReviewsDao reviewsDao;
	User user;
	Cart cart;
	CartItem cartItem;
	Product product;
	Category category;
	Supplier supplier;
	Reviews reviews;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.ecommerce.Backend");
		context.refresh();

		userDao = (UserDao) context.getBean("userDao");
		productDao = (ProductDao) context.getBean("productDao");
		cartItemDao = (CartItemDao) context.getBean("cartItemDao");
		cartDao = (CartDao) context.getBean("cartDao");
		categoryDao = (CategoryDao) context.getBean("categoryDao");
		supplierDao = (SupplierDao) context.getBean("supplierDao");
		reviewsDao = (ReviewsDao) context.getBean("reviewsDao");
	}

	// @Test // adding user
	public void test1() {
		user = new User();
		user.setContact("9811219960");
		user.setEmail("meenukashyap@gmail.com");
		user.setEnabled(true);
		user.setUser_firstName("meenu");
		user.setUser_lastName("kashyap");
		user.setPassword("7838463655");
		// user.setRole();
		user.setAddress("rohini sector-16");
		user.setUser_gender("female");
		user.setUser_dob("01091997");
		user.setUser_state("Delhi");

		assertEquals(true, userDao.add(user));

	}

	// @Test // add supplier
	public void test2() {
		supplier = new Supplier();
		supplier.setSupplier_address("Delhi");
		supplier.setSupplier_brandName("doodass");
		supplier.setSupplier_companyName("Mayank union pvt. ltd.");
		supplier.setSupplier_email("mayank@bookbag.com");
		supplier.setSupplier_enabled(true);
		supplier.setSupplier_password("7838463655");
		supplier.setSupplier_phoneNo("7838463655");

		assertEquals(true, supplierDao.addSupplier(supplier));
	}

	// @Test // Category Add
	public void test3() {
		category = new Category();
		category.setCategory_enabled(true);
		category.setCategory_type("epics");
		category.setCategory_level("indian");

		assertEquals(true, categoryDao.add(category));

	}

	// @Test // adding product
	public void test4() {
		product = new Product();
		category = categoryDao.getCategoryByCategory_level("class 9");
		supplier = supplierDao.getSupplierByBrandName("doodass");
		product.setProduct_activeIs(true);
		product.setProduct_author("ncert");
		product.setProduct_bookName("manika sanskrit class 9");
		product.setProduct_description("this is sanskrit text book, brand new ncert book for class 9");
		product.setProduct_imgUrl("/resources/Images/ncertclass9sanskritmanika.png");
		product.setProduct_language("hindi");
		product.setProduct_price((long) 179);
		product.setProduct_status("new");
		product.setProduct_quantity(100);
		product.setCategory(category);
		product.setSupplier(supplier);

		product.setProduct_publisher("ncert");

		assertEquals(true, productDao.add(product));
	}

	// @Test // buying a new product //adding to cart by checking if already
	// existing
	public void test5() {
		user = userDao.getUserById((long) 1);
		cart = user.getCart();
		product = productDao.getProductById((long) 2);
		cartItem = cartItemDao.getCartItemByCartIdAndProductId(cart, product);
		Boolean check = product.isProduct_activeIs();
		if (product.getProduct_quantity() == 0 || check.equals(false)) {
			System.out.println("Sorry !! this product is out of stock");
		} else {
			if (cartItem == null) {
				cartItem = new CartItem();
				cartItem.setCart(cart);
				int availQty = product.getProduct_quantity();
				int sellqty = 1;
				if (sellqty <= availQty) {
					cartItem.setProduct(product);
					cartItem.setSell_quantity(sellqty);
					cartItem.setTotal_price(cartItem.getSell_quantity() * product.getProduct_price());
					assertEquals(true, cartItemDao.addCartItem(cartItem));
					int NewQty = availQty - sellqty;
					product.setProduct_quantity(NewQty);

					assertEquals(true, productDao.update(product));
					test13();
				} else {
					System.out.println("SORRY !!!! mr. You can buy only :- " + product.getProduct_quantity()
							+ " units of this product. Please change your choice. ");
				}
			}

			else {
				int availQty = product.getProduct_quantity();
				int sellingQty = cartItem.getSell_quantity();
				sellingQty += 1;
				// System.out.println("helo"+product.isProduct_activeIs());
				if (1 <= availQty) {
					cartItem.setSell_quantity(sellingQty);
					cartItem.setTotal_price(cartItem.getSell_quantity() * product.getProduct_price());
					assertEquals(true, cartItemDao.updateCartItem(cartItem));
					int NewQty = availQty - 1;
					product.setProduct_quantity(NewQty);
					assertEquals(true, productDao.update(product));
					test13();

				} else {
					System.out.println("SORRY !!!!  You can buy only :- " + product.getProduct_quantity()
							+ " units of this product. Please change your choice. ");
				}

			}
		}
	}

	// @Test // Clicking + to increase the existing quantity
	public void test6() {
		user = userDao.getUserById((long) 1);
		cart = user.getCart();
		product = productDao.getProductById((long) 2);
		cartItem = cartItemDao.getCartItemByCartIdAndProductId(cart, product);
		int availQty = product.getProduct_quantity();
		int sellingQty = cartItem.getSell_quantity();
		sellingQty += 1;
		Boolean check = product.isProduct_activeIs();
		if (1 <= availQty && check.equals(true)) {
			cartItem.setSell_quantity(sellingQty);
			cartItem.setTotal_price(cartItem.getSell_quantity() * product.getProduct_price());
			assertEquals(true, cartItemDao.updateCartItem(cartItem));
			int NewQty = availQty - 1;
			product.setProduct_quantity(NewQty);
			assertEquals(true, productDao.update(product));
			test13();

		} else {
			System.out.println(
					"SORRY !!!!  This product is out of stock"/*
																 * You can buy
																 * only :- " +
																 * product.
																 * getProduct_quantity
																 * () +
																 * " units of this product. Please change your choice. "
																 */);
		}

	}

	// @Test // clicking on - button to decrease the quantity
	public void test7() {
		user = userDao.getUserById((long) 1);
		cart = user.getCart();
		product = productDao.getProductById((long) 2);
		cartItem = cartItemDao.getCartItemByCartIdAndProductId(cart, product);
		int availQty = product.getProduct_quantity();
		int sellingQty = cartItem.getSell_quantity();
		if (sellingQty > 0) {
			sellingQty -= 1;

			cartItem.setSell_quantity(sellingQty);
			cartItem.setTotal_price(cartItem.getSell_quantity() * product.getProduct_price());
			assertEquals(true, cartItemDao.updateCartItem(cartItem));
			int NewQty = availQty + 1;
			product.setProduct_quantity(NewQty);
			assertEquals(true, productDao.update(product));
			test13();
		} else {
			System.out.println("You cant further delete this product");
		}

	}

	// @Test // deleting cartitem (single product remove button)
	public void test8() {
		user = userDao.getUserById((long) 1);
		cart = user.getCart();

		product = productDao.getProductById((long) 2);
		cartItem = cartItemDao.getCartItemByCartIdAndProductId(cart, product);
		if (cartItem == null) {
			System.out.println("This product is not present in your Cart");
		} else {
			Long cartItem_Id = cartItem.getCartItem_Id();

			int availQty = product.getProduct_quantity();
			int sellingQty = cartItem.getSell_quantity();
			availQty += sellingQty;
			product.setProduct_quantity(availQty);
			System.out.println(availQty + "............." + sellingQty);
			assertEquals(true, cartItemDao.deleteCartItem(cartItem_Id));
			assertEquals(true, productDao.update(product));
			test13();

		}

	}

	// @Test //displaying category by category type
	public void test9() {
		List<Category> list = categoryDao.getCategoryByCategoty_type("Text Books");
		for (Category c : list) {
			System.out.println(c.toString());
		}

	}

	// @Test // displaying product by category Type
	public void test10() {
		category = categoryDao.getCategoryById((long) 1);
		List<Product> list = productDao.getProductByCategoryType(category);
		for (Product p : list) {
			System.out.println(p.toString());
		}

	}

	// @Test // displaying product by category level
	public void test11() {
		category = categoryDao.getCategoryById((long) 1);
		List<Product> list = productDao.getProductByCategoryLevel(category);
		for (Product p : list) {
			System.out.println(p.toString());
		}

	}

	// @Test// deleting the user
	public void test12() {
		String email = "hemant@gmail.com";
		assertEquals(true, userDao.delete(email));
	}

	// @Test//deleting product
	public void test13() {
		Long product_id = (long) 4;
		assertEquals(true, productDao.delete(product_id));

	}

	// @Test // deleting all cartItems and cart after checkout
	public void test14() {
		user = userDao.getUserById((long) 1);
		cart = user.getCart();
		cartDao.deleteAllCartItems(cart);
		cart.setCartItemCount(0);
		cart.setGrandTotal(0);
		assertEquals(true, cartDao.updateCart(cart));

	}

	// @Test // searching product
	public void test15() {
		List<Product> list = productDao.searchProductByProductName("math");
		for (Product p : list) {
			System.out.println(p.toString());
		}

	}

	// @Test //update product
	public void test16() {
		product = productDao.getProductById((long) 34);
		product.setProduct_imgUrl("");
	}

	// @Test //search product
	public void test17() {
		List<Product> product = productDao.searchProductByProductName("vitan");
		//
		for (Product p : product) {
			System.out.println(p.toString() + "hello");
		}

	}

	// @Test // getting cartitem and cart
	public void test18() {
		product = new Product();
		user = userDao.getUserByUsername("pavleen.sethi@gmail.com");
		cart = user.getCart();
		List<CartItem> list = cartItemDao.cartItemGetByCart(cart);
		int cartItemCount = cartItemDao.getTotalQuantity(cart);
		long grandTotal = cartItemDao.getGrandTotal(cart);
		cart.setCartItemCount(cartItemCount);
		cart.setGrandTotal(grandTotal);

		for (CartItem c : list) {
			System.out.println("Hello" + c.toString());
			System.out.println(c.getProduct().getProduct_bookName());
		}
		assertEquals(true, cartDao.updateCart(cart));
		cart = user.getCart();
		System.out.println("cart" + cart.toString());
	}

	// @Test //reviews add
	public void test19() {
		reviews = new Reviews();
		product = productDao.getProductById((long) 35);
		user = userDao.getUserById((long) 5);
		reviews.setReview_stars(2);
		reviews.setReview_message("Very Good Product");
		reviews.setUser(user);
		reviews.setProduct(product);
		assertEquals(true, reviewsDao.addReviews(reviews));
	}

	// @Test //reviews average
	public void test20() {
		// reviews = new Reviews();
		product = productDao.getProductById((long) 35);
		if (product.isProduct_activeIs() == true) {
			List<Reviews> list = reviewsDao.getAverageRatingOfProduct(product);
			int listlength = list.size();
			int totalReview = 0;
			for (Reviews r : list) {
				if (r.isReview_enabled() == true) {
					totalReview += r.getReview_stars();
				}
			}
			totalReview /= listlength;
			System.out.println("Average :" + totalReview);
		}
		// assertEquals(true,reviewsDao.addReviews(reviews));
	}

	@Test // reviews of user
	public void test21() {
		// reviews = new Reviews();
		// product = productDao.getProductById((long) 35);
		user = userDao.getUserById((long) 6);
		
		if (user.isEnabled() == true) {
			List<Reviews> list = reviewsDao.getAllReviewsOfUser(user);
			int listlength = list.size();
			System.out.println("Size is "+listlength);
			// int totalReview = 0;
			for (Reviews r : list) {
				System.out.println("Rating :" + r.getReview_message());
				if (r.isReview_enabled() == true) {
					// totalReview +=r.getReview_stars();
					System.out.println("Rating :" + r.toString());
				}

			}

			// System.out.println("Average :"+totalReview);}
			// assertEquals(true,reviewsDao.addReviews(reviews));
		}
	}
}
