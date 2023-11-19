package tim.projekat.unit.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tim.projekat.model.Klijent;
import tim.projekat.model.Korisnik;
import tim.projekat.model.VerificationToken;
import tim.projekat.model.Vozac;
import tim.projekat.repozitorijumi.KorisnikRepo;
import tim.projekat.repozitorijumi.TokenRepo;
import tim.projekat.servisi.KorisnikServis;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class KorisnikServisTests {

    @Mock
    private KorisnikRepo korisnikRepo;

    @Mock
    private TokenRepo tokenRepo;

    @InjectMocks
    private KorisnikServis korisnikServis;



    @Test
    void getKorisnikByEmail() {
        String email = "test@example.com";
        Klijent expectedUser = new Klijent(); // Replace with actual user initialization

        Mockito.when(korisnikRepo.getKorisnikByEmail(email)).thenReturn(expectedUser);

        Korisnik actualUser = korisnikServis.getKorisnikByEmail(email);

        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void findAllByType() {
        String userType = "Klijent";
        List<Korisnik> userList = Arrays.asList(new Klijent(), new Vozac()); // Replace with actual user initialization

        Mockito.when(korisnikRepo.findAll()).thenReturn(userList);

        List<Korisnik> result = korisnikServis.findAllByType(userType);

        Assertions.assertEquals(1, result.size()); // Assuming only one user of the specified type in the list
        Assertions.assertTrue(result.get(0) instanceof Klijent);
    }

    // Add more test cases for other methods as needed


    @BeforeEach
    void setUp() {
        // ...
    }
}

