package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class HotelBooking {
    private List<Tilkøb> tilkøb;
    private Hotel hotel;
    private boolean erDobbeltVærelse;
    private LocalDate ankomstDato, afrejseDato;
    private Deltager deltager;

    public HotelBooking(Hotel hotel, boolean erEnkeltVærelse, LocalDate ankomstDato, LocalDate afrejseDato, Deltager deltager) {
        this.hotel = hotel;
        this.erDobbeltVærelse = erEnkeltVærelse;
        this.ankomstDato = ankomstDato;
        this.afrejseDato = afrejseDato;
        this.deltager = deltager;
        this.tilkøb = new ArrayList<>();
    }

    public List<Tilkøb> getTilkøb() {
        return tilkøb;
    }

    public void tilføjTilkøb(Tilkøb tilkøb) {
        this.tilkøb.add(tilkøb);
    }

    public LocalDate getAfrejseDato() {
        return afrejseDato;
    }

    public LocalDate getAnkomstDato() {
        return ankomstDato;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public boolean getErDobbeltværelse() {
        return erDobbeltVærelse;
    }

    public String getDeltagerNavn() {
        return this.deltager.getNavn();
    }

    public double udregnPris() {
        double værelsesPris = this.erDobbeltVærelse ? hotel.getDobbeltVærelsesPris() : getHotel().getEnkeltVærelsesPris();
        double tilkøbsPris = this.tilkøb.stream().mapToDouble(Tilkøb::getPris).sum();
        return værelsesPris * ChronoUnit.DAYS.between(ankomstDato, afrejseDato) + tilkøbsPris;
    }

    //Metode til TableColumn reflection -> er nødt til at eksistere som det er bygget
    public double getUdregnPris() {
        return udregnPris();
    }
}
