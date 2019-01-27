package com.app.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.app.custom_exceptions.ExceptionCode;
import com.app.custom_exceptions.ExceptionInfo;
import com.app.custom_exceptions.MyException;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class CarJSONParser {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Car> readCarsFromJSON(String jsonFileName) {

        try (FileReader fileReader = new FileReader(jsonFileName)) {
            Car[] cars = gson.fromJson(fileReader, Car[].class);
            return Arrays.asList(cars);
        }  catch (Exception e) {
        throw new MyException(new ExceptionInfo(ExceptionCode.JSON, e.getMessage()));
        }
    }
}
