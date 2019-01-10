package sort.impl;

import compare.Porownanie;
import sort.Sortowanie;

import java.util.List;

public class SortowaniePrzezWstawianie implements Sortowanie {


    @Override
    public void sortuj(List<Integer> elements, Porownanie p) {

        if (elements == null || elements.size() == 0 || p == null) {
            return;
        }
        for (int i = 0; i < elements.size() - 1; i++) {

            Integer keyElement = elements.get(i + 1);
            int j = i;
            while (p.porownaj(keyElement, elements.get(j)) < 0 && j >= 0) {
                elements.set(j + 1, elements.get(j));
                elements.set(j, keyElement);
                if (j > 0) {
                    j--;
                }
            }
        }
    }
}
