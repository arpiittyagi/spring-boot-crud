package com.arpit.crud.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@Autowired
	private ProductService service;

	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {

		
		return new ResponseEntity<>(service.saveProduct(product), HttpStatus.CREATED);
			

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
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/productById/{id}")
	public ResponseEntity<Product> findProductById(@PathVariable int id) {
		Optional<Product> product = service.getProductById(id);
		if (product.isPresent()) {
			return new ResponseEntity<>(product.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/productByName/{name}")
	public ResponseEntity<Product> findProductByName(@PathVariable String name) {
		Optional<Product> product = service.getProductByName(name);
		if (product.isPresent()) {
			return new ResponseEntity<>(product.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update")
	public Product updateProduct(@RequestBody Product product) {
		return service.updateProductById(product);

	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);

	}

}
