package com.example.NileMart.repository;

import com.example.NileMart.model.Product;

import java.util.Optional;

public interface ProductRepository extends Repository<Product, String> {

    default Optional<Product> findBySku(String sku) {
        return findById(sku);
    }
}