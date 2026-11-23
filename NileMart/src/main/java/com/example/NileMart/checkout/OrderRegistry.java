package com.example.NileMart.checkout;

import com.example.NileMart.common.Money;
import com.example.NileMart.model.Order;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class OrderRegistry {

    private final Clock clock;
    private final AtomicInteger counter = new AtomicInteger(0);
    // CopyOnWriteArrayList: many reads (order pages), few writes (one per checkout), safe across threads.
    private final List<Order> orders = new CopyOnWriteArrayList<>();

    public OrderRegistry(Clock clock) {
        this.clock = clock;
    }

    public String nextNumber() {
        return String.format("NM-%s-%05d",
                LocalDate.now(clock).format(DateTimeFormatter.BASIC_ISO_DATE),
                counter.incrementAndGet());
    }

    public void save(Order order) {
        orders.add(order);
    }

    public Optional<Order> findByNumber(String orderNumber) {
        return orders.stream()
                .filter(o -> o.orderNumber().equals(orderNumber))
                .findFirst();
    }

    public List<Order> findAll() {
        List<Order> newestFirst = new ArrayList<>(orders);
        Collections.reverse(newestFirst);
        return List.copyOf(newestFirst);
    }

    public BigDecimal revenue() {
        return orders.stream()
                .map(Order::total)
                .reduce(Money.ZERO, BigDecimal::add);
    }

    @PreDestroy
    public void endOfDay() {
        System.out.println("End of day: " + orders.size() + " orders, revenue "
                + Money.format(revenue()) + " EGP.");
    }
}