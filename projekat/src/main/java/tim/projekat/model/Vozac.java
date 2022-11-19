package tim.projekat.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name="vozac")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Vozac extends Korisnik {
    private Boolean status; // Da li je aktivan na sistemu, tj. da li je prijavljen
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vozilo_id", referencedColumnName = "id")
    private Vozilo vozilo;

    @OneToMany
    private List<Voznja> voznje;

    public Vozac() {
    }

    public Vozac(String email, String lozinka, String ime, String prezime, String grad, String brojTel, Vozilo vozilo) {
        super(email, lozinka, ime, prezime, grad, brojTel);
        this.setAktivan(true);
        this.status = false;
        this.vozilo = vozilo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }

    public List<Voznja> getVoznje() {
        return voznje;
    }

    public void setVoznje(List<Voznja> voznje) {
        this.voznje = voznje;
    }

    @Override
    public String toString() {
        return "Vozac{" +
                "status=" + status +
                ", vozilo=" + vozilo +
                "} " + super.toString();
    }
}
