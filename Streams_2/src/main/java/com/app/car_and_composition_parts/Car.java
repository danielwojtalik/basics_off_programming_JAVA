package com.app.car_and_composition_parts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Car {

    private String brand;
    private BigDecimal price;
    private int mileage;
    private Engine engine;
    private CarBody carBody;
    private Wheel wheel;


}
