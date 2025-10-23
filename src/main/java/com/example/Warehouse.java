package com.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public final class Warehouse {

    private static final Map<String, Warehouse> INSTANCES = new HashMap<>();

    private final String name;
    private final List<Product> products = new ArrayList<>();
    private final Set<Product> changedProducts = new HashSet<>();

    private Warehouse(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public Optional<Product> getProductById(UUID id) {
        return products.stream()
                .filter(p -> p.uuid().equals(id))
                .findFirst();
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (getProductById(product.uuid()).isPresent()) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }
        products.add(product);
    }

    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        Product product = getProductById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
        product.price(newPrice);
        changedProducts.add(product);
    }

    public void remove(UUID id) {
        products.removeIf(p -> p.uuid().equals(id));
    }

    public void clearProducts() {
        products.clear();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public static Warehouse getInstance() {
        return getInstance("DefaultWarehouse");
    }

    public static Warehouse getInstance(String name) {
        return INSTANCES.computeIfAbsent(name, Warehouse::new);
    }

    public Set<Product> getChangedProducts() {
        return Collections.unmodifiableSet(changedProducts);
    }

    public List<Perishable> expiredProducts() {
        return products.stream()
                .filter(p -> p instanceof Perishable per && per.isExpired())
                .map(p -> (Perishable) p)
                .collect(Collectors.toList());
    }

    public List<Shippable> shippableProducts() {

        return products.stream()
                .filter(p -> p instanceof Shippable)
                .map(p -> (Shippable) p)
                .collect (Collectors.toList());
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        if (products.isEmpty()) {
            return new HashMap<>();
        }
        return products.stream()
                .collect(Collectors.groupingBy(Product::category));
    }
}