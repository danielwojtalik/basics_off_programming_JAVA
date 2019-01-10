package com.app.sort_list_of_rows;

// this class provides sort operation and save the results to new json file

import com.app.compare.IComparsion;
import com.app.custom_exceptions.ExceptionCode;
import com.app.custom_exceptions.ExceptionInfo;
import com.app.custom_exceptions.MyException;
import com.app.impl_sort.ISort;
import com.app.read_and_write_json.JsonWriter;
import com.app.read_and_write_json.Row;
import java.util.List;



public class SortAndSaveJsonFile {

    public static void sort(List<Row> elementsToSort) {
        try {
            for (Row row : elementsToSort) {
                IComparsion compareMethod = ReadCompareAndSortMethods.getComparisonMethod(row.getHowToCompare());
                ISort sortMethod = ReadCompareAndSortMethods.getSortMethod(row.getHowToSort());
                sortMethod.sort(row.getNumbersToSort(), compareMethod);
            }
        }catch (MyException e) {
            throw new MyException(new ExceptionInfo(ExceptionCode.ERROR_1, e.getMessage()));
        }
    }

    public static void saveToJson(List<Row> elementsToSave, String jsonFilename) {
        try {
            JsonWriter.saveRowsListToJson(elementsToSave, jsonFilename);
        } catch (MyException e) {
            throw new MyException(new ExceptionInfo(ExceptionCode.ERROR_1, e.getMessage()));
        }
    }
}
