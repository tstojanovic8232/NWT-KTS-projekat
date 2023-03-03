package tim.projekat.model;

public class Address {
    private String displayName;
    private String countryCode;
    private String city;
    private String state;
    private String postcode;

    public Address() {
    }

    public Address(String displayName, String countryCode, String city, String state, String postcode) {
        this.displayName = displayName;
        this.countryCode = countryCode;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return displayName + ", " + city;
    }
}
