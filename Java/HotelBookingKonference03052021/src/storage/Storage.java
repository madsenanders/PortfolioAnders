package storage;

import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final static List<Konference> konferencer = new ArrayList<>();
    private final static List<Hotel> hoteller = new ArrayList<>();
    private final static List<Deltager> deltagerer = new ArrayList<>();


    public static void tilføjHotel(Hotel hotel) {
        hoteller.add(hotel);
    }

    public static void fjernHotel(Hotel hotel) {
        hoteller.remove(hotel);
    }

    public static ArrayList<Hotel> getHoteller() {
        return new ArrayList<>(hoteller);
    }

    public static void fjernKonference(Konference konference) {
        konferencer.remove(konference);
    }

    public static void tilføjKonference(Konference konference) {
        konferencer.add(konference);
    }

    public static ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }

    public static ArrayList<Deltager> getDeltagere() {
        return new ArrayList<>(deltagerer);
    }

    public static void tilføjDeltager(Deltager deltager) {
        deltagerer.add(deltager);
    }

    public static void fjernDeltager(Deltager deltager) {
        //TODO: DER ER NOGET GALT HER
//        konferencer.remove(deltager);
    }

    public static void initStorage() {
        Konference konference1 = new Konference("Init konference", LocalDate.now(), LocalDate.now().plusDays(3), LocalDate.now(), LocalDate.now().plusDays(3), "Sønderhøj 30", 1000);
        tilføjKonference(konference1);
        Konference konference2 = new Konference("En dag konference", LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now(), LocalDate.now().plusDays(1), "Sønderhøj 20", 1500);
        tilføjKonference(konference2);
        Konference konference3 = new Konference("Hav og Himmel", LocalDate.now(), LocalDate.now().plusDays(3), LocalDate.now(), LocalDate.now().plusDays(3), "Sønderhøj 15", 1500);
        tilføjKonference(konference3);

        Deltager deltager1 = new Deltager("Anders Andersen Andersensen", "98765432", "anders@anders.anders", "Andershus", "Andersmark", "Andershøj 30");
        tilføjDeltager(deltager1);
        Deltager deltager2 = new Deltager("Finn Madsen", "98625432", "finn@madsen.com", "Aarhus", "Danmark", "sønderhøj 30");
        tilføjDeltager(deltager2);
        Deltager deltager3 = new Deltager("Niels Petersen", "98723432", "niels@petersen.com", "Aarhus", "Danmark", "sønderhøj 30");
        tilføjDeltager(deltager3);

        Hotel hotel1 = new Hotel("Den Hvide Svane", "Sønderhøj 29", 1250, 1050);
        tilføjHotel(hotel1);
        Hotel hotel2 = new Hotel("Hotel Phønix", "Sønderhøj 99", 800, 700);
        tilføjHotel(hotel2);
        Hotel hotel3 = new Hotel("Pension Tusindfryd", "Sønderhøj 19", 600, 500);
        tilføjHotel(hotel3);

        konference1.tilføjHotel(hotel1);

        hotel1.opretTilkøb("WI-FI", 50);
        hotel2.opretTilkøb("Morgenmad", 200);
        hotel3.opretTilkøb("Bad", 100);

        konference1.opretUdflugt(LocalDateTime.now(), LocalDateTime.now().plusHours(5), 300, "Skovtur", true, "Tur i skoven", "Skovvej 3");
        konference1.opretUdflugt(LocalDateTime.now(), LocalDateTime.now().plusHours(5), 150, "Museumstur", false, "Tur på Museum", "Museumvej 3");
    }

}
