package com.app;

import com.app.exceptions.ExceptionCode;
import com.app.exceptions.MyException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;

public class InputDataParser {


    private static Gson gson = (new GsonBuilder()).setPrettyPrinting().create();

    public static Numbers readFromJson(String jsonFileName) {
        try (FileReader fileReader = new FileReader(jsonFileName)) {
            Numbers numbers = gson.fromJson(fileReader, Numbers.class);
            return numbers;
        }catch(Exception e) {
            throw new MyException(ExceptionCode.JSON, e.getMessage());
        }
    }
}

