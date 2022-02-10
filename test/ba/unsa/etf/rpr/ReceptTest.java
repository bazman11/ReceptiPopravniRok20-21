package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceptTest {
    @Test
    public void prazanReceptTest() {
        Recept r = new Recept("Test");
        r.setVrstaPripreme(VrstaPripreme.PECENJE);
        r.setPodatak(10);
        assertEquals("Test", r.getNazivJela());
        assertEquals(VrstaPripreme.PECENJE, r.getVrstaPripreme());
        assertEquals(10, r.getPodatak());
    }

    @Test
    public void palacinkeTest() {
        Recept r = new Recept("Palačinke");
        r.dodajSastojak(new PraskastiSastojak("brašna", 200));
        r.dodajSastojak(new TecniSastojak("jajeta", 2));
        r.dodajSastojak(new TecniSastojak("ulja", 1));
        r.dodajSastojak(new TecniSastojak("mlijeka", 3));
        r.dodajSastojak(new PraskastiSastojak("šećera", 10));
        r.dodajSastojak(new PraskastiSastojak("soli", 10));
        r.setVrstaPripreme(VrstaPripreme.PRZENJE);
        r.setPodatak(5);
        String rezultat = "Recept za Palačinke\n200 g brašna\n2 dl jajeta\n1 dl ulja\n3 dl mlijeka\n10 g šećera\n10 g soli\nPržiti 5 minuta";
        assertEquals(rezultat, r.toString());
    }

    @Test
    public void istoJeloTest() {
        Recept r = new Recept("Uštipci");
        r.dodajSastojak(new PraskastiSastojak("brašna", 200));
        // Dodajemo isti sastojak
        r.dodajSastojak(new PraskastiSastojak("brašna", 100));
        // Sljedeći sastojak nije isti jer nije istog tipa
        r.dodajSastojak(new TecniSastojak("brašna", 100));
        r.setVrstaPripreme(VrstaPripreme.PECENJE);
        r.setPodatak(200);
        String rezultat = "Recept za Uštipci\n300 g brašna\n100 dl brašna\nPeći na 200 stepeni";
        assertEquals(rezultat, r.toString());
    }

    @Test
    public void izbaciSastojakTest() {
        Recept r = new Recept("Grašak");
        r.dodajSastojak(new PraskastiSastojak("graška", 400));
        r.dodajSastojak(new PraskastiSastojak("pilećeg mesa", 300));
        r.dodajSastojak(new TecniSastojak("ulja", 1));
        r.dodajSastojak(new PraskastiSastojak("luka", 100));
        // Izbacujemo ulje, količina nije bitna
        r.izbaciSastojak(new TecniSastojak("ulja", 2));
        r.dodajSastojak(new PraskastiSastojak("maslaca", 50));
        r.dodajSastojak(new PraskastiSastojak("mrkve", 100));
        r.setVrstaPripreme(VrstaPripreme.KUHANJE);
        r.setPodatak(30);
        String rezultat = "Recept za Grašak\n400 g graška\n300 g pilećeg mesa\n100 g luka\n50 g maslaca\n100 g mrkve\nKuhati 30 minuta";
        assertEquals(rezultat, r.toString());
    }

    @Test
    public void izbaciSastojakIzuzetakTest() {
        Recept r = new Recept("Palačinke");
        r.dodajSastojak(new PraskastiSastojak("brašna", 200));
        r.dodajSastojak(new TecniSastojak("jajeta", 2));
        r.dodajSastojak(new TecniSastojak("ulja", 1));
        r.dodajSastojak(new TecniSastojak("mlijeka", 3));
        assertThrows(NoSuchSastojakException.class,
                () -> r.izbaciSastojak(new PraskastiSastojak("soli", 1)),
                "Nepoznat sastojak soli");
    }

    @Test
    public void izbaciSastojakIzuzetakTest2() {
        Recept r = new Recept("Palačinke");
        r.dodajSastojak(new PraskastiSastojak("brašna", 200));
        r.dodajSastojak(new TecniSastojak("jajeta", 2));
        r.dodajSastojak(new TecniSastojak("ulja", 1));
        r.dodajSastojak(new TecniSastojak("mlijeka", 3));
        assertThrows(NoSuchSastojakException.class,
                // Nije isti tip sastojka
                () -> r.izbaciSastojak(new PraskastiSastojak("ulja", 1)),
                "Nepoznat sastojak ulja");
    }

    @Test
    public void potpunoPrazanReceptTest() {
        Recept r = new Recept("Test");
        String rezultat = "Recept za Test\n\nKuhati 0 minuta";
        assertEquals(rezultat, r.toString());
    }
}