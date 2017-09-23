package com.niit.ecommerce.Backend.dao;

import java.util.List;

import com.niit.ecommerce.Backend.entity.Supplier;

public interface SupplierDao {

	boolean addSupplier(Supplier supplier);

	boolean updateSupplier(Supplier supplier);

	boolean deleteSupplierByBrandName(String brandName);

	public List<Supplier> getSupplierByCompanyName(String companyName);

	public Supplier getSupplierByBrandName(String brandName);

	public List<Supplier> getAllSupplier();

	public List<Supplier> getAllActiveSupplier();

}
