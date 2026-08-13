/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view.sidebar;

import com.projectwizard.core.navigation.NavigationController;
import com.projectwizard.core.navigation.NavigationTarget;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Componente público { Sidebar}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class Sidebar extends VBox {

    public Sidebar() {

        setSpacing(6);
        setPadding(new Insets(10));
        setPrefWidth(220);

        create("🏠 Home", NavigationTarget.HOME);
        create("📦 New Project", NavigationTarget.NEW_PROJECT);
        create("📂 Open Project", NavigationTarget.OPEN_PROJECT);
        create("📚 Templates", NavigationTarget.TEMPLATES);
        create("🌱 Git", NavigationTarget.GIT);
        create("🐙 GitHub", NavigationTarget.GITHUB);
        create("⚙ Settings", NavigationTarget.SETTINGS);
        create("ℹ About", NavigationTarget.ABOUT);

    }

    private void create(String text, NavigationTarget target) {

        Button b = new Button(text);

        b.setMaxWidth(Double.MAX_VALUE);

        VBox.setVgrow(b, Priority.NEVER);

        b.setOnAction(e -> {

            NavigationController
                    .getInstance()
                    .navigate(target);

        });

        getChildren().add(b);

    }

}
