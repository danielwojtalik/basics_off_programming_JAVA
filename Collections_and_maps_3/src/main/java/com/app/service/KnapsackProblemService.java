package com.app.service;

import com.app.converter.ClientJsonConverter;
import com.app.converter.ProductJsonConverter;
import com.app.exceptions.ExceptionCode;
import com.app.exceptions.MyException;
import com.app.model.Client;
import com.app.model.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class KnapsackProblemService {

    private List<Client> clients;
    private List<Product> products;


    public KnapsackProblemService(String clientJsonFile, String productJsonFile) {

        if (clientJsonFile == null || productJsonFile == null){
            throw new MyException(ExceptionCode.KNAPSACK_PROBLEM, "NULL WITHIN CONSTRUCTOR");
        }
        ClientJsonConverter clientJsonConverter = new ClientJsonConverter(clientJsonFile);
        this.clients = clientJsonConverter.fromJson().get();
        ProductJsonConverter productJsonConverter = new ProductJsonConverter(productJsonFile);
        this.products = productJsonConverter.fromJson().get();

    }

    // return map with category number and list of products belongs to this category with sorted products with the least price/quantity ratio
    private Map<Integer, List<Product>> sortProductsByCategory() {
        return
                this.products
                        .stream()
                        .collect(Collectors.groupingBy(product -> product.getCategory())).entrySet()
                        .stream()
                        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().stream().sorted((Comparator.comparing(product -> product.getPrice().divide(BigDecimal.valueOf(product.getQuantity()), 2, RoundingMode.HALF_UP))))
                                .collect(Collectors.toList())));
    }

    // You can buy products until you have money, if product is too expensive you need to take it out from cart
    private void calculateClientAccountBalance(Client c, List<Product> products) {
        c.setAccountBalance(c.getAccountBalance().subtract(products.get(products.size() - 1).getPrice()));
        if (c.getAccountBalance().compareTo(BigDecimal.ZERO) < 0) {
            c.setAccountBalance(c.getAccountBalance().add(products.get(products.size() - 1).getPrice()));
            products.remove(products.get(products.size() - 1));
        }
    }

    // return map with key Client and List of all products which customer can afford to buy according to his shopping preferences
    public Map<Client, List<Product>> resolveKnapsackProblem() {
        Map<Client, List<Product>> clientProducts = new LinkedHashMap<>();
        for (Client c : this.clients) {
            ListIterator<Integer> it = c.getPreferences().listIterator(); // get list of preferences from every single client
            List<Product> list = new ArrayList<>();
            while (it.hasNext()) { // if there is next category
                Integer category = it.next();
                try {
                    if (sortProductsByCategory().get(category) == null) {
                        throw new MyException(ExceptionCode.KNAPSACK_PROBLEM, "CLIENT: " + c + " WANT BUY PRODUCTS FROM: " + category + " CATEGORY WHICH IS UNAVAILABLE IN A STORE");
                    }
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    if (it.hasNext()) { // If there is next category check it
                        category = it.next(); // If there is not next category break
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
            clientProducts.put(c, list);
        }
        return clientProducts;
    }
}
