package model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String navn;
    private String adresse;
    private double dobbeltVærelsesPris;
    private double enkeltVærelsesPris;
    private List<Tilkøb> tilkøb;

    public Hotel(String navn, String adresse, double dobbeltVærelsesPris, double enkeltVærelsesPris) {
        this.navn = navn;
        this.adresse = adresse;
        this.dobbeltVærelsesPris = dobbeltVærelsesPris;
        this.enkeltVærelsesPris = enkeltVærelsesPris;
        this.tilkøb = new ArrayList<>();
    }

    public String getNavn() {
        return navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public double getDobbeltVærelsesPris() {
        return dobbeltVærelsesPris;
    }

    public List<Tilkøb> getTilkøb() {
        return new ArrayList<>(this.tilkøb);
    }

    public Tilkøb opretTilkøb(String navn, double pris) {
        Tilkøb tilkøb = new Tilkøb(navn, pris);
        this.tilkøb.add(tilkøb);
        return tilkøb;
    }

    public void fjernTilkøb(Tilkøb tilkøb) {
        this.tilkøb.remove(tilkøb);
    }

    public double getEnkeltVærelsesPris() {
        return enkeltVærelsesPris;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDobbeltVærelsesPris(double dobbeltVærelsesPris) {
        this.dobbeltVærelsesPris = dobbeltVærelsesPris;
    }

    public void setEnkeltVærelsesPris(double enkeltVærelsesPris) {
        this.enkeltVærelsesPris = enkeltVærelsesPris;
    }

    @Override
    public String toString() {
        return navn;
    }
}
