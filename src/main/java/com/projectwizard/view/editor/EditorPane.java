package com.projectwizard.view.editor;

import java.io.File;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class EditorPane extends BorderPane {
    private final TextArea editor = new TextArea();
    private File currentFile;

    public EditorPane(File file, String content) {
        this.currentFile = file;
        editor.setText(content);
        editor.setStyle("-fx-font-family: 'Consolas', 'Monospace'; -fx-font-size: 13px;");
        setCenter(editor);
    }

    public String getText() { return editor.getText(); }
    public File getFile() { return currentFile; }
}
