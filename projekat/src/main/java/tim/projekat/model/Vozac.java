package tim.projekat.model;

import tim.projekat.model.enums.NacinPlacanja;

import javax.persistence.*;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@DiscriminatorValue("vozac")
public class Vozac extends Korisnik {

    private String trenutna_lokacija;
    private NacinPlacanja nacinPlacanja;
    private String podaciPlacanja;

    private Boolean uVoznji;    // Da li je trenutno u voznji
    private Boolean blokiran;   // Da li je blokiran od strane administratora
    private Boolean status; // Da li je aktivan na sistemu, tj. da li je prijavljen
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vozilo_id", referencedColumnName = "id")
    private Vozilo vozilo;

    @OneToMany
    private List<Voznja> voznje;

    public Vozac() {
        super();
    }

    public Vozac(String email, String lozinka, String ime, String prezime, String grad, String brojTel, Vozilo vozilo, String lokacija) {
        super(email, lozinka, ime, prezime, grad, brojTel);
        this.trenutna_lokacija = lokacija;
        this.vozilo = vozilo;
        this.blokiran = false;
        this.uVoznji = false;
        this.status = false;
        this.setAktivan(true);
    }

    public String getTrenutna_lokacija() {
        return trenutna_lokacija;
    }

    public void setTrenutna_lokacija(String trenutna_lokacija) {
        this.trenutna_lokacija = trenutna_lokacija;
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


    public Boolean getuVoznji() {
        return uVoznji;
    }

    public void setuVoznji(Boolean uVoznji) {
        this.uVoznji = uVoznji;
    }

    public Boolean getBlokiran() {
        return blokiran;
    }

    public void setBlokiran(Boolean blokiran) {
        this.blokiran = blokiran;
    }

    public NacinPlacanja getNacinPlacanja() {
        return nacinPlacanja;
    }

    public void setNacinPlacanja(NacinPlacanja nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }

    public String getPodaciPlacanja() {
        return podaciPlacanja;
    }

    public void setPodaciPlacanja(String podaciPlacanja) {
        this.podaciPlacanja = podaciPlacanja;
    }

    @Override
    public String toString() {
        return "Vozac{" +
                "trenutna_lokacija='" + trenutna_lokacija + '\'' +
                ", nacinPlacanja=" + nacinPlacanja +
                ", podaciPlacanja='" + podaciPlacanja + '\'' +
                ", uVoznji=" + uVoznji +
                ", blokiran=" + blokiran +
                ", status=" + status +
                ", vozilo=" + vozilo +
                ", voznje=" + voznje +
                "} " + super.toString();
    }
}
