package tim.projekat.servisi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tim.projekat.model.Klijent;
import tim.projekat.model.Vozac;
import tim.projekat.model.Voznja;
import tim.projekat.repozitorijumi.VoznjaRepo;

import java.util.List;

@Service
public class VoznjaServis {
    @Autowired
    VoznjaRepo voznjaRepo;

    public void save(Voznja v) {
        this.voznjaRepo.save(v);
    }

    public void endDrive(Voznja v) {
        v.setGotova(true);
        this.voznjaRepo.save(v);
    }

    public List<Voznja> getClientHistory(Klijent k){
        return this.voznjaRepo.getVoznjasByKlijentiContainsAndGotovaTrue(k);
    }

    public List<Voznja> getClientUpcoming(Klijent k){
        return this.voznjaRepo.getVoznjasByKlijentiContainsAndGotovaFalse(k);
    }

    public List<Voznja> getDriverUpcoming(Vozac v){
        return this.voznjaRepo.getVoznjasByVozacAndGotovaFalse(v);
    }

    public List<Voznja> getDriverHistory(Vozac v){
        return this.voznjaRepo.getVoznjasByVozacAndGotovaTrue(v);
    }
}
