package com.projectwizard.view.editor;

import java.io.File;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class EditorPane extends BorderPane {

    private final File file;
    private final TextArea textArea;

    public EditorPane(File file, String content) {
        this.file = file;

        textArea = new TextArea(content != null ? content : "");
        textArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 12;");
        textArea.setWrapText(false);
        textArea.setEditable(true);

        setCenter(textArea);
    }

    public File getFile() {
        return file;
    }

    public String getTabLabel() {
        return file.getName();
    }

}
