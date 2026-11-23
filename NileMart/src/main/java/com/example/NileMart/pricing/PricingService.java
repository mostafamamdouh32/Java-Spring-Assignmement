package com.example.NileMart.pricing;

import com.example.NileMart.model.Product;

import java.math.BigDecimal;


public interface PricingService {
    BigDecimal getPrice(Product product, int quantity);
}
