package com.app.find_second_biggest_element;

// this class find for me the second biggest element in sorted list of Integer values.
// When there isn't second biggest element, method "secondBiggestElement" return "null" (for example 1,1,1,1,1).

import com.app.custom_exceptions.ExceptionCode;
import com.app.custom_exceptions.ExceptionInfo;
import com.app.custom_exceptions.MyException;
import com.app.read_and_write_json.Row;
import java.util.ArrayList;
import java.util.List;

public class ListOfSecondElements {
    public static Integer secondBiggestElement(Row row) {

        List<Integer> secondBiggestElement = new ArrayList<>();
        Integer secondLargest = row.getNumbersToSort().get(0);
        Integer largest = row.getNumbersToSort().get(0);

        if (row.getNumbersToSort().size() < 2){
            throw new MyException(new ExceptionInfo(ExceptionCode.ERROR_1, "Invalid Input"));
        }

        for (int i = 1; i < row.getNumbersToSort().size(); i++) {
            if (row.getNumbersToSort().get(0) == row.getNumbersToSort().get(i)) {
                if (row.getNumbersToSort().get(row.getNumbersToSort().size() - 1) == row.getNumbersToSort().get(0))
                    return null;
            }
            i++;
        }
        for (int i = 1; i < row.getNumbersToSort().size(); i++) {
            if (row.getNumbersToSort().get(i) > largest) {
                secondLargest = largest;
                largest = row.getNumbersToSort().get(i);
            }
            // I do not need this block, because i have sorted first
//            else if (row.getNumbersToSort().get(i) > secondLargest && row.getNumbersToSort().get(i) != largest) {
//                secondLargest = row.getNumbersToSort().get(i);
//            }
        }
        return secondLargest;
    }

    public static List<Integer> listOfSecondBiggestElements(List<Row> listOfRows){
        List<Integer> integerList = new ArrayList<>();
        for (Row row:listOfRows) {
            integerList.add(secondBiggestElement(row));
        }
        return integerList;
    }
}



