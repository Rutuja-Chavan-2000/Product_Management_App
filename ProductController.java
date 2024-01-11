package com.example.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Product;



@RestController
public class ProductController {
	
	@Autowired
	SessionFactory factory;
	
	
	 @PostMapping("addNewProduct")
	 public String addNewProduct(@RequestBody Product product) {
		 
		 Session session=factory.openSession();
		 Transaction tx=session.beginTransaction();
		 session.persist(product); 
		 tx.commit();
		 
		return "product data saved!!";
		 
	 }
	 
	 @SuppressWarnings({ "deprecation", "unchecked" })
	@GetMapping("getAllProduct")
	 public List<Product> getAllProduct(){

		 Session session=factory.openSession();
		return session.createQuery("from Product").list();
		 
	 }
	 
	 @GetMapping("getOneProduct/{pid}")
	 public Product getOneProduct(@PathVariable int pid) {
		 
		 Session session=factory.openSession();
		 Product product=session.get(Product.class, pid);
		return product;
		 
	 }
	 
	 @GetMapping("getOneProductName/{name}")
	 public Product getOneProductName(@PathVariable String name) {
		 
		 Session session=factory.openSession();
		 Product product=session.get(Product.class, name);
		return product;
		 
	 }
	 
	 
	 
	 // http://localhost:8080/updateProductInfo/1?name=eraser&price=200
	 
	 @PutMapping("updateProductInfo/{pid}")
    public String updateProductInfo(@PathVariable int pid,@RequestParam String name,@RequestParam long price) {
		 
		 Session session=factory.openSession();
		 Product product=session.get(Product.class, pid);
		 Transaction tx=session.beginTransaction();
		 product.setName(name);
		 product.setPrice(price);
		 
		 session.update(product); 
		 tx.commit();
		 
		return "product updated";
		
	 }
	 
	 
	 
	 
	 
	 @DeleteMapping("deleteProduct/{pid}")
	 public String deleteProduct(@PathVariable int pid) {
		 
		 Session session=factory.openSession();
		 Product product=session.get(Product.class, pid);
		 Transaction tx=session.beginTransaction();
		 session.delete(product); 
		 tx.commit();
		 
		return "product deleted";
		 
	 }
	 
	 
}
