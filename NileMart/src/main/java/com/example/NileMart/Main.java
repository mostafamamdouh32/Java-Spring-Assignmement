package com.example.NileMart;

import com.example.NileMart.cart.ShoppingCart;
import com.example.NileMart.common.Money;
import com.example.NileMart.config.AppConfig;
import com.example.NileMart.model.Customer;
import com.example.NileMart.model.Governorate;
import com.example.NileMart.model.Zone;
import com.example.NileMart.notification.EmailNotificationSender;
import com.example.NileMart.notification.SmsNotificationSender;
import com.example.NileMart.service.ProductService;
import com.example.NileMart.shipping.ShippingQuoteService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {

//            ProductService service = context.getBean(ProductService.class);
//            System.out.println(service.getPrice("ACC-CHG-20W", 2));   // 1300.00
//
//            System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
//            for (String name : context.getBeanDefinitionNames()) {
//                System.out.println(name);
//            }
//            System.out.println(Zone.GREATER_CAIRO.governorates());
//            System.out.println(Zone.REMOTE.governorates());
//            System.out.println(Governorate.values().length);
//            ShippingQuoteService shipping = context.getBean(ShippingQuoteService.class);
//            System.out.println(shipping.quote(Governorate.CAIRO, Money.of("5000.00")));
//            System.out.println(shipping.quote(Governorate.CAIRO, Money.of("4999.99")));
//            System.out.println(shipping.quote(Governorate.MATROUH, Money.of("999999.00")));
//            Customer mona = new Customer("Mona Adel", "01012345678", "mona@example.com");
//            new SmsNotificationSender().send(mona, "Hello");
//            new EmailNotificationSender().send(mona, "Hello");
//            Customer noEmail = new Customer("", "123", "x");
//            new EmailNotificationSender().send(noEmail, "Hello");
            ShoppingCart cart = context.getBean(ShoppingCart.class);
            cart.open(1, new Customer("Mona Adel", "01012345678", null));
            cart.addItem("ACC-CHG-20W", 2);
            cart.addItem("FSH-GAL-001", 1);
            System.out.println(cart.view());
            System.out.println(context.getBean(ProductService.class) == context.getBean(ProductService.class)); // true
            System.out.println(context.getBean(ShoppingCart.class) == context.getBean(ShoppingCart.class));     // false
// subtotal 2200.00, itemCount 3, empty false
        }
    }
}