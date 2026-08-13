/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

/**
 * Componente público { LogcatPane}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class LogcatPane extends BorderPane {
    private final TextArea logArea = new TextArea();

    public LogcatPane() {
        logArea.setEditable(false);
        logArea.setStyle("-fx-control-inner-background: #0c0c0c; -fx-text-fill: #a9b7c6; -fx-font-family: 'Monospace';");
        setCenter(logArea);
    }

    public void log(String msg) {
        logArea.appendText(msg + "\n");
    }
}
