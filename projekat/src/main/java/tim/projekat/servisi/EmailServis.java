package tim.projekat.servisi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServis {
    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mrs.isa.test@outlook.com");
        message.setTo(email);
        message.setSubject("Email Confirmation");
        message.setText("Please click on the following link to confirm your email: "
                + "http://localhost:4200/confirm?token=" + token);
        mailSender.send(message);
    }
}
