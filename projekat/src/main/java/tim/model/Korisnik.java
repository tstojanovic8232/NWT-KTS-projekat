package tim.model;

import tim.model.enums.NacinPlacanja;

public class Korisnik {
    private String email;
    private String lozinka;
    private String ime;
    private String prezime;
    private String grad;
    private String brojTel;
    private Boolean aktivan;    // Da li je kliknuo na link za aktivaciju u mailu
    private NacinPlacanja nacinPlacanja;
    private String podaciPlacanja;
    private Boolean uVoznji;    // Da li je trenutno u voznji
    private Boolean blokiran;   // Da li je blokiran od strane administratora

    public Korisnik() {
    }

    public Korisnik(String email, String lozinka, String ime, String prezime, String grad, String brojTel) {
        this.email = email;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.grad = grad;
        this.brojTel = brojTel;
        this.aktivan = false;
        this.uVoznji = false;
        this.blokiran = false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
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

    public Boolean getAktivan() {
        return aktivan;
    }

    public void setAktivan(Boolean aktivan) {
        this.aktivan = aktivan;
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

    @Override
    public String toString() {
        return "Korisnik{" +
                "email='" + email + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", grad='" + grad + '\'' +
                ", brojTel='" + brojTel + '\'' +
                ", aktivan=" + aktivan +
                ", nacinPlacanja=" + nacinPlacanja +
                ", podaciPlacanja='" + podaciPlacanja + '\'' +
                ", uVoznji=" + uVoznji +
                ", blokiran=" + blokiran +
                '}';
    }
}
