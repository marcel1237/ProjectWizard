/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.core.navigation;

import java.io.File;

import com.projectwizard.view.*;
import com.projectwizard.view.about.AboutView;
import com.projectwizard.view.dashboard.DashboardView;
import com.projectwizard.view.git.GitView;
import com.projectwizard.view.github.GitHubView;
import com.projectwizard.view.newproject.NewProjectView;
import com.projectwizard.view.openproject.OpenProjectView;
import com.projectwizard.view.settings.SettingsView;
import com.projectwizard.view.templates.TemplatesView;
import com.projectwizard.workspace.ProjectWorkspace;
import com.projectwizard.service.PersistenceService;

/**
 * Componente público { NavigationController}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public final class NavigationController {

    private static final NavigationController INSTANCE = new NavigationController();
    private WorkspacePane workspace;
    private ProjectWorkspace projectWorkspace;

    private NavigationController() {
    }

    public static NavigationController getInstance() {
        return INSTANCE;
    }

    public void setWorkspace(WorkspacePane workspace) {
        this.workspace = workspace;
    }

    public void navigate(NavigationTarget target) {
        if (workspace == null) return;

        switch (target) {
            case HOME -> workspace.setContent(new DashboardView());
            case NEW_PROJECT -> workspace.setContent(new NewProjectView());
            case OPEN_PROJECT -> workspace.setContent(new OpenProjectView());
            case TEMPLATES -> workspace.setContent(new TemplatesView());
            case GIT -> workspace.setContent(new GitView());
            case GITHUB -> workspace.setContent(new GitHubView());
            case SETTINGS -> workspace.setContent(new SettingsView());
            case ABOUT -> workspace.setContent(new AboutView());
            case BROWSER -> {
                BrowserPane bp = new BrowserPane();
                String lastPath = new PersistenceService().getLastProjectPath();
                if (lastPath != null) bp.setProjectPath(lastPath);
                workspace.setContent(bp);
            }
            case TERMINAL -> workspace.setContent(new TerminalPane());
            case DATABASE -> workspace.setContent(new DatabasePane());
            case GEMINI -> workspace.setContent(new GeminiPane());
            case SHELL_SCRIPTING -> workspace.setContent(new ShellScriptingPane());
            case WORKSPACE -> {
                if (projectWorkspace == null) {
                    projectWorkspace = new ProjectWorkspace();
                }
                workspace.setContent(projectWorkspace);
            }
        }
    }

    public void navigateWithProject(NavigationTarget target, File projectRoot) {
        if (workspace == null || projectRoot == null) return;

        if (target == NavigationTarget.WORKSPACE) {
            new PersistenceService().saveLastProjectPath(projectRoot.getAbsolutePath());
            projectWorkspace = new ProjectWorkspace();
            projectWorkspace.loadProject(projectRoot);
            workspace.setContent(projectWorkspace);
        }
    }

    public void shutdown() {
        if (projectWorkspace != null) projectWorkspace.dispose();
        if (workspace != null && workspace.getCenter() instanceof TerminalPane terminal) terminal.dispose();
        if (workspace != null && workspace.getCenter() instanceof GeminiPane gemini) gemini.dispose();
    }

}
