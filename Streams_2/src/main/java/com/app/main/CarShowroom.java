package com.app.main;

import com.app.car_and_composition_parts.Car;
import com.app.cutom_exception.ExceptionCode;
import com.app.cutom_exception.MyException;
import com.app.json_reader_writer.InputDataJsonParser;
import com.app.my_data_reader.MyDataReader;
import com.app.json_reader_writer.WriteToJson;
import com.app.enum_types.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data


public class CarShowroom {
    private Set<Car> carSet;


    public static void main(String[] args) {

        // I create here to instances of CarShowroom class (cs and cs2) by using Args Constructor (Initialization cars from single json file)
        List<String> jsonFileSingleCar = new ArrayList<>(); // for cs
        jsonFileSingleCar.add("Streams_2/target/single_car_output/Aston_Martin_4500000zl.json");
        jsonFileSingleCar.add("Streams_2/target/single_car_output/Bentley_8000000zl.json");
        jsonFileSingleCar.add("Streams_2/target/single_car_output/BMW_220000zl.json");
        jsonFileSingleCar.add("Streams_2/target/single_car_output/Fiat_126p_1000000zl.json");
        jsonFileSingleCar.add("Streams_2/target/single_car_output/Hyundai_45000zl.json");
        jsonFileSingleCar.add("Streams_2/target/single_car_output/Nissan_120000zl.json");
        jsonFileSingleCar.add("Streams_2/target/single_car_output/Warszawa_150000zl.json");

        List<String> jsonFileSingleCar2 = new ArrayList<>(); //for cs2
        jsonFileSingleCar2.add("Streams_2/target/single_car_output/Hyundai_45000zl.json");
        jsonFileSingleCar2.add("Streams_2/target/single_car_output/Warszawa_150000zl.json");

        List<String> jsonFileSingleCar3 = new ArrayList<>(); // for cs3 (empty list to check exception handling)

        // Initialization CarShowroom
        CarShowroom cs = new CarShowroom(jsonFileSingleCar);
        CarShowroom cs2 = new CarShowroom(jsonFileSingleCar2);
        CarShowroom cs3 = new CarShowroom(jsonFileSingleCar3);

        // method use to sort a set of cars by the price order - DEMO
        System.out.println("<GET SET OF CARS SORTED BY PRICE>");
        Set<Car> setOfCarsSortedByPrice;
        try {
            setOfCarsSortedByPrice = cs.sortByPrice();
            setOfCarsSortedByPrice.forEach(car -> System.out.println("Brand: " + car.getBrand() + ", Price: " + car.getPrice()));
            System.out.println("__________________________________________________________________________________________________________________________________");
        } catch (MyException e) {
            e.printStackTrace();
            System.err.println(e.getExceptionInfo().getExceptionCode());
            System.err.println(e.getExceptionInfo().getExceptionMassage());
            System.err.println(e.getExceptionInfo().getLocalDateTime());
        }

        // method use to sort a set of cars by the brand name order - DEMO
        System.out.println("<GET SET OF CARS SORTED BY BRAND>");
        try {
            Set<Car> setOfCarsSortedByBrand = cs.sortByBrand();
            setOfCarsSortedByBrand.forEach(car -> System.out.println("Brand: " + car.getBrand() + ", Price: " + car.getPrice()));
            System.out.println("__________________________________________________________________________________________________________________________________");
        } catch (MyException e) {
            e.printStackTrace();
            System.err.println(e.getExceptionInfo().getExceptionCode());
            System.err.println(e.getExceptionInfo().getExceptionMassage());
            System.err.println(e.getExceptionInfo().getLocalDateTime());
        }

        // method use to sort a set of cars by the colour of car body - DEMO
        System.out.println("<GET SET OF CARS SORTED BY COLOUR>");
        try {
            Set<Car> setOfCarsSortedByColour = cs.sortedByColour();
            setOfCarsSortedByColour.forEach(car -> System.out.println("Brand: " + car.getBrand() + ", Price: " + car.getPrice() + " colour: " + car.getCarBody().getColourOfBody()));
            System.out.println("__________________________________________________________________________________________________________________________________");
        } catch (
                MyException e) {
            e.printStackTrace();
            System.err.println(e.getExceptionInfo().getExceptionCode());
            System.err.println(e.getExceptionInfo().getExceptionMassage());
            System.err.println(e.getExceptionInfo().getLocalDateTime());
        }

        // method use to sort a set of cars by the type of car body - DEMO
        System.out.println("<GET SET OF CARS SORTED BY TYPE OF BODY>");
        try {
            Set<Car> listOfCarsSortedByBodyType = cs.sortedByBodyType();
            listOfCarsSortedByBodyType.forEach(car -> System.out.println("Brand: " + car.getBrand() + ", Price: " + car.getPrice() + " Body Type: " + car.getCarBody().getKindOfBody()));
            System.out.println("__________________________________________________________________________________________________________________________________");
        } catch (MyException e) {
            e.printStackTrace();
            System.err.println(e.getExceptionInfo().getExceptionCode());
            System.err.println(e.getExceptionInfo().getExceptionMassage());
            System.err.println(e.getExceptionInfo().getLocalDateTime());
        }

        // method use to sort a set of cars by the colour of car body - DEMO
        System.out.println("<GET SET OF CARS WITH SPECIFIC BODY AND IN SPECIFIC PRICE>");
        try {
            Set<Car> filteredList = cs.getCarWithConcreteBodyTypeAndConcretePrice(KindOfBody.SEDAN, new BigDecimal("0"), new BigDecimal("500000"));
            filteredList.forEach(car -> System.out.println("Brand: " + car.getBrand() + ", Price: " + car.getPrice() + " Body Type: " + car.getCarBody().getKindOfBody()));
            System.out.println("__________________________________________________________________________________________________________________________________");
        } catch (MyException e) {
            e.printStackTrace();
            System.err.println(e.getExceptionInfo().getExceptionCode());
            System.err.println(e.getExceptionInfo().getExceptionMassage());
            System.err.println(e.getExceptionInfo().getLocalDateTime());
        }

        // method to select cars with petrol or diesel engine - DEMO
        System.out.println("<GET SET OF CARS WITH SPECIFIC TYPE OF ENGINE (PETROL OR DIESEL>");
        try {
            Set<Car> specificEngineTypeCars = cs.getCarsListWithSpecificFuel(EngineType.DIESEL_ENGINE);
            specificEngineTypeCars.forEach(car -> System.out.println("Brand: " + car.getBrand() + ", Price: " + car.getPrice() + " Enegine: " + car.getEngine().getEngineType()));
            System.out.println("__________________________________________________________________________________________________________________________________");
        } catch (MyException e) {
            e.printStackTrace();
            System.err.println(e.getExceptionInfo().getExceptionCode());
            System.err.println(e.getExceptionInfo().getExceptionMassage());
            System.err.println(e.getExceptionInfo().getLocalDateTime());
        }

        // method to configure your car from all available -DEMO
        System.out.println("<CONFIGURE YOUR CAR>");
        try {
            cs.findCarWithYourParameters();
            System.out.println("__________________________________________________________________________________________________________________________________");
        } catch (MyException e) {
            e.printStackTrace();
            System.err.println(e.getExceptionInfo().getExceptionCode());
            System.err.println(e.getExceptionInfo().getExceptionMassage());
            System.err.println(e.getExceptionInfo().getLocalDateTime());
        }

        // method which show statistic of all cars grouped by price or engine capacity or wheel size- DEMO
        System.out.println("<GET STATISTICS FOR PRICE, CAPACITY AND WHEEL SIZE>");
        try {
            cs.getStatistics(ParametesForStatistic.CAR_PRICE);
            cs.getStatistics(ParametesForStatistic.ENGINE_CAPACITY);
            cs.getStatistics(ParametesForStatistic.WHEEL_SIZE);
            System.out.println("__________________________________________________________________________________________________________________________________");
        } catch (MyException e) {
            e.printStackTrace();
            System.err.println(e.getExceptionInfo().getExceptionCode());
            System.err.println(e.getExceptionInfo().getExceptionMassage());
            System.err.println(e.getExceptionInfo().getLocalDateTime());
        }

        // method which use properties file to read distance to firs inspection to all car - DEMO
        System.out.println("<GET INSPECTION DISTANCE FROM PROPERTIES FILE>");
        try {
            Map<String, String> mapWithCarInspectionDistance = cs.getProperty();
            Set<Map.Entry<String, String>> entries = cs.getProperty().entrySet();


            for (Map.Entry<String, String> e : entries) {
                System.out.println(e.getKey() + " " + e.getValue());
            }
            System.out.println("__________________________________________________________________________________________________________________________________");

        // method which sort previous map by value - DEMO
        System.out.println("<GET INSPECTION DISTANCE FOR CARS WITH DESCENDING ORDER>");
            Map<String, String> sortedByValue = CarShowroom.sortedMapByValue(mapWithCarInspectionDistance);
            System.out.println(sortedByValue);
            System.out.println("__________________________________________________________________________________________________________________________________");
        } catch (
                MyException e) {
            e.printStackTrace();
            System.err.println(e.getExceptionInfo().getExceptionCode());
            System.err.println(e.getExceptionInfo().getExceptionMassage());
            System.err.println(e.getExceptionInfo().getLocalDateTime());
        }

        // static method to find the carShowRoom with the smallest car AVG price, and save this car showroom cars to json file (only brand and price)
        System.out.println("<GET INSPECTION DISTANCE FOR CARS WITH DESCENDING ORDER>");
        try {
            List<CarShowroom> carShowroomList = new ArrayList<>();
            carShowroomList.add(cs);
            carShowroomList.add(cs2);
            getCarShowroomWithTheBiggestAvgCarPrice(carShowroomList, "Streams_2/target/CarShowRoomWithLessAVGCarPrice.json");
            System.out.println("SAVE TO JSON FILE.......");
            System.out.println("__________________________________________________________________________________________________________________________________");
        } catch (MyException e) {
            e.printStackTrace();
            System.err.println(e.getExceptionInfo().getExceptionCode());
            System.err.println(e.getExceptionInfo().getExceptionMassage());
            System.err.println(e.getExceptionInfo().getLocalDateTime());
        }
    }


