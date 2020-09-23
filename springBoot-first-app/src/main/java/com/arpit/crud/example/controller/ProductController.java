package com.arpit.crud.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arpit.crud.example.entity.Product;
import com.arpit.crud.example.service.ProductService;

@RestController
public class ProductController {

	private static final String DELETE_MESSAGE = "Delete operation can not performed";
	private static final String SAVE_FAILED_MESSAGE = "Save operation can not performed";
	private static final String UPDATE_FAILED_MESSSAGE = "update operation can not performed";
	private static final String READ_FAILED_MESSAGE = "Data retrieval failed";

	@Autowired
	private ProductService service;

	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {

		Optional<Product> productOption = service.getProductByName(product.getName());
		if (productOption.isPresent()) {
			throw new ProductNotFoundException("product already exsist with the name " + product.getName());
		} else {
			return new ResponseEntity<>(service.saveProduct(product), HttpStatus.CREATED);
		}
	}

	@PostMapping("/addProducts")
	public List<Product> addProducts(@RequestBody List<Product> product) {
		return service.saveProducts(product);

	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> finaAllProducts() {
		try {
			return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
		} catch (Exception ex) {
			throw new ProductNotFoundException(READ_FAILED_MESSAGE);
		}

	}

	@GetMapping("/productById/{id}")
	public ResponseEntity<Product> findProductById(@PathVariable int id) {
		Optional<Product> product = service.getProductById(id);
		if (product.isPresent()) {
			return new ResponseEntity<>(product.get(), HttpStatus.OK);
		} else {
			throw new ProductNotFoundException(READ_FAILED_MESSAGE);

		}

	}

	@GetMapping("/productByName/{name}")
	public ResponseEntity<Product> findProductByName(@PathVariable String name) {
		Optional<Product> product = service.getProductByName(name);
		if (product.isPresent()) {
			return new ResponseEntity<>(product.get(), HttpStatus.OK);
		} else {
			throw new ProductNotFoundException(READ_FAILED_MESSAGE);
		}
	}

	@PutMapping("/update")
	public Product updateProduct(@RequestBody Product product) {
		Optional<Product> optionalProduct = service.getProductById(product.getId());
		if (optionalProduct.isPresent()) {
			return service.updateProductById(product);
		} else {
			throw new ProductNotFoundException(UPDATE_FAILED_MESSSAGE);
		}

	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		Optional<Product> product = service.getProductById(id);
		if (product.isPresent()) {
			return service.deleteProduct(id);
		} else {
			throw new ProductNotFoundException(DELETE_MESSAGE);

		}

	}

}
