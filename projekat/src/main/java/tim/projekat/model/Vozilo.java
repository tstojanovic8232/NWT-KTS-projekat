package tim.projekat.model;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name="vozilo")
public class Vozilo {
    private String registracija;
    private double cena;

    @Override
    public String toString() {
        return "Vozilo{" +
                "registracija='" + registracija + '\'' +
                ", cena=" + cena +
                ", vozac=" + vozac +
                ", id=" + id +
                '}';
    }

    @OneToOne
    private Vozac vozac;

    @Id
    @GeneratedValue
    private Long id;

    public Vozilo() {
    }

    public Vozilo(String registracija, double cena) {
        this.registracija = registracija;
        this.cena = cena;
    }

    public String getRegistracija() {
        return registracija;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Vozac getVozac() {
        return vozac;
    }

    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
