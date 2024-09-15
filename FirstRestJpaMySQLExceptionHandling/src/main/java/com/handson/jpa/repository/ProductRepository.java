package com.handson.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handson.jpa.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
