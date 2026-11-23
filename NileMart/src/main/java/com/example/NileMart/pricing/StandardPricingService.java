
package com.example.NileMart.pricing;

import com.example.NileMart.common.Money;
import com.example.NileMart.model.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Primary
public class StandardPricingService implements PricingService {

    @Override
    public BigDecimal getPrice(Product product, int quantity) {
        return Money.scale(product.getPrice()
                .multiply(BigDecimal.valueOf(quantity)));


    }
}
