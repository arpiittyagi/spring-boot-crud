package com.arpit.crud.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpit.crud.example.entity.Product;
import com.arpit.crud.example.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public Product saveProduct(Product product) {
		return productRepository.save(product);

	}

	public List<Product> saveProducts(List<Product> product) {
		return productRepository.saveAll(product);
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Optional<Product> getProductById(int id) {

		Optional<Product> product = productRepository.findById(id);

		return product;

	}

	public Optional<Product> getProductByName(String name) {
		return Optional.of(productRepository.findByName(name));
	}

	public String deleteProduct(int id) {
		productRepository.deleteById(id);
		return "product is deleted!!" + id;

	}

	public Product updateProductById(Product product) {
		
		Product exsistingProduct = productRepository.findById(product.getId()).orElse(null);
		exsistingProduct.setName(product.getName());
		exsistingProduct.setQuantity(product.getQuantity());
		exsistingProduct.setPrice(product.getPrice());
		return productRepository.save(product);

	}

}
