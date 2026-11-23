package com.example.NileMart.model;

import com.example.NileMart.common.Money;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Collectors;

public enum Zone {

    GREATER_CAIRO(Money.of("50.00"), true),
    ALEX_DELTA(Money.of("65.00"), true),
    CANAL(Money.of("70.00"), true),
    UPPER_EGYPT(Money.of("85.00"), true),
    REMOTE(Money.of("120.00"), false);
    private final BigDecimal fee;
    private final boolean freeShippingEligible;

    Zone(BigDecimal fee, boolean freeShippingEligible) {
        this.fee = fee;
        this.freeShippingEligible = freeShippingEligible;
    }
    public BigDecimal getFee() {
        return fee;
    }

    public boolean isFreeShippingEligible() {
        return freeShippingEligible;
    }
    public EnumSet<Governorate> governorates() {
        return Arrays.stream(Governorate.values())
                .filter(g -> g.getZone() == this)
                .collect(Collectors.toCollection(
                        () -> EnumSet.noneOf(Governorate.class)));
    }
}
