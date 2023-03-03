package tim.projekat.model;

public class Address {
    private String road;
    private String house_number;
    private String city;

    public Address() {
    }

    public Address(String road, String house_number, String city) {
        this.road = road;
        this.house_number = house_number;
        this.city = city;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        if (house_number != null)
            return road + " " + house_number + ", " + city;
        else
            return road + ", " + city;
    }
}
