package main;

import compare.impl.PorownanieZwykle;
import read_from_file.OdczytPliku;
import compare.Porownanie;
import compare.impl.PorownanieSuma;
import sort.Sortowanie;
import sort.impl.SortowanieBabelkowe;
import sort.impl.SortowaniePrzezWstawianie;
import sort.impl.SortowanieSzybkie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Porownanie myComparsion = new PorownanieZwykle(); // ustawiam interfejs na takiej klasie, ktora go implementuje, gdzie jest odpowiednia dla mnie implementacja metody porownaj


        System.out.println("<SORTOWANIE BABELKOWE>");
        Sortowanie sortowanieBabelkowe = new SortowanieBabelkowe();
        List<Integer> mojaLista = new ArrayList<>(Arrays.asList(34, 55, 48, 17, 458, 54, 789, 44));
        System.out.println(mojaLista);
        sortowanieBabelkowe.sortuj(mojaLista, myComparsion);
        System.out.println(mojaLista);

        System.out.println("_____________________________________________________________________________________________________________________________________________");
        System.out.println("<SORTOWANIE PRZEZ WSTAWIANIE>");
        Sortowanie sortowaniePrzezWstawianie = new SortowaniePrzezWstawianie();
        List<Integer> mojaLista1 = new ArrayList<>(Arrays.asList(102, 66, 55, 5, 6, 2, 13, 28, 15, 6, 7));
        System.out.println(mojaLista1);
        sortowaniePrzezWstawianie.sortuj(mojaLista1, myComparsion);
        System.out.println(mojaLista1);

        System.out.println("_____________________________________________________________________________________________________________________________________________");
        System.out.println("<SORTOWANIE PRZEZ QUICKSORT>");
        Sortowanie sortowanieSzybkie = new SortowanieSzybkie();
        List<Integer> mojaLista2 = new ArrayList<>(Arrays.asList(102, 66, 55, 5, 6, 2, 13, 28, 15, 6, 7));
        System.out.println(mojaLista2);
        sortowanieSzybkie.sortuj(mojaLista2, myComparsion);
        System.out.println(mojaLista2);
        System.out.println("_____________________________________________________________________________________________________________________________________________");

        System.out.println("<POROWNIANIE SUMA>");
        PorownanieSuma porownanieSuma = new PorownanieSuma();
        int wynik = porownanieSuma.porownaj(12, 13);
        System.out.println(wynik);
        System.out.println("_____________________________________________________________________________________________________________________________________________");

        System.out.println("<ODCZYT PLIKU TEKSTOWEGO>");
        System.out.println(OdczytPliku.odczytPliku("PodstawyObiektowosci1/GeneratedInputFile.txt"));

        PorownanieSuma porownanieSuma1 = new PorownanieSuma();
        porownanieSuma1.porownaj(45, 12);
    }
}
