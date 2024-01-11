package tim.projekat.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import tim.projekat.model.Klijent;
import tim.projekat.model.Korisnik;
import tim.projekat.model.Vozac;
import tim.projekat.model.Voznja;
import tim.projekat.requestDTO.KorisnikEmailDTO;
import tim.projekat.servisi.KorisnikServis;
import tim.projekat.servisi.VoznjaServis;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VoznjaKontrolerObavljanjeTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KorisnikServis korisnikServis;

    @Autowired
    private VoznjaServis voznjaServis;

    @Test
    public void testStartDrive() throws Exception {
        Vozac testVozac = new Vozac();
        testVozac.setEmail("test2@example.com");
        testVozac.setuVoznji(true);
        testVozac.setVoznje(new ArrayList<>());


        // Create a client
        Klijent k = new Klijent();
        k.setEmail("kli2@gmail.com");
        k.setuVoznji(true);
        k.setVoznje(new ArrayList<>());


        // Create a drive
        Voznja testVoznja = new Voznja();
        testVoznja.setGotova(false);
        voznjaServis.save(testVoznja);

        // Add the drive to both the driver and client
        testVozac.addVoznja(testVoznja);
        k.addVoznja(testVoznja);

        korisnikServis.save(k);
        korisnikServis.save(testVozac);

        KorisnikEmailDTO korisnikEmailDTO = new KorisnikEmailDTO("test2@example.com", "vozac");

        ResultActions resultActions = mockMvc.perform(post("/drives/start")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(korisnikEmailDTO)));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.gotova").value(false));


        korisnikServis.save(testVozac);
        voznjaServis.save(testVoznja);
    }

    @Test
    public void testEndDrive() throws Exception {
        Vozac testVozac = new Vozac();
        testVozac.setEmail("test1@example.com");
        testVozac.setuVoznji(true);
        testVozac.setVoznje(new ArrayList<>());


        // Create a client
        Klijent k = new Klijent();
        k.setEmail("kli@gmail.com");
        k.setuVoznji(true);
        k.setVoznje(new ArrayList<>());


        // Create a drive
        Voznja testVoznja = new Voznja();
        testVoznja.setGotova(false);
        voznjaServis.save(testVoznja);

        // Add the drive to both the driver and client
        testVozac.addVoznja(testVoznja);
        k.addVoznja(testVoznja);

        korisnikServis.save(k);
        korisnikServis.save(testVozac);
        KorisnikEmailDTO korisnikEmailDTO = new KorisnikEmailDTO("test1@example.com", "vozac");

        ResultActions resultActions = mockMvc.perform(post("/drives/stop")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(korisnikEmailDTO)));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.gotova").value(true));


        korisnikServis.save(testVozac);
        voznjaServis.save(testVoznja);
    }

    @Test
    public void testEndDriveWithInvalidEmail() throws Exception {
        Vozac testVozac = new Vozac();
        testVozac.setEmail("test9@example.com");
        testVozac.setuVoznji(true);
        korisnikServis.save(testVozac);
        KorisnikEmailDTO dto=new KorisnikEmailDTO("NONEXIST@GMAIL.COM","VOZAC");
        String jsonDto = objectMapper.writeValueAsString(dto);
        ResultActions resultActions = mockMvc.perform(post("/drives/stop")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonDto));
        System.out.println(resultActions);
        resultActions.andExpect(status().isInternalServerError());


        // Additional assertions or verifications based on your requirements
    }
}
