package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Iterable<Product> findAllByCategoryId(Long id);

    Iterable<Product> findAllByCategoryName(String name);

    Iterable<Product> findAllByPriceBetween(int from, int to);

    Iterable<Product> findAllByNameContaining(String name);
}