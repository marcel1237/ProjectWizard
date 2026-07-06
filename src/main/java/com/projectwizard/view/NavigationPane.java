package com.projectwizard.view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class NavigationPane extends StackPane {

    public NavigationPane() {

        getChildren().add(new Label("Navigation"));

        setPrefWidth(220);

    }

}
