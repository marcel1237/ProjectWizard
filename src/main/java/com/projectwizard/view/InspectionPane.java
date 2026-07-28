package com.projectwizard.view;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class InspectionPane extends VBox {
    private final ListView<String> issuesList = new ListView<>();

    public InspectionPane() {
        getChildren().addAll(new Label("🔍 Problemas de Código"), issuesList);
        issuesList.getItems().add("Nenhum problema detectado.");
    }
}
