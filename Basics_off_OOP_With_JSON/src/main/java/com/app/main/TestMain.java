package com.app.main;

// use only for my learning

import com.app.compare.IComparsion;
import com.app.compare.impl.CompareBySumOfDigits;
import com.app.impl_sort.ISort;
import com.app.impl_sort.impl.BubbleSort;

import java.util.ArrayList;
import java.util.List;

public class TestMain {


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(33);
        list.add(22);
        list.add(55);
        list.add(11);
        list.add(66);

        ISort sort = new BubbleSort();
        IComparsion compare = new CompareBySumOfDigits();
        sort.sort(list, compare);

        for (Integer element : list) {
            System.out.println(element);
        }
    }
}
