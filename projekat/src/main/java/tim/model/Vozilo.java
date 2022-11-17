package tim.model;

public class Vozilo {
    private String registracija;
    private double cena;

    public Vozilo() {
    }

    public Vozilo(String registracija, double cena) {
        this.registracija = registracija;
        this.cena = cena;
    }

    public String getRegistracija() {
        return registracija;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Vozilo{" +
                "registracija='" + registracija + '\'' +
                ", cena=" + cena +
                '}';
    }
}
