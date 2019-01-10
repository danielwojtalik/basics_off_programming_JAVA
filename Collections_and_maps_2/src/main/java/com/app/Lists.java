package com.app;

import com.app.exceptions.ExceptionCode;
import com.app.exceptions.MyException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;


@AllArgsConstructor
@Data
public class Lists {


    private List<Integer> minValues = new ArrayList();
    private List<Integer> avgValues = new ArrayList();
    private List<Integer> maxValues = new ArrayList();


    Lists(Numbers numbers) {
        if (numbers.getNumbersList().size() < 2) {
            throw new MyException(ExceptionCode.VALIDATION, "You need at least two elements in every list of numbers");
        }

        for (List<Integer> list : numbers.getNumbersList()) {

            this.minValues.add((list.stream().sorted().collect(Collectors.toList())).get(0));
            this.avgValues.add((list.stream().sorted().collect(Collectors.toList())).get(1));
            this.maxValues.add((list.stream().sorted().collect(Collectors.toList())).get(2));

        }

    }

    public boolean isPerfectFile() {
        boolean perfect = true;

        for (int i = 0; i < this.minValues.size() -1; ++i) {
            if (this.minValues.get(i) >= this.avgValues.get(i) || this.avgValues.get(i) >= this.maxValues.get(i)) {
                perfect = false;
                break;
            }
        }

        return perfect;
    }

    public boolean isPerfectFile2() {
        boolean perfect = true;
        int a = Collections.min(this.avgValues);
        int b = Collections.min(this.maxValues);

        for (Integer element : this.minValues) {
            if (element > a) {
                perfect = false;
                break;
            }
        }

        for (Integer element : this.avgValues) {
            if (element > b) {
                perfect = false;
                break;
            }
        }
        return perfect;
    }

    public Map<String, List<Integer>> whichNeedToBeDeleted() {

        Map<String, List<Integer>> mapOfElementsToDelete = new LinkedHashMap();
        List<Integer> firstListDeleteElement = new ArrayList();
        List<Integer> secondListDeleteElement = new ArrayList();
        int a = Collections.min(this.avgValues);
        int b = Collections.min(this.maxValues);

        for (Integer element : this.minValues) {
            if (element > a) {
                firstListDeleteElement.add(element);
            }
        }

        mapOfElementsToDelete.put("From 1. List:", firstListDeleteElement);

        for (Integer element : this.avgValues) {
            if (element > b) {
                secondListDeleteElement.add(element);
            }
        }

        mapOfElementsToDelete.put("From 2. List:", secondListDeleteElement);

        return mapOfElementsToDelete;
    }

    public int divideByDiff() {
        int max1 = Collections.max(this.minValues);
        int min2 = Collections.min(this.avgValues);
        int result = max1 - min2;
        System.out.println(result);
        int counter = 0;
        if (result != 0) {

            for (Integer element : this.maxValues) {
                if (element % result == 0) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public String longestMonotonicList() {
        int a = 1;
        List<Integer> monotonic1 = new ArrayList();
        List<Integer> monotonic2 = new ArrayList();
        List<Integer> monotonic3 = new ArrayList();

        int max1 = 1;
        int max2 = 1;
        int max3 = 1;


        for (int i = 0; i < this.minValues.size() - 1; ++i) {
            if (this.minValues.get(i) <= this.minValues.get(i + 1)) {
                ++a;
                monotonic1.add(a);
            } else {
                a = 1;
            }
        }
        max1 = Collections.max(monotonic1);
        a = 1;

        for (int i = 0; i < this.avgValues.size() - 1; ++i) {
            if (this.avgValues.get(i) <= this.avgValues.get(i + 1)) {
                ++a;
                monotonic2.add(a);
            } else {
                a = 1;
            }
        }

        max2 = Collections.max(monotonic2);
        a = 1;

        for (int i = 0; i < this.maxValues.size() - 1; ++i) {
            if ((Integer) this.maxValues.get(i) <= (Integer) this.maxValues.get(i + 1)) {
                ++a;
                monotonic3.add(a);
            } else {
                a = 1;
            }
        }

        max3 = Collections.max(monotonic3);
        if (max1 > max2 && max1 > max3) {
            return "PIERWSZA";
        } else if (max2 > max1 && max2 > max3) {
            return "DRUGA";
        } else if (max3 > max1 && max3 > max2) {
            return "TRZECIA";
        } else {
            return "There is more than one list with the longest nondecreasing sequence";
        }
    }

    public Map<String, Integer> biggestDiffForIndex() {

        List<Integer> listOfDiff = new ArrayList();
        Map<String, Integer> mapOfDiff = new LinkedHashMap();
        List<Integer> minValuesSorted = this.minValues.stream().sorted().collect(Collectors.toList());
        System.out.println(minValuesSorted);
        List<Integer> avgValuesSorted = this.avgValues.stream().sorted().collect(Collectors.toList());
        System.out.println(avgValuesSorted);
        List<Integer> maxValuesSorted = this.maxValues.stream().sorted().collect(Collectors.toList());
        System.out.println(maxValuesSorted);

        for (int i = 0; i < minValuesSorted.size(); ++i) {
            listOfDiff.add(maxValuesSorted.get(i) - avgValuesSorted.get(i) - minValuesSorted.get(i));
        }

        Integer maxDiffIndex = listOfDiff.indexOf(Collections.max(listOfDiff));
        mapOfDiff.put("Index of max diff: ", maxDiffIndex);
        Integer minDiffIndex = listOfDiff.indexOf(Collections.min(listOfDiff));
        mapOfDiff.put("Index of min diff: ", minDiffIndex);
        return mapOfDiff;
    }
}
