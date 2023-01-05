package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import model.Hotel;
import model.Konference;
import model.KonferenceTilmelding;
import model.Udflugt;


import java.time.LocalDateTime;
import java.util.Arrays;

import static controller.Controller.*;


public class AdminPane extends VBox {
    private TableView<KonferenceTilmelding> tilmeldingTableView;
    private TableView<Konference> konferenceTableView;
    private TableView<Hotel> hotelTableView;
    private TableView<Udflugt> udflugtTableView;
    private Button tilmeldDeltagerKonference, opretHotelButton, sletDeltagerTilmelding;

    public AdminPane() {
        this.initContent();
    }

    private void initContent() {
        this.setPadding(new Insets(20));
        konferenceTabel();
        deltagerTabel();
        hotelTabel();
        udflugtTabel();
        kontrolButtons();

    }

    private void deltagerTabel() {
        Label label = new Label("Deltagere");
        this.getChildren().add(label);

        tilmeldingTableView = new TableView<>();
        tilmeldingTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tilmeldingTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                sletDeltagerTilmelding.setDisable(false);
            }
        });

        TableColumn<KonferenceTilmelding, String> navneKol = new TableColumn<>("Deltager");
        navneKol.setCellValueFactory(new PropertyValueFactory<>("deltagerNavn"));

        TableColumn<KonferenceTilmelding, Double> prisKol = new TableColumn<>("Samlet pris");
        prisKol.setCellValueFactory(new PropertyValueFactory<>("samletPris"));

        TableColumn<KonferenceTilmelding, String> foredragsholderKol = new TableColumn<>("Foredragsholder");
        foredragsholderKol.setCellValueFactory(new PropertyValueFactory<>("erForedragsholder"));


        TableColumn<KonferenceTilmelding, String> adresseKol = new TableColumn<>("Adresse");
        adresseKol.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        TableColumn<KonferenceTilmelding, String> byKol = new TableColumn<>("By");
        byKol.setCellValueFactory(new PropertyValueFactory<>("by"));

        TableColumn<KonferenceTilmelding, String> landKol = new TableColumn<>("Land");
        landKol.setCellValueFactory(new PropertyValueFactory<>("land"));

        TableColumn<KonferenceTilmelding, String> emailKol = new TableColumn<>("E-mail");
        emailKol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<KonferenceTilmelding, String> tlfNrKol = new TableColumn<>("Telefonnummer");
        tlfNrKol.setCellValueFactory(new PropertyValueFactory<>("tlfNr"));

        TableColumn<KonferenceTilmelding, String> ledsagerKol = new TableColumn<>("Ledsager");
        ledsagerKol.setCellValueFactory(new PropertyValueFactory<>("ledsagerNavn"));

        tilmeldingTableView.getColumns().addAll(Arrays.asList(navneKol, prisKol, foredragsholderKol, adresseKol, byKol, landKol, emailKol, tlfNrKol, ledsagerKol));

        tilmeldingTableView.prefWidthProperty().bind(super.widthProperty());
        this.getChildren().add(tilmeldingTableView);
    }

    private void konferenceTabel() {
        Label label = new Label("Konferencer");
        this.getChildren().add(label);

        this.konferenceTableView = new TableView<>();
        konferenceTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        konferenceTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldSelecetion, newSelection) -> {
            if (oldSelecetion != null) {
                updateDeltagerListView();
            }
            if (newSelection != null) {
                tilmeldDeltagerKonference.setDisable(false);
                updateUdflugtTableView();
                updateHotelTableView();
            }

        });

        TableColumn<Konference, String> nameCol = new TableColumn<>("Konference");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("navn"));

        TableColumn<Konference, String> startCol = new TableColumn<>("Start");
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));

        TableColumn<Konference, String> endCol = new TableColumn<>("Slut");
        endCol.setCellValueFactory(new PropertyValueFactory<>("slut"));

        TableColumn<Konference, String> priceCol = new TableColumn<>("Pris pr. dag");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("prisPrDag"));

        TableColumn<Konference, String> registerOpenCol = new TableColumn<>("Tilmelding åbner");
        registerOpenCol.setCellValueFactory(new PropertyValueFactory<>("tilmeldingStart"));

        TableColumn<Konference, Integer> antalTilmeldte = new TableColumn<>("Antal tilmeldte");
        antalTilmeldte.setCellValueFactory(new PropertyValueFactory<>("antalTilmeldinger"));
        antalTilmeldte.setId("TilmeldteTal");

        konferenceTableView.getColumns().addAll(Arrays.asList(nameCol, startCol, endCol, priceCol, registerOpenCol, antalTilmeldte));

        konferenceTableView.getItems().addAll(Controller.getKonferencer());
        konferenceTableView.prefWidthProperty().bind(super.widthProperty());
        this.getChildren().add(konferenceTableView);
    }

    private void hotelTabel() {
        Label label = new Label("Hoteller");
        this.getChildren().add(label);

        this.hotelTableView = new TableView<>();
        hotelTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Hotel, String> nameCol = new TableColumn<>("Hotel");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("navn"));

        TableColumn<Hotel, String> adresseCol = new TableColumn<>("Adresse");
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        TableColumn<Hotel, String> enkeltprisCol = new TableColumn<>("Enkeltværelses Pris");
        enkeltprisCol.setCellValueFactory(new PropertyValueFactory<>("enkeltVærelsesPris"));

        TableColumn<Hotel, String> dobbeltprisCol = new TableColumn<>("Dobbeltværelses Pris");
        dobbeltprisCol.setCellValueFactory(new PropertyValueFactory<>("dobbeltVærelsesPris"));

        hotelTableView.getColumns().addAll(Arrays.asList(nameCol, adresseCol, enkeltprisCol, dobbeltprisCol));

        hotelTableView.prefWidthProperty().bind(super.widthProperty());
        hotelTableView.getSelectionModel().selectFirst();
        this.getChildren().add(hotelTableView);
    }

    private void udflugtTabel() {
        Label label = new Label("Udflugter");
        this.getChildren().add(label);

        this.udflugtTableView = new TableView<>();
        udflugtTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Udflugt, String> nameCol = new TableColumn<>("Udflugt");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("navn"));

        TableColumn<Udflugt, LocalDateTime> adresseCol = new TableColumn<>("Start");
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("start"));

        TableColumn<Udflugt, LocalDateTime> enkeltprisCol = new TableColumn<>("Slut");
        enkeltprisCol.setCellValueFactory(new PropertyValueFactory<>("Slut"));

        TableColumn<Udflugt, Double> dobbeltprisCol = new TableColumn<>("Pris");
        dobbeltprisCol.setCellValueFactory(new PropertyValueFactory<>("pris"));

        TableColumn<Udflugt, String> lokationCol = new TableColumn<>("Lokation");
        lokationCol.setCellValueFactory(new PropertyValueFactory<>("lokation"));

        TableColumn<Udflugt, Boolean> frokostCol = new TableColumn<>("Med Frokost");
        frokostCol.setCellValueFactory(new PropertyValueFactory<>("medFrokost"));

        TableColumn<Udflugt, String> antalLedsagereCol = new TableColumn<>("Antal ledsagere");
        antalLedsagereCol.setCellValueFactory(new PropertyValueFactory<>("antalLedsagere"));


        udflugtTableView.getColumns().addAll(Arrays.asList(nameCol, adresseCol, enkeltprisCol, dobbeltprisCol, lokationCol, frokostCol, antalLedsagereCol));

        udflugtTableView.prefWidthProperty().bind(super.widthProperty());
        udflugtTableView.getSelectionModel().selectFirst();
        this.getChildren().add(udflugtTableView);
    }

    private void kontrolButtons() {
        HBox box = new HBox();
        tilmeldDeltagerKonference = new Button("Tilmeld deltager");
        box.getChildren().add(tilmeldDeltagerKonference);
        tilmeldDeltagerKonference.setDisable(true);

        sletDeltagerTilmelding = new Button("Slet deltager tilmelding");
        box.getChildren().add(sletDeltagerTilmelding);
        sletDeltagerTilmelding.setDisable(true);

        Button deleteKonferenceButton = new Button("Slet Konference");
        box.getChildren().add(deleteKonferenceButton);


        Button createKonferenceButton = new Button("Opret Konference");
        box.getChildren().add(createKonferenceButton);

        Button visUdflugtDeltagerButton = new Button("Vis deltagere på udflugt");
        box.getChildren().add(visUdflugtDeltagerButton);

        opretHotelButton = new Button("Opret Hotel");
        box.getChildren().add(opretHotelButton);

        Button visBookinger = new Button("Vis bookinger");
        box.getChildren().add(visBookinger);

        tilmeldDeltagerKonference.setOnAction(event -> visTilmeldingsVindue());
        createKonferenceButton.setOnAction(event -> visOpretKonferenceVindue());
        deleteKonferenceButton.setOnAction(event -> deleteKonferencer());
        sletDeltagerTilmelding.setOnAction((event -> deleteDeltagerTilmelding()));
        visUdflugtDeltagerButton.setOnAction(event -> visUdflugtDeltagerVindue());
        opretHotelButton.setOnAction(event -> visOpretHotelVindue());
        visBookinger.setOnAction(event -> visHotelBookinger());
        this.getChildren().add(box);
    }

    private void visHotelBookinger() {
        VisHotelBookinger vindue = new VisHotelBookinger(hotelTableView.getSelectionModel().getSelectedItem(), konferenceTableView.getSelectionModel().getSelectedItem());
        vindue.show();
    }

    private void visOpretHotelVindue() {
        OpretHotelVindue vindue = new OpretHotelVindue();
        vindue.show();
    }

    private void visUdflugtDeltagerVindue() {
        UdflugtDeltagereVindue vindue = new UdflugtDeltagereVindue(udflugtTableView.getSelectionModel().getSelectedItem());
        vindue.show();
    }

    private void visOpretKonferenceVindue() {
        OpretkonferenceVindue vindue = new OpretkonferenceVindue();
        vindue.showAndWait();
        updateKonferenceTableView();
    }

    private void visTilmeldingsVindue() {
        TilmeldKonference vindue = new TilmeldKonference(this.konferenceTableView.getFocusModel().getFocusedItem());
        vindue.showAndWait();
        updateKonferenceTableView();
        updateHotelTableView();
        updateDeltagerListView();
        updateUdflugtTableView();

    }

    private void updateKonferenceTableView() {
        Konference konference = konferenceTableView.getSelectionModel().getSelectedItem();
        Konference konferenceAtSlette = null;
        for (int i = 0; i < konferenceTableView.getItems().size(); i++) {
            if (konferenceTableView.getItems().get(i) == konference) {
                konferenceAtSlette = konferenceTableView.getItems().get(i);
            }
        }
        if (konferenceAtSlette != null) {
            konferenceTableView.getItems().remove(konferenceAtSlette);
        }
        Controller.getKonferencer().forEach(konf -> {
            if (konf == konference || !konferenceTableView.getItems().contains(konf)) {
                konferenceTableView.getItems().add(konf);
            }
        });
        konferenceTableView.getSelectionModel().select(konference);

    }

    private void updateUdflugtTableView() {
        Udflugt udflugt = udflugtTableView.getSelectionModel().getSelectedItem();
        udflugtTableView.getItems().clear();
        udflugtTableView.getItems().addAll(konferenceTableView.getSelectionModel().getSelectedItem().getUdflugter());
        udflugtTableView.getSelectionModel().select(udflugt);

    }

    private void updateHotelTableView() {
        Hotel hotel = hotelTableView.getSelectionModel().getSelectedItem();
        hotelTableView.getItems().clear();
        hotelTableView.getItems().addAll(konferenceTableView.getSelectionModel().getSelectedItem().getHoteller());
        hotelTableView.getSelectionModel().select(hotel);
    }

    private void deleteKonferencer() {
        Konference konference = konferenceTableView.getSelectionModel().getSelectedItem();
        removeKonference(konference);
        updateKonferenceTableView();
    }

    private void deleteDeltagerTilmelding() {
        KonferenceTilmelding konferenceTilmelding = tilmeldingTableView.getSelectionModel().getSelectedItem();
        konferenceTableView.getSelectionModel().getSelectedItem().sletKonferenceTilmelding(konferenceTilmelding);
        updateDeltagerListView();
    }

    public void updateDeltagerListView() {
        tilmeldingTableView.getItems().clear();
        tilmeldingTableView.getItems().addAll(konferenceTableView.getSelectionModel().getSelectedItem().getKonferenceTilmeldinger());
    }
}