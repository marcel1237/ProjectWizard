/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view;

import com.projectwizard.core.ApplicationContext;
import com.projectwizard.core.navigation.NavigationController;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Componente público { MainWindow}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class MainWindow {

    private final ApplicationContext context =
            ApplicationContext.getInstance();

    public void show(Stage stage) {

        BorderPane root = new BorderPane();

        MenuBar menuBar = new MenuBar();

        Menu file = new Menu("File");
        Menu help = new Menu("Help");

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> stage.close());

        MenuItem about = new MenuItem("About");

        about.setOnAction(e ->
                context.getDialogService().showInformation(
                        "About",
                        context.getApplicationName()
                                + "\nVersion "
                                + context.getVersion()
                )
        );

        file.getItems().add(exit);
        help.getItems().add(about);

        menuBar.getMenus().addAll(file, help);

        VBox top = new VBox(
                menuBar,
                new MainToolBar()
        );

        root.setTop(top);


        WorkspacePane workspace = new WorkspacePane();

        root.setCenter(workspace);

        root.setBottom(
                new StatusBar()
        );

        Scene scene =
                new Scene(root, 1400, 850);

        stage.setScene(scene);
        stage.setTitle(
                context.getApplicationName()
        );

        stage.setOnCloseRequest(event -> NavigationController.getInstance().shutdown());

        stage.centerOnScreen();
        stage.show();

    }

}
