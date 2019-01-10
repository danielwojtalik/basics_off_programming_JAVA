package com.app.main;

import com.app.custom_exceptions.MyException;
import com.app.find_second_biggest_element.ListOfSecondElements;
import com.app.read_and_write_json.Row;
import com.app.read_and_write_json.InputDataJsonParser;
import com.app.sort_list_of_rows.SortAndSaveJsonFile;

import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Row> rowList; // declaring a list of Row - Row is the object which is read from json File
        try {
            rowList = InputDataJsonParser.readFromJson("Basics_off_OOP_With_JSON/target/InputFile.json"); // reading date from input json File
            SortAndSaveJsonFile.sort(rowList); // sort elements using one of three sort algorithm and two different comparision algorithm
            SortAndSaveJsonFile.saveToJson(rowList,"Basics_off_OOP_With_JSON/target/OutputFile.json"); // save sorted elements in new json File
            List<Integer> listOfSecondBiggestElement = ListOfSecondElements.listOfSecondBiggestElements(rowList);  // there is a list with the second biggest element in every row
            System.out.println(listOfSecondBiggestElement);
        }catch (MyException e){ // My exception - I can manage for example to hide description to user, by comment System.err.println(e.getExceptionInfo().getDescription());
            System.err.println(e.getExceptionInfo().getCode());
            System.err.println(e.getExceptionInfo().getDescription());
            System.err.println(e.getExceptionInfo().getDateTime());
        }





    }
}
