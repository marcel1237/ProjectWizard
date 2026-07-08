package com.projectwizard.view;

import com.projectwizard.core.ApplicationContext;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WelcomePane extends VBox {

    public WelcomePane() {

        ApplicationContext context = new ApplicationContext();

        setSpacing(20);
        setAlignment(Pos.CENTER);

        Label title = new Label(context.getApplicationName());
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        Label version = new Label("Version " + context.getVersion());

        Button newProject = new Button("New Project");
        newProject.setPrefWidth(180);

        Button openProject = new Button("Open Project");
        openProject.setPrefWidth(180);

        newProject.setOnAction(e ->
                context.getDialogService().showInformation(
                        "New Project",
                        "Project Wizard\n\nThe project creation wizard will be implemented in the next steps."
                )
        );

        openProject.setOnAction(e ->
                context.getDialogService().showInformation(
                        "Open Project",
                        "This feature will be implemented soon."
                )
        );

        getChildren().addAll(
                title,
                version,
                newProject,
                openProject
        );

    }

}
