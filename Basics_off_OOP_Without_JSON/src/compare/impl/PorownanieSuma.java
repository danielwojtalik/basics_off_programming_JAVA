package compare.impl;

import compare.Porownanie;

public class PorownanieSuma implements Porownanie {
    @Override
    public int porownaj(Integer e1, Integer e2) {
        return Integer.compare(sumOfDigits(e1), sumOfDigits(e2));
    }

    public static int sumOfDigits(int e) {
        return String.valueOf(e)
                .chars()
                .map(Character::getNumericValue)
                .sum();
        /*int sum = 0;
        int d;

        while (e > 0) {
            d = e % 10;
            sum += d;
            e = e / 10;
        }
        return sum;*/
    }
}
