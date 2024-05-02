package com.ms.product.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Data
@Component
public class ShoppingCart {
    private HashMap<UUID, Product> products = new HashMap<>();

    public ShoppingCart(HashMap<UUID, Product> products) {
        this.products = products;
    }
}
