package other_operations;

import compare.impl.PorownanieZwykle;
import read_from_file.OdczytTablicy;
import sort.impl.SortowanieSzybkie;


import java.util.ArrayList;
import java.util.List;

public class DrugiNajwiekszyElement {

    /*This class return the list of second biggest element in every row of input file using the natural order*/

    public static List<Integer> drugiNajwiekszyElement() {

        List<List<Integer>> odczytaneDane = OdczytTablicy.odczytTablicy("Basics_off_OOP_Without_JSON/OutputFile.txt");
        SortowanieSzybkie sortowanieSzybkie = new SortowanieSzybkie();
        List<Integer> secondBiggestElementInEveryRow = new ArrayList<>();

        for (List<Integer> list : odczytaneDane) {
            sortowanieSzybkie.sortuj(list, new PorownanieZwykle());

            // 1 1 1 1 1 1 1 1 1 -> wynik null
            int secondLargest = list.get(0);
            int largest = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) > largest) {
                    secondLargest = largest;
                    largest = list.get(i);
                }
                else if (list.get(i) > secondLargest && list.get(i) != largest) {
                    secondLargest = list.get(i);
                }
            }
            secondBiggestElementInEveryRow.add(secondLargest);
        }
        return secondBiggestElementInEveryRow;

    }


}
