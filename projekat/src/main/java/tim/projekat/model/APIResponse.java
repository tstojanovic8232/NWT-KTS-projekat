package tim.projekat.model;

public class APIResponse {
    private String display_name;
    private Address address;

    public APIResponse() {
    }

    public APIResponse(String display_name, Address address) {
        this.display_name = display_name;
        this.address = address;
    }

    public String getdisplay_name() {
        return display_name;
    }

    public void setdisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "APIResponse{" +
                "display_name='" + display_name + '\'' +
                ", address=" + address +
                '}';
    }
}
