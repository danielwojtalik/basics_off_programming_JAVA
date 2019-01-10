package com.app.json_reader_writer;

import com.app.car_and_composition_parts.Car;
import com.app.cutom_exception.ExceptionCode;
import com.app.cutom_exception.MyException;
import com.app.main.CarShowroom;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


public class WriteToJson {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // method to write json input file to inividual files with single car
    public static List<String> writeCarToJson(List<Car> carList) {
        List<String> jsonFileNameList = new ArrayList<>();
        for (Car element : carList) {
            String fileName = "Streams_2/target/single_car_output/" + element.getBrand() + "_" + element.getPrice() + "zl" + ".json";
            try (FileWriter fileWriter = new FileWriter(fileName)) {
                gson.toJson(element, fileWriter);
            } catch (Exception e) {
                throw new MyException(ExceptionCode.READ_WRITE, "WRONG FILE NAME");
            }
            jsonFileNameList.add(fileName);
        }
        return jsonFileNameList;
    }
    // method to write cars from one of car showrooms with specific format - brand and price
    public static void writeCarPriceAndBrandToJson(CarShowroom carShowroom, String jsonFilename) {
        try (FileWriter fileWriter = new FileWriter(jsonFilename)) {
            List<String> brandAndPrice = new ArrayList<>();
            for (Car car : carShowroom.getCarSet()) {
                brandAndPrice.add(car.getBrand() + ": " + car.getPrice() + "zl");
            }
            gson.toJson(brandAndPrice, fileWriter);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.READ_WRITE, "WRONG FILE NAME");
        }
    }
}


