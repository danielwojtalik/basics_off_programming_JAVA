package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    String name;
    ProductCategory productCategory;
    BigDecimal productPrice;

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", productCategory=" + productCategory +
                ", productPrice=" + productPrice +
                '}';
    }
}


