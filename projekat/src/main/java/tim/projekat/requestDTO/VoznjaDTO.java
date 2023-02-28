package tim.projekat.requestDTO;

public class VoznjaDTO {
    String from;
    String to;
    String warn;
    String client;
    double km;
    double price;
    String date;

    public VoznjaDTO() {
    }

    public VoznjaDTO(String from, String to, String warn, String client, double km, double price, String date) {
        this.from = from;
        this.to = to;
        this.warn = warn;
        this.client = client;
        this.km = km;
        this.price = price;
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "VoznjaDTO{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", warn='" + warn + '\'' +
                ", client='" + client + '\'' +
                ", km=" + km +
                ", price=" + price +
                ", date='" + date + '\'' +
                '}';
    }
}
