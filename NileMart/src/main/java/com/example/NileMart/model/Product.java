package com.example.NileMart.model;




import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
    @EqualsAndHashCode.Include
    private final String sku;
    private final String name;
    private final Category category;
    private final BigDecimal price;
    @Setter
    private int stockQuantity;
    public Product(String sku, String name, Category category,
                   BigDecimal price, int stockQuantity) {
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

}
