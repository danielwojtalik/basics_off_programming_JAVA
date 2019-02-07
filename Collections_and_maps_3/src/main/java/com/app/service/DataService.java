package com.app.service;

import com.app.converter.CategoryJsonConverter;
import com.app.exceptions.ExceptionCode;
import com.app.exceptions.MyException;
import com.app.model.Client;
import com.app.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
class DataService {
    private final Map<Client, List<Product>> clientProducts;
    private final Map<String, String> categories;

    DataService(String clientsJsonFilename, String productsJsonFilename, String categoryJsonFilename) {
        if (clientsJsonFilename == null || productsJsonFilename == null || categoryJsonFilename == null) {
            throw new MyException(ExceptionCode.DATA_SERVICE, "NULL POINTER EXCEPTION");
        }
        this.clientProducts = new KnapsackProblemService(clientsJsonFilename, productsJsonFilename).resolveKnapsackProblem();
        this.categories = new CategoryJsonConverter(categoryJsonFilename).fromJson().get();

    }

    // get Client which bought most products
    public Client getClientWithMostProducts() {
        return clientProducts
                .entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("ANY ELEMENT EXISTS"));
    }


    //get Client, who have spent most money on service?
    public Client getClientWhoSpentMostMoney() {
        return clientProducts
                .entrySet()
                .stream()
                .sorted((e1, e2) -> (e2.getValue().stream().map(product -> product.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add)
                        .compareTo(e1.getValue().stream().map(product -> product.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add))))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("ANY ELEMENT EXISTS"));
    }

    // show statistics of products
    public void showProductsSellingStatistics() {

        Map<Product, Long> productLongMap;

        // return map with quantity of each product
        productLongMap = this.clientProducts.values()
                .stream()
                .flatMap(l -> l.stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long max = Collections.max(productLongMap.values());
        long min = Collections.min(productLongMap.values());

        Set<Product> mostOftenChose = productLongMap.entrySet().stream().filter(e -> e.getValue() == max)
                .collect(Collectors.toList()).stream().collect(Collectors.toMap(ee1 -> ee1.getKey(), ee1 -> ee1.getValue())).keySet();

        Set<Product> leastOftenChose = productLongMap.entrySet().stream().filter(e -> e.getValue() == min)
                .collect(Collectors.toSet()).stream().collect(Collectors.toMap(ee1 -> ee1.getKey(), ee1 -> ee1.getValue())).keySet();

        // show statistics
        System.out.println("How many times each product was bought:");
        productLongMap.forEach((k, v) -> System.out.println(k + " = " + v));
        System.out.println();
        System.out.println("List of the most-bought products(" + max + " times)" + ":");
        mostOftenChose.forEach(product -> System.out.println(product));
        System.out.println();
        System.out.println("List of the least-purchased products(" + min + " times)" + ":");
        leastOftenChose.forEach(product -> System.out.println(product));
        System.out.println();
        System.out.println("______________________________________________________________________________________________________");
    }

    public Map<String, Long> getCategoryPopularity() {
        List<Product> productList = new ArrayList<>();
        this.clientProducts.values().forEach(products -> productList.addAll(products));
        return
                productList
                        .stream()
                        .collect(Collectors.groupingBy(p -> p.getCategory(), Collectors.counting()))
                        .entrySet()
                        .stream()
                        .sorted(Comparator.comparing(Map.Entry::getValue))
                        .collect(Collectors.toMap(e -> this.categories.get(Integer.toString(e.getKey())), e -> e.getValue(), (v1, v2) -> v1, () -> new LinkedHashMap<>()));
    }

}