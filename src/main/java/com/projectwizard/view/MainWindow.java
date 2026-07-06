package com.projectwizard.view;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow {

    public void show(Stage stage) {

        BorderPane root = new BorderPane();

        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> stage.close());

        MenuItem aboutItem = new MenuItem("About");

        fileMenu.getItems().add(exitItem);
        helpMenu.getItems().add(aboutItem);

        menuBar.getMenus().addAll(fileMenu, helpMenu);

        VBox top = new VBox(
                menuBar,
                new MainToolBar()
        );

        SplitPane splitPane = new SplitPane(
                new NavigationPane(),
                new WorkspacePane()
        );

        splitPane.setDividerPositions(0.22);

        root.setTop(top);
        root.setCenter(splitPane);
        root.setBottom(new StatusBar());

        Scene scene = new Scene(root, 1100, 700);

        stage.setTitle("Project Wizard");
        stage.setScene(scene);

        stage.centerOnScreen();
        stage.show();

    }

}
