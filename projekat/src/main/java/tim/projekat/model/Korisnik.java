package tim.projekat.model;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "korisnik")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tip_korisnika",
        discriminatorType = DiscriminatorType.STRING)
public class Korisnik {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String email;
    private String lozinka;


    private String ime;
    private String prezime;
    private String grad;
    private String brojTel;

    public Korisnik( String email, String lozinka, String ime, String prezime, String grad, String brojTel) {

        this.email = email;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.grad = grad;
        this.brojTel = brojTel;
        this.aktivan = false;
    }

    private Boolean aktivan;    // Da li je kliknuo na link za aktivaciju u mailu


    public Korisnik() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getAktivan() {
        return aktivan;
    }

    public void setAktivan(Boolean aktivan) {
        this.aktivan = aktivan;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", grad='" + grad + '\'' +
                ", brojTel='" + brojTel + '\'' +
                ", aktivan=" + aktivan +
                '}';
    }
}
