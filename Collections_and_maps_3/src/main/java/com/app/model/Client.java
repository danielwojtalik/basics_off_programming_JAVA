package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Data

public class Client {

    private String name;
    private String surname;
    private int age;
    private BigDecimal accountBalance;
    private List<Integer> preferences;
}
