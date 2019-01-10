package com.app.json_reader_writer;

import com.app.car_and_composition_parts.Car;
import com.app.cutom_exception.ExceptionCode;
import com.app.cutom_exception.MyException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class InputDataJsonParser {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // method to retrieve list of Car class objects from json file
    public static List<Car> readFromJson(String jsonFileName) {

        try (FileReader reader = new FileReader(jsonFileName)) {
            Car[] cars = gson.fromJson(reader, Car[].class);
            return Arrays.asList(cars);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.READ_WRITE, "WRONG FILE NAME");
        }

    }

    // method to read single car
    public static Car readFromJsonSingleCar(String jsonFileName) {

        try (FileReader reader = new FileReader(jsonFileName)) {
            Car car = gson.fromJson(reader, Car.class);
            return car;
        } catch (Exception e) {
            throw new MyException(ExceptionCode.READ_WRITE, "WRONG FILE NAME");
        }
    }
}
