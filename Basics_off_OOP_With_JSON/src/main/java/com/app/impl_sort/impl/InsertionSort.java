package com.app.impl_sort.impl;

// this class provides implementation of Insertion Sort

import com.app.compare.IComparsion;
import com.app.custom_exceptions.ExceptionCode;
import com.app.custom_exceptions.ExceptionInfo;
import com.app.custom_exceptions.MyException;
import com.app.impl_sort.ISort;

import java.util.List;

public class InsertionSort implements ISort {
    @Override
    public void sort(List<Integer> numbersToSort, IComparsion methodOfComparison) {
        try {
            if (numbersToSort == null || methodOfComparison == null) {
                throw new NullPointerException("At least one of variable is NOT initialized");
            }
        } catch (Exception e) {
            throw new MyException(new ExceptionInfo(ExceptionCode.VALIDATION, e.getMessage()));
        }

        for (int i = 0; i < numbersToSort.size() - 1; i++) {
            Integer keyElement = numbersToSort.get(i + 1);
            int j = i;
            while (methodOfComparison.compare(keyElement, numbersToSort.get(j)) < 0 && j >= 0) {
                numbersToSort.set(j + 1, numbersToSort.get(j));
                numbersToSort.set(j, keyElement);
                if (j > 0) {
                    j--;
                }
            }
        }
    }
}
