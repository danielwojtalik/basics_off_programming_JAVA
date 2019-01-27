package com.app.main;

import com.app.model.ProductCategory;
import com.app.my_exceptions.MyException;

public class Main {

    public static void main(String[] args) {

        try {
            DataProcess sh = new DataProcess("Collections_and_maps_1/Shopping_1.json");

            System.out.println("<SHOW ME ALL CLIENTS WITH THEIR ALL PRODUCTS AND QUANTITY OF THOSE PRODUCTS>");
            sh.getClientShoppingMap().forEach((k, v) -> System.out.println("key: " + k + "value: " + v));
            System.out.println("__________________________________________________________________________________________");
            System.out.println("<SHOW ME BEST CLIENT, WHO HAS SPENT MOST MONEY ON ALL PRODUCTS>");
            System.out.println(sh.theBestClient());
            System.out.println("__________________________________________________________________________________________");
            System.out.println("<SHOW ME BEST CLIENT, WHO HAS SPENT MOST MONEY IN GIVEN CATEGORY>");
            System.out.println(sh.theBestClientInCategory(ProductCategory.LITERATURE));
            System.out.println("__________________________________________________________________________________________");
            System.out.println("<SHOW ME QUANTITIES OF ALL PRODUCTS CATEGORY GROUPED BY CLIENT AGE>");
            System.out.println(sh.comparisionOfSoldCategoriesInAgeOfClient());
            System.out.println("__________________________________________________________________________________________");
            System.out.println("<SHOW ME QUANTITIES OF ALL PRODUCTS CATEGORY GROUPED BY CLIENT AGE ORDERED DESC BY QUANTITY OF PRODUCT IN EVERY CATEGORY>");
            System.out.println(DataProcess.sortCategoriesByQuantity(sh.comparisionOfSoldCategoriesInAgeOfClient()));
            System.out.println("__________________________________________________________________________________________");
            System.out.println("<SHOW ME  LIST OF PRODUCT CATEGORY WHICH ARE BEST SOLD IN EVERY AGE OF CLIENTS>");
            System.out.println(DataProcess.mapOfClientsAgeAndListOfBestCategory(sh.comparisionOfSoldCategoriesInAgeOfClient()));
            System.out.println("__________________________________________________________________________________________");
            System.out.println("<SHOW ME STATISTICS>");
            sh.avgPriceInCategory();
            System.out.println("__________________________________________________________________________________________");
//            System.out.println("<SHOW ME THE MOST OFTEN BOUGHT CATEGORY FROM CLIENTS>");
//            sh.oftenBoughtCategoryByClients();
//            System.out.println("__________________________________________________________________________________________");
            System.out.println("<SHOW ME CLIENTS WHICH HAVE BOUGHT MOST OFTEN PRODUCTS FROM EVERY CATEGORY>");
            System.out.println(sh.oftenBoughtCategoryByClients());
            System.out.println("__________________________________________________________________________________________");
            System.out.println("<SHOW ME ALL CLIENTS WITH ACCOUNT BALANCE AFTER BUYING ALL PRODUCTS IN THEIR LIST - CAN THEY AFFORD ALL SHOPPING? >");
            System.out.println(sh.canBuyAllProducts());
            System.out.println("__________________________________________________________________________________________");
        } catch (MyException e) {
            e.printStackTrace();
            System.out.println(e.getExceptionInfo().getExceptionCode());
            System.out.println(e.getExceptionInfo().getDescription());
            System.out.println(e.getExceptionInfo().getExceptionTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
