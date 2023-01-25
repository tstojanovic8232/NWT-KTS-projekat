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
}
