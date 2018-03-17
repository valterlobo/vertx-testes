package com.imaginary.poc.vertx.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imaginary.poc.vertx.spring.entity.Product;
import com.imaginary.poc.vertx.spring.repository.ProductRepository;

import java.util.List;

/**
 * Simple Spring service bean to expose the results of a trivial database call
 */
@Service
public class ProductService {

  @Autowired
  private ProductRepository repo;

  public List<Product> getAllProducts() {
    return repo.findAll();
  }

}
