package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import controller.Controller;

public class MainApp extends Application {
    @Override
    public void init() {
        Controller.init();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Arcitecture Demo");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        Tab tabAdmin = new Tab("Admin");
        tabPane.getTabs().add(tabAdmin);

        AdminPane adminPane = new AdminPane();
        tabAdmin.setContent(adminPane);
        tabAdmin.setOnSelectionChanged(event -> adminPane.updateDeltagerListView());

        Tab tabParticipant = new Tab("Deltager");
        tabPane.getTabs().add(tabParticipant);

        ParticipantPane participantPane = new ParticipantPane();
        tabParticipant.setContent(participantPane);

        tabParticipant.setOnSelectionChanged(event -> participantPane.updateTableView());
    }


}
