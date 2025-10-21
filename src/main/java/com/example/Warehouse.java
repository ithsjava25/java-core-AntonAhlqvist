package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class Warehouse {

    private static final Map<String, Warehouse> INSTANCES = new HashMap<>();

    private final String name;
    private final List<Product> products = new ArrayList<>();

    private Warehouse(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public static Warehouse getInstance() {
        return getInstance();
    }

    public static Warehouse getInstance(String name) {
        return INSTANCES.computeIfAbsent(name, Warehouse::new);
    }

    public List<Shippable> shippableProducts() {

        return products.stream()
                .filter(p -> p instanceof Shippable)
                .map(p -> (Shippable) p)
                .collect (Collectors.toList());
    }
}