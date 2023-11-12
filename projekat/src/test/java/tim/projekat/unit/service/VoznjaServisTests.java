package tim.projekat.unit.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tim.projekat.model.Vozac;
import tim.projekat.model.Voznja;
import tim.projekat.repozitorijumi.VoznjaRepo;
import tim.projekat.servisi.VoznjaServis;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        voz = new Voznja();
        voz.setId(1L);
        voz.setGotova(false);

        voz2 = new Voznja();
        voz2.setId(2L);
        voz2.setGotova(false);

        voz3 = new Voznja();
        voz3.setId(3L);
        voz3.setGotova(true);

        vozac = new Vozac();
        List<Voznja> voznje = new ArrayList<>();
        voznje.add(voz);
        voznje.add(voz3);
        vozac.setVoznje(voznje);

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
}
