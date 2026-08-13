/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view;

import com.projectwizard.core.navigation.NavigationController;
import com.projectwizard.core.navigation.NavigationTarget;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Componente público { NavigationPane}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class NavigationPane extends VBox {

    public NavigationPane() {

        setSpacing(8);
        setPadding(new Insets(10));
        setPrefWidth(230);

        addButton("🏠 Home", NavigationTarget.HOME);
        addButton("📦 New Project", NavigationTarget.NEW_PROJECT);
        addButton("📂 Open Project", NavigationTarget.OPEN_PROJECT);
        addButton("📁 Templates", NavigationTarget.TEMPLATES);

        getChildren().add(new Separator());

        addButton("🌿 Git", NavigationTarget.GIT);
        addButton("☁ GitHub", NavigationTarget.GITHUB);

        getChildren().add(new Separator());

        addButton("⚙ Settings", NavigationTarget.SETTINGS);
        addButton("ℹ About", NavigationTarget.ABOUT);

        VBox spacer = new VBox();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        getChildren().add(spacer);

        Button version = new Button("Project Wizard 1.0");
        version.setDisable(true);
        version.setMaxWidth(Double.MAX_VALUE);

        getChildren().add(version);
    }

    private void addButton(String text, NavigationTarget target) {

        Button button = new Button(text);

        button.setPrefHeight(36);
        button.setMaxWidth(Double.MAX_VALUE);

        button.setOnAction(e ->
                NavigationController
                        .getInstance()
                        .navigate(target));

        getChildren().add(button);

    }

}
