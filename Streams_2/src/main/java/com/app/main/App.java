package com.app.main;

import com.app.car_and_composition_parts.Car;
import com.app.cutom_exception.MyException;
import com.app.json_reader_writer.InputDataJsonParser;
import com.app.json_reader_writer.WriteToJson;

import java.util.List;

public class App {
    public static void main(String[] args) {

        // I use this a method to fetch the data from json file and create from it the collection (list) of Car objects - DEMO
        System.out.println("<READ CARS FROM JSON FILE>");
        List<Car> listOfCars;
        try {
            listOfCars = InputDataJsonParser.readFromJson("Streams_2/target/CarDataInputFile.json");
            System.out.println(".............");
            System.out.println("__________________________________________________________________________________________________________________________________");


            // There is a method to write every car from the list to the individual json file and return name to this file - DEMO
            System.out.println("<WRITE CAR OBJECT TO SINGLE JSON FILE WITH OWN NAME>");
            List<String> listOfJsonFileName = WriteToJson.writeCarToJson(listOfCars);
            listOfJsonFileName.forEach(element -> System.out.println(element));
            System.out.println("__________________________________________________________________________________________________________________________________");
        } catch (MyException e) {
            e.printStackTrace();
            System.err.println(e.getExceptionInfo().getExceptionCode());
            System.err.println(e.getExceptionInfo().getExceptionMassage());
            System.err.println(e.getExceptionInfo().getLocalDateTime());
        }
    }
}
