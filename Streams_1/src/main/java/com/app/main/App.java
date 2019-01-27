package com.app.main;

import com.app.converter.Car;
import com.app.converter.CarJSONParser;
import com.app.custom_exceptions.MyException;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("<SHOW ALL CAR OBJECTS FROM THE JSON FILE>");

        List<Car> carList;
        try {
            carList = CarJSONParser.readCarsFromJSON("Streams_1/target/listOfCars.json");

            for (Car c : carList) {
                System.out.println(c);

                System.out.println("__________________________________________________________________________________________________________________________________________________");

                System.out.println("<SHOW THE SORTED LIST OF OBJECTS SORTED BY BRAND>");
                Car.sortListByBrand(carList);
                System.out.println("__________________________________________________________________________________________________________________________________________________");

                System.out.println("<SHOW THE SORTED LIST OF OBJECTS SORTED BY PRICE OF>");
                Car.sortListByPrice(carList);
                System.out.println("__________________________________________________________________________________________________________________________________________________");

                System.out.println("<SHOW THE SORTED LIST OF OBJECTS SORTED BY ENGINE SIZE>");
                Car.sortListByEngineSize(carList);
                System.out.println("__________________________________________________________________________________________________________________________________________________");

                System.out.println("<SHOW THE SORTED LIST OF OBJECTS SORTED BY NUMBER OF COMPONENTS>");
                Car.sortListByNumberOfComponent(carList);
                System.out.println("__________________________________________________________________________________________________________________________________________________");

                System.out.println("<SHOW THE OBJECTS WITH MILEAGE LESS THEN GIVE VALUE>");
                List<Car> carListToEdit = new ArrayList(CarJSONParser.readCarsFromJSON("Streams_1/target/listOfCars.json"));
                Car.delateElementsWithTooBigMileage(carListToEdit, 32000);
                System.out.println("__________________________________________________________________________________________________________________________________________________");

                System.out.println("<SHOW THE LIST OF STINGS BRAND-MILEAGE>");
                List<String> brandMileage = Car.BrandMileageString(carList);
                System.out.println("__________________________________________________________________________________________________________________________________________________");

                System.out.println("<SHOW ME FULL STATISTIC OF MILEAGE, ENGINE_SIZE, PRICE>");
                Car.giveMeFullStatistic(carList);
                System.out.println("__________________________________________________________________________________________________________________________________________________");

                System.out.println("<SHOW ME THE MOST EXPENSIVE CAR>");
                Car mostExpensiveCar = Car.showMostExpensiveCar(carList); // this method return an Optional
                System.out.println(mostExpensiveCar);
                System.out.println("__________________________________________________________________________________________________________________________________________________");

                System.out.println("<CHECK IF THERE IS COMMON COMPONENT>");
                Car.isCommonComponent(carList, "ABS");
                System.out.println("__________________________________________________________________________________________________________________________________________________");

                System.out.println("<EDIT THE LIST OF COMPONENT IN ALPHABETICALLY ORDER>");
                Car.sortComponentsInList(carList);
            }
        } catch (MyException e) {
            System.err.println(e.getExceptionInfo().getCode());
            System.err.println(e.getExceptionInfo().getDescription());
            System.err.println(e.getExceptionInfo().getDateTime());
        }


    }
}