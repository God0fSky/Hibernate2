package com.example.hibernate2.repositories;


import com.example.hibernate2.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
