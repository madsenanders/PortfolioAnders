package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TilmeldKonference extends Stage {

    private final Konference konference;
    private TextField txtNavn, txtAdresse, txtBy, txtTlfNr, txtLand, txtEmail, ledsagerNavn, ledsagerEmail, ledsagerTlfNr;
    private Label lblNavn, lblAdresse, lblBy, lblTlfNr, lblLand, lblEmail, lblTilkøb;
    private VBox deltagerInput, hotelInput, ledsagerInput;
    private CheckBox checkBoxHotel, checkBoxLedsager, checkBoxForedragsholder;
    private final GridPane pane;
    private ComboBox<Hotel> hotelComboBox;
    private final List<CheckBox> hotelTilkøb = new ArrayList<>();
    private RadioButton rbEnkeltværelse, rbDobbeltværelse;
    private DatePicker datePickerAfrejse, datePickerAnkomst;
    private List<Udflugt> valgteUdflugter = new ArrayList<>();
    private ListView<Text> tilmeldteUdflugterListView;

    public TilmeldKonference(Konference konference) {
        this.konference = konference;
        this.pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(true);

        this.initContent();

        Scene scene = new Scene(pane, 700, 450);
        this.setScene(scene);
    }

    private void initContent() {
        renderInputFields();
        checkBoxes();
    }

    private void checkBoxes() {
        checkBoxHotel = new CheckBox("Hotel");
        pane.add(checkBoxHotel, 0, 7);
        checkBoxHotel.setOnAction(event -> toggleHotelInput());

        checkBoxLedsager = new CheckBox("Ledsager");
        pane.add(checkBoxLedsager, 1, 7);
        checkBoxLedsager.setOnAction(event -> toggleLedsagerInput());

        checkBoxForedragsholder = new CheckBox("Foredragsholder");
        pane.add(checkBoxForedragsholder, 0,5);

        Button btnConfirm = new Button("OK");
        pane.add(btnConfirm, 2, 7);
        btnConfirm.setOnAction(event -> opretTilmelding());
    }

    private void opretTilmelding() {
        KonferenceTilmelding konferenceTilmelding = konference.opretKonferenceTilmelding(checkBoxForedragsholder.isSelected(), "", "", Controller.createDeltager(txtNavn.getText(), txtTlfNr.getText(), txtEmail.getText(), txtBy.getText(), txtLand.getText(), txtAdresse.getText()));
        if (checkBoxHotel.isSelected()) {
            Hotel chosenHotel = hotelComboBox.getSelectionModel().getSelectedItem();
            if (chosenHotel != null) {
                HotelBooking hotelBooking = konferenceTilmelding.opretHotelBooking(hotelComboBox.getSelectionModel().getSelectedItem(), erDobbeltværelse(), datePickerAnkomst.getValue(), datePickerAfrejse.getValue());
                hotelTilkøb.forEach(tilkøb -> {
                    if (tilkøb.isSelected()) {
                        hotelBooking.tilføjTilkøb(((Tilkøb) tilkøb.getUserData()));
                    }
                });
            }
        }

        if (checkBoxLedsager.isSelected()) {
            Ledsager ledsager = konferenceTilmelding.getDeltager().opretLedsager(ledsagerEmail.getText(), ledsagerTlfNr.getText(), ledsagerNavn.getText());
            valgteUdflugter.forEach(ledsager::tilføjUdflugt);
        }
        this.close();
    }

    private boolean erDobbeltværelse() {
        return checkBoxLedsager.isSelected() || rbDobbeltværelse.isSelected();
    }

    private void toggleLedsagerInput() {
        if (ledsagerInput == null) {
            ledsagerInput();
            pane.add(ledsagerInput, 2, 0);
        } else if (pane.getChildren().contains(ledsagerInput)) {
            pane.getChildren().remove(ledsagerInput);
        } else {
            pane.add(ledsagerInput, 2, 0);
        }
    }

    private void toggleHotelInput() {
        if (hotelInput == null) {
            hotelInput();
            pane.add(hotelInput, 1, 0);
        } else if (pane.getChildren().contains(hotelInput)) {
            pane.getChildren().remove(hotelInput);
        } else {
            pane.add(hotelInput, 1, 0);
        }
    }

    private void ledsagerInput() {
        ledsagerInput = new VBox();
        ledsagerInput.setSpacing(10);

        Label lblLedsager = new Label("Ledsager");
        ledsagerInput.getChildren().add(lblLedsager);

        ledsagerNavn = new TextField();
        ledsagerNavn.setPromptText("Navn");
        ledsagerInput.getChildren().add(ledsagerNavn);

        ledsagerEmail = new TextField();
        ledsagerEmail.setPromptText("E-mail");
        ledsagerInput.getChildren().add(ledsagerEmail);

        ledsagerTlfNr = new TextField();
        ledsagerTlfNr.setPromptText("Tlf. Nr");
        ledsagerInput.getChildren().add(ledsagerTlfNr);

        Button btnUdflugter = new Button("Tilføj til udflugt(er)");
        btnUdflugter.setOnAction(event -> visUdflugtModal());
        ledsagerInput.getChildren().add(btnUdflugter);
    }

    private void visUdflugtModal() {
        TilmeldLedsagerTilUdflugt window = new TilmeldLedsagerTilUdflugt(konference);
        tilmeldteUdflugterListView = new ListView<>();
        window.showAndWait();

        this.valgteUdflugter.addAll(window.getValgteUdflugter());
        for (int i = 0; i < window.getValgteUdflugter().size(); i++) {
            Text t = new Text(10, 50, window.getValgteUdflugter().get(i).getNavn());
            tilmeldteUdflugterListView.getItems().add(t);
        }
        ledsagerInput.getChildren().add(tilmeldteUdflugterListView);

    }

    private void hotelInput() {
        hotelInput = new VBox();
        hotelInput.setSpacing(10);

        Label lblHotel = new Label("Hotel");
        hotelInput.getChildren().add(lblHotel);
        hotelComboBox = new ComboBox<>();
        hotelComboBox.getItems().addAll(konference.getHoteller());
        hotelComboBox.setPromptText("Vælg et hotel");
        hotelComboBox.setOnAction(event -> opdaterHotelTilkøb());
        hotelInput.getChildren().add(hotelComboBox);

        ToggleGroup tglGroup = new ToggleGroup();
        rbEnkeltværelse = new RadioButton("Enkeltværelse");
        rbDobbeltværelse = new RadioButton("Dobbeltværelse");
        rbEnkeltværelse.setToggleGroup(tglGroup);
        rbDobbeltværelse.setToggleGroup(tglGroup);

        hotelInput.getChildren().add(rbEnkeltværelse);
        hotelInput.getChildren().add(rbDobbeltværelse);

        hotelInput.getChildren().add(new Label("Ankomstdato"));
        datePickerAnkomst = new DatePicker();
        datePickerAnkomst.getEditor().setDisable(true);
        datePickerAnkomst.setOnAction(event -> disableDatesBeforeArrival());
        hotelInput.getChildren().add(datePickerAnkomst);


        hotelInput.getChildren().add(new Label("Afrejsedato"));
        datePickerAfrejse = new DatePicker();
        datePickerAfrejse.getEditor().setDisable(true);
        hotelInput.getChildren().add(datePickerAfrejse);

    }

    private void disableDatesBeforeArrival() {
        if (datePickerAnkomst.getValue() != null) {
            datePickerAfrejse.setDayCellFactory(d ->
                    new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            setDisable(item.isBefore(datePickerAnkomst.getValue()) || item.isEqual(datePickerAnkomst.getValue()));
                        }
                    });
            if (datePickerAfrejse.getValue() == null || datePickerAfrejse.getValue().isBefore(datePickerAnkomst.getValue())) {
                datePickerAfrejse.setValue(datePickerAnkomst.getValue().plusDays(1));
            }
        }
    }

    private void opdaterHotelTilkøb() {
        hotelTilkøb.forEach(tilkøb -> hotelInput.getChildren().remove(tilkøb));
        hotelTilkøb.clear();


        List<Tilkøb> tilkøbsListe = hotelComboBox.getSelectionModel().getSelectedItem().getTilkøb();
        tilkøbsListe.forEach(tilkøb -> {
            CheckBox checkBox = new CheckBox(tilkøb.getNavn());
            checkBox.setUserData(tilkøb);
            hotelTilkøb.add(checkBox);
        });

        if (!hotelInput.getChildren().contains(lblTilkøb)) {
            lblTilkøb = new Label("Tilkøb");
            hotelInput.getChildren().add(lblTilkøb);
        }
        hotelInput.getChildren().addAll(hotelTilkøb);
    }

    private void renderInputFields() {
        deltagerInput = new VBox();
        deltagerInput.setSpacing(10);

        Label lblParticipant = new Label("Deltager");
        deltagerInput.getChildren().add(lblParticipant);

        txtNavn = new TextField();
        createTextField(txtNavn, "Navn");

        txtAdresse = new TextField();
        createTextField(txtAdresse, "Adresse");

        txtBy = new TextField();
        createTextField(txtBy, "By");

        txtLand = new TextField();
        createTextField(txtLand, "Land");

        txtEmail = new TextField();
        createTextField(txtEmail, "E-mail");

        txtTlfNr = new TextField();
        createTextField(txtTlfNr, "Tlf. nr");

        pane.add(deltagerInput, 0, 0);
    }

    private void createTextField(TextField textField, String textFieldPromptText) {
        textField.setPromptText(textFieldPromptText);
        deltagerInput.getChildren().add(textField);
    }
}