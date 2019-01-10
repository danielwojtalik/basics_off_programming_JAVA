package main;

import exceptions.MyException;
import other_operations.DrugiNajwiekszyElement;
import other_operations.SortAndSaveFile;
import read_from_file.OdczytPliku;

import java.util.List;

public class Main2 {

    public static void main(String[] args) {

        try {
            SortAndSaveFile.sortuj(OdczytPliku.odczytPliku("Basics_off_OOP_Without_JSON/GeneratedInputFile.txt"));
        } catch (MyException e) {
            System.err.println(e.getExceptionInfo().getCode());
            System.err.println(e.getExceptionInfo().getDescription());
            System.err.println(e.getExceptionInfo().getDateTime());
        }
        System.out.println("<GENERATE FILE \"OutputFile.txt\" WITH SORTED ELEMENTS IN SEPARATED ROWS>");
        System.out.println("__________________________________________________________________________________________________________");


        System.out.println("<LISTA DRUGICH CO DO WIELKOSCI ELEMENTOW W KAZDYM WIERSZU>");
        List<Integer> listaDrugichElementow = DrugiNajwiekszyElement.drugiNajwiekszyElement();
        System.out.println(listaDrugichElementow);


    }
}
