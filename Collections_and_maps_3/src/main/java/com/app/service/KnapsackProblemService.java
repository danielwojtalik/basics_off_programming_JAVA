package com.app.service;

import com.app.converter.ClientJsonConverter;
import com.app.converter.ProductJsonConverter;
import com.app.exceptions.ExceptionCode;
import com.app.exceptions.MyException;
import com.app.model.Client;
import com.app.model.Product;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class KnapsackProblemService {

    private List<Client> clientList;
    private List<Product> productList;


    public KnapsackProblemService(String clientJsonFile, String productJsonFile) {

        ClientJsonConverter clientJsonConverter = new ClientJsonConverter(clientJsonFile);
        try {
            this.clientList = clientJsonConverter.fromJson().get();
        } catch (MyException e) {
            System.err.println("EXCEPTION FROM CLIENT JSON FILE PARSER");
            System.err.println(e.getExceptionInfo().getExceptionMessage());
            System.out.println(e.getExceptionInfo().getLocalDateTime());
        }

        ProductJsonConverter productJsonConverter = new ProductJsonConverter(productJsonFile);
        try {
            this.productList = productJsonConverter.fromJson().get();
        } catch (MyException e) {
            System.err.println("EXCEPTION FROM PRODUCT JSON FILE");
            System.err.println(e.getExceptionInfo().getExceptionMessage());
            System.out.println(e.getExceptionInfo().getLocalDateTime());
        }
    }

    // CREATION OF MAP WITH EVERY CLIENT AND LIST OF PRODUCT WHICH THEY WANT AND CAN (MONEY) BUY
    public Map<Client, List<Product>> resolveKnapsackProblem() {
        Map<Client, List<Product>> productBoughtByClientMap = new LinkedHashMap<>();

        for (Client c : this.clientList) {
            ListIterator<Integer> it = c.getPreferences().listIterator(); // get list of preferences from every single client
            List<Product> list = new ArrayList<>();
            while (it.hasNext()) { // if there is next category
                Integer category = it.next();
                try {
                    if (sortProductsByCategory().get(category) == null) {
                        throw new NullPointerException("CLIENT: " + c + " WANT BUY PRODUCTS FROM: " + category + " CATEGORY WHICH IS UNAVAILABLE IN A STORE");
                    }
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    if (it.hasNext()) {
                        category = it.next();
                    } else {
                        break;
                    }
                }
                ListIterator<Product> pit = sortProductsByCategory().get(category).listIterator();
                while (pit.hasNext()) {
                    list.add(pit.next());
                    calculateClientAccountBalance(c, list);
                }
            }
            productBoughtByClientMap.put(c, list);
        }
        return productBoughtByClientMap;
    }

    // return map with category number and list of products belongs to this category with sorted products with the least price/quantity ratio
    private Map<Integer, List<Product>> sortProductsByCategory() {
        return
                this.productList.
                        stream()
                        .collect(Collectors.groupingBy(product -> product.getCategory())).entrySet()
                        .stream()
                        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().stream().sorted((Comparator.comparing(product -> product.getPrice().divide(BigDecimal.valueOf(product.getQuantity()), 2, RoundingMode.HALF_UP))))
                                .collect(Collectors.toList())));
    }

    private void calculateClientAccountBalance(Client c, List<Product> products) {
        c.setAccountBalance(c.getAccountBalance().subtract(products.get(products.size() - 1).getPrice()));
        if (c.getAccountBalance().compareTo(BigDecimal.ZERO) < 0) {
            c.setAccountBalance(c.getAccountBalance().add(products.get(products.size() - 1).getPrice()));
            products.remove(products.get(products.size() - 1));
        }
    }
}
