package tim.projekat.model;

import tim.projekat.requestDTO.RegisterDTO;
import tim.projekat.model.enums.NacinPlacanja;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@DiscriminatorValue("klijent")
public class Klijent extends Korisnik {
    private NacinPlacanja nacinPlacanja;
    private String podaciPlacanja;
    private Boolean uVoznji;    // Da li je trenutno u voznji
    private Boolean blokiran;   // Da li je blokiran od strane administratora
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "voznje_klijenti",
            joinColumns = @JoinColumn(name = "klijent_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "voznja_id", referencedColumnName = "id"))
    private List<Voznja> voznje;


    public Klijent(RegisterDTO dto) {
        super(dto.getEmail(), dto.getPassword(),dto.getName(),dto.getLastname(),dto.getCity(),dto.getPhone());

        this.uVoznji = false;
        this.blokiran = false;
        this.voznje = new ArrayList<>();

        this.nacinPlacanja=NacinPlacanja.Default;
        this.podaciPlacanja="";
    }


    public Klijent() {

    }

    public Klijent(String email, String lozinka, String ime, String prezime, String grad, String brojTel) {
        super(email, lozinka,ime,prezime,grad,brojTel);

        this.uVoznji = false;
        this.blokiran = false;
        this.voznje = new ArrayList<>();
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

    public void addVoznja(Voznja voznja) {
        this.voznje.add(voznja);
    }

    @Override
    public String toString() {
        return "Klijent{" +
                "nacinPlacanja=" + nacinPlacanja +
                ", podaciPlacanja='" + podaciPlacanja + '\'' +
                ", uVoznji=" + uVoznji +
                ", blokiran=" + blokiran +
                ", voznje=" + voznje +
                "} " + super.toString();
    }
}
