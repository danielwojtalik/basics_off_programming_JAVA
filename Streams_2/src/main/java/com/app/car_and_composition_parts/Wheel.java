package com.app.car_and_composition_parts;


import com.app.enum_types.KindOfWheel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder

public class Wheel {

    private String producerName;
    private int sizeOfWheel;
    private KindOfWheel kindOfWheel;
}

