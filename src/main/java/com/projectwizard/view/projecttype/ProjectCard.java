/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view.projecttype;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Componente público { ProjectCard}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class ProjectCard extends VBox {

    public ProjectCard(
            String icon,
            String title,
            String description
    ) {

        setSpacing(6);
        setPadding(new Insets(15));

        setPrefWidth(260);

        setStyle(
            "-fx-background-radius:10;" +
            "-fx-border-radius:10;" +
            "-fx-border-color:#555;" +
            "-fx-padding:15;"
        );

        Label iconLabel = new Label(icon);
        iconLabel.setStyle("-fx-font-size:28px;");

        Label titleLabel = new Label(title);
        titleLabel.setStyle(
                "-fx-font-size:18px;" +
                "-fx-font-weight:bold;"
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setWrapText(true);

        getChildren().addAll(
                iconLabel,
                titleLabel,
                descriptionLabel
        );

    }

}
