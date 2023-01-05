package gui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Konference;
import model.Udflugt;

import java.util.List;

public class TilmeldLedsagerTilUdflugt extends Stage {
    private final GridPane pane;
    private ListView<Udflugt> udflugter;
    private Konference konference;

    public TilmeldLedsagerTilUdflugt(Konference konference) {
        this.konference = konference;

        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(true);
        this.pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        this.initContent();

        Scene scene = new Scene(pane, 600, 450);
        this.setScene(scene);
    }

    private void initContent() {
        udflugter = new ListView<>((FXCollections.observableArrayList(konference.getUdflugter())));
        udflugter.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        pane.add(udflugter, 0, 0);

        Button btnOK = new Button("OK");
        btnOK.setOnAction(event -> this.close());
        pane.add(btnOK, 0,1);
    }

    public List<Udflugt> getValgteUdflugter() {
        return udflugter.getSelectionModel().getSelectedItems();
    }
}
