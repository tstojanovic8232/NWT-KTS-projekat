package tim.projekat.responseDTO;

public class LoginResponseDTO {
    private String email;
    private String role;

    public LoginResponseDTO(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public LoginResponseDTO() {
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
}
