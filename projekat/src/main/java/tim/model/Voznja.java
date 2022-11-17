package tim.model;

import java.time.LocalDateTime;
import java.util.List;

public class Voznja {
    private Vozac vozac;
    private List<Korisnik> putnici;
    private String polaziste;
    private List<String> stanice;
    private String destinacija;     // Za sad String dok ne rešimo rad sa mapama
    private double brojKilometara;
    private String napomena;
    private LocalDateTime datumVreme;
    private double cena;
    private Boolean gotova;
    private int ocena;

    public Voznja() {
    }

    public Voznja(Vozac vozac, List<Korisnik> putnici, double brojKilometara, String napomena, LocalDateTime datumVreme, double cena) {
        this.vozac = vozac;
        this.putnici = putnici;
        this.brojKilometara = brojKilometara;
        this.napomena = napomena;
        this.datumVreme = datumVreme;
        this.cena = cena;
        this.gotova = false;
        this.ocena = 0;
    }

    public Vozac getVozac() {
        return vozac;
    }

    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }

    public List<Korisnik> getPutnici() {
        return putnici;
    }

    public void setPutnici(List<Korisnik> putnici) {
        this.putnici = putnici;
    }

    public double getBrojKilometara() {
        return brojKilometara;
    }

    public void setBrojKilometara(double brojKilometara) {
        this.brojKilometara = brojKilometara;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public LocalDateTime getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(LocalDateTime datumVreme) {
        this.datumVreme = datumVreme;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Boolean getGotova() {
        return gotova;
    }

    public void setGotova(Boolean gotova) {
        this.gotova = gotova;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    @Override
    public String toString() {
        return "Voznja{" +
                "vozac=" + vozac +
                ", putnici=" + putnici +
                ", polaziste='" + polaziste + '\'' +
                ", stanice=" + stanice +
                ", destinacija='" + destinacija + '\'' +
                ", brojKilometara=" + brojKilometara +
                ", napomena='" + napomena + '\'' +
                ", datumVreme=" + datumVreme +
                ", cena=" + cena +
                ", gotova=" + gotova +
                ", ocena=" + ocena +
                '}';
    }
}
