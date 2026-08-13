/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard;

import com.projectwizard.view.MainWindow;
//import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.projectwizard.theme.ThemeManager;
import com.projectwizard.theme.ThemeType;
import com.projectwizard.view.sidebar.Sidebar;
import com.projectwizard.view.dashboard.DashboardView;
import com.projectwizard.service.PersistenceService;
import com.projectwizard.core.navigation.NavigationController;
import com.projectwizard.core.navigation.NavigationTarget;
import java.io.File;


/**
 * Componente público { App}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class App {

    public void start(Stage stage) {

    	ThemeManager.apply(ThemeType.PRIMER_DARK);
        MainWindow window = new MainWindow();
        window.show(stage);

        // Restaurar último projeto aberto (Estilo Navegador)
        restoreLastProject();

        Scene scene = stage.getScene();

        if (scene != null) {

            scene.getStylesheets().add(
                    getClass()
                            .getResource("/themes/projectwizard.css")
                            .toExternalForm()
            );

        }

    }

    private void restoreLastProject() {
        PersistenceService persistence = new PersistenceService();
        String lastPath = persistence.getLastProjectPath();
        if (lastPath != null) {
            File projectDir = new File(lastPath);
            if (projectDir.exists() && projectDir.isDirectory()) {
                NavigationController.getInstance()
                        .navigateWithProject(NavigationTarget.WORKSPACE, projectDir);
            }
        }
    }

}
