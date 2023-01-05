package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Hotel;
import model.Konference;

import java.time.LocalDate;

public class OpretkonferenceVindue extends Stage {
    private final GridPane pane;
    private TextField txtKonferenceNavn, txtKonferenceAdresse, txtKonferencePrisPrDag;
    private DatePicker datePickerStart, datePickerSlut, datePickerTstart, datePickerTslut;
    private Button btnOK;
    private ListView<Hotel> hotellerListView;


    public OpretkonferenceVindue() {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(true);

        this.pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        this.initContent();

        Scene scene = new Scene(pane, 700, 450);
        this.setScene(scene);
    }

    private void initContent() {
        txtKonferenceNavn = new TextField();
        txtKonferenceNavn.setPromptText("Indtast konference navn");
        pane.add(txtKonferenceNavn, 1, 0);

        txtKonferenceAdresse = new TextField();
        txtKonferenceAdresse.setPromptText("Indtast konference adresse");
        pane.add(txtKonferenceAdresse, 1, 1);

        txtKonferencePrisPrDag = new TextField();
        txtKonferencePrisPrDag.setPromptText("Indtast pris pr. dag for konference");
        pane.add(txtKonferencePrisPrDag, 1, 2);

        btnOK = new Button("Opret");
        pane.add(btnOK, 1, 11);
        btnOK.setOnAction(event -> createKonference());
        renderDatePickers();


        Label lblVælgKonference = new Label("Vælg konference");
        pane.add(lblVælgKonference, 2,0);
        hotellerListView = new ListView<Hotel>();
        hotellerListView.getItems().setAll(Controller.getHoteller());
        hotellerListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        pane.add(hotellerListView, 2,1, 1, 4);

    }

    private void renderDatePickers() {
        pane.add(new Label("Startdato"), 1, 3);

        datePickerStart = new DatePicker();
        datePickerStart.getEditor().setDisable(true);
        pane.add(datePickerStart, 1, 4);
        datePickerStart.setOnAction(event -> disableInvalidDates(false));

        pane.add(new Label("Slutdato"), 1, 5);

        datePickerSlut = new DatePicker();
        datePickerSlut.getEditor().setDisable(true);
        pane.add(datePickerSlut, 1, 6);

        pane.add(new Label("Tilmelding start"), 1, 7);

        datePickerTstart = new DatePicker();
        datePickerTstart.getEditor().setDisable(true);
        datePickerTstart.setOnAction(event -> disableInvalidDates(true));
        pane.add(datePickerTstart, 1, 8);

        pane.add(new Label("Tilmelding slut"), 1, 9);

        datePickerTslut = new DatePicker();
        datePickerTslut.getEditor().setDisable(true);
        pane.add(datePickerTslut, 1, 10);
    }

    private void disableInvalidDates(boolean isTilmelding) {
        DatePicker datePicker1;
        DatePicker datePicker2;
        if (isTilmelding) {
            datePicker1 = datePickerTstart;
            datePicker2 = datePickerTslut;
        } else {
            datePicker1 = datePickerStart;
            datePicker2 = datePickerSlut;
        }
        if (datePicker1.getValue() != null) {
            datePicker2.setDayCellFactory(d ->
                    new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            setDisable(item.isBefore(datePicker1.getValue()) || item.isEqual(datePicker1.getValue()));
                        }
                    });
            if (datePicker2.getValue() == null || datePicker2.getValue().isBefore(datePicker1.getValue())) {
                datePicker2.setValue(datePicker1.getValue().plusDays(1));
            }
        }
    }

    private void createKonference() {
        Konference konf = Controller.createKonference(txtKonferenceNavn.getText(),
                datePickerStart.getValue(),
                datePickerSlut.getValue(),
                datePickerTstart.getValue(),
                datePickerTslut.getValue(),
                txtKonferenceAdresse.getText(),
                Double.parseDouble(txtKonferencePrisPrDag.getText()));
        for (int i = 0; i < hotellerListView.getSelectionModel().getSelectedItems().size(); i++) {
            konf.tilføjHotel(hotellerListView.getSelectionModel().getSelectedItems().get(i));
        }
        this.close();
    }

}
