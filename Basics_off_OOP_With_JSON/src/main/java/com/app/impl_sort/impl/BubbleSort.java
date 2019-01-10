package com.app.impl_sort.impl;

// this class provides the most efficient Bubble Sort algorithm

import com.app.compare.IComparsion;
import com.app.custom_exceptions.ExceptionCode;
import com.app.custom_exceptions.ExceptionInfo;
import com.app.custom_exceptions.MyException;
import com.app.impl_sort.ISort;

import java.util.List;

public class BubbleSort implements ISort {
    @Override
    public void sort(List<Integer> numberToSort, IComparsion methodOfComparison) {

        try{
            if (numberToSort == null || methodOfComparison == null) {
                throw new NullPointerException("THERE IS NULL REFERENCE OF VARIABLE NUMBERTOSORT OR METHODCOMPARSION");
            }

        }catch (Exception e){
            throw new MyException(new ExceptionInfo(ExceptionCode.VALIDATION, e.getMessage()));
        }

        int temp;
        boolean isSorted;
        for (int i = numberToSort.size() - 1; i > 0; i--) {
            isSorted = true;
            for (int j = 0; j < i; j++) {

                if (methodOfComparison.compare(numberToSort.get(j), numberToSort.get(j + 1)) > 0) {
                    temp = numberToSort.get(j);
                    numberToSort.set(j, numberToSort.get(j + 1));
                    numberToSort.set(j + 1, temp);
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }

        }

    }
}
