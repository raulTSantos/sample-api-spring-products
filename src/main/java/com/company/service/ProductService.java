package com.company.service;

import java.util.List;

import com.company.dto.ProductDTO;
import com.company.model.Product;

public interface ProductService {
	public Product save(Product obj);

	public List<Product> getAll();
	
	public List<Product> findByEnabled();

	public void deleteById(String id);

	public ProductDTO update(ProductDTO dto, String id);

	public void partialUpdate(ProductDTO dto, String id);
	
}
