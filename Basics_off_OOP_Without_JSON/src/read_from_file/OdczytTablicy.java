package read_from_file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*This class is used to read the text file, where every row i started and ending with "[" and "]"*/

public class OdczytTablicy {

    public static List<List<Integer>> odczytTablicy(String nazwaPliku) {
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
        if (wiersz == null || !wiersz.matches("(\\D*\\d+\\D*)*\\d+\\D*")) {
            return null;
        }
        String wieszMod = wiersz.substring(1, wiersz.length());


        List<String> numbersStr = new ArrayList<>(Arrays.asList(wieszMod.split("\\D+")));
        List<Integer> numbers = new ArrayList<>();
        for (
                int i = 0; i < numbersStr.size(); i++) {
            numbers.add(Integer.parseInt(numbersStr.get(i)));
        }
        return numbers;
    }

}

