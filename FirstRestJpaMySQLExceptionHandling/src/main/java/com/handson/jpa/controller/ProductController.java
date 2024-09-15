package com.handson.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handson.jpa.exceptions.InvalidValueException;
import com.handson.jpa.exceptions.NotFoundException;
import com.handson.jpa.model.Product;
import com.handson.jpa.service.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
	ProductService service;

//	@PostMapping("/product")
	@PostMapping
	@RequestMapping(path="product",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addProduct(@RequestBody Product product){
		return ResponseEntity.status(201).body(service.save(product));
	}
	
	@GetMapping("/products")
	public ResponseEntity<?> getProducts(){
		return ResponseEntity.status(200).body(service.getProducts());
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductByID(@PathVariable long id) throws NotFoundException{
		Product product = service.getProductByID(id);
		if(product==null)
			//calling the global @ExceptionHandler @RestControllerAdvice
		throw new NotFoundException("no such product :(");
		return ResponseEntity.status(200).body(product);
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deleteProductByID(@PathVariable long id) throws NotFoundException{
		if(service.deleteProductByID(id))
			return ResponseEntity.status(200).body("Successfully deleted :)");
		else
			throw new NotFoundException("no such product :(");
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable long id,@RequestBody Product product) throws NotFoundException{
		product.setProductID(id);
		if(service.updateProduct(id, product)==null)
			throw new NotFoundException("no such product :(");
		return ResponseEntity.status(200).body(product);
	}
	
	//dummy method to check invalid value custom exception
	@GetMapping("/test/{str}")
	public String message(@PathVariable String str) throws InvalidValueException,Exception {
		if(str==null)
			throw new NullPointerException();
		if(str.chars().anyMatch(c->Character.isDigit(c)))
			throw new InvalidValueException("invalid value");
		if(str.length()<3)
			//will be called when you try to access an inexistent route/url
			throw new Exception("str too short");
		return "accepted";
		
	}
	

}
