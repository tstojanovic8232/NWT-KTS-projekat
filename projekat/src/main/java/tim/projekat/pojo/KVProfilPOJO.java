package tim.projekat.pojo;

import tim.projekat.model.Klijent;
import tim.projekat.model.Vozac;
public class KVProfilPOJO {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String city;
    private String phone;
    private String billingType;
    private String billingInfo;

    public KVProfilPOJO(String name, String lastname, String email, String password, String city, String phone, String billingType, String billingInfo) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.city = city;
        this.phone = phone;
        this.billingType = billingType;
        this.billingInfo = billingInfo;
    }

    public KVProfilPOJO() {
    }

    public KVProfilPOJO(Klijent k) {
        this.name = k.getIme();
        this.lastname = k.getPrezime();
        this.email = k.getEmail();
        this.password = k.getLozinka();
        this.city = k.getGrad();
        this.phone = k.getBrojTel();
        this.billingType = k.getNacinPlacanja().toString();
        this.billingInfo = k.getPodaciPlacanja();

    }

    public KVProfilPOJO(Vozac k) {
        this.name = k.getIme();
        this.lastname = k.getPrezime();
        this.email = k.getEmail();
        this.password = k.getLozinka();
        this.city = k.getGrad();
        this.phone = k.getBrojTel();
        this.billingType = k.getNacinPlacanja().toString();
        this.billingInfo = k.getPodaciPlacanja();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public String getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(String billingInfo) {
        this.billingInfo = billingInfo;
    }

    @Override
    public String toString() {
        return "ProfilPOJO{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", billingType='" + billingType + '\'' +
                ", billingInfo='" + billingInfo + '\'' +
                '}';
    }
}
