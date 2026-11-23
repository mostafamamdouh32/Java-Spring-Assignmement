package com.example.NileMart.checkout;

import com.example.NileMart.common.Money;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TaxCalculator {
    private final BigDecimal rate;

    public TaxCalculator(@Value("${store.vat-rate}") BigDecimal rate) {
        this.rate = rate;
    }
    public BigDecimal vat(BigDecimal subtotal ) {
        return Money.scale(subtotal.multiply(rate));
    }
}
