package tim.projekat.model;

import tim.projekat.dto.RegisterDTO;
import tim.projekat.model.enums.NacinPlacanja;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@DiscriminatorValue("klijent")
public class Klijent extends Korisnik {
    private String ime;
    private String prezime;
    private String grad;
    private String brojTel;
    private NacinPlacanja nacinPlacanja;
    private String podaciPlacanja;
    private Boolean uVoznji;    // Da li je trenutno u voznji
    private Boolean blokiran;   // Da li je blokiran od strane administratora
    @ManyToMany(targetEntity = Voznja.class, mappedBy = "klijenti")
    private List<Voznja> voznje;


    public Klijent(RegisterDTO dto) {
        super(dto.getEmail(), dto.getPassword());
        this.ime = dto.getName();
        this.prezime = dto.getLastname();
        this.brojTel = dto.getPhone();
        this.uVoznji = false;
        this.blokiran = false;
        this.voznje = new ArrayList<>();
        this.grad=dto.getCity();
    }

    public Klijent() {

    }

    public Klijent(String email, String lozinka, String ime, String prezime, String grad, String brojTel) {
        super(email, lozinka);
        this.ime = ime;
        this.prezime = prezime;
        this.grad = grad;
        this.brojTel = brojTel;
        this.uVoznji = false;
        this.blokiran = false;
        this.voznje = new ArrayList<>();
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

    public List<Voznja> getVoznje() {
        return voznje;
    }

    public void setVoznje(List<Voznja> voznje) {
        this.voznje = voznje;
    }

    @Override
    public String toString() {
        return "Klijent{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", grad='" + grad + '\'' +
                ", brojTel='" + brojTel + '\'' +
                ", nacinPlacanja=" + nacinPlacanja +
                ", podaciPlacanja='" + podaciPlacanja + '\'' +
                ", uVoznji=" + uVoznji +
                ", blokiran=" + blokiran +
                ", voznje=" + voznje +
                "} " + super.toString();
    }
}
