package com.example.NileMart;
import com.example.NileMart.common.Money;
import com.example.NileMart.model.Category;
import com.example.NileMart.model.Product;
import com.example.NileMart.pricing.PromotionalPricingService;
import com.example.NileMart.pricing.StandardPricingService;
import com.example.NileMart.repository.InMemoryProductRepository;
import com.example.NileMart.service.ProductService;


import java.util.HashSet;
import java.util.List;
public class ManualWiring {
    public static void main(String[] args) {
        InMemoryProductRepository repository = new InMemoryProductRepository();
        repository.seed();

        ProductService standard = new ProductService(repository, new StandardPricingService());
        System.out.println(standard.getPrice("ACC-CHG-20W", 2));

        ProductService promo = new ProductService(repository, new PromotionalPricingService(20));
        System.out.println(promo.getPrice("ACC-CHG-20W", 2));

        System.out.println(standard.groupByCategory().keySet());
        System.out.println(standard.search(p -> p.getStockQuantity() == 0));

        Product a = new Product("X-1", "A", Category.BOOKS, Money.of("10.00"), 1);
        Product b = new Product("X-1", "B", Category.BOOKS, Money.of("99.00"), 5);
        System.out.println(a.equals(b));
        System.out.println(new HashSet<>(List.of(a, b)).size());

        //**********************************************//
        //1. Which class contains new StandardPricingService()? Which class would you edit to switch to promotionalpricing?
        //
        //**********************************************//

    }
}