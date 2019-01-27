package com.app.service;

import com.app.converter.CategoryJsonConverter;
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
public class DataService {
    private  Map<Client, List<Product>> clientShoppingMap;
    private  Map<String, String> category;

    public DataService(String clientsJsonFilename, String productsJsonFilename, String categoryJsonFilename) {
        this.clientShoppingMap = new KnapsackProblemService(clientsJsonFilename, productsJsonFilename).resolveKnapsackProblem();
        try{
            this.category = new CategoryJsonConverter(categoryJsonFilename).fromJson().get();
        }catch (MyException e){
            System.err.println("EXCEPTION FROM CATEGORY JSON FILE PARSER");
            System.err.println(e.getExceptionInfo().getExceptionMessage());
        }
    }

    //show me Map with client and their shopping
    public void showClientShopping(){
        this.clientShoppingMap.forEach((k,v) -> System.out.println(k + " gets: "+v.stream().map(product -> product.getName()).collect(Collectors.toList())));
    }


    //show the client, who bought the most products?
    public void clientWithMostProducts() {
        System.out.println(clientShoppingMap
                .entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("ANY ELEMENT EXISTS")));
        System.out.println("______________________________________________________________________________________________________");

    }

    //show the client, who have spent most money on service?
    public void clientSpentMostMoney() {
        System.out.println(clientShoppingMap
                .entrySet()
                .stream()
                .sorted((e1, e2) -> (e2.getValue().stream().map(product -> product.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add)
                        .compareTo(e1.getValue().stream().map(product -> product.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add))))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("ANY ELEMENT EXISTS")));
        System.out.println("______________________________________________________________________________________________________");
    }

    // how many times each product was bought
    public void showProductsSellingStatistics() {
        List<Product> productList = new ArrayList<>();
        Map<Product, Long> productLongMap;

        for (Client c : this.clientShoppingMap.keySet()) {
            productList.addAll(this.clientShoppingMap.get(c));
        }
        productLongMap = productList
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(ee1 -> ee1.getKey(), ee2 -> ee2.getValue()));

        long max = Collections.max(productLongMap.values());
        long min = Collections.min(productLongMap.values());

        Set<Product> mostOftenChose = productLongMap.entrySet().stream().filter(e -> e.getValue() == max)
                .collect(Collectors.toList()).stream().collect(Collectors.toMap(ee1 -> ee1.getKey(), ee1 -> ee1.getValue())).keySet();

        Set<Product> leastOftenChose = productLongMap.entrySet().stream().filter(e -> e.getValue() == min)
                .collect(Collectors.toSet()).stream().collect(Collectors.toMap(ee1 -> ee1.getKey(), ee1 -> ee1.getValue())).keySet();

        System.out.println("How many times each product was bought:");
        productLongMap.forEach((k, v) -> System.out.println(k + " = " + v));
        System.out.println();
        System.out.println("List of the most-bought products(" + max + " times)" + ":");
        mostOftenChose.forEach(product -> System.out.println(product));
        System.out.println();
        System.out.println("List of the least-purchased products(" + min + " times)"+ ":");
        leastOftenChose.forEach(product -> System.out.println(product));
        System.out.println();
        System.out.println("______________________________________________________________________________________________________");
    }

    public void showCategoryPopularity() {
        Map<String, Long> productLongMap;
        List<Product> productList = new ArrayList<>();
        this.clientShoppingMap.values().forEach(products -> productList.addAll(products));

        productLongMap = productList
                .stream()
                .collect(Collectors.groupingBy(p -> p.getCategory(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toMap(e -> this.category.get(Integer.toString(e.getKey())), e -> e.getValue(), (v1, v2) -> v1, () -> new LinkedHashMap<>()));
        productLongMap.forEach((k, v) -> System.out.println(k + " = " + v));
        System.out.println("______________________________________________________________________________________________________");
    }
}