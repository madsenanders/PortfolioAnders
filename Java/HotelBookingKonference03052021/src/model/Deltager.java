package model;

public class Deltager extends Person{
    private String by;
    private String land;
    private String adresse;
    private Ledsager ledsager;

    public Deltager(String navn, String telefonNr, String email, String by, String land, String adresse) {
        super(navn, telefonNr, email);
        this.by = by;
        this.land = land;
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return super.getNavn();
    }

    public String getBy() {
        return by;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getLand() {
        return land;
    }

    public Ledsager opretLedsager(String email, String telefonNr, String navn) {
        Ledsager l = new Ledsager(navn, telefonNr, email, this);
        this.ledsager = l;
        return l;
    }

    public Ledsager getLedsager() {
        return ledsager;
    }

    public String getLedsagerNavn() {
        return ledsager != null ?  getLedsager().getNavn() : "";
    }
}
