package com.app.service;

import com.app.exceptions.MyException;

import java.util.stream.Collectors;

public class MenuService {

    private final String clientsJsonFilename = "Collections_and_maps_3/Clients.json";
    private final String productsJsonFilename = "Collections_and_maps_3/Products.json";
    private final String categoryJsonFilename = "Collections_and_maps_3/Category.json";
    private DataService dataService = new DataService(clientsJsonFilename, productsJsonFilename, categoryJsonFilename);
    private UserDataService userDataService = new UserDataService();
    private final String menuContent = getMessage();


    public void mainMenu() {
        while (true) {
            try {
                int option;
                option = userDataService.getInt(menuContent);
                switch (option) {
                    case 1:
                        showClientShopping();
                        break;
                    case 2:
                        showClientWithMostProducts();
                        break;
                    case 3:
                        showClientWhoSpentMostMoney();
                        break;
                    case 4:
                        dataService.showProductsSellingStatistics();
                        break;
                    case 5:
                        showCategoryPopularity();
                        break;
                    case 6:
                        userDataService.close();
                        return;
                }
            } catch (MyException e) {
                System.out.println(e.getExceptionInfo().getExceptionMessage());
                System.out.println(e.getExceptionInfo().getLocalDateTime());
            }
        }
    }

    private String getMessage() {
        return
                        "1. Show me clients map with products names only\n" +
                        "2. Which client buy most products?\n" +
                        "3: Which client spent most money?\n" +
                        "4: Get comparison with quantity of each product, most-bought and least-bought product list\n" +
                        "5: Get name of each category with their popularity \n" +
                        "6: To exit program";
    }

    //show me Map with client and their shopping
    void showClientShopping() {
        dataService.getClientProducts().forEach((k, v) -> System.out.println(k + " gets: " + v.stream().map(product -> product.getName()).collect(Collectors.toList())));
    }

    //show the client, who bought the most products?
    public void showClientWithMostProducts() {
        System.out.println(dataService.getClientWithMostProducts());
        System.out.println("______________________________________________________________________________________________________");
    }
    // show the client who spent most money
    public void showClientWhoSpentMostMoney() {
        System.out.println(dataService.getClientWhoSpentMostMoney());
        System.out.println("______________________________________________________________________________________________________");

    }
    // show category popularity
    public void showCategoryPopularity() {
        dataService.getCategoryPopularity().forEach((k, v) -> System.out.println(k + " = " + v));
        System.out.println("______________________________________________________________________________________________________");
    }
}
