/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view.dashboard;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Componente público { DashboardView}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class DashboardView extends VBox {

    public DashboardView() {

        setSpacing(20);

        setAlignment(Pos.CENTER);

        Label title = new Label("Project Wizard");

        title.setStyle("-fx-font-size:30px;-fx-font-weight:bold;");

        Label subtitle = new Label(
                "Create amazing projects"
        );

        Button newProject = new Button("New Project");

        Button openProject = new Button("Open Project");

        getChildren().addAll(

                title,

                subtitle,

                newProject,

                openProject

        );

    }

}
