package compare.impl;

import compare.Porownanie;

public class PorownanieZwykle implements Porownanie {
    @Override
    public int porownaj(Integer e1, Integer e2) {
        return e1.compareTo(e2);
    }
}
