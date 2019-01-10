package com.app.compare.impl;

import com.app.compare.IComparsion;

// this class provides static method to compare two numbers based on sum of their digits

public class CompareBySumOfDigits implements IComparsion {
    @Override
    public int compare(Integer i1, Integer i2) {
        return Integer.compare(sumOfDigits(i1), sumOfDigits(i2));
    }

    public static Integer sumOfDigits(Integer integer) {
        return String.valueOf(integer)
                .chars()
                .map(Character::getNumericValue)
                .sum();
    }

}
