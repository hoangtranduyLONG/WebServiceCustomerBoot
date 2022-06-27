package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    public Iterable<Product> findAllByCategoryId(Long id){
        if (id == 0) {
            return productRepository.findAll();
        }
        return productRepository.findAllByCategoryId(id);
    }

    public Iterable<Product> findAllByCategoryName(String name){
        return productRepository.findAllByCategoryName(name);
    }

    public Iterable<Product> findAllByPriceBetween(int from, int to){
        return productRepository.findAllByPriceBetween(from, to);
    }

    public Iterable<Product> findNameContai(String name) {
        return productRepository.findAllByNameContaining(name);
    }
}