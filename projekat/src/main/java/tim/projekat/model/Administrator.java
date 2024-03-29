package tim.projekat.model;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@DiscriminatorValue("admin")
public class Administrator extends Korisnik {
    public Administrator() {
    }

    public Administrator(String email, String lozinka, String ime, String prezime, String grad, String brojTel) {
        super(email, lozinka, ime, prezime, grad, brojTel);
        this.setAktivan(true);
    }

    @Override
    public String toString() {
        return "Administrator{} " + super.toString();
    }
}
