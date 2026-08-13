/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

/**
 * Componente público { BrowserPane}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class BrowserPane extends BorderPane {
    private final TabPane tabPane = new TabPane();
    private String projectPath;

    public BrowserPane() {
        tabPane.getTabs().addAll(
            createLazyTab("🌐 Google", "https://www.google.com"),
            createLazyTab("🤖 Duck.ai", "https://duck.ai"),
            createLazyTab("🦁 Brave Leo", "https://search.brave.com/"),
            createLazyTab("💬 ChatGPT", "https://chatgpt.com"),
            createLazyTab("🐚 ExplainShell", "https://explainshell.com")
        );
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        setCenter(tabPane);
    }

    private Tab createLazyTab(String title, String url) {
        Tab tab = new Tab(title);
        
        // Placeholder para não consumir memória até que o usuário clique
        StackPane placeholder = new StackPane(new Label("Clique para carregar " + title));
        tab.setContent(placeholder);

        tab.selectedProperty().addListener((obs, oldVal, isSelected) -> {
            if (isSelected && tab.getContent() instanceof StackPane) {
                tab.setContent(createBrowserContent(url));
            }
        });

        return tab;
    }

    private Node createBrowserContent(String url) {
        VBox container = new VBox(4);
        container.setPadding(new Insets(6));
        
        HBox urlBox = new HBox(5);
        TextField urlBar = new TextField(url);
        Button refreshBtn = new Button("🔄");
        HBox.setHgrow(urlBar, Priority.ALWAYS);
        urlBox.getChildren().addAll(urlBar, refreshBtn);

        WebView webView = new WebView();
        javafx.scene.web.WebEngine engine = webView.getEngine();

        // Configuração de User Agent para evitar que sites pesados enviem código incompatível
        engine.setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

        urlBar.setOnAction(e -> {
            String input = urlBar.getText();
            if (!input.startsWith("http://") && !input.startsWith("https://")) {
                input = "https://" + input;
            }
            engine.load(input);
        });

        refreshBtn.setOnAction(e -> engine.reload());

        engine.locationProperty().addListener((obs, oldVal, newVal) -> {
            urlBar.setText(newVal);
        });

        // Tratamento de erros e alertas para evitar travamentos da UI
        engine.setOnError(event -> System.err.println("[Browser Error] " + event.getMessage()));
        engine.setOnAlert(event -> System.out.println("[Browser Alert] " + event.getData()));

        engine.load(url);
        VBox.setVgrow(webView, Priority.ALWAYS);
        container.getChildren().addAll(urlBox, webView);
        return container;
    }

    public void setProjectPath(String path) {
        this.projectPath = path;
    }
}
