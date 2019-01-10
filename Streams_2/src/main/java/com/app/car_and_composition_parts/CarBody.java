package com.app.car_and_composition_parts;

import com.app.enum_types.CarEquipment;
import com.app.enum_types.ColourOfBody;
import com.app.enum_types.KindOfBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class CarBody {
    private ColourOfBody colourOfBody;
    private KindOfBody kindOfBody;
    List<CarEquipment> carEquipmentList;



}


