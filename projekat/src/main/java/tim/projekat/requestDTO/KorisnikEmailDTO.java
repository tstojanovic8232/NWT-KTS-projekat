package tim.projekat.requestDTO;

public class KorisnikEmailDTO {
    private String email;
    private String role;

    public KorisnikEmailDTO() {
    }

    public KorisnikEmailDTO(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "KorisnikEmailDTO{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

