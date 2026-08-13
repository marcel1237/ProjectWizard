/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 * Componente público { InspectionPane}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class InspectionPane extends VBox {
    private final ListView<String> issuesList = new ListView<>();

    public InspectionPane() {
        getChildren().addAll(new Label("🔍 Problemas de Código"), issuesList);
        issuesList.getItems().add("Nenhum problema detectado.");
    }
}
