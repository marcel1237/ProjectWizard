package com.projectwizard.view;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class LogcatPane extends BorderPane {
    private final TextArea logArea = new TextArea();

    public LogcatPane() {
        logArea.setEditable(false);
        logArea.setStyle("-fx-control-inner-background: #0c0c0c; -fx-text-fill: #a9b7c6; -fx-font-family: 'Monospace';");
        setCenter(logArea);
    }

    public void log(String msg) {
        logArea.appendText(msg + "\n");
    }
}
