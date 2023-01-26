package tim.projekat.pojo;

public class LoginPOJO {
    private String email;
    private String role;

    public LoginPOJO(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public LoginPOJO() {
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
