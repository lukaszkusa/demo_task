package com.example.demo.product;

import java.time.ZonedDateTime;
import java.util.UUID;

public class ProductFactory {

    public static Product buildProduct(final String name, final double price){
        return Product.builder()
                .id(UUID.randomUUID())
                .createdAt(ZonedDateTime.now())
                .lastModifiedAt(ZonedDateTime.now())
                .name(name)
                .price(price)
                .build();

    }

    public static ProductCreateOrder buildProductCreateOrder(final String name, final double price){
        return ProductCreateOrder.builder()
                .name(name)
                .price(price)
                .build();
    }
}
