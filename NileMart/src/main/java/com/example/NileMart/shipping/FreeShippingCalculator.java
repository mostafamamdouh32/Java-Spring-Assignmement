package com.example.NileMart.shipping;

import com.example.NileMart.common.Money;
import com.example.NileMart.model.Governorate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("freeShipping")
public class FreeShippingCalculator implements ShippingCalculator {
    @Override
    public BigDecimal calculate(Governorate g) {
        return Money.ZERO;
    }
}
