package com.projectwizard.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class StatusBar extends HBox {

    public StatusBar() {

        Label status = new Label("Ready");

        Label version = new Label("1.0");

        HBox spacer = new HBox();

        HBox.setHgrow(spacer, Priority.ALWAYS);

        getChildren().addAll(
                status,
                spacer,
                version
        );

        setPadding(new Insets(6,10,6,10));
        setAlignment(Pos.CENTER_LEFT);

    }

}
