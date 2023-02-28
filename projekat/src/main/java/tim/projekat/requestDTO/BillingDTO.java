package tim.projekat.requestDTO;

public class BillingDTO {

   private String billingData;
   private String billingType;
   private String email;

   private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BillingDTO(String billingData, String billingType, String email, String role) {
        this.billingData = billingData;
        this.billingType = billingType;
        this.email = email;
        this.role=role;
    }

    public BillingDTO() {

    }

    public String getBillingData() {
        return billingData;
    }

    public void setBillingData(String billingData) {
        this.billingData = billingData;
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "BillingDTO{" +
                "billingData='" + billingData + '\'' +
                ", billingType='" + billingType + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
