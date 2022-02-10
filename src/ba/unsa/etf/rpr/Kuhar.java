package ba.unsa.etf.rpr;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Kuhar {
    final private HashMap<String, Recept> mapa = new HashMap<>();

    public void dodajRecept(Recept r) {
        mapa.put(r.getNazivJela(), r);
    }

    public Recept dajRecept(String nazivJela) {
        return mapa.get(nazivJela);
    }

    public int brojRecepata() {
        return mapa.size();
    }

    public ArrayList<Recept> receptiSaSastojkom(Sastojak s) {
        return mapa.values().stream().filter(recept ->
                recept.getSastojci().stream().anyMatch(sast -> sast.getNaziv().equals(s.getNaziv()))
        ).collect(Collectors.toCollection(ArrayList::new));
    }

    public Set<Sastojak> sviSastojci() {
        /* return mapa.values().stream().map(Recept::getSastojci).flatMap(List::stream).collect(Collectors.toCollection(TreeSet::new)); */
        Set<Sastojak> rezultat = new TreeSet<>();
        for(Recept r : mapa.values()) {
            System.out.println("Recept " + r.getNazivJela());
            for (Sastojak s : r.getSastojci()) {
                System.out.println("Dodajem sastojak " + s.getNaziv());
                rezultat.add(s);
            }
        }
        return rezultat;
    }

    public ArrayList<Recept> filtriraj(Predicate<Recept> p) {
        return mapa.values().stream().filter(p).collect(Collectors.toCollection(ArrayList::new));
    }
}
