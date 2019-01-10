package com.app.read_and_write_json;

// this class provides save date in json file format

import com.app.custom_exceptions.ExceptionCode;
import com.app.custom_exceptions.ExceptionInfo;
import com.app.custom_exceptions.MyException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.util.List;

public class JsonWriter {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public static void saveRowsListToJson(List<Row> rows, String jsonFilename){
        try(FileWriter fileWriter = new FileWriter(jsonFilename)){
            gson.toJson(rows, fileWriter);
        }
        catch (Exception e){
            throw new MyException(new ExceptionInfo(ExceptionCode.JSON, e.getMessage()));
        }
    }


}
