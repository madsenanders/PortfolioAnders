package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class Konference {
    private String navn;
    private LocalDate start;
    private LocalDate slut;
    private LocalDate tilmeldingStart;
    private LocalDate tilmeldingSlut;
    private String adresse;
    private double prisPrDag;
    private List<KonferenceTilmelding> konferenceTilmeldinger;
    private List<Udflugt> udflugter;
    private List<Hotel> hoteller;

    public Konference(String navn, LocalDate start, LocalDate slut, LocalDate tilmeldingStart,
                      LocalDate tilmeldingSlut, String adresse, double pris) {
        this.navn = navn;
        this.start = start;
        this.slut = slut;
        this.tilmeldingStart = tilmeldingStart;
        this.tilmeldingSlut = tilmeldingSlut;
        this.adresse = adresse;
        this.prisPrDag = pris;
        this.konferenceTilmeldinger = new ArrayList<>();
        this.udflugter = new ArrayList<>();
        this.hoteller = new ArrayList<>();
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getSlut() {
        return slut;
    }

    public void setSlut(LocalDate slut) {
        this.slut = slut;
    }

    public LocalDate getTilmeldingStart() {
        return tilmeldingStart;
    }

    public void setTilmeldingStart(LocalDate tilmeldingStart) {
        this.tilmeldingStart = tilmeldingStart;
    }

    public LocalDate getTilmeldingSlut() {
        return tilmeldingSlut;
    }

    public void setTilmeldingSlut(LocalDate tilmeldingSlut) {
        this.tilmeldingSlut = tilmeldingSlut;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getPrisPrDag() {
        return prisPrDag;
    }

    public void setPrisPrDag(double pris) {
        this.prisPrDag = pris;
    }

    public List<KonferenceTilmelding> getKonferenceTilmeldinger() {
        return new ArrayList<>(this.konferenceTilmeldinger);
    }

    public KonferenceTilmelding opretKonferenceTilmelding(boolean erForedragsholder,
                                                          String firma, String firmaTlfNr, Deltager deltager) {
        KonferenceTilmelding konferenceTilmelding = new KonferenceTilmelding(erForedragsholder, firma, firmaTlfNr, deltager, this);
        this.konferenceTilmeldinger.add(konferenceTilmelding);
        return konferenceTilmelding;
    }

    public void sletKonferenceTilmelding(KonferenceTilmelding konferenceTilmelding) {
        this.konferenceTilmeldinger.remove(konferenceTilmelding);
    }

    public List<Udflugt> getUdflugter() {
        return new ArrayList<>(this.udflugter);
    }

    public Udflugt opretUdflugt(LocalDateTime start, LocalDateTime slut, double pris, String navn, boolean medFrokost, String beskrivelse, String lokation) {
        Udflugt udflugt = new Udflugt(start, slut, pris, lokation, navn, beskrivelse, medFrokost, this);
        this.udflugter.add(udflugt);
        return udflugt;
    }


    public void tilføjHotel(Hotel hotel) {
        if (!this.hoteller.contains(hotel)) {
            this.hoteller.add(hotel);
        }
    }

    public void fjernHotel(Hotel hotel) {
        if(this.hoteller.contains(hotel)) {
            this.hoteller.remove(hotel);
        }
    }

    public int getAntalTilmeldinger() {
        return this.konferenceTilmeldinger.size();
    }
    public boolean erTilmeldingerÅben() {
        return getTilmeldingStart().isBefore(LocalDate.now()) || getTilmeldingStart().isEqual(LocalDate.now()) &&
                getTilmeldingSlut().isAfter(LocalDate.now()) || getTilmeldingSlut().isEqual(LocalDate.now());
    }

    @Override
    public String toString() {
        return getNavn();
    }

    public List<Hotel> getHoteller() {
        return new ArrayList<Hotel>(hoteller);
    }
}
