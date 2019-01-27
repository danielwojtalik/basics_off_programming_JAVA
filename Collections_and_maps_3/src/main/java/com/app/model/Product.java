package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor

public class Product {

    private String name;
    private int quantity;
    private BigDecimal price;
    private int category;
}
