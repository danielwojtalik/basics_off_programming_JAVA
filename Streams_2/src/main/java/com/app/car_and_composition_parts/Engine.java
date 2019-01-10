package com.app.car_and_composition_parts;

import com.app.enum_types.EngineType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder

public class Engine {

    private EngineType engineType;
    private BigDecimal engineSize;
    private BigDecimal fuelConsumption;

}


