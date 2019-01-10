package read_from_file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*This class is used for read every row of input txt file and load it to the List<List<Integer>>*/

public class OdczytPliku {

    public static List<List<Integer>> odczytPliku(String nazwaPliku) {
        List<List<Integer>> lines = new ArrayList<>();


        try {
            FileReader reader = new FileReader(nazwaPliku);
            Scanner sc = new Scanner(reader);

            while (sc.hasNextLine()) {
                lines.add(rozbijLiczby(sc.nextLine()));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static List<Integer> rozbijLiczby(String wiersz) {
        if (wiersz == null || !wiersz.matches("(\\d+\\D*)*\\d+")) {
            return null;
        }

        List<String> numbersStr = new ArrayList<>(Arrays.asList(wiersz.split("\\D+")));
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < numbersStr.size(); i++) {
            numbers.add(Integer.parseInt(numbersStr.get(i)));
        }
        return numbers;
    }
}
