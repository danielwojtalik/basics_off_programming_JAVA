package sort.impl;

import compare.Porownanie;
import sort.Sortowanie;

import java.util.Collections;
import java.util.List;

public class SortowanieSzybkie implements Sortowanie {
    @Override
    public void sortuj(List<Integer> elements, Porownanie p) {

        if (elements == null || elements.size() == 0 || p == null) {
            return;
        }
        int n = elements.size();
        quickSort(elements, 0, n - 1, p);
    }

    private static void quickSort(List<Integer> elements, int left, int right, Porownanie p) {

        if (left > right) {
            return;
        }
        int border = partitionList(elements,left,right,p);
        quickSort(elements, left, border - 1, p);
        quickSort(elements, border + 1, right, p);
    }

    private static int partitionList(List<Integer> elements, int left, int right, Porownanie p) {
        int pivotValue = elements.get(right);
        int border = left - 1;
        int i = left;
        while (i < right) {
            if (p.porownaj(elements.get(i), pivotValue) < 0) {
                border++;
                if (border != i) {
                    Collections.swap(elements, border, i);
                }
            }
            i++;
        }
        border++;
        if (border != right) {
            Collections.swap(elements, border, right);
        }
        return border;
    }
}


