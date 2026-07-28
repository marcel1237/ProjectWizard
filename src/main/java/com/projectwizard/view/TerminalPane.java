package com.projectwizard.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import org.fxmisc.richtext.InlineCssTextArea;
import org.fxmisc.flowless.VirtualizedScrollPane;
import java.io.*;
import java.nio.charset.StandardCharsets;
import javafx.application.Platform;

public class TerminalPane extends BorderPane {
    private final InlineCssTextArea outputArea = new InlineCssTextArea();
    private final TextField inputField = new TextField();
    private Process process;
    private BufferedWriter writer;

    public TerminalPane() {
        outputArea.setEditable(false);
        outputArea.setStyle("-fx-background-color: #0c0c0c; -fx-font-family: 'Monospace'; -fx-font-size: 13px;");
        
        inputField.setStyle("-fx-background-color: #1e1e1e; -fx-text-fill: #00ff00; -fx-font-family: 'Monospace'; -fx-border-color: #333;");
        inputField.setPromptText("Digite um comando e pressione Enter...");
        
        setCenter(new VirtualizedScrollPane<>(outputArea));
        setBottom(inputField);

        startShell();

        inputField.setOnAction(e -> {
            String cmd = inputField.getText();
            if (cmd != null && !cmd.isEmpty()) {
                sendCommand(cmd);
                inputField.clear();
            }
        });
    }

    private void startShell() {
        try {
            ProcessBuilder pb = new ProcessBuilder("bash", "-i");
            pb.redirectErrorStream(true);
            process = pb.start();

            writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream(), StandardCharsets.UTF_8));

            Thread outputThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        appendOutput(line + "\n");
                    }
                } catch (IOException e) {
                    appendError("Erro ao ler shell: " + e.getMessage() + "\n");
                }
            });
            outputThread.setDaemon(true);
            outputThread.start();

            appendOutput("--- Shell Iniciada (Bash) ---\n");

        } catch (IOException e) {
            appendError("Falha ao iniciar shell: " + e.getMessage() + "\n");
        }
    }

    public void sendCommand(String cmd) {
        try {
            writer.write(cmd + "\n");
            writer.flush();
        } catch (IOException e) {
            appendError("Erro ao enviar comando: " + e.getMessage() + "\n");
        }
    }

    public void appendOutput(String text) {
        Platform.runLater(() -> {
            int start = outputArea.getLength();
            outputArea.appendText(text);
            outputArea.setStyle(start, outputArea.getLength(), "-fx-fill: #cccccc;");
            outputArea.requestFollowCaret();
        });
    }

    public void appendError(String text) {
        Platform.runLater(() -> {
            int start = outputArea.getLength();
            outputArea.appendText(text);
            outputArea.setStyle(start, outputArea.getLength(), "-fx-fill: #e74c3c;");
            outputArea.requestFollowCaret();
        });
    }

    public void logSystem(String msg) {
        appendOutput("[SISTEMA] " + msg + "\n");
    }

    public void clear() {
        Platform.runLater(outputArea::clear);
    }
}
