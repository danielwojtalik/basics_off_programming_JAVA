package com.app.impl_sort;

// interface which is use to different sort method implementation
import com.app.compare.IComparsion;
import java.util.List;

@FunctionalInterface
public interface ISort {
    void sort (List<Integer> numberToSort, IComparsion methodOfComparison);
}
