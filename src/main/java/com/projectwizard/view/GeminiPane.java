/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import com.projectwizard.service.PersistenceService;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.nio.charset.StandardCharsets;

/**
 * Componente público { GeminiPane}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class GeminiPane extends VBox {
    private final WebView chatHistory = new WebView();
    private final TextField userInput = new TextField();
    private final ComboBox<String> modelSelector = new ComboBox<>();
    private final Label statusLabel = new Label("🤖 Status: Pronto");
    private final PersistenceService persistence = new PersistenceService();
    private final StringBuilder htmlContent = new StringBuilder("<html><body style='background-color: #1e1f22; color: #bcbec4; font-family: sans-serif; font-size: 13px;'>");
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private String projectRoot;
    private String currentFileName;
    private String currentFileContent;

    public GeminiPane() {
        setPadding(new Insets(0));
        setSpacing(0);
        getStyleClass().add("gemini-pane");

        // Header
        HBox header = new HBox(10);
        header.setPadding(new Insets(10));
        header.setStyle("-fx-background-color: #2b2d30; -fx-border-color: #3c3f41; -fx-border-width: 0 0 1 0;");
        
        Label title = new Label("✨ Gemini");
        title.setStyle("-fx-font-weight: bold; -fx-text-fill: #dfe1e5;");
        
        modelSelector.getItems().addAll("qwen2.5-coder:1.5b", "gemini-1.5-flash", "gemini-1.5-pro");
        modelSelector.setValue("qwen2.5-coder:1.5b");

        header.getChildren().addAll(title, new Region(), modelSelector);
        HBox.setHgrow(header.getChildren().get(1), Priority.ALWAYS);

        VBox.setVgrow(chatHistory, Priority.ALWAYS);
        
        // Input Area
        VBox inputArea = new VBox(10);
        inputArea.setPadding(new Insets(10));
        userInput.setPromptText("Pergunte ao Gemini...");
        userInput.setOnAction(e -> handleSend());

        inputArea.getChildren().addAll(statusLabel, userInput);

        getChildren().addAll(header, chatHistory, inputArea);
        updateChat();
    }

    public void setContext(String fileName, String content) {
        this.currentFileName = fileName;
        this.currentFileContent = content.length() > 5000 ? content.substring(0, 5000) : content;
    }

    public void setProjectRoot(String path) {
        this.projectRoot = path;
    }

    private void handleSend() {
        String text = userInput.getText().trim();
        if (text.isEmpty()) return;

        appendMessage("Você", text);
        userInput.clear();
        userInput.setDisable(true);
        
        String model = modelSelector.getValue();
        boolean isLocal = model.contains("qwen");
        statusLabel.setText(isLocal ? "🤖 Status: Chamando Ollama..." : "🤖 Status: Chamando Gemini...");

        executor.submit(() -> {
            try {
                String fullPrompt = buildPrompt(text);
                ProcessBuilder pb;
                if (isLocal) {
                    pb = new ProcessBuilder("ollama", "run", model, fullPrompt);
                } else {
                    pb = new ProcessBuilder("/snap/bin/gemini", "--model", model, "--prompt", fullPrompt);
                    String apiKey = persistence.getGeminiApiKey();
                    if (!apiKey.isEmpty()) pb.environment().put("GEMINI_API_KEY", apiKey);
                }

                if (projectRoot != null) pb.directory(new File(projectRoot));
                pb.redirectErrorStream(true);
                Process process = pb.start();

                StringBuilder response = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) response.append(line).append("\n");
                }
                int exitCode = process.waitFor();
                if (exitCode != 0) throw new IOException("AI command exited with code " + exitCode + ": " + response);
                
                String finalResponse = response.toString();
                Platform.runLater(() -> {
                    appendMessage("Gemini", finalResponse);
                    userInput.setDisable(false);
                    statusLabel.setText("🤖 Status: Pronto");
                    persistence.incrementUsage();
                });

            } catch (Exception e) {
                Platform.runLater(() -> {
                    appendMessage("Erro", e.getMessage());
                    userInput.setDisable(false);
                });
            }
        });
    }

    private String buildPrompt(String text) {
        String system = "Responda de forma técnica e direta focada em Java/C++.";
        if (currentFileName != null) {
            return String.format("%s\n\nContexto: Arquivo %s\n```\n%s\n```\nPergunta: %s", system, currentFileName, currentFileContent, text);
        }
        return system + "\n\nPergunta: " + text;
    }

    private void appendMessage(String sender, String msg) {
        htmlContent.append("<div style='margin-bottom: 15px;'>")
                   .append("<b>").append(escapeHtml(sender)).append("</b><br>")
                   .append(escapeHtml(msg).replace("\n", "<br>"))
                   .append("</div>");
        updateChat();
    }

    private void updateChat() {
        Platform.runLater(() -> chatHistory.getEngine().loadContent(htmlContent.toString() + "</body></html>"));
    }

    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    public void dispose() {
        executor.shutdownNow();
    }
}
