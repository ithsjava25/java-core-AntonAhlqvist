package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product implements Shippable {

    private final int warrantyMonths;
    private final BigDecimal weight;

    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price, int warrantyMonths, BigDecimal weight) {

        super(id, name, category, price);

        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }

    @Override
    public BigDecimal weight() {
        return weight;
    }
}