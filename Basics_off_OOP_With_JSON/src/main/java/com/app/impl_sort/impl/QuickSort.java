package com.app.impl_sort.impl;

// this class provides implementation of Quick Sort

import com.app.compare.IComparsion;
import com.app.custom_exceptions.ExceptionCode;
import com.app.custom_exceptions.ExceptionInfo;
import com.app.custom_exceptions.MyException;
import com.app.impl_sort.ISort;

import java.util.Collections;
import java.util.List;

public class QuickSort implements ISort {
    @Override
    public void sort(List<Integer> numbersToSort, IComparsion methodOfComparison) {
        try {
            if (numbersToSort == null || methodOfComparison == null) {
                throw new NullPointerException("one of the varaibles aren't initialized");
            }
        } catch (Exception e) {
            throw new MyException(new ExceptionInfo(ExceptionCode.VALIDATION, e.getMessage()));
        }

        int n = numbersToSort.size();
        quickSortAlgorithm(numbersToSort, 0, n - 1, methodOfComparison);

    }

    public static void quickSortAlgorithm(List<Integer> elements, int left, int right, IComparsion ic) {
        if (left > right) {
            return;
        }

        int border = partitionList(elements, left, right, ic);
        quickSortAlgorithm(elements, left, border - 1, ic);
        quickSortAlgorithm(elements, border + 1, right, ic);
    }

    public static int partitionList(List<Integer> elements, int left, int right, IComparsion ic) {
        int pivotValue = elements.get(right);
        int border = left - 1;
        int i = left;
        while (i < right) {
            if (ic.compare(elements.get(i), pivotValue) < 0) {
                border++;
                if (border != i) {
                    Collections.swap(elements, border, i);
                }
            }
            i++;
        }
        border++;
        if (border != right) {
            Collections.swap(elements, border, right);
        }
        return border;
    }


}
