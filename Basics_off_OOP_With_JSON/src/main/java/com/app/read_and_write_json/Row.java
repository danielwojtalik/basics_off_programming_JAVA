package com.app.read_and_write_json;

// this class provides compatibility with json file to read/write. There I use also lombok to get getters, setters and constructors

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder

public class Row {

    private int howToSort;
    private int howToCompare;
    private List<Integer> numbersToSort;

}
