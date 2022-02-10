package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class KuharTest {
    public Kuhar pomocnaKuhar() {
        Kuhar kuhar = new Kuhar();
        Recept r = new Recept("Palačinke");
        r.dodajSastojak(new PraskastiSastojak("brašna", 200));
        r.dodajSastojak(new TecniSastojak("jajeta", 2));
        r.dodajSastojak(new TecniSastojak("ulja", 1));
        r.dodajSastojak(new TecniSastojak("mlijeka", 3));
        r.dodajSastojak(new PraskastiSastojak("šećera", 10));
        r.dodajSastojak(new PraskastiSastojak("soli", 10));
        r.setVrstaPripreme(VrstaPripreme.PRZENJE);
        r.setPodatak(5);
        kuhar.dodajRecept(r);

        r = new Recept("Grašak");
        r.dodajSastojak(new PraskastiSastojak("graška", 400));
        r.dodajSastojak(new PraskastiSastojak("pilećeg mesa", 300));
        r.dodajSastojak(new TecniSastojak("ulja", 1));
        r.dodajSastojak(new PraskastiSastojak("luka", 100));
        r.setVrstaPripreme(VrstaPripreme.KUHANJE);
        r.setPodatak(30);
        kuhar.dodajRecept(r);
        kuhar.dodajRecept(new Recept("R1"));
        kuhar.dodajRecept(new Recept("R2"));
        kuhar.dodajRecept(new Recept("R3"));

        r = new Recept("Uštipci");
        r.dodajSastojak(new PraskastiSastojak("brašna", 200));
        r.setVrstaPripreme(VrstaPripreme.PECENJE);
        r.setPodatak(200);
        kuhar.dodajRecept(r);

        return kuhar;
    }


    @Test
    public void prazanKuhar() {
        Kuhar kuhar = new Kuhar();
        assertNull(kuhar.dajRecept("Neko jelo"));
    }

    @Test
    public void dodajReceptTest() {
        Kuhar kuhar = new Kuhar();
        Recept r = new Recept("Palačinke");
        r.dodajSastojak(new PraskastiSastojak("brašna", 200));
        r.dodajSastojak(new TecniSastojak("jajeta", 2));
        r.dodajSastojak(new TecniSastojak("ulja", 1));
        r.dodajSastojak(new TecniSastojak("mlijeka", 3));
        r.dodajSastojak(new PraskastiSastojak("šećera", 10));
        r.dodajSastojak(new PraskastiSastojak("soli", 10));
        r.setVrstaPripreme(VrstaPripreme.PRZENJE);
        r.setPodatak(5);

        kuhar.dodajRecept(r);
        Recept r2 = kuhar.dajRecept("Palačinke");
        String rezultat = "Recept za Palačinke\n200 g brašna\n2 dl jajeta\n1 dl ulja\n3 dl mlijeka\n10 g šećera\n10 g soli\nPržiti 5 minuta";
        assertEquals(rezultat, r2.toString());
    }

    @Test
    public void dajReceptTest() {
        Kuhar kuhar = pomocnaKuhar();

        Recept r = kuhar.dajRecept("Palačinke");
        String rezultat = "Recept za Palačinke\n200 g brašna\n2 dl jajeta\n1 dl ulja\n3 dl mlijeka\n10 g šećera\n10 g soli\nPržiti 5 minuta";
        assertEquals(rezultat, r.toString());
        r = kuhar.dajRecept("Uštipci");
        assertEquals("Uštipci", r.getNazivJela());
    }

    @Test
    public void brojRecepataTest() {
        Kuhar kuhar = pomocnaKuhar();
        assertEquals(6, kuhar.brojRecepata());
    }

    @Test
    public void dajReceptNullTest() {
        Kuhar kuhar = new Kuhar();
        kuhar.dodajRecept(new Recept("R1"));
        kuhar.dodajRecept(new Recept("R2"));
        kuhar.dodajRecept(new Recept("R3"));
        assertNull(kuhar.dajRecept("R"));
        assertEquals("R2", kuhar.dajRecept("R2").getNazivJela());
        assertNull(kuhar.dajRecept("R4"));
    }

    @Test
    public void receptiSaSastojkomTest() {
        Kuhar kuhar = pomocnaKuhar();

        ArrayList<Recept> recepti = kuhar.receptiSaSastojkom(new PraskastiSastojak("brašna", 10));
        assertEquals(2, recepti.size());
        if (recepti.get(0).getNazivJela().equals("Palačinke")) {
            assertEquals("Uštipci", recepti.get(1).getNazivJela());
            String rezultat = "Recept za Palačinke\n200 g brašna\n2 dl jajeta\n1 dl ulja\n3 dl mlijeka\n10 g šećera\n10 g soli\nPržiti 5 minuta";
            assertEquals(rezultat, recepti.get(0).toString());
        } else {
            assertEquals("Uštipci", recepti.get(0).getNazivJela());
            assertEquals("Palačinke", recepti.get(1).getNazivJela());
            String rezultat = "Recept za Palačinke\n200 g brašna\n2 dl jajeta\n1 dl ulja\n3 dl mlijeka\n10 g šećera\n10 g soli\nPržiti 5 minuta";
            assertEquals(rezultat, recepti.get(1).toString());
        }
    }

    @Test
    public void receptiSaSastojkomTest2() {
        Kuhar kuhar = new Kuhar();
        kuhar.dodajRecept(new Recept("R1"));
        kuhar.dodajRecept(new Recept("R2"));
        ArrayList<Recept> recepti = kuhar.receptiSaSastojkom(new PraskastiSastojak("sastojak", 10));
        assertEquals(0, recepti.size());

        // Nije bitan tip sastojka
        Recept r = new Recept("Jelo 1");
        r.dodajSastojak(new PraskastiSastojak("sastojak", 100));
        kuhar.dodajRecept(r);
        kuhar.dodajRecept(new Recept("R3"));
        r = new Recept("Jelo 2");
        r.dodajSastojak(new TecniSastojak("sastojak", 200));
        kuhar.dodajRecept(r);

        assertEquals(5, kuhar.brojRecepata());

        recepti = kuhar.receptiSaSastojkom(new PraskastiSastojak("sastojak", 10));
        assertEquals(2, recepti.size());
    }

    @Test
    public void sviSastojciTest() {
        Kuhar kuhar = new Kuhar();
        Set<Sastojak> sastojci = kuhar.sviSastojci();
        assertEquals(0, sastojci.size());

        kuhar = pomocnaKuhar();

        sastojci = kuhar.sviSastojci();
        String rezultat = "";
        for (Sastojak s : sastojci)
            rezultat += s + "\n";
        String ocekivani = "1 dl ulja\n2 dl jajeta\n3 dl mlijeka\n10 g šećera\n100 g luka\n200 g brašna\n300 g pilećeg mesa\n400 g graška\n";
        assertEquals(rezultat, ocekivani);
    }

    @Test
    public void sviSastojciTest2() {
        Kuhar kuhar = new Kuhar();
        kuhar.dodajRecept(new Recept("R1"));
        kuhar.dodajRecept(new Recept("R2"));

        Recept r = new Recept("Jelo 1");
        r.dodajSastojak(new PraskastiSastojak("sastojak", 100));
        kuhar.dodajRecept(r);
        r = new Recept("Jelo 2");
        r.dodajSastojak(new PraskastiSastojak("sastojak", 100));
        r.dodajSastojak(new PraskastiSastojak("sastojak", 100)); // Sada je 200
        kuhar.dodajRecept(r);
        r = new Recept("Jelo 3");
        r.dodajSastojak(new PraskastiSastojak("sastojak", 50));
        r.dodajSastojak(new PraskastiSastojak("sastojak", 30));
        r.dodajSastojak(new PraskastiSastojak("sastojak", 20)); // Sada je 100
        kuhar.dodajRecept(r);

        Set<Sastojak> sastojci = sastojci = kuhar.sviSastojci();
        String rezultat = "";
        for (Sastojak s : sastojci)
            rezultat += s + "\n";
        String ocekivani = "100 g sastojak\n200 g sastojak\n";
        assertEquals(rezultat, ocekivani);
    }

    @Test
    public void filtrirajTest() {
        Kuhar kuhar = pomocnaKuhar();
        ArrayList<Recept> recepti = kuhar.filtriraj(r -> r.getNazivJela().contains("k"));
        assertEquals(2, recepti.size());
    }

    @Test
    public void filtrirajTest2() {
        Kuhar kuhar = pomocnaKuhar();
        ArrayList<Recept> recepti = kuhar.filtriraj(r -> r.getVrstaPripreme().equals(VrstaPripreme.PECENJE));
        assertEquals(1, recepti.size());
        assertEquals("Uštipci", recepti.get(0).getNazivJela());
    }
}