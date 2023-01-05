package gui;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Ledsager;
import model.Udflugt;

import java.util.Arrays;

public class UdflugtDeltagereVindue extends Stage {
    private final GridPane pane;
    private final Udflugt udflugt;
    private TableView<Ledsager> ledsagereTableView;


    public UdflugtDeltagereVindue(Udflugt udflugt) {
        this.udflugt = udflugt;

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
        ledsagereTabel();

        Button btnOK = new Button("OK");
        btnOK.setOnAction(event -> this.close());
        pane.add(btnOK, 0,2);
    }

    private void ledsagereTabel() {
        this.ledsagereTableView = new TableView<>();
        ledsagereTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        Label label = new Label("Ledsagere");
        pane.add(label, 0, 0);

        TableColumn<Ledsager, String> nameCol = new TableColumn<>("Navn");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("navn"));

        TableColumn<Ledsager, String> telefonNrCol = new TableColumn<>("TelefonNr");
        telefonNrCol.setCellValueFactory(new PropertyValueFactory<>("telefonNr"));

        TableColumn<Ledsager, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Ledsager, String> deltagerCol = new TableColumn<>("Tilknyttet til Deltager");
        deltagerCol.setCellValueFactory(new PropertyValueFactory<>("tilknyttetDeltagerNavn"));

        ledsagereTableView.getColumns().addAll(Arrays.asList(nameCol, telefonNrCol, emailCol, deltagerCol));

        ledsagereTableView.getItems().addAll(udflugt.getLedsagere());
        ledsagereTableView.prefWidthProperty().bind(super.widthProperty());
        ledsagereTableView.getSelectionModel().selectFirst();
        pane.add(ledsagereTableView, 0, 1);
    }
}
