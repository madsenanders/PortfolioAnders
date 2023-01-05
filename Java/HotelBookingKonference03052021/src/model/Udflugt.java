package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Udflugt {
    private LocalDateTime start;
    private LocalDateTime slut;
    private double pris;
    private String lokation;
    private String navn;
    private String beskrivelse;
    private boolean medFrokost;
    private Konference konference;
    private List<Ledsager> ledsagere;

    public Udflugt(LocalDateTime start, LocalDateTime slut, double pris, String lokation, String navn, String beskrivelse, boolean medFrokost, Konference konference) {
        this.start = start;
        this.slut = slut;
        this.pris = pris;
        this.lokation = lokation;
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.medFrokost = medFrokost;
        this.konference = konference;
        this.ledsagere = new ArrayList<>();
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getSlut() {
        return slut;
    }

    public void setSlut(LocalDateTime slut) {
        this.slut = slut;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public String getLokation() {
        return lokation;
    }

    public void setLokation(String lokation) {
        this.lokation = lokation;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public boolean isMedFrokost() {
        return medFrokost;
    }

    public void setMedFrokost(boolean medFrokost) {
        this.medFrokost = medFrokost;
    }

    public Konference getKonference() {
        return konference;
    }

    public void setKonference(Konference konference) {
        this.konference = konference;
    }

    public ArrayList<Ledsager> getLedsagere() {
        return new ArrayList<>(this.ledsagere);
    }

    public int getAntalLedsagere() {
        return ledsagere.size();
    }

    public void tilføjLedsager(Ledsager ledsager) {
        if (!ledsagere.contains(ledsager)) {
            this.ledsagere.add(ledsager);
            ledsager.tilføjUdflugt(this);
        }
    }

    @Override
    public String toString() {
        return getNavn();
    }
}
