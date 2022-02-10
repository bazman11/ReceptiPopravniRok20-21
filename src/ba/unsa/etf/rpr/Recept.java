package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Recept {
    private String nazivJela;
    private ArrayList<Sastojak> sastojci = new ArrayList<>();
    private VrstaPripreme vrstaPripreme = VrstaPripreme.KUHANJE;
    private int podatak;

    public Recept(String nazivJela) {
        this.nazivJela = nazivJela;
    }

    public String getNazivJela() {
        return nazivJela;
    }

    public void setNazivJela(String nazivJela) {
        this.nazivJela = nazivJela;
    }

    public ArrayList<Sastojak> getSastojci() {
        return sastojci;
    }

    public VrstaPripreme getVrstaPripreme() {
        return vrstaPripreme;
    }

    public void setVrstaPripreme(VrstaPripreme vrstaPripreme) {
        this.vrstaPripreme = vrstaPripreme;
    }

    public int getPodatak() {
        return podatak;
    }

    public void setPodatak(int podatak) {
        this.podatak = podatak;
    }

    public void dodajSastojak(Sastojak s) {
        sastojci.stream().filter(sast -> sast.getNaziv().equals(s.getNaziv()) && sast.getClass().equals(s.getClass())).
            findFirst().ifPresentOrElse(sast -> sast.setKolicina(sast.getKolicina()+s.getKolicina()), () -> sastojci.add(s));
    }

    public void izbaciSastojak(Sastojak s) {
        sastojci.stream().filter(sast -> sast.getNaziv().equals(s.getNaziv()) && sast.getClass().equals(s.getClass())).
            findFirst().ifPresentOrElse(sast -> sastojci.remove(sast), () -> {
                throw new NoSuchSastojakException("Nepoznat sastojak " + s.getNaziv());
            });
    }

    @Override
    public String toString() {
        return "Recept za " + nazivJela + "\n" + sastojci.stream().map(Object::toString).collect(Collectors.joining("\n")) +
                "\n" + (vrstaPripreme == VrstaPripreme.PECENJE ? "Peći na "+podatak+" stepeni" :
                        vrstaPripreme == VrstaPripreme.KUHANJE ? "Kuhati "+podatak+" minuta" : "Pržiti " + podatak + " minuta");
    }
}
