package com.example.NileMart.cart;

import com.example.NileMart.common.Check;
import com.example.NileMart.common.Money;
import com.example.NileMart.exception.NotFoundException;
import com.example.NileMart.exception.OutOfStockException;
import com.example.NileMart.exception.StoreException;
import com.example.NileMart.model.Customer;
import com.example.NileMart.model.Product;
import com.example.NileMart.service.ProductService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")

public class ShoppingCart {
    @Getter
    private int number;
    @Getter
    private Customer customer;
  private Map<String, Integer> lines  = new LinkedHashMap<>();
  private ProductService productService;
  private final int maxQuantityPerProduct;

    public ShoppingCart(ProductService productService, @Value("${store.max-quantity-per-product}") int maxQuantityPerProduct) {
        this.productService = productService;
        this.maxQuantityPerProduct = maxQuantityPerProduct;

    }
    public void open(int number, Customer customer) {
        if (this.customer != null) {
            throw new IllegalStateException("This cart is already open.");
        }
        this.number = number;
        this.customer = customer;
    }


  public synchronized void addItem(String sku, int quantity) {
      Product p =Check.found(productService.findBySku(sku),
              () -> new NotFoundException("Product " + sku + " was not found."));
      if (p.getStockQuantity() == 0) {
          throw new OutOfStockException(p.getName() + " is out of stock.");
      }

      int newQuantity = lines.getOrDefault(sku, 0) + quantity;

      if (newQuantity > maxQuantityPerProduct ) {
          throw new StoreException("You can add at most 10 of " + p.getName());
      }
      if (newQuantity > p.getStockQuantity()) {
          throw new OutOfStockException("Only " + p.getStockQuantity()
                  + " available for " + p.getName());
      }
      lines.put(sku, newQuantity);
  }
  public synchronized boolean isEmpty(){
    return lines.isEmpty();
  }
  public synchronized void clear(){
      lines.clear();
  }
  public synchronized  BigDecimal subtotal() {
      return lines.entrySet().stream()
              .map(e -> productService.getPrice(e.getKey(), e.getValue()))
              .reduce(Money.ZERO, BigDecimal::add);
    }
    public synchronized CartView view() {
        List<LineView> views = lines.entrySet().stream()
                .map(e -> {
                    Product p = Check.found(productService.findBySku(e.getKey()),
                            () -> new NotFoundException("Product " + e.getKey() + " was not found."));
                    return new LineView(p.getSku(), p.getName(), e.getValue(),
                            productService.getPrice(e.getKey(), e.getValue()));
                })
                .toList();
        int itemCount = lines.values().stream().mapToInt(Integer::intValue).sum();
        return new CartView(number, customer, views, itemCount, subtotal(), lines.isEmpty());
    }
}
