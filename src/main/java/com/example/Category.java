package com.example;

import java.util.HashMap;
import java.util.Map;

public final class Category {

    private static final Map<String, Category> CACHE = new HashMap<>();

    private String name;

    private Category(String name) {
        this.name = name;
    }

    public static Category of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        String normalized = name.trim();
        return CACHE.computeIfAbsent(normalized, Category::new);
    }
}