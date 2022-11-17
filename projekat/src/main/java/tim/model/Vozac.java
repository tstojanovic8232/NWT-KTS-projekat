package tim.model;

import java.util.List;

public class Vozac extends Korisnik {
    private Boolean status; // Da li je aktivan na sistemu, tj. da li je prijavljen
    private Vozilo vozilo;

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

    @Override
    public String toString() {
        return "Vozac{" +
                "status=" + status +
                ", vozilo=" + vozilo +
                "} : " + super.toString();
    }
}
