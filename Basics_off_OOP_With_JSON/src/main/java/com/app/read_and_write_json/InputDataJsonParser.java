package com.app.read_and_write_json;

// Using Gson to read json file

import com.app.custom_exceptions.ExceptionCode;
import com.app.custom_exceptions.ExceptionInfo;
import com.app.custom_exceptions.MyException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class InputDataJsonParser {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Row> readFromJson(String jsonFileName) {
        try (FileReader fileReader = new FileReader(jsonFileName)) {
            Row[] rows = gson.fromJson(fileReader, Row[].class);
            return Arrays.asList(rows);
        } catch (Exception e) {
            throw new MyException(new ExceptionInfo(ExceptionCode.JSON, e.getMessage()));
        }
    }
}
