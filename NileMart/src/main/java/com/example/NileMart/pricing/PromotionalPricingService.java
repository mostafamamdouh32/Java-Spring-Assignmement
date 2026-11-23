package com.example.NileMart.pricing;

import com.example.NileMart.common.Money;
import com.example.NileMart.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PromotionalPricingService implements PricingService {

    private final int discountAmount;

    public PromotionalPricingService(@Value("${store.discount-amount}") int discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public BigDecimal getPrice(Product product, int quantity) {
        BigDecimal price = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        BigDecimal discount = BigDecimal.valueOf(discountAmount)
                .divide(BigDecimal.valueOf(100));
        return Money.scale(price.subtract(price.multiply(discount)));
    }
}
