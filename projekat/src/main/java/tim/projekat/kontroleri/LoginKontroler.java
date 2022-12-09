package tim.projekat.kontroleri;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(
        value = "/login",
        method = RequestMethod.GET,
        consumes = "application/json",
        produces = "application/json")
public class LoginKontroler {

    @PostMapping("/test")
    public void login() {


    }
}
