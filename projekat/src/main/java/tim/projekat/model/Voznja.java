package tim.projekat.model;

import tim.projekat.requestDTO.VoznjaDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "voznja")
public class Voznja {
    @ManyToOne
    @JoinColumn(name = "vozac_id", nullable = false)
    private Vozac vozac;

    @ManyToMany(targetEntity = Klijent.class)
    private List<Klijent> klijenti;

    private String polaziste;
    //    @ElementCollection
//    private List<String> stanice;
    private String destinacija;     // Za sad String dok ne rešimo rad sa mapama
    private double brojKilometara;
    private String napomena;
    private LocalDateTime datumVreme;
    private double cena;
    private Boolean gotova;
    private int ocena;

    @Id
    @GeneratedValue
    private Long id;

    public Voznja() {
    }

    public Voznja(List<Klijent> klijenti, VoznjaDTO vDTO) {
        this.klijenti = klijenti;
        this.gotova = false;
        this.ocena = 0;
        this.polaziste = vDTO.getFrom();
        this.destinacija = vDTO.getTo();
        this.napomena = vDTO.getWarn();
        this.brojKilometara = vDTO.getKm();
        this.cena = vDTO.getPrice();
        this.datumVreme = LocalDateTime.parse(vDTO.getDate().split("\\.")[0]);
    }

    public Voznja(Vozac vozac, List<Klijent> putnici, double brojKilometara, String napomena, LocalDateTime datumVreme, double cena) {
        this.vozac = vozac;
        this.klijenti = putnici;
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

    public List<Klijent> getPutnici() {
        return klijenti;
    }

    public void setPutnici(List<Klijent> putnici) {
        this.klijenti = putnici;
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

    public String getPolaziste() {
        return polaziste;
    }

    public void setPolaziste(String polaziste) {
        this.polaziste = polaziste;
    }

//    public List<String> getStanice() {
//        return stanice;
//    }
//
//    public void setStanice(List<String> stanice) {
//        this.stanice = stanice;
//    }

    public String getDestinacija() {
        return destinacija;
    }

    public void setDestinacija(String destinacija) {
        this.destinacija = destinacija;
    }

    @Override
    public String toString() {
        return "Voznja{" +
                "vozac=" + vozac +
                ", putnici=" + klijenti +
                ", polaziste='" + polaziste + '\'' +
//                ", stanice=" + stanice +
                ", destinacija='" + destinacija + '\'' +
                ", brojKilometara=" + brojKilometara +
                ", napomena='" + napomena + '\'' +
                ", datumVreme=" + datumVreme +
                ", cena=" + cena +
                ", gotova=" + gotova +
                ", ocena=" + ocena +
                ", id=" + id +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
