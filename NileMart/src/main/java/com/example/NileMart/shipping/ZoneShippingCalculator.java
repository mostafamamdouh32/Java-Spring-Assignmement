package com.example.NileMart.shipping;

import com.example.NileMart.model.Governorate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Primary
@Component
public class ZoneShippingCalculator implements  ShippingCalculator {
    @Override
    public BigDecimal calculate(Governorate g) {
        return g.getZone().getFee();
    }
}
