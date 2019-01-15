package com.app.main;


import com.app.model.Client;
import com.app.model.Product;
import com.app.model.ProductCategory;
import com.app.my_exceptions.MyException;
import com.app.read_from_json.ClientProducts;
import com.app.read_from_json.InputDataJsonParser;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Data

public class Shopping {
    private Map<Client, Map<Product, Long>> clientShoppingMap;

    public static void main(String[] args) {

        try {
            Shopping sh = new Shopping("CollectionsAndMaps_1/Shopping_1.json");


            System.out.println("<SHOW ME ALL CLIENTS WITH THEIR ALL PRODUCTS AND QUANTITY OF THOSE PRODUCTS>");
            sh.clientShoppingMap.forEach((k, v) -> System.out.println("key: " + k + "value: " + v));
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
            System.out.println(sortCategoriesByQuantity(sh.comparisionOfSoldCategoriesInAgeOfClient()));
            System.out.println("__________________________________________________________________________________________");
            System.out.println("<SHOW ME  LIST OF PRODUCT CATEGORY WHICH ARE BEST SOLD IN EVERY AGE OF CLIENTS>");
            System.out.println(mapOfClientsAgeAndListOfBestCategory(sh.comparisionOfSoldCategoriesInAgeOfClient()));
            System.out.println("__________________________________________________________________________________________");
            System.out.println("<SHOW ME STATISTICS>");
            sh.avgPriceInCategory();
            System.out.println("__________________________________________________________________________________________");
//            System.out.println("<SHOW ME THE MOST OFTEN BOUGHT CATEGORY FROM CLIENTS>");
//            sh.oftenBoughtCategoryByClients();
//            System.out.println("__________________________________________________________________________________________");
            System.out.println("<SHOW ME CLIENTS WHICH BOUGHT MOST OFTEN PRODUCTS FROM EVERY CATEGORY>");
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

    // create a constructor which take as a parameter JSON file name and parse this file with result of map with all clients
    // with all products and quantity of it which they have bought (client can buy multiple times the same products). It also should work if the client appear more than one time in file
    // (client can make shopping every morning e.g.).
    public Shopping(String jsonFileName) {

        // parsing file to the list of ClientProducts
        List<ClientProducts> clientProductsList = InputDataJsonParser.readFromJson(jsonFileName);
        // grouping this list to elements belong to the same client and flatmapping to Product list
        Map<Client, List<Product>> grouped = clientProductsList
                .stream()
                .collect(Collectors.groupingBy(ClientProducts::getClient))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().flatMap(cp -> cp.getProductList().stream()).collect(Collectors.toList())));

        // counting the same products bought by each client
        Map<Client, Map<Product, Long>> mapClientProductListLong = new HashMap<>();
        grouped.forEach((k, v) -> mapClientProductListLong.put(k, v.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))));
        this.clientShoppingMap = mapClientProductListLong;
        //mapClientProductListLong.forEach((k,v)-> System.out.println("key: "+ k + "value: " + v));
    }

    public Client theBestClient() { // client who spent the most money in general

        Map<Client, BigDecimal> moneyFromClients = new HashMap<>();

        Set<Map.Entry<Client, Map<Product, Long>>> entriesSet = clientShoppingMap.entrySet();
        for (Map.Entry<Client, Map<Product, Long>> e : entriesSet) {

            // put values to map moneyFromClients
            BigDecimal sum = e.getValue().keySet()
                    .stream().map(product -> product.getProductPrice().multiply(BigDecimal.valueOf(e.getValue().get(product)))) // Important - multiply by quantity (DONE)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            moneyFromClients.put(e.getKey(), sum);
        }
        //return the first client after desc sorting
        return moneyFromClients
                .entrySet()
                .stream()
                .sorted((p1, p2) -> p2.getValue().compareTo(p1.getValue()))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("ANY COMPONENT FOUND"))
                .getKey();
    }

    // the same as previous, but we consider only one ProductCategory (argument of method)
    public Client theBestClientInCategory(ProductCategory productCategory) { // client who spent the most money in given category

        Map<Client, BigDecimal> moneyFromClients = new HashMap<>();
        BigDecimal sum;
        Set<Map.Entry<Client, Map<Product, Long>>> entriesSet = clientShoppingMap.entrySet();

        for (Map.Entry<Client, Map<Product, Long>> e : entriesSet) {
            sum = new BigDecimal("0");
            for (Product p : e.getValue().keySet()) {
                if (p.getProductCategory() == productCategory) {
                    sum = sum.add(p.getProductPrice().multiply(BigDecimal.valueOf(e.getValue().get(p)))); // Important - multiply by quantity (DONE)
                }
            }
            moneyFromClients.put(e.getKey(), sum);
        }
        return moneyFromClients
                .entrySet()
                .stream()
                .sorted((p1, p2) -> p2.getValue().compareTo(p1.getValue()))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("ANY COMPONENT FOUND"))
                .getKey();
    }

    // counting how many products was sold in every category for each clients age
    public Map<Integer, Map<ProductCategory, Long>> comparisionOfSoldCategoriesInAgeOfClient() {
        Set<Map.Entry<Client, Map<Product, Long>>> entriesSet = clientShoppingMap.entrySet();

        Map<Integer, Map<ProductCategory, Long>> m1 = entriesSet
                .stream()
                .collect(Collectors.toMap
                        (
                                s -> s.getKey().getAge(), // Integer is key off map - this is age of clients
                                s -> s.getValue().entrySet().stream().collect(Collectors.toMap( // here I create map with sum off all productCategory bought by every single client
                                        s2 -> s2.getKey().getProductCategory(), // set key as ProductCategory (for Product)
                                        s2 -> s2.getValue(), // set values quantity of ProductsCategory
                                        (v1, v2) -> v1 + v2, // when ProductCategory has repeated add two quantities (it's the same category)
                                        () -> new LinkedHashMap<>()
                                )),
                                (v1, v2) -> Stream.concat(v1.entrySet().stream(), v2.entrySet().stream()) // when two clients have the same age, concat them
                                        .collect(Collectors.toMap(
                                                Map.Entry::getKey,
                                                Map.Entry::getValue,
                                                (vv1, vv2) -> vv1 + vv2 // add quantities off products in every category when the age off client is repeted
                                        )),
                                () -> new LinkedHashMap<>()
                        ));
        return m1;
    }

    public static Map<Integer, Map<ProductCategory, Long>> sortCategoriesByQuantity(Map<Integer, Map<ProductCategory, Long>> map) {

        Map<Integer, Map<ProductCategory, Long>> m2 = new LinkedHashMap<>();
        Set<Map.Entry<Integer, Map<ProductCategory, Long>>> entriesm1 = map.entrySet();

        for (Map.Entry<Integer, Map<ProductCategory, Long>> e : entriesm1) {
            m2.put(e.getKey(), e.getValue()
                    .entrySet()
                    .stream()
                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                    .collect(Collectors.toMap(
                            c -> c.getKey(),
                            c -> c.getValue(),
                            (v1, v2) -> v1,
                            () -> new LinkedHashMap<>()
                    )));
        }
        return m2;
    }

    // filter the previous and show only the best categories (categorieS because there could be sold the same quantity off products in two or more different categories)
    public static Map<Integer, List<ProductCategory>> mapOfClientsAgeAndListOfBestCategory(Map<Integer, Map<ProductCategory, Long>> map) {
        Map<Integer, List<ProductCategory>> m3 = new LinkedHashMap<>();

        for (Map.Entry<Integer, Map<ProductCategory, Long>> e : map.entrySet()) {
            long max = Collections.max(e.getValue().values());
            m3.put(e.getKey(), e.getValue()
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() == max)
                    .map(entry -> entry.getKey())
                    .collect(Collectors.toList())
            );

        }
        return m3;
    }

    public void avgPriceInCategory() {

        Set<Map.Entry<Client, Map<Product, Long>>> entriesSet = clientShoppingMap.entrySet();
        Set<Product> products = entriesSet // I use here Set<Product>, because I do not need duplicates value of Products
                .stream()
                .flatMap(e -> e.getValue().keySet().stream())
                .collect(Collectors.toSet());

        // Create map with ProductCategories and list of all products which belong to it
        Map<ProductCategory, List<Product>> map = new LinkedHashMap<>();
        for (Product p : products) {
            if (!map.containsKey(p.getProductCategory())) {
                List<Product> list = new ArrayList<>();
                list.add(p);

                map.put(p.getProductCategory(), list);
            } else {
                map.get(p.getProductCategory()).add(p);
            }
        }

        // AVG PRICE OF PRODUCTS IN CATEGORY
        Map<ProductCategory, BigDecimal> mapWithAvgPriceInCategory = new LinkedHashMap<>();
        Set<Map.Entry<ProductCategory, List<Product>>> entries = map.entrySet();

        for (Map.Entry<ProductCategory, List<Product>> me : entries) {
            mapWithAvgPriceInCategory.put(me.getKey(), me.getValue()
                    .stream()
                    .map(product -> product.getProductPrice())
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(BigDecimal.valueOf(me.getValue().size()), 2, RoundingMode.HALF_UP));
        }

        // MAX PRICE OF PRODUCTS IN CATEGORY
        Map<ProductCategory, Product> mapWithMaxPriceInCategory = new LinkedHashMap<>();
        for (Map.Entry<ProductCategory, List<Product>> me : entries) {
            mapWithMaxPriceInCategory.put(me.getKey(), me.getValue()
                    .stream()
                    .sorted(Comparator.comparing(product -> product.getProductPrice(), Comparator.reverseOrder()))
                    .findFirst()
                    .orElseThrow(() -> new NullPointerException("THERE IS ANY PRODUCT")));
        }

        // MIN PRICE OF PRODUCTS IN CATEGORY
        Map<ProductCategory, Product> mapWithMinPriceInCategory = new LinkedHashMap<>();
        for (Map.Entry<ProductCategory, List<Product>> me : entries) {
            mapWithMinPriceInCategory.put(me.getKey(), me.getValue()
                    .stream()
                    .sorted(Comparator.comparing(product -> product.getProductPrice()))
                    .findFirst()
                    .orElseThrow(() -> new NullPointerException("THERE IS ANY PRODUCT")));
        }

        System.out.println("Avarage products price in every category:");
        System.out.println(mapWithAvgPriceInCategory);
        System.out.println("The most expensive product in every category:");
        System.out.println(mapWithMaxPriceInCategory);
        System.out.println("The cheapest product in every category:");
        System.out.println(mapWithMinPriceInCategory);
    }

    public Map<ProductCategory, List<Client>> oftenBoughtCategoryByClients() {

        Set<Map.Entry<Client, Map<Product, Long>>> entriesSet = clientShoppingMap.entrySet();

        // lets check all available product categories
        Set<ProductCategory> productCategorySet = entriesSet
                .stream()
                .flatMap(e -> e.getValue().keySet().stream().map(product -> product.getProductCategory())).collect(Collectors.toSet());

        // create a map with key as product category and value as how many products from this category client has bought
        Map<ProductCategory, Map<Client, Long>> productCategoryMap = new HashMap<>();
        Map<Client, Long> clientLongMap = new HashMap<>();
        for (ProductCategory pc : productCategorySet) {
            for (Map.Entry<Client, Map<Product, Long>> e : entriesSet) {
                clientLongMap.put(e.getKey(), e.getValue().keySet().stream().filter(product -> product.getProductCategory() == pc).count());
            }
            productCategoryMap.put(pc, new HashMap<>(clientLongMap));
            clientLongMap.clear();
        }

        // create map with ProductCategory and all clients who have bought the biggest quantity of products in this category
        Map<ProductCategory, List<Client>> productCategoryClientMap = new HashMap<>();

        // fill this map with appropriate values
        for (Map.Entry<ProductCategory, Map<Client, Long>> e : productCategoryMap.entrySet()) {
            long max = Collections.max(e.getValue().values());
            productCategoryClientMap.put(e.getKey(), e.getValue()
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() == max)
                    .map(entry -> entry.getKey())
                    .collect(Collectors.toList())
            );
        }
        // If I need to see partial results use...

        /*   for (Map.Entry<ProductCategory, Map<Client, Long>> e : entries) {
            System.out.println("keys: " + e.getKey() + " value: " + e.getValue());
        }
        for (Map.Entry<ProductCategory, List<Client>> e : productCategoryClientMap.entrySet()) {
            System.out.println("keys: " + e.getKey() + " value: " + e.getValue());*/
        return productCategoryClientMap;


    }

    public Map<Client, BigDecimal> canBuyAllProducts() {

        Set<Map.Entry<Client, Map<Product, Long>>> entriesSet = clientShoppingMap.entrySet();
        Map<Client, BigDecimal> clientAccontBalanceMap = new HashMap<>();
        BigDecimal sum;


        for (Map.Entry<Client, Map<Product, Long>> e : entriesSet) {
            sum = new BigDecimal("0");
            for (Product p : e.getValue().keySet()) {

                sum = sum.add(p.getProductPrice().multiply(BigDecimal.valueOf(e.getValue().get(p)))); // Important - multiply by quantity (DONE)
            }
            sum = e.getKey().getBankBalance().subtract(sum);

            clientAccontBalanceMap.put(e.getKey(), sum);
        }
        return clientAccontBalanceMap;
    }
}