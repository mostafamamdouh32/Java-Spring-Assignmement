package com.example.NileMart.model;

import com.example.NileMart.exception.StoreException;

public record Customer(String fullName, String phone, String email) {

    public Customer {
        if (fullName == null || fullName.isBlank()) {
            throw new StoreException("The customer name is required.");
        }
        if (phone == null || !phone.matches("01[0125][0-9]{8}")) {
            throw new StoreException("The phone must have 11 digits and start with 010, 011, 012 or 015.");
        }
        if (email == null || email.isBlank()) {
            email = null;
        } else if (!email.contains("@")) {
            throw new StoreException("The email must contain @.");
        }
    }
}