package tim.projekat.responseDTO;

import tim.projekat.model.Klijent;
import tim.projekat.model.Korisnik;
import tim.projekat.model.Voznja;

import java.time.format.DateTimeFormatter;

public class DrivingEntryDTO {
    String clientName;
    String date;
    String price;
    String origin;
    String destination;

    public DrivingEntryDTO() {
    }

    public DrivingEntryDTO(String clientName, String date, String price, String origin, String destination) {
        this.clientName = clientName;
        this.date = date;
        this.price = price;
        this.origin = origin;
        this.destination = destination;
    }

    public DrivingEntryDTO(Voznja voznja, Korisnik k, String pol, String odr) {
        this.clientName = k.getIme() + " " + k.getPrezime();
        this.date = voznja.getDatumVreme().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm"));
        this.price = String.valueOf(voznja.getCena());
        this.origin = pol;
        this.destination = odr;
    }
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "DrivingEntryDTO{" +
                "clientName='" + clientName + '\'' +
                ", date='" + date + '\'' +
                ", price='" + price + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
