package com.app.main;


import com.app.exceptions.MyException;
import com.app.model.Client;
import com.app.model.Product;
import com.app.service.KnapsackProblemService;
import com.app.service.DataService;
import com.app.service.MenuService;

import java.util.*;

public class App {
    public static void main(String[] args) {

        new MenuService().mainMenu();
    }
}
