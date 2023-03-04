package tim.projekat.requestDTO;

public class SwitchStateDTO {
    private boolean state;
    private String email;

    public SwitchStateDTO() {
    }

    public SwitchStateDTO(boolean state, String email) {
        this.state = state;
        this.email = email;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SwitchStateDTO{" +
                "state=" + state +
                ", email='" + email + '\'' +
                '}';
    }
}
