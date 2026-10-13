/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Separator;
import com.projectwizard.core.navigation.NavigationController;
import com.projectwizard.core.navigation.NavigationTarget;

/**
 * Componente público { MainToolBar}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class MainToolBar extends ToolBar {

    public MainToolBar() {

        Button newProject = new Button("New Project");
        Button open = new Button("Open");

        open.setOnAction(e -> NavigationController.getInstance().navigate(NavigationTarget.OPEN_PROJECT));

        Button workspace = new Button("📂 Workspace");
        workspace.setOnAction(e -> NavigationController.getInstance().navigate(NavigationTarget.WORKSPACE));

        Button browser = new Button("🌐 Browser");
        browser.setOnAction(e -> NavigationController.getInstance().navigate(NavigationTarget.BROWSER));

        Button terminal = new Button("💻 Term");
        terminal.setOnAction(e -> NavigationController.getInstance().navigate(NavigationTarget.TERMINAL));

        Button ai = new Button("✨ Gemini");
        ai.setOnAction(e -> NavigationController.getInstance().navigate(NavigationTarget.GEMINI));

        Button shell = new Button("🐚 Shell");
        shell.setOnAction(e -> NavigationController.getInstance().navigate(NavigationTarget.SHELL_SCRIPTING));

        Button settings = new Button("⚙ Settings");

        getItems().addAll(
                newProject, open,
                new Separator(),
                workspace, browser, terminal, ai, shell,
                new Separator(),
                settings
        );
    }
}
