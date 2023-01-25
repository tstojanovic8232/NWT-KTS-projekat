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

    private Boolean aktivan;    // Da li je kliknuo na link za aktivaciju u mailu


    public Korisnik() {
    }

    public Korisnik(String email, String lozinka) {
        this.email = email;
        this.lozinka = lozinka;
        this.aktivan = false;
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
                ", aktivan=" + aktivan +
                '}';
    }
}
