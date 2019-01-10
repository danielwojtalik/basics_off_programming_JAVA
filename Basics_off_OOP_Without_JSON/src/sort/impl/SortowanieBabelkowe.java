package sort.impl;

import compare.Porownanie;
import sort.Sortowanie;

import java.util.List;

public class SortowanieBabelkowe implements Sortowanie {

    @Override
    public void sortuj(List<Integer> elements, Porownanie p) {

        if (elements == null || elements.size() == 0 || p == null) {
            return;
        }

        int temp;
        boolean czyPosortowane;
        for (int i = elements.size() - 1; i > 0; i--) { // powtarza obiegi
            czyPosortowane = true; // przed kolejnym duzym obiegiem zakladm ze juz wszystko posortowano
            for (int j = 0; j < i; j++) { //realizuje porownania miedzy elementami

                if (p.porownaj(elements.get(j), elements.get(j + 1)) > 0) {
                    temp = elements.get(j);
                    elements.set(j, elements.get(j + 1));
                    elements.set(j + 1, temp);
                    czyPosortowane = false;
                }
            }

            if (czyPosortowane) // jezeli czyPosortowane jest true
            {
                break; //przerywasz petle for
            }
        }
    }
}
