package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class KonferenceTilmelding {

    private boolean erForedragsholder;
    private String firma;
    private String firmaTlfNr;
    private Deltager deltager;
    private Konference konference;
    private HotelBooking hotelBooking;

    public KonferenceTilmelding(boolean erForedragsholder,String firma, String firmaTlfNr, Deltager deltager, Konference konference) {
        this.erForedragsholder = erForedragsholder;
        this.firma = firma;
        this.firmaTlfNr = firmaTlfNr;
        this.deltager = deltager;
        this.konference = konference;
    }

    public boolean isErForedragsholder() {
        return erForedragsholder;
    }

    public Deltager getDeltager() {
        return deltager;
    }

    public void setDeltager(Deltager deltager) {
        this.deltager = deltager;
    }

    public Konference getKonference() {
        return konference;
    }

    public void setKonference(Konference konference) {
        this.konference = konference;
    }

    public String getBy() {
        return deltager.getBy();
    }

    public String getAdresse() {
        return deltager.getAdresse();
    }

    public String getLand() {
        return deltager.getLand();
    }

    public String getEmail() {
        return deltager.getEmail();
    }

    public String getTlfNr() {
        return deltager.getTelefonNr();
    }

    public String getLedsagerNavn() {
        return getDeltager().getLedsagerNavn();
    }

    public HotelBooking getHotelBooking() {
        return hotelBooking;
    }

    private double getPrisPrDagKonference() {
        if (erForedragsholder) {
            return 0;
        } else {
            return konference.getPrisPrDag();
        }
    }

    public double beregnPris() {
        long daysBetweenConferenceStartEnd = ChronoUnit.DAYS.between(konference.getStart(), konference.getSlut());
        return (double) daysBetweenConferenceStartEnd * this.getPrisPrDagKonference() + this.udregnHotelPris() + this.udregnLedsagerPris();
    }

    private double udregnLedsagerPris() {
        if (getDeltager().getLedsager() != null) {
            double sum = 0;
            for (Udflugt udflugt : getDeltager().getLedsager().getUdflugter()) {
                sum += udflugt.getPris();
            }
            return sum;
        } else {
            return 0;
        }
    }

    private double udregnHotelPris() {
        if (getHotelBooking() != null) {
            return getHotelBooking().udregnPris();
        } else {
            return 0;
        }
    }

    public HotelBooking opretHotelBooking(Hotel hotel, boolean erEnkeltværelse, LocalDate ankomstDato, LocalDate afrejseDato) {
        HotelBooking hotelBooking = new HotelBooking(hotel, erEnkeltværelse, ankomstDato, afrejseDato, deltager);
        this.hotelBooking = hotelBooking;
        return hotelBooking;
    }

    public String getDeltagerNavn() {
        return deltager.getNavn();
    }

    public double getSamletPris() {
        return beregnPris();
    }
}
