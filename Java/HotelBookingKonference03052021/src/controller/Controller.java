package controller;

import model.Deltager;
import model.Hotel;
import model.Konference;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    public static Konference createKonference(String navn, LocalDate start, LocalDate slut, LocalDate tilmeldingStart,
                                              LocalDate tilmeldingSlut, String adresse, double pris) {
        Konference konference = new Konference(navn, start, slut, tilmeldingStart, tilmeldingSlut, adresse, pris);
        Storage.tilføjKonference(konference);
        return konference;
    }

    public static void removeKonference(Konference konference) {
        Storage.fjernKonference(konference);
    }

    public static ArrayList<Konference> getKonferencer() {
        return Storage.getKonferencer();
    }

    public static Hotel createHotel(String navn, String adresse, double dobbeltVærelsesPris, double enkeltVærelsesPris) {
        Hotel hotel = new Hotel(navn, adresse, dobbeltVærelsesPris, enkeltVærelsesPris);
        Storage.tilføjHotel(hotel);
        return hotel;
    }

    public static ArrayList<Hotel> getHoteller() {
        return Storage.getHoteller();
    }

    public static Deltager createDeltager(String navn, String telefonNr, String email, String by, String land, String adresse) {
        Deltager deltager = new Deltager(navn, telefonNr, email, by, land, adresse);
        Storage.tilføjDeltager(deltager);
        return deltager;
    }

    public static void init() {
        Storage.initStorage();
    }
}
