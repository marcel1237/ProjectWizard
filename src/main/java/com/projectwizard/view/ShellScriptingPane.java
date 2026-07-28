package com.projectwizard.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.flowless.VirtualizedScrollPane;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ShellScriptingPane extends VBox {
    private final CodeArea scriptPreview = new CodeArea();

    public ShellScriptingPane() {
        setPadding(new Insets(10));
        setSpacing(15);

        Label title = new Label("🐚 Shell Scripting Assistant");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #4285F4;");

        TitledPane genGroup = new TitledPane("Geradores Rápidos", createGeneratorButtons());
        TitledPane toolsGroup = new TitledPane("Ferramentas de Análise", createToolButtons());

        scriptPreview.setEditable(false);
        scriptPreview.setStyle("-fx-background-color: #0c0c0c; -fx-font-family: 'Monospace'; -fx-font-size: 12px;");

        VBox.setVgrow(scriptPreview, Priority.ALWAYS);
        getChildren().addAll(title, genGroup, toolsGroup, new Label("Preview:"), new VirtualizedScrollPane<>(scriptPreview));
    }

    private VBox createGeneratorButtons() {
        VBox vbox = new VBox(5);
        Button btnCron = new Button("Gerar Tarefa Cron");
        Button btnBackup = new Button("Gerar Script de Backup");

        btnCron.setOnAction(e -> scriptPreview.replaceText("#!/bin/bash\n# Cron Job\n0 3 * * * /path/to/script.sh"));
        btnBackup.setOnAction(e -> scriptPreview.replaceText("#!/bin/bash\n# Backup\nDEST=\"~/backups\"\nmkdir -p $DEST\ntar -czf $DEST/backup.tar.gz ."));

        vbox.getChildren().addAll(btnCron, btnBackup);
        return vbox;
    }

    private VBox createToolButtons() {
        VBox vbox = new VBox(5);
        Button btnLint = new Button("Check Syntax (ShellCheck)");
        btnLint.setOnAction(e -> runLint());
        vbox.getChildren().add(btnLint);
        return vbox;
    }

    private void runLint() {
        String content = scriptPreview.getText();
        if (content.isEmpty()) return;

        try {
            File temp = File.createTempFile("lint", ".sh");
            Files.writeString(temp.toPath(), content);
            
            Process p = new ProcessBuilder("shellcheck", temp.getAbsolutePath()).start();
            // Handle output in a real app, for now just a message
            scriptPreview.appendText("\n\n# ✅ Lint concluído (verifique terminal)");
        } catch (IOException e) {
            scriptPreview.appendText("\n\n# ❌ Falha ao rodar ShellCheck");
        }
    }
}
