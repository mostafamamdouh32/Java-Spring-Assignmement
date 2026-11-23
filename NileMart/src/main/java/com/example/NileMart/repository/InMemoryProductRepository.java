package com.example.NileMart.repository;

import com.example.NileMart.model.Category;
import com.example.NileMart.model.Product;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final Map<String, Product> products = new ConcurrentHashMap<>();
    public InMemoryProductRepository() {


    }
    @PostConstruct
    public void seed() {
        save(new Product("PH-X5-128", "Smartphone X5 128GB",
                Category.ELECTRONICS, BigDecimal.valueOf(16000.00), 12));
        save(new Product("ACC-CHG-20W", "Fast Charger 20W",
                Category.ELECTRONICS, BigDecimal.valueOf(650.00), 40));
        save(new Product("ACC-BUDS-6", "Wireless Earbuds",
                Category.ELECTRONICS, BigDecimal.valueOf(1100.00), 8));
        save(new Product("FSH-GAL-001", "Cotton Galabeya",
                Category.FASHION, BigDecimal.valueOf(900.00), 25));
        save(new Product("HOM-PAN-28", "Non-stick Frying Pan 28cm",
                Category.HOME_KITCHEN, BigDecimal.valueOf(1200.00), 3));
        save(new Product("HOM-SPC-RCK", "Wooden Spice Rack",
                Category.HOME_KITCHEN, BigDecimal.valueOf(750.00), 10));
        save(new Product("BTY-SUN-50", "Sun Cream SPF50",
                Category.BEAUTY, BigDecimal.valueOf(350.00), 0));
        save(new Product("BK-CAI-TRI", "The Cairo Trilogy",
                Category.BOOKS, BigDecimal.valueOf(450.00), 15));

    }
    @PreDestroy
    public void close() {
        System.out.println("Catalog closed");
    }
    @Override
    public List<Product> findAll() {

            return List.copyOf(products.values());

    }

    @Override
    public Optional<Product> findById(String id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(products.get(id));
    }
    @Override
    public Product save(Product product) {
        products.put(product.getSku(), product);
        return product;
    }

}
