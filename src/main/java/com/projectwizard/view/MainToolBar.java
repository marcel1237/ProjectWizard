package com.projectwizard.view;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

import com.projectwizard.core.navigation.NavigationController;
import com.projectwizard.core.navigation.NavigationTarget;

public class MainToolBar extends ToolBar {

    public MainToolBar() {

        Button newProject = new Button("New Project");

        Button open = new Button("Open");

        open.setOnAction(e ->
                NavigationController.getInstance()
                        .navigate(NavigationTarget.OPEN_PROJECT)
        );

        Button settings = new Button("Settings");

        getItems().addAll(
                newProject,
                open,
                settings
        );
    }
}
