package model;
import java.util.ArrayList;
import java.util.List;

public class Ledsager extends Person{
    private List<Udflugt> udflugter;
    private Deltager tilknyttetDeltager;

    Ledsager(String navn, String telefonNr, String email, Deltager deltager) {
        super(navn, telefonNr, email);
        this.tilknyttetDeltager = deltager;
        this.udflugter = new ArrayList<>();
    }

    public ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(this.udflugter);
    }

    public void tilføjUdflugt(Udflugt udflugt) {
        if (!udflugter.contains(udflugt)) {
            this.udflugter.add(udflugt);
            udflugt.tilføjLedsager(this);
        }

    }

    public String getTilknyttetDeltagerNavn() {
        return this.tilknyttetDeltager.getNavn();
    }
}
