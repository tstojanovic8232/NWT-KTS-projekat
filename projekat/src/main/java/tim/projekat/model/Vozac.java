package tim.projekat.model;

import tim.projekat.model.enums.NacinPlacanja;

import javax.persistence.*;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@DiscriminatorValue("vozac")
public class Vozac extends Korisnik {
    private String ime;
    private String prezime;
    private String grad;
    private String brojTel;

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

    public Vozac(String email, String lozinka, String ime, String prezime, String grad, String brojTel, Vozilo vozilo) {
        super(email, lozinka);
        this.ime = ime;
        this.prezime = prezime;
        this.grad = grad;
        this.brojTel = brojTel;
        this.vozilo = vozilo;
        this.blokiran = false;
        this.uVoznji = false;
        this.status = false;
        this.setAktivan(true);
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

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getBrojTel() {
        return brojTel;
    }

    public void setBrojTel(String brojTel) {
        this.brojTel = brojTel;
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
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", grad='" + grad + '\'' +
                ", brojTel='" + brojTel + '\'' +
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
