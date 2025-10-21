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

    @Override
    public BigDecimal calculateShippingCost() {

        if (weight.compareTo(BigDecimal.valueOf(5)) > 0) {
            return BigDecimal.valueOf(128);
        } else {
            return BigDecimal.valueOf(79);
        }
    }

    @Override
    public String productDetails() {
        return ("Something");
    }
}