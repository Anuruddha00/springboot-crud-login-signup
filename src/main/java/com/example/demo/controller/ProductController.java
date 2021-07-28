package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;

	
	//get all product
	@GetMapping("/product")
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	//create product REST API
	@PostMapping("/product")
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	//get Product by ID REST API
	@GetMapping("/product/{product_id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long product_id){
		Product product=productRepository.findById(product_id)
				.orElseThrow(()-> new ResourceNotFoundException("Product Not exixts with Id"+ product_id));
			return ResponseEntity.ok(product);
	}
	
	//update REST API
	@PutMapping("/product/{product_id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long product_id , @RequestBody Product productDetails){
		Product product=productRepository.findById(product_id)
		.orElseThrow(()-> new ResourceNotFoundException("Product Not exist with Id"+ product_id));
		
		product.setProductName(productDetails.getProductName());
		product.setPrice(productDetails.getPrice());
		product.setQuantity(productDetails.getQuantity());
		
		Product updatedProduct= productRepository.save(product);
		
		return ResponseEntity.ok(updatedProduct);
	}
	
	
	//Delete product
	@DeleteMapping("/product/{product_id}")
	public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long product_id){
		Product product = productRepository.findById(product_id)
				.orElseThrow(()->new ResourceNotFoundException("Product Not exist with Id" + product_id));
		
		productRepository.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	

	
}
