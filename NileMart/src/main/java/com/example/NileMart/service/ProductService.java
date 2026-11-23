package com.example.NileMart.service;

import com.example.NileMart.common.Check;
import com.example.NileMart.exception.NotFoundException;
import com.example.NileMart.exception.OutOfStockException;
import com.example.NileMart.exception.StoreException;
import com.example.NileMart.model.Category;
import com.example.NileMart.model.Product;
import com.example.NileMart.pricing.PricingService;
import com.example.NileMart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final PricingService pricingService;



    private Product getProduct(String sku) {
        return Check.found(productRepository.findBySku(sku),
                () -> new NotFoundException("Product " + sku + " was not found."));
    }
    public Product save(Product product ) {
        return productRepository.save(product);
    }
    public List<Product> findAll(){
        return productRepository.findAll().stream().sorted(Comparator.comparing(Product::getCategory)
                        .thenComparing(Product::getName)).toList();
    }
    public Optional<Product> findBySku(String sku){
        return productRepository.findBySku(sku);
    }
    public List<Product> search(Predicate<Product> filter) {
        return findAll().stream()
                .filter(filter)
                .toList();
    }
    public Map<Category, List<Product>> groupByCategory() {
        return findAll().stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        () -> new EnumMap<>(Category.class),
                        Collectors.toList()));
    }
    public BigDecimal getPrice(String sku, int quantity) {
        return pricingService.getPrice(getProduct(sku), quantity);
    }
    public void reduceStock(String sku, int quantity) {
        if (quantity <= 0) {
            throw new StoreException("Quantity must be greater than zero");
        }
        Product product =getProduct(sku);
        if (product.getStockQuantity() < quantity) {
            throw new OutOfStockException("Only " + product.getStockQuantity()
                    + " available for " + product.getName() + ".");
        }
            product.setStockQuantity(product.getStockQuantity() - quantity);

    }
}
