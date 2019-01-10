package com.app.sort_list_of_rows;

// this class provides control the method of sorting and comparing (it is depend from the date in input json file)

import com.app.compare.IComparsion;
import com.app.compare.impl.CommonCompare;
import com.app.compare.impl.CompareBySumOfDigits;
import com.app.custom_exceptions.ExceptionCode;
import com.app.custom_exceptions.ExceptionInfo;
import com.app.custom_exceptions.MyException;
import com.app.impl_sort.ISort;
import com.app.impl_sort.impl.BubbleSort;
import com.app.impl_sort.impl.InsertionSort;
import com.app.impl_sort.impl.QuickSort;


public class ReadCompareAndSortMethods {

    public static ISort getSortMethod(Integer sortMethodNumber) {
        try {
            if (sortMethodNumber == null) {
                throw new NullPointerException("SortMethodNumber is null");
            }
        } catch (Exception e) {
            throw new MyException(new ExceptionInfo(ExceptionCode.ERROR_1, e.getMessage()));
        }

        if (sortMethodNumber == 1) {
            return new BubbleSort();
        } else if (sortMethodNumber == 2) {
            return new InsertionSort();
        } else if (sortMethodNumber == 3) {
            return new QuickSort();
        } else try {
            throw new ArithmeticException("the valid value of sort number is 1 or 2 or 3. Please insert one of that value");
        } catch (Exception e) {
            throw new MyException(new ExceptionInfo(ExceptionCode.ERROR_1, e.getMessage()));
        }
    }

    public static IComparsion getComparisonMethod(Integer comparsionMethodNumber) {
        try {
            if (comparsionMethodNumber == null) {
                throw new NullPointerException("comparisonMethodNumber is null");
            }
        } catch (Exception e) {
            throw new MyException(new ExceptionInfo(ExceptionCode.ERROR_1, e.getMessage()));
        }

        if (comparsionMethodNumber == 1) {
            return new CompareBySumOfDigits();
        } else if (comparsionMethodNumber == 2) {
            return new CommonCompare();
        } else try {
            throw new ArithmeticException("the valid value of comparison number is 1 or 2. Please insert one of that value");
        } catch (Exception e) {
            throw new MyException(new ExceptionInfo(ExceptionCode.ERROR_1, e.getMessage()));
        }


    }


}

