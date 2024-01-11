package tim.projekat.unit.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tim.projekat.model.Klijent;
import tim.projekat.model.Vozac;
import tim.projekat.model.Voznja;
import tim.projekat.repozitorijumi.VoznjaRepo;
import tim.projekat.servisi.VoznjaServis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VoznjaServisTests {

    @Mock
    private VoznjaRepo vozRepo;

    @InjectMocks
    private VoznjaServis vozServis;

    private Voznja voz;
    private Voznja voz2;
    private Voznja voz3;

    private List<Voznja> buduceVoznje;

    private Vozac vozac;
    private Klijent klijent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        voz = new Voznja();
        voz.setId(1L);
        voz.setDatumVreme(LocalDateTime.parse("2023-03-15 08:30:00", formatter));
        voz.setGotova(false);

        voz2 = new Voznja();
        voz2.setId(2L);
        voz2.setDatumVreme(LocalDateTime.parse("2023-03-15 09:30:00", formatter));
        voz2.setGotova(false);

        voz3 = new Voznja();
        voz3.setId(3L);
        voz3.setDatumVreme(LocalDateTime.parse("2023-03-20 08:30:00", formatter));
        voz3.setGotova(true);

        vozac = new Vozac();
        List<Voznja> voznje = new ArrayList<>();
        voznje.add(voz);
        voznje.add(voz3);
        vozac.setVoznje(voznje);

        klijent = new Klijent();
        List<Voznja> klijentVoznje = new ArrayList<>();
        klijentVoznje.add(voz);
        klijentVoznje.add(voz2);
        klijent.setVoznje(klijentVoznje);


        buduceVoznje = new ArrayList<>();
    }

    @Test
    void givenVozac_thenReturnUpcomingVoznje() {
        // Arrange
        buduceVoznje.add(voz);
        buduceVoznje.add(voz2);
        when(vozRepo.getVoznjasByGotovaFalse()).thenReturn(buduceVoznje);

        // Act
        List<Voznja> upcomingVoznje = vozServis.getDriverUpcoming(vozac);

        // Assert
        assertThat(upcomingVoznje).isNotNull();
        assertThat(upcomingVoznje).hasSize(1);
        assertThat(upcomingVoznje.get(0)).isEqualTo(voz);
    }

    @Test
    void givenVozac_whenVozacVoznjeIsEmpty_thenReturnEmptyList() {
        // Arrange
        buduceVoznje.add(voz);
        buduceVoznje.add(voz2);
        when(vozRepo.getVoznjasByGotovaFalse()).thenReturn(buduceVoznje);
        vozac.setVoznje(new ArrayList<>());

        // Act
        List<Voznja> upcomingVoznje = vozServis.getDriverUpcoming(vozac);

        // Assert
        assertThat(upcomingVoznje).isNotNull();
        assertThat(upcomingVoznje).isEmpty();
    }

    @Test
    void givenVozac_whenUpcomingVoznjeIsEmpty_thenReturnEmptyList() {
        // Arrange
        when(vozRepo.getVoznjasByGotovaFalse()).thenReturn(buduceVoznje);

        // Act
        List<Voznja> upcomingVoznje = vozServis.getDriverUpcoming(vozac);

        // Assert
        assertThat(upcomingVoznje).isNotNull();
        assertThat(upcomingVoznje).isEmpty();
    }

    @Test
    void givenVozac_whenVozacVoznjeAllHaveGotovaTrue_thenReturnEmptyList() {
        // Arrange
        when(vozRepo.getVoznjasByGotovaFalse()).thenReturn(buduceVoznje);
        List<Voznja> voznje = new ArrayList<>();
        voznje.add(voz3);
        vozac.setVoznje(voznje);

        // Act
        List<Voznja> upcomingVoznje = vozServis.getDriverUpcoming(vozac);

        // Assert
        assertThat(upcomingVoznje).isNotNull();
        assertThat(upcomingVoznje).isEmpty();
    }

    @Test
    void givenNullVozac_whenUpcoming_thenThrowNullPointerException() {
        // Arrange
        Vozac v = null;

        // Act and Assert
        assertThrows(NullPointerException.class, () -> vozServis.getDriverUpcoming(v));
    }

    @Test
    void givenVozac_thenReturnUpcomingVoznjeSortedByDatumVreme() {
        // Arrange
        buduceVoznje.add(voz2);
        buduceVoznje.add(voz);
        when(vozRepo.getVoznjasByGotovaFalse()).thenReturn(buduceVoznje);
        vozac.getVoznje().add(voz2);

        // Act
        List<Voznja> upcomingVoznje = vozServis.getDriverUpcoming(vozac);

        // Assert
        assertThat(upcomingVoznje).isNotNull();
        assertThat(upcomingVoznje).hasSize(2);
        assertThat(upcomingVoznje).containsExactly(voz, voz2);
    }

    @Test
    void givenKlijent_thenReturnClientHistory() {
        // Arrange
        List<Voznja> klijentVoznje = new ArrayList<>();
        klijentVoznje.add(voz);
        klijentVoznje.add(voz2);
        klijent.setVoznje(klijentVoznje);

        List<Voznja> gotoveVoznje = new ArrayList<>();
        gotoveVoznje.add(voz);
        gotoveVoznje.add(voz2);
        when(vozRepo.getVoznjasByGotovaTrue()).thenReturn(gotoveVoznje);

        // Act
        List<Voznja> clientHistory = vozServis.getClientHistory(klijent);

        // Assert
        assertThat(clientHistory).isNotNull();
        assertThat(clientHistory).hasSize(2);
        assertThat(clientHistory).containsExactly(voz2, voz);
    }

    @Test
    void givenKlijentWithNoHistory_thenReturnEmptyList() {
        // Arrange
        List<Voznja> gotoveVoznje = new ArrayList<>();
        when(vozRepo.getVoznjasByGotovaTrue()).thenReturn(gotoveVoznje);
        klijent.setVoznje(new ArrayList<>());

        // Act
        List<Voznja> clientHistory = vozServis.getClientHistory(klijent);

        // Assert
        assertThat(clientHistory).isNotNull();
        assertThat(clientHistory).isEmpty();
    }

    @Test
    void givenNullKlijent_thenThrowNullPointerException() {
        // Arrange
        Klijent klijent = null;

        // Act and Assert
        assertThrows(NullPointerException.class, () -> vozServis.getClientHistory(klijent));
    }

    @Test
    void givenVozac_thenReturnDriverHistory() {
        // Arrange
        List<Voznja> vozacVoznje = new ArrayList<>();
        vozacVoznje.add(voz);
        vozacVoznje.add(voz3);
        vozac.setVoznje(vozacVoznje);

        List<Voznja> gotoveVoznje = new ArrayList<>();
        gotoveVoznje.add(voz);
        gotoveVoznje.add(voz3);
        when(vozRepo.getVoznjasByGotovaTrue()).thenReturn(gotoveVoznje);

        // Act
        List<Voznja> driverHistory = vozServis.getDriverHistory(vozac);

        // Assert
        assertThat(driverHistory).isNotNull();
        assertThat(driverHistory).hasSize(2);
        assertThat(driverHistory).containsExactly(voz3, voz);
    }

    @Test
    void givenVozacWithNoHistory_thenReturnEmptyList() {
        // Arrange
        List<Voznja> gotoveVoznje = new ArrayList<>();
        when(vozRepo.getVoznjasByGotovaTrue()).thenReturn(gotoveVoznje);
        vozac.setVoznje(new ArrayList<>());

        // Act
        List<Voznja> driverHistory = vozServis.getDriverHistory(vozac);

        // Assert
        assertThat(driverHistory).isNotNull();
        assertThat(driverHistory).isEmpty();
    }

    @Test
    void givenNullVozac_whenHistory_thenThrowNullPointerException() {
        // Arrange
        Vozac vozac = null;

        // Act and Assert
        assertThrows(NullPointerException.class, () -> vozServis.getDriverHistory(vozac));
    }


}
