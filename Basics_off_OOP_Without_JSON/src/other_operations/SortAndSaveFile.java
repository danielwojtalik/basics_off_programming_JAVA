package other_operations;

import compare.impl.PorownanieZwykle;
import exceptions.ExceptionCode;
import exceptions.ExceptionInfo;
import exceptions.MyException;
import compare.Porownanie;
import compare.impl.PorownanieSuma;
import sort.Sortowanie;
import sort.impl.SortowanieBabelkowe;
import sort.impl.SortowaniePrzezWstawianie;
import sort.impl.SortowanieSzybkie;

import java.util.List;

public class SortAndSaveFile {

    /*This class take the input txt file, and read method to impl_sort and method to compare numbers according to specification,
    then compare loaded elements from input file and write the results of sorting into OutputFile.txt*/

    public static void sortuj(List<List<Integer>> listOfNumberRows) {
        for (List<Integer> row : listOfNumberRows) {
            Sortowanie sortowanie = convertIntToSortMethod(takeTypeOfSortMethod(row));
            Porownanie porownanie = convertIntToCompareMethod(takeTypeOfCompareMethod(row));
            sortowanie.sortuj(elementsToSort(row), porownanie);
            ZapisDoPliku.zapisdoPlikuListOfIntegeres("Basics_off_OOP_Without_JSON/OutputFile.txt", elementsToSort(row));
        }

    }

    public static List<Integer> elementsToSort(List<Integer> allElements) {
        return allElements.subList(2, allElements.size());
    }

    public static int takeTypeOfSortMethod(List<Integer> row) {
        return row.get(0);
    }

    public static int takeTypeOfCompareMethod(List<Integer> row) {
        return row.get(1);
    }

    public static Sortowanie convertIntToSortMethod(int howToSort) { // musze tutaj dodac obsluge wyjatkow na koniec, bo przeciez nie moge miec tutaj innej wartosci niz 1,2,3 a program dalej music dzialac
        if (howToSort == 1) {
            return new SortowanieBabelkowe();
        } else if (howToSort == 2) {
            return new SortowaniePrzezWstawianie();
        } else if (howToSort == 3) {
            return new SortowanieSzybkie();
        } else try {
            throw new ArithmeticException("the first element of every row could be 1, 2 or 3. Please insert one of that value");
        } catch (Exception e) {
            throw new MyException(new ExceptionInfo(ExceptionCode.ERROR_1, e.getMessage()));
        }

    }

    public static Porownanie convertIntToCompareMethod(int howToCompare) { // musze tutaj dodac obsluge wyjatkow na koniec, bo przeciez nie moge miec tutaj innej wartosci niz 1,2,3 a program dalej music dzialac
        if (howToCompare == 1) {
            return new PorownanieSuma();
        } else if (howToCompare == 2) {
            return new PorownanieZwykle();
        } else throw new ArithmeticException("the secound element of every row could be 1 or 2");
    }


}
