package com.app;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Numbers {

    private List<List<Integer>> numbersList;
}
