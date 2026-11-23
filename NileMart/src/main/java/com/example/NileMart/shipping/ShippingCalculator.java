package com.example.NileMart.shipping;

import com.example.NileMart.model.Governorate;

import java.math.BigDecimal;

public interface ShippingCalculator {
    BigDecimal calculate(Governorate g);
}
