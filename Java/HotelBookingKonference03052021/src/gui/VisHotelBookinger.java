package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Hotel;
import model.HotelBooking;
import model.Konference;
import model.KonferenceTilmelding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class VisHotelBookinger extends Stage {
    private final VBox box;
    private Hotel hotel;
    private Konference konference;
    private TableView<HotelBooking> hotelBookingTableView;

    public VisHotelBookinger(Hotel hotel, Konference konference) {
        this.hotel = hotel;
        this.konference = konference;

        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(true);

        this.box = new VBox();
        box.setPadding(new Insets(20));

        this.initContent();

        Scene scene = new Scene(box, 700, 450);
        this.setScene(scene);
    }

    private void initContent() {
        ArrayList<HotelBooking> tilmeldinger = new ArrayList<>();
        for (int i = 0; i < konference.getKonferenceTilmeldinger().size(); i++) {
            KonferenceTilmelding kt = konference.getKonferenceTilmeldinger().get(i);
            if (kt.getHotelBooking() != null) {
                Hotel h = kt.getHotelBooking().getHotel();
                if (h == hotel) {
                    tilmeldinger.add(kt.getHotelBooking());
                }
            }
        }

        hotelBookingTableView = new TableView<>();
        hotelBookingTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<HotelBooking, String> deltagerCol = new TableColumn<>("Deltager navn");
        deltagerCol.setCellValueFactory(new PropertyValueFactory<>("deltagerNavn"));

        TableColumn<HotelBooking, LocalDate> ankomstCol = new TableColumn<>("Ankomst");
        ankomstCol.setCellValueFactory(new PropertyValueFactory<>("ankomstDato"));

        TableColumn<HotelBooking, LocalDate> afrejseCol = new TableColumn<>("Afrejse");
        afrejseCol.setCellValueFactory(new PropertyValueFactory<>("afrejseDato"));

        TableColumn<HotelBooking, Double> prisCol = new TableColumn<>("Booking pris");
        prisCol.setCellValueFactory(new PropertyValueFactory<>("udregnPris"));

        TableColumn<HotelBooking, Boolean> værelsesCol = new TableColumn<>("Dobbeltværelse");
        værelsesCol.setCellValueFactory(new PropertyValueFactory<>("erDobbeltværelse"));

        hotelBookingTableView.getColumns().addAll(Arrays.asList(deltagerCol, ankomstCol, afrejseCol, prisCol, værelsesCol));
        hotelBookingTableView.getItems().addAll(tilmeldinger);
        box.getChildren().add(hotelBookingTableView);
    }
}
