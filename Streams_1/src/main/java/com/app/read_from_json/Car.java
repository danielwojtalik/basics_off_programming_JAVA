package com.app.read_from_json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder

public class Car {

    private String brand;
    private BigDecimal price;
    private double engineSize;
    private int mileage;
    private List<String> components;


    public static void sortListByBrand(List<Car> carList) {
        carList.stream()
                .sorted(Comparator.comparing(car -> car.getBrand()))
                .forEach(car -> System.out.println(car));
    }


    public static void sortListByPrice(List<Car> carList) {
        carList.stream()
                .sorted(Comparator.comparing(car -> car.getPrice()))
                .forEach(car -> System.out.println(car));
    }

    public static void sortListByEngineSize(List<Car> carList) {
        carList.stream()
                .sorted(Comparator.comparing(car -> car.getEngineSize()))
                .forEach(car -> System.out.println(car));
    }

    public static void sortListByNumberOfComponent(List<Car> carList) {
        carList.stream()
                .sorted(Comparator.comparing(car -> car.getComponents().size()))
                .forEach(car -> System.out.println(car));
    }

    public static void delateElementsWithTooBigMileage(List<Car> carList, int maxMileage) {
        Iterator<Car> toRemoveElements = carList.iterator();
        while (toRemoveElements.hasNext()) {
            if (toRemoveElements.next().getMileage() > maxMileage) {
                toRemoveElements.remove();
            }
        }
        for (Car car : carList) {
            System.out.println(car);
        }
    }

    public static List<String> BrandMileageString(List<Car> carList) {

        List<Car> alphabeticallyCarList = carList.stream()
                .sorted(Comparator.comparing(car -> car.getBrand()))
                .collect(Collectors.toList());
        List<String> cuttedAphabeticallyCarList = new ArrayList<>();
        for (Car car : alphabeticallyCarList) {
            cuttedAphabeticallyCarList.add(car.getBrand() + "-" + car.getMileage());
        }

        for (String s : cuttedAphabeticallyCarList) {
            System.out.println(s);
        }
        return cuttedAphabeticallyCarList;
    }

    public static void giveMeFullStatistic(List<Car> carList) {
        IntSummaryStatistics mileageStatistic = carList
                .stream()
                .collect(Collectors.summarizingInt(car -> car.getMileage()));

        DoubleSummaryStatistics engineSizeStatistic = carList
                .stream()
                .collect(Collectors.summarizingDouble(car -> car.getEngineSize()));


        // MIN PRICE FOR STATISTIC
        BigDecimal minPrice = carList
                .stream()
                .map(car -> car.getPrice())
                .sorted()
                .limit(1)
                .collect(Collectors.toList()).get(0);
        // MAX PRICE FOR STATISTIC
        BigDecimal maxPrice = carList
                .stream()
                .map(car -> car.getPrice())
                .sorted((o1, o2) -> o2.compareTo(o1))
                .limit(1)
                .collect(Collectors.toList()).get(0);
        // AVG PRICE FOR STATISTIC
        List<BigDecimal> priceList = carList
                .stream()
                .map(car -> car.getPrice())
                .collect(Collectors.toList());

        int numersOfCars = 0;
        BigDecimal avgPrice = new BigDecimal(0);
        Iterator<BigDecimal> results = priceList.iterator();

        while (results.hasNext()) {
            numersOfCars++;
            avgPrice = avgPrice.add(results.next());
        }
        avgPrice = avgPrice.divide(BigDecimal.valueOf(numersOfCars));

        System.out.println("MAX_MILEAGE: " + mileageStatistic.getMax() + "; MIN_MILEAGE: " + mileageStatistic.getMin() + "; AVG_MILEAGE " + mileageStatistic.getAverage());
        System.out.println("MAX_ENGINE_SIZE: " + engineSizeStatistic.getMax() + "; MIN_ENGINE_SIZE: " + engineSizeStatistic.getMin() + "; AVG_ENGINE_SIZE " + engineSizeStatistic.getAverage());
        System.out.println("MAX_PRICE: " + maxPrice + "; MIN_PRICE: " + minPrice + "; AVG_PRICE " + avgPrice);

    }

    public static Car showMostExpensiveCar(List<Car> carList) {
        Car maxPriceCar = carList.
                stream().
                sorted(Comparator.comparing(car -> car.getPrice(), Comparator.reverseOrder()))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("CAN'T FIND OBJECT"));
        return maxPriceCar;
    }

    public static void isCommonComponent(List<Car> carList, String component) {
        if (carList
                .stream()
                .allMatch(car -> car.getComponents().contains(component))) {
            System.out.println(component + " is an common component");
        } else {
            System.out.println(component + " isn't an common component");
        }
    }

    public static void sortComponentsInList(List<Car> carList) {

        for (Car car : carList) {
            car.getComponents().sort((o1, o2) -> o1.compareTo(o2));
            System.out.println(car);
        }

    }
}

