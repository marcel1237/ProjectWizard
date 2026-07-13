package com.projectwizard.view.editor;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EditorHost extends BorderPane {

    private final TextArea editor;
    private final Label titleLabel;

    public EditorHost() {
        titleLabel = new Label("No file selected");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        titleLabel.setStyle("-fx-text-fill: #666; -fx-padding: 5;");

        VBox titleBox = new VBox(titleLabel);
        titleBox.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #ddd; "
                + "-fx-border-width: 0 0 1 0;");
        titleBox.setPadding(new Insets(5, 10, 5, 10));

        editor = new TextArea();
        editor.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 12;");
        editor.setWrapText(false);
        editor.setEditable(false);

        setTop(titleBox);
        setCenter(editor);
    }

    public void setTitle(String title) {
        titleLabel.setText("📄 " + title);
    }

    public void setContent(String content) {
        editor.setText(content != null ? content : "");
    }

    public void clear() {
        editor.clear();
        titleLabel.setText("No file selected");
    }

}
