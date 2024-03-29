package tim.projekat.servisi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tim.projekat.model.Klijent;
import tim.projekat.model.Vozac;
import tim.projekat.model.Voznja;
import tim.projekat.repozitorijumi.VoznjaRepo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Voznja> getClientHistory(Klijent k) {
        List<Voznja> klijentV = k.getVoznje();
        List<Voznja> sveGotoveV = this.voznjaRepo.getVoznjasByGotovaTrue();
        List<Voznja> klijentGV = new ArrayList<Voznja>();
        for (Voznja voznja : klijentV) {
            if (sveGotoveV.contains(voznja)) klijentGV.add(voznja);
        }
        klijentGV = klijentGV.stream()
                .sorted(Comparator.comparing(Voznja::getDatumVreme,Comparator.reverseOrder()))
                .collect(Collectors.toList());
        return klijentGV;
    }

    public List<Voznja> getClientUpcoming(Klijent k) {
        List<Voznja> klijentV = k.getVoznje();
        List<Voznja> sveBuduceV = this.voznjaRepo.getVoznjasByGotovaFalse();
        List<Voznja> klijentBV = new ArrayList<Voznja>();
        for (Voznja voznja : klijentV) {
            if (sveBuduceV.contains(voznja)) klijentBV.add(voznja);
        }
        klijentBV = klijentBV.stream()
                .sorted(Comparator.comparing(Voznja::getDatumVreme))
                .collect(Collectors.toList());
        return klijentBV;
    }

    public List<Voznja> getDriverUpcoming(Vozac v) {
        List<Voznja> vozacV = v.getVoznje();
        List<Voznja> sveBuduceV = this.voznjaRepo.getVoznjasByGotovaFalse();
        List<Voznja> vozacBV = new ArrayList<Voznja>();
        for (Voznja voznja : vozacV) {
            if (sveBuduceV.contains(voznja)) vozacBV.add(voznja);
        }
        vozacBV = vozacBV.stream()
                .sorted(Comparator.comparing(Voznja::getDatumVreme))
                .collect(Collectors.toList());
        return vozacBV;
    }

    public List<Voznja> getDriverHistory(Vozac v) {
        List<Voznja> vozacV = v.getVoznje();
        List<Voznja> sveGotoveV = this.voznjaRepo.getVoznjasByGotovaTrue();
        List<Voznja> vozacGV = new ArrayList<Voznja>();
        for (Voznja voznja : vozacV) {
            if (sveGotoveV.contains(voznja)) vozacGV.add(voznja);
        }
        vozacGV = vozacGV.stream()
                .sorted(Comparator.comparing(Voznja::getDatumVreme,Comparator.reverseOrder()))
                .collect(Collectors.toList());
        return vozacGV;
    }
}
