package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SastojakTest {
    @Test
    void brasnoTest() {
        Sastojak brasno = new PraskastiSastojak("brašna",100);
        assertEquals("brašna", brasno.getNaziv());
        assertEquals("100 g brašna", brasno.toString());
    }

    @Test
    void uljeTest() {
        Sastojak ulje = new TecniSastojak("", 0);
        ulje.setNaziv("ulja");
        ulje.setKolicina(6);
        assertEquals(6, ulje.getKolicina());
        assertEquals("6 dl ulja", ulje.toString());
    }
}