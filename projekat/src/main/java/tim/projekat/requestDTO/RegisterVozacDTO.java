package tim.projekat.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterVozacDTO {
    private String name;
    private String lastname;
    private String password;
    private String email;
    private String phone;

    private String city;
    private String registration;
    private Double price;
}