    // Args Constructor to initialize CarShowroom object from individual JSON files
    public CarShowroom(List<String> carInitializationList) {
        Set<Car> carSetConst = new HashSet<>();
        try {
            if (carInitializationList == null){
                throw new NullPointerException("CAR_INITIALIZER_LIST IS NULL");
            }

            for (int i = 0; i < carInitializationList.size(); i++) {
                carSetConst.add(InputDataJsonParser.readFromJsonSingleCar(carInitializationList.get(i)));
                this.carSet = carSetConst;
            }
        } catch (MyException e) {
            e.printStackTrace();
            System.err.println(e.getExceptionInfo().getExceptionCode());
            System.err.println(e.getExceptionInfo().getExceptionMassage());
            System.err.println(e.getExceptionInfo().getLocalDateTime());
        }
    }

    // this method return Set ordered by price (I don't want to have duplicates) with LinkedHasSet implementation (I need to preserve order)
    public Set<Car> sortByPrice() {
        try {
            if (carSet == null) {

                throw new NullPointerException("CARSET IS NULL");
            }
            return carSet.stream()
                    .sorted(Comparator.comparing(car -> car.getPrice()))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        } catch (Exception e) {
            throw new MyException(ExceptionCode.CAR_SHOWROOM, e.getMessage());

        }
    }

    // this method return Set ordered by brand (I don't want to have duplicates) with LinkedHasSet implementation (I need to preserve order)
    public Set<Car> sortByBrand() {
        try {
            if (carSet == null) {
                throw new NullPointerException("CARSET IS NULL");
            }
            Set<Car> carSortByBrand = carSet.stream()
                    .sorted(Comparator.comparing(car -> car.getBrand()))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
            return carSortByBrand;
        } catch (Exception e) {
            throw new MyException(ExceptionCode.CAR_SHOWROOM, e.getMessage());
        }

    }

    // this method return Set ordered by body colour (I don't want to have duplicates) with LinkedHasSet implementation (I need to preserve order)
    public Set<Car> sortedByColour() {
        try {
            if (carSet == null) {
                throw new NullPointerException("carSet is not initialized");
            }
            Set<Car> carSortByColour = carSet.stream()
                    .sorted(Comparator.comparing(car -> car.getCarBody().getColourOfBody()))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
            return carSortByColour;
        } catch (Exception e) {
            throw new MyException(ExceptionCode.CAR_SHOWROOM, e.getMessage());
        }
    }

    // this method return Set ordered by body type (I don't want to have duplicates) with LinkedHasSet implementation (I need to preserve order)
    public Set<Car> sortedByBodyType() {
        try {
            if (carSet == null) {
                throw new NullPointerException("Car Set is NULL");
            }
            Set<Car> carSorteByBodyType = carSet.stream()
                    .sorted(Comparator.comparing(car -> car.getCarBody().getKindOfBody().toString()))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
            return carSorteByBodyType;
        } catch (Exception e) {
            throw new MyException(ExceptionCode.CAR_SHOWROOM, e.getMessage());
        }
    }

    // this method return Set of cars with predefine car body type and range of prices
    public Set<Car> getCarWithConcreteBodyTypeAndConcretePrice(KindOfBody bodyType, BigDecimal
            minPrice, BigDecimal maxPrice) {
        try {
            if (carSet == null) {
                throw new NullPointerException("CAR SET IS NULL");
            }
            if (bodyType == null) {
                throw new NullPointerException("BODYTYPE IS EMPTY");
            }
            if (minPrice == null) {
                throw new NullPointerException("MIN_PRICE IS EMPTY");
            }
            if (maxPrice == null) {
                throw new NullPointerException("MAX_PRICE IS EMPTY");
            }
            Set<Car> filteredCarSet = carSet
                    .stream()
                    .filter(car -> car.getCarBody().getKindOfBody().equals(bodyType))
                    .filter(car -> (car.getPrice().compareTo(minPrice) > 0 && car.getPrice().compareTo(maxPrice) < 0))
                    .collect(Collectors.toSet());
            return filteredCarSet;
        } catch (Exception e) {
            throw new MyException(ExceptionCode.CAR_SHOWROOM, e.getMessage());
        }
    }

    // get subset of cars with specific engine (petrol/diesel)
    public Set<Car> getCarsListWithSpecificFuel(EngineType engineType) {
        try {
            if (engineType == null) {
                throw new NullPointerException("ENGINE TYPE IS NULL");
            }
            if (carSet == null) {
                throw new NullPointerException("CAR SET IS NULL");
            }
            Set<Car> listOfCarsWithSpecificFuel = carSet
                    .stream()
                    .filter(car -> car.getEngine().getEngineType().equals(engineType))
                    .collect(Collectors.toSet());
            return listOfCarsWithSpecificFuel;

        } catch (Exception e) {
            throw new MyException(ExceptionCode.CAR_SHOWROOM, e.getMessage());
        }
    }

    // configure your Car
    public void findCarWithYourParameters() {

        try {

            if (carSet == null) {
                throw new NullPointerException("CAR SET IS NULL");
            }
            BigDecimal maxPrice;
            BigDecimal minPrice;
            EngineType engineType;
            BigDecimal minCapacity;
            BigDecimal maxCapacity;
            List<CarEquipment> carEquipment = new ArrayList<>(); // tutaj moze lepiej byloby wziac set, ktory to nie przechowuje duplikatow?

            System.out.println("Please give the minimum value of Car which you need:");
            minPrice = MyDataReader.sc.nextBigDecimal();
            if (!minPrice.toString().matches("[0-9]+[',']?[0-9]*")) {
                throw new InputMismatchException("Write price in format: 200000,00 or 200000");
            }
            System.out.println("Please give the maximum value of Car which you need:");
            maxPrice = MyDataReader.sc.nextBigDecimal();
            if (!maxPrice.toString().matches("[0-9]+[',']?[0-9]*")) {
                throw new InputMismatchException("Write price in format: 200000,00 or 200000");
            }
            if (minPrice.compareTo(new BigDecimal("0")) < 0 || maxPrice.compareTo(new BigDecimal("0")) < 0) { // need to be surround by try-catch and throw MyException
                throw new InputMismatchException("min price and max price can not be less than 0");
            }
            if (minPrice.compareTo(maxPrice) > 0) {
                throw new InputMismatchException("min price can not be bigger than max price");
            }
            System.out.println("Please give the preferred engine type of your new Car:\n For Petrol engine press 1\n For Diesel engine press 2\n ELSE DEFAULT VALUE IS PETROL_ENGINE");
            int typeOfEngine = MyDataReader.sc.nextInt();
            if (typeOfEngine == 1)
                engineType = EngineType.PETROL_ENGINE;
            else if (typeOfEngine == 2) {
                engineType = EngineType.DIESEL_ENGINE;
            } else {
                engineType = EngineType.PETROL_ENGINE;
            }

            System.out.println("Please give the minimum capacity of engine:");
            minCapacity = MyDataReader.sc.nextBigDecimal();
            if (!minCapacity.toString().matches("[0-9]+['.']?[0-9]*")) {
                throw new InputMismatchException( "WRONG FORMAT FOR CAPACITY");
            }
            if (minCapacity.compareTo(new BigDecimal("0")) < 0) {
                throw new InputMismatchException( "ENGINE CAPACITY CAN NOT BE LESS THAN 0");
            }

            System.out.println("Please give the maximum capacity of engine:");
            maxCapacity = MyDataReader.sc.nextBigDecimal();
            if (!maxCapacity.toString().matches("[0-9]+['.']?[0-9]*")) {
                throw new InputMismatchException( "WRONG FORMAT FOR CAPACITY");
            }
            if (maxCapacity.compareTo(new BigDecimal("0")) < 0) {
                throw new InputMismatchException( "ENGINE CAPACITY CAN NOT BE LESS THAN 0");
            }
            System.out.println("Please give the car equipment that you need:\n For ELECTRICAL_WING_MIRROR PRESS 1 \n For ELECTRICAL_WINDOW PRESS 2 \n" +
                    " For BACK_DOOR press 3 \n For ALARM press 4 \n For FOG_LAMPS press 5\n For AIR_CONDITIONING press \n For AUDIO press 7\n For LEATHER_INTERIOR press 8\n" +
                    " TO END CONFIGURATION PRESS 9");
            int i = MyDataReader.sc.nextInt();
            while (i != 9) {
                i = MyDataReader.sc.nextInt();
                if (i < 1 || i > 9) {
                    throw new InputMismatchException( "THIS EQUIPMENT IS NOT AVAILABLE");
                }
                switch (i) {
                    case 1:
                        carEquipment.add(CarEquipment.ELECTRICAL_WING_MIRROR);
                        break;

                    case 2:
                        carEquipment.add(CarEquipment.ELECTRICAL_WINDOW);
                        break;

                    case 3:
                        carEquipment.add(CarEquipment.BACK_DOOR);
                        break;

                    case 4:
                        carEquipment.add(CarEquipment.ALARM);
                        break;

                    case 5:
                        carEquipment.add(CarEquipment.FOG_LAMPS);
                        break;

                    case 6:
                        carEquipment.add(CarEquipment.AIR_CONDITIONING);
                        break;

                    case 7:
                        carEquipment.add(CarEquipment.AUDIO);
                        break;

                    case 8:
                        carEquipment.add(CarEquipment.LEATHER_INTERIOR);
                        break;
                    case 9:
                        i = 9;
                        break;
                }
            }

            carSet.stream()
                    .filter(car -> car.getPrice().compareTo(minPrice) > 0)
                    .filter(car -> car.getPrice().compareTo(maxPrice) < 0)
                    .filter(car -> car.getEngine().getEngineType().equals(engineType))
                    .filter(car -> car.getEngine().getEngineSize().compareTo(maxCapacity) < 0)
                    .filter(car -> car.getEngine().getEngineSize().compareTo(minCapacity) > 0)
                    .filter(car -> car.getCarBody().getCarEquipmentList().containsAll(carEquipment))
                    .collect(Collectors.toList()).forEach(car -> System.out.println(car));

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.CAR_SHOWROOM, e.getMessage());
        }
    }

    // get car statistic - there I need create another method to avoid repetition of code
    public void getStatistics(ParametesForStatistic parameterForStatistic) {

        try {
            if (carSet == null) {
                throw new NullPointerException("CAR SET IS NULL");
            }

            if (parameterForStatistic == ParametesForStatistic.CAR_PRICE) {

                // MIN PRICE FOR STATISTIC
                BigDecimal minPrice = carSet
                        .stream()
                        .map(car -> car.getPrice())
                        .sorted()
                        .limit(1)
                        .collect(Collectors.toList()).get(0);

                // MAX PRICE FOR STATISTIC
                BigDecimal maxPrice = carSet
                        .stream()
                        .map(car -> car.getPrice())
                        .sorted((o1, o2) -> o2.compareTo(o1))
                        .limit(1)
                        .collect(Collectors.toList()).get(0);

                // AVG PRICE FOR STATISTIC
                List<BigDecimal> priceList = carSet
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
                avgPrice = avgPrice.divide(BigDecimal.valueOf(numersOfCars), 2);

                System.out.println("MAX_PRICE: " + maxPrice + "; MIN_PRICE: " + minPrice + "; AVG_PRICE " + avgPrice);
            } else if (parameterForStatistic == ParametesForStatistic.ENGINE_CAPACITY) {

                //MIN CAPACITY OF ENGINE
                BigDecimal minCapacity = carSet.
                        stream()
                        .map(car -> car.getEngine().getEngineSize())
                        .sorted()
                        .limit(1)
                        .collect(Collectors.toList()).get(0);

                // MAX CAPACITY OF ENGINE
                BigDecimal maxCapacity = carSet
                        .stream()
                        .map(car -> car.getEngine().getEngineSize())
                        .sorted((o1, o2) -> o2.compareTo(o1))
                        .limit(1)
                        .collect(Collectors.toList()).get(0);
                // AVG CAPACITY OF ENGINE
                List<BigDecimal> capacityList = carSet
                        .stream()
                        .map(car -> car.getEngine().getEngineSize())
                        .collect(Collectors.toList());

                int numersOfCars = 0;
                BigDecimal avgCapacity = new BigDecimal("0");
                Iterator<BigDecimal> results = capacityList.iterator();

                while (results.hasNext()) {
                    numersOfCars++;
                    avgCapacity = avgCapacity.add(results.next());
                }
                avgCapacity = avgCapacity.divide(BigDecimal.valueOf(numersOfCars), 2);
                System.out.println("MAX_CAPACITY: " + maxCapacity + "; MIN_CAPACITY: " + minCapacity + "; AVG_CAPACITY: " + avgCapacity);
            } else if (parameterForStatistic == ParametesForStatistic.WHEEL_SIZE) {

                IntSummaryStatistics wheelSize = carSet
                        .stream()
                        .collect(Collectors.summarizingInt(car -> car.getWheel().getSizeOfWheel()));

                System.out.println("MAX_WHEEL_SIZE: " + wheelSize.getMax() + "; MIN_WHEEL_SIZE: " + wheelSize.getMin() + "; AVG_WHEEL_SIZE " + String.format("%.2f", wheelSize.getAverage()));
            } else {
                throw new NullPointerException("WRONG PARAMETER");
            }
        } catch (Exception e) {
            throw new MyException(ExceptionCode.CAR_SHOWROOM, e.getMessage());
        }
    }

    // get the first inspection distance from property file
    private Map<String, String> getProperty() {
        try {
            Properties properties = new Properties();
            Map<String, String> distanceToInspection = new HashMap<>();
            if (carSet == null) {
                throw new MyException(ExceptionCode.CAR_SHOWROOM, "CAR SET IS NULL");
            }
            try (InputStream input = new FileInputStream("Streams_2/target/config.properties")) {

                // load properties file
                properties.load(input);

                for (Car car : this.carSet) {
                    distanceToInspection.put(car.getBrand(), properties.getProperty(car.getBrand()));
                }
            } catch (Exception e) {
                throw new MyException(ExceptionCode.CAR_SHOWROOM, e.getMessage());
            }
            return distanceToInspection;
        } catch (Exception e) {
            throw new MyException(ExceptionCode.CAR_SHOWROOM, e.getMessage());
        }
    }

    // sort the previous map by distance value (ascending)
    private static Map<String, String> sortedMapByValue(Map<String, String> mapToSort) {
        try {
            if (mapToSort == null) {
                throw new NullPointerException( "MAP IS NULL");
            }

            return mapToSort
                    .entrySet()
                    .stream()
                    .sorted(comparingByValue((o1, o2) -> o2.compareTo(o1)))
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new)
                    );
        } catch (Exception e) {
            throw new MyException(ExceptionCode.CAR_SHOWROOM, e.getMessage());
        }
    }

    // get the map with cars grouped by SUMMER AND WINTER wheels
    public static Map<String, List<Car>> getCarWithSummer_WinterWheels(List<Car> carlist) {

        try {
            if (carlist == null) {
                throw new NullPointerException("CARLIST IS NULL");
            }
            Map<String, List<Car>> returnMap = new TreeMap<>();
            List<Car> summerWheels = new ArrayList<>();
            List<Car> winterWheels = new ArrayList<>();
            for (Car car : carlist) {
                if (car.getWheel().getKindOfWheel().equals(KindOfWheel.SUMMER_WHEEL)) {
                    summerWheels.add(car);
                } else if (car.getWheel().getKindOfWheel().equals(KindOfWheel.WINTER_WHEEL)) {
                    winterWheels.add(car);
                }
            }
            returnMap.put("SUMMER", summerWheels);
            returnMap.put("WINTER", winterWheels);
            return returnMap;
        } catch (Exception e) {
            throw new MyException(ExceptionCode.CAR_SHOWROOM, e.getMessage());
        }
    }

    // save to json file this car showroom where avg price of all cars is the least (car representation like Brand: price of car)
    public static void getCarShowroomWithTheBiggestAvgCarPrice(List<CarShowroom> csList, String jsonFile) {

        try {
            if (csList == null) {
                throw new NullPointerException("CS LIST IS NULL");
            }
            Map<String, BigDecimal> csWithAvgPrice = new LinkedHashMap<>();

            for (CarShowroom cs : csList) {

                List<BigDecimal> priceList = cs.getCarSet()
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
                avgPrice = avgPrice.divide(BigDecimal.valueOf(numersOfCars), 2);
                csWithAvgPrice.put(Integer.toString(csList.indexOf(cs)), avgPrice);
            }
            Map<String, BigDecimal> csWithAvgPriceSortedByAvgPrice = new LinkedHashMap<>();

            csWithAvgPriceSortedByAvgPrice = csWithAvgPrice.entrySet()
                    .stream()
                    .sorted(comparingByValue((o1, o2) -> o1.compareTo(o2)))
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

            Map.Entry<String, BigDecimal> entry = csWithAvgPriceSortedByAvgPrice.entrySet().iterator().next();
            String key = entry.getKey();
            WriteToJson.writeCarPriceAndBrandToJson(csList.get(Integer.parseInt(key)), jsonFile);

        } catch (Exception e) {
            throw new MyException(ExceptionCode.CAR_SHOWROOM, e.getMessage());
        }
    }
}



