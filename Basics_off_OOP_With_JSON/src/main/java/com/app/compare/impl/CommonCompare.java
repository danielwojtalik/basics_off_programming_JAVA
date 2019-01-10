package com.app.compare.impl;

import com.app.compare.IComparsion;

// this class provide the static method to compare two numbers by their value

public class CommonCompare implements IComparsion {
    @Override
    public int compare(Integer i1, Integer i2) {
        return Integer.compare(i1, i2);
    }
}
