package tim.projekat.model;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "vozilo")
@SequenceGenerator(name = "vozilo_seq", sequenceName = "vozilo_seq", allocationSize = 1)
public class Vozilo {
    private String registracija;
    private double cena;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vozilo_seq")
    private Long id;


    public Vozilo() {
    }

    public Vozilo(String registracija, double cena) {
        this.registracija = registracija;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Vozilo{" +
                "registracija='" + registracija + '\'' +
                ", cena=" + cena +
                ", id=" + id +
                '}';
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
