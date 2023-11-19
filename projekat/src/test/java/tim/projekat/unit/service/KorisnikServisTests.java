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
import tim.projekat.model.*;
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
    @Test
    void findAllByTypeShouldReturnOnlyVozac() {
        String userType = "Vozac";
        List<Korisnik> userList = Arrays.asList(new Klijent(), new Vozac()); // Replace with actual user initialization

        Mockito.when(korisnikRepo.findAll()).thenReturn(userList);

        List<Korisnik> result = korisnikServis.findAllByType(userType);

        Assertions.assertEquals(1, result.size());
        Assertions.assertTrue(result.get(0) instanceof Vozac);
    }

    @Test
    void findAllByTypeShouldReturnEmptyList() {
        String userType = "NonExistentType";
        List<Korisnik> userList = Arrays.asList(new Klijent(), new Vozac()); // Replace with actual user initialization

        Mockito.when(korisnikRepo.findAll()).thenReturn(userList);

        List<Korisnik> result = korisnikServis.findAllByType(userType);

        Assertions.assertTrue(result.isEmpty());
    }
    // Add more test cases for other methods as needed


    @Test
    void getKlijentShouldReturnKlijentForVoznja() {
        // Arrange
        Voznja voznja = new Voznja();
        Klijent expectedKlijent = new Klijent();

        // Use anyLong() or eq() to handle null argument
        when(korisnikRepo.getKlijentByVoznjeContains(eq(voznja.getId()))).thenReturn(expectedKlijent);

        // Act
        Klijent resultKlijent = korisnikServis.getKlijent(voznja);

        // Assert
        assertThat(resultKlijent).isNotNull();
        assertThat(resultKlijent).isSameAs(expectedKlijent);

        // Verify that the repository method was called with the correct argument
        verify(korisnikRepo, times(1)).getKlijentByVoznjeContains(voznja.getId());
    }



    @Test
    void getKlijentShouldReturnNullForNonExistingVoznja() {
        // Arrange
        Voznja nonExistingVoznja = new Voznja();

        // Use anyLong() or eq() to handle null argument
        when(korisnikRepo.getKlijentByVoznjeContains(eq(nonExistingVoznja.getId()))).thenReturn(null);

        // Act
        Klijent resultKlijent = korisnikServis.getKlijent(nonExistingVoznja);

        // Assert
        assertThat(resultKlijent).isNull();

        // Verify that the repository method was called with the correct argument
        verify(korisnikRepo, times(1)).getKlijentByVoznjeContains(nonExistingVoznja.getId());
    }


    @BeforeEach
    void setUp() {
        // ...
    }
}

