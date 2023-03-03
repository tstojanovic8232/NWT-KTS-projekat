package tim.projekat.model;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name="vozilo")
@SequenceGenerator(name = "vozilo_seq", sequenceName = "vozilo_seq", allocationSize = 1)
public class Vozilo {
    private String registracija;
    private double cena;

    @Override
    public String toString() {
        return "Vozilo{" +
                "registracija='" + registracija + '\'' +
                ", cena=" + cena +
                ", id=" + id +
                '}';
    }



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vozilo_seq")
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
