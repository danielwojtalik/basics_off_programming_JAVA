package com.app;

import com.app.exceptions.MyException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            Numbers numbers = InputDataParser.readFromJson("Collections_and_maps_2/numbers.json");
            System.out.println("Print read list from JSON");
            System.out.println(numbers);
            System.out.println("_______________________________________________________________________________________________________________________________");
            Lists myList = new Lists(numbers);
            System.out.println("Print my list on the screen");
            System.out.println(myList);
            System.out.println("_______________________________________________________________________________________________________________________________");
            System.out.println("Check if this is perfect file - return wrong only if in the row is two or three same values");
            System.out.println("This is perfect file: " + myList.isPerfectFile());
            System.out.println("_______________________________________________________________________________________________________________________________");
            System.out.println("Check if this is perfect file - return wrong if the smallest element from avg is less than any element from min or if the smallest element from max is less than any element from avg");
            System.out.println("This is perfect file: " + myList.isPerfectFile2());
            System.out.println("_______________________________________________________________________________________________________________________________");
            System.out.println("Map with list of elements, which need to be delete to provide perfect input file \n" + myList.whichNeedToBeDeleted());
            System.out.println("_______________________________________________________________________________________________________________________________");
            System.out.println("How many elements divides by diff (max element from 1. list and min element from 2. list");
            System.out.println("Amount of elements: " + myList.divideByDiff());
            System.out.println("_______________________________________________________________________________________________________________________________");
            System.out.println("Longest nondecreasing sequence: " + myList.longestMonotonicList());
            System.out.println("_______________________________________________________________________________________________________________________________");
            System.out.println("Index of biggest and lowest diff:");
            System.out.println(myList.biggestDiffForIndex());
            System.out.println("_______________________________________________________________________________________________________________________________");
        } catch (MyException e) {
            e.printStackTrace();
            System.err.println(e.getExceptionInfo().getExceptionCode());
            System.err.println(e.getExceptionInfo().getLocalDateTime());
            System.err.println(e.getExceptionInfo().getExceptionMessage());
        }
    }
}