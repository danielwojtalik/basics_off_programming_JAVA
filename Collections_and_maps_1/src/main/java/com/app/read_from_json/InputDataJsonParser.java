package com.app.read_from_json;

import com.app.my_exceptions.ExceptionCode;
import com.app.my_exceptions.MyException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.util.*;

public class InputDataJsonParser {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // method to retrieve list of Clients class objects from json file
    public static List<ClientProducts> readFromJson(String jsonFileName) {

        try (FileReader fileReader = new FileReader(jsonFileName)) {
            ClientProducts[] clientToReads = gson.fromJson(fileReader, ClientProducts[].class);
            return Arrays.asList(clientToReads);
        }catch (Exception e){
            throw new MyException(ExceptionCode.JSON, e.getMessage());
        }
    }
}













            /*List<Client> withoutDuplicates = new ArrayList<>();



            for (Client c : readClientToReadList) {

                for(Iterator<Client> it = withoutDuplicates.iterator(); it.hasNext();){
                    Client clientToReadIt = it.next();
                    if (!clientToReadIt.getName().equals(c.getName()) && !clientToReadIt.getSurname().equals(c.getSurname()) && clientToReadIt.getAge() != (clientToReadIt.getAge())) {
                        withoutDuplicates.add(c);
                    }
                    else if (clientToReadIt.getName().equals(c.getName()) && clientToReadIt.equals(c.getSurname()) && clientToReadIt.getAge() == (clientToReadIt.getAge())) {
                        it.next().getProducts().addAll(c.getProducts());
                    }
                }*/
                /*for (Client c1 : withoutDuplicates) {
                    if (!c1.getName().equals(c.getName()) && !c1.getSurname().equals(c.getSurname()) && c1.getAge() != (c.getAge())) {
                    withoutDuplicates.add(c);
                    }
                    else if (c1.getName().equals(c.getName()) && c1.getSurname().equals(c.getSurname()) && c1.getAge() == (c.getAge())) {
                        c1.getProducts().addAll(c.getProducts());
                    }
                }
        }*/


           /* Map<Client, List<Product>> concatClientWithShoppingList =
                    readClientToReadList.
                            stream()
                            .collect(Collectors.toMap(client -> client, client ->
                                    client.getProducts(), (oldValue, newValue) ->
                                    Stream.concat(oldValue.stream(), newValue.stream()).collect(Collectors.toList())));

            List<Client> clientList = new ArrayList<>();
            for (Client e : concatClientWithShoppingList.keySet()) {
                e.setProducts(concatClientWithShoppingList.get(e));
                clientList.add(e);
            }
            System.out.println(withoutDuplicates);
            return withoutDuplicates;
        }
    }
}
*/