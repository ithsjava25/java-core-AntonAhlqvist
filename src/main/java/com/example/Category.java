package com.example;

import java.util.HashMap;
import java.util.Map;

public final class Category {

    private static final Map<String, Category> CACHE = new HashMap<>();

    private String name;

    private Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        } else if (name.isBlank()) {
            throw new IllegalArgumentException("Category name can't be blank");
        }
        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1).trim();
        return CACHE.computeIfAbsent(formattedName, Category::new);
    }
}