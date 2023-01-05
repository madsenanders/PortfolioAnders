package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OpretHotelVindue extends Stage {

    private final GridPane pane;
    private TextField navn, adresse, enkeltVærelsesPris, dobbeltVærelsesPris;

    public OpretHotelVindue() {

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
        navn = new TextField();
        navn.setPromptText("Navn");

        adresse = new TextField();
        adresse.setPromptText("Adresse");

        enkeltVærelsesPris = new TextField();
        enkeltVærelsesPris.setPromptText("Pris enkeltværelse");

        dobbeltVærelsesPris = new TextField();
        dobbeltVærelsesPris.setPromptText("Pris dobbeltværelse");

        pane.add(navn, 0, 0);
        pane.add(adresse, 0, 1);
        pane.add(enkeltVærelsesPris, 0, 2);
        pane.add(dobbeltVærelsesPris, 0, 3);

        Button btnOK = new Button("Opret");
        btnOK.setOnAction(event -> opretHotel());

        pane.add(btnOK, 0, 8);
    }

    private void opretHotel() {
        Controller.createHotel(navn.getText(), adresse.getText(), Double.parseDouble(dobbeltVærelsesPris.getText()), Double.parseDouble(enkeltVærelsesPris.getText()));
        this.close();
    }
}
