package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Konference;

import java.util.Arrays;

public class ParticipantPane extends VBox {
    private TableView<Konference> konferenceTableView;

    public ParticipantPane() {
        this.setPadding(new Insets(10));
        initContent();
    }

    private void initContent() {
        conferenceTableView();
        conferenceRegisterButton();
    }

    private void conferenceTableView() {
        Label label = new Label("Konferencer");
        this.getChildren().add(label);

        this.konferenceTableView = new TableView<>();

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

        konferenceTableView.getColumns().addAll(Arrays.asList(nameCol, startCol, endCol, priceCol, registerOpenCol, antalTilmeldte));

        konferenceTableView.getItems().addAll(Controller.getKonferencer());
        konferenceTableView.setPrefWidth(800);
        this.getChildren().add(konferenceTableView);
    }

    private void conferenceRegisterButton() {
        Button konferenceTilmelding = new Button("Tilmeld Konference");
        konferenceTilmelding.setOnAction(event -> showRegisterWindow());
        this.getChildren().add(konferenceTilmelding);
    }

    private void showRegisterWindow() {
        Konference konference = konferenceTableView.getFocusModel().getFocusedItem();
        boolean localDate = konference.erTilmeldingerÅben();
        if (konference.erTilmeldingerÅben()) {
            TilmeldKonference window = new TilmeldKonference(konference);
            window.showAndWait();
            updateTableView();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Tilmelding er ikke åben endnu for konferencen " + konference.getNavn());
            alert.show();
        }
    }

    public void updateTableView() {
        konferenceTableView.getItems().clear();
        konferenceTableView.getItems().addAll(Controller.getKonferencer());
    }

}