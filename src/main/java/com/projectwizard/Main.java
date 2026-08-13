/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Componente público { Main}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class Main extends Application {

    private final App app = new App();

    @Override
    public void start(Stage stage) {

        app.start(stage);

    }

    public static void main(String[] args) {

        launch(args);

    }

}
