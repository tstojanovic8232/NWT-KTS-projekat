package tim.projekat.model;

import tim.projekat.model.enums.NacinPlacanja;
import tim.projekat.requestDTO.RegisterDTO;
import tim.projekat.requestDTO.RegisterVozacDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@DiscriminatorValue("vozac")
public class Vozac extends Korisnik {

    private String trenutnaLokacija;
    private NacinPlacanja nacinPlacanja;
    private String podaciPlacanja;

    private Boolean uVoznji;    // Da li je trenutno u voznji
    private Boolean blokiran;   // Da li je blokiran od strane administratora
    private Boolean status; // Da li je aktivan na sistemu, tj. da li je prijavljen
    @OneToOne
    @JoinColumn(name = "vozilo_id", referencedColumnName = "id")
    private Vozilo vozilo;

    @OneToMany
    @JoinColumn(name = "vozac_id")
    private List<Voznja> voznje;

    public Vozac() {
        super();
    }

    public Vozac(String email, String lozinka, String ime, String prezime, String grad, String brojTel, Vozilo vozilo, String lokacija) {
        super(email, lozinka, ime, prezime, grad, brojTel);
        this.trenutnaLokacija = lokacija;
        this.vozilo = vozilo;
        this.blokiran = false;
        this.uVoznji = false;
        this.status = false;
        this.setAktivan(true);
    }

    public Vozac(RegisterVozacDTO dto,Vozilo v) {
        super(dto.getEmail(),"",dto.getName(),dto.getLastname(),dto.getCity(),dto.getPhone());

        this.uVoznji = false;
        this.blokiran = false;
        this.voznje = new ArrayList<>();

        this.nacinPlacanja=NacinPlacanja.Default;
        this.podaciPlacanja="";
        this.status=false;
        this.trenutnaLokacija="";
        this.vozilo=v;


    }

    public String getTrenutnaLokacija() {
        return trenutnaLokacija;
    }

    public void setTrenutnaLokacija(String trenutna_lokacija) {
        this.trenutnaLokacija = trenutna_lokacija;
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

    public void addVoznja(Voznja voznja) {
        this.voznje.add(voznja);
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
                "trenutna_lokacija='" + trenutnaLokacija + '\'' +
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
