package com.projectwizard.view.views;

import com.projectwizard.core.ApplicationContext;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class NewProjectView extends BorderPane {

    public NewProjectView() {

        ApplicationContext context = new ApplicationContext();

        setPadding(new Insets(20));

        Label title = new Label("New Project");
        title.setStyle("-fx-font-size:24px;-fx-font-weight:bold;");

        Button back = new Button("← Home");

        back.setOnAction(e ->
                context.getNavigationService().navigate(new HomeView())
        );

        HBox top = new HBox(15, back, title);
        top.setAlignment(Pos.CENTER_LEFT);

        setTop(top);

    }

}
