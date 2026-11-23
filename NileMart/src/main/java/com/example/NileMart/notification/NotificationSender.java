package com.example.NileMart.notification;

import com.example.NileMart.model.Customer;

public interface NotificationSender {
    void send(Customer customer, String message);

    default boolean supports(Customer customer) {
        return true;
    }
}