package com.imaginary.poc.vertx.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imaginary.poc.vertx.spring.entity.Product;

/**
 * Spring Data JPA repository to connect our service bean to data
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
