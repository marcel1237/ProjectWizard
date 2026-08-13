/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view.settings;

import com.projectwizard.service.PersistenceService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Componente público { SettingsView}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class SettingsView extends BorderPane {

    public SettingsView() {
        setPadding(new Insets(20));

        VBox content = new VBox(20);
        content.setAlignment(Pos.TOP_LEFT);

        Label title = new Label("Settings");
        title.setStyle("-fx-font-size:28px;-fx-font-weight:bold;");

        // AI Settings
        VBox aiGroup = new VBox(10);
        aiGroup.setStyle("-fx-border-color: #3c3f41; -fx-border-width: 1; -fx-padding: 10;");
        
        Label aiTitle = new Label("AI Configuration (Gemini)");
        aiTitle.setStyle("-fx-font-weight: bold;");
        
        PasswordField apiKeyField = new PasswordField();
        PersistenceService persistence = new PersistenceService();
        apiKeyField.setText(persistence.getGeminiApiKey());
        
        Button saveBtn = new Button("Save API Key");
        saveBtn.setOnAction(e -> {
            persistence.saveGeminiApiKey(apiKeyField.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "API Key saved!");
            alert.showAndWait();
        });

        aiGroup.getChildren().addAll(aiTitle, new Label("API Key:"), apiKeyField, saveBtn);

        content.getChildren().addAll(title, aiGroup);
        setCenter(content);
    }
}
