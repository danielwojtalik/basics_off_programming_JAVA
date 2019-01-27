package com.app.service;

import com.app.exceptions.MyException;

public class MenuService {

    private final String clientsJsonFilename = "Collections_and_maps_3/Clients.json";
    private final String productsJsonFilename = "Collections_and_maps_3/Products.json";
    private final String categoryJsonFilename = "Collections_and_maps_3/Category.json";
    private DataService dataService = new DataService(clientsJsonFilename, productsJsonFilename, categoryJsonFilename);
    private UserDataService userDataService = new UserDataService();
    private final String menuContent = getMessage();


    public void mainMenu() {
        boolean loop = true;
        while (loop) {
            try {
                int option;
                option = userDataService.getInt(menuContent);
                switch (option) {
                    case 1:
                        dataService.showClientShopping();
                    case 2:
                        dataService.clientWithMostProducts();
                        break;
                    case 3:
                        dataService.clientSpentMostMoney();
                        break;
                    case 4:
                        dataService.showProductsSellingStatistics();
                        break;
                    case 5:
                        dataService.showCategoryPopularity();
                        break;
                    case 6:
                        userDataService.close();
                        loop = false;
                        break;
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
}
