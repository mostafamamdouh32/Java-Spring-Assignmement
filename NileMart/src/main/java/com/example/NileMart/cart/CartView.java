package com.example.NileMart.cart;

import com.example.NileMart.model.Customer;

import java.math.BigDecimal;
import java.util.List;

public record CartView(int number, Customer customer, List<LineView> lines,
                       int itemCount, BigDecimal subtotal, boolean empty) {}
