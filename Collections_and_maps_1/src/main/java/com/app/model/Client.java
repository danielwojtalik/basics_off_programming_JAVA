package com.app.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor


public class Client {
    private String name;
    private String surname;
    private int age;
    private BigDecimal bankBalance;



}
