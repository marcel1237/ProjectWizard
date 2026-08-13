/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.workspace;

import java.io.File;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import com.projectwizard.model.Project;
import com.projectwizard.view.*;
import com.projectwizard.view.editor.EditorHost;
import com.projectwizard.view.editor.EditorSupport;
import com.projectwizard.view.explorer.PackageExplorer;
import com.projectwizard.view.explorer.FileTreeCell;
import com.projectwizard.service.BackupService;

/**
 * Componente público { ProjectWorkspace}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class ProjectWorkspace extends BorderPane {

    private Project currentProject;
    private PackageExplorer explorer;
    private EditorHost editor;
    private ProjectHeader header;

    private TabPane bottomTabs;
    private TabPane eastTabs;
    private SplitPane horizontalSplitter;
    private SplitPane verticalSplitter;

    private GeminiPane geminiPane;
    private GitPane gitPane;
    private TerminalPane terminalPane;
    private VersionsPane versionsPane;
    private SavesPane savesPane;

    public ProjectWorkspace() {
        setPadding(new Insets(0));
    }

    public void loadProject(File projectRoot) {
        if (terminalPane != null) terminalPane.dispose();
        if (geminiPane != null) geminiPane.dispose();
        if (editor != null) editor.clear();
        currentProject = new Project(projectRoot);
        BackupService.getInstance().init(projectRoot.getAbsolutePath());
        
        header = new ProjectHeader(currentProject);
        setTop(header);

        explorer = new PackageExplorer();
        explorer.setCellFactory(v -> new FileTreeCell());
        explorer.openProject(projectRoot);

        editor = new EditorHost();
        editor.restoreState();
        setupFileOpenHandler();

        // East Pane
        eastTabs = new TabPane();
        geminiPane = new GeminiPane();
        geminiPane.setProjectRoot(projectRoot.getAbsolutePath());
        gitPane = new GitPane();
        gitPane.setProjectPath(projectRoot.getAbsolutePath());
        eastTabs.getTabs().addAll(new Tab("✨ AI", geminiPane), new Tab("🌿 Git", gitPane), new Tab("🐚 Shell", new ShellScriptingPane()));

        // South Pane
        bottomTabs = new TabPane();
        terminalPane = new TerminalPane();
        versionsPane = new VersionsPane();
        savesPane = new SavesPane();
        bottomTabs.getTabs().addAll(
            new Tab("💻 Terminal", terminalPane), 
            new Tab("🗄 DB", new DatabasePane()),
            new Tab("🕰️ Versões", versionsPane),
            new Tab("💾 Saves", savesPane)
        );

        horizontalSplitter = new SplitPane(explorer, editor, eastTabs);
        horizontalSplitter.setDividerPositions(0.20, 0.75);

        verticalSplitter = new SplitPane(horizontalSplitter, bottomTabs);
        verticalSplitter.setOrientation(javafx.geometry.Orientation.VERTICAL);
        verticalSplitter.setDividerPositions(0.75);

        setCenter(verticalSplitter);

        setupToggles();
        setupBuildHandler();
    }

    private void setupToggles() {
        header.setOnToggleEast(() -> {
            if (horizontalSplitter.getItems().contains(eastTabs)) {
                horizontalSplitter.getItems().remove(eastTabs);
            } else {
                horizontalSplitter.getItems().add(eastTabs);
                horizontalSplitter.setDividerPositions(0.20, 0.75);
            }
        });

        header.setOnToggleSouth(() -> {
            if (verticalSplitter.getItems().contains(bottomTabs)) {
                verticalSplitter.getItems().remove(bottomTabs);
            } else {
                verticalSplitter.getItems().add(bottomTabs);
                verticalSplitter.setDividerPositions(0.75);
            }
        });
    }

    private void setupBuildHandler() {
        header.setOnBuildRequested(() -> {
            if (!verticalSplitter.getItems().contains(bottomTabs)) {
                verticalSplitter.getItems().add(bottomTabs);
                verticalSplitter.setDividerPositions(0.75);
            }
            bottomTabs.getSelectionModel().select(0); // Terminal
            terminalPane.logSystem("Iniciando Backup + Build...");

            new Thread(() -> {
                // 1. Backup
                try {
                    BackupService.getInstance().performBuildBackup("Manual Build Triggered");
                    terminalPane.appendOutput("✅ Backup concluído.\n");
                } catch (RuntimeException exception) {
                    terminalPane.appendError("❌ Backup falhou; build cancelado: " + exception.getMessage() + "\n");
                    return;
                }

                // 2. Build (Executa script se existir)
                File buildScript = new File(currentProject.getRootDirectory(), "build.sh");
                if (buildScript.exists()) {
                    terminalPane.appendOutput("🚀 Executando build.sh...\n");
                    terminalPane.sendCommand("./build.sh");
                } else {
                    terminalPane.appendOutput("⚠️ Arquivo build.sh não encontrado na raiz.\n");
                }
                
                javafx.application.Platform.runLater(() -> {
                    versionsPane.loadVersions();
                });
            }, "project-build").start();
        });
    }

    private void setupFileOpenHandler() {
        explorer.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                TreeItem<File> selected = explorer.getSelectionModel().getSelectedItem();
                if (selected != null && selected.getValue() != null && selected.getValue().isFile()) {
                    File file = selected.getValue();
                    if (EditorSupport.isTextFile(file)) {
                        editor.openFile(file);
                    }
                }
            }
        });
    }

    public void dispose() {
        if (terminalPane != null) terminalPane.dispose();
        if (geminiPane != null) geminiPane.dispose();
        if (editor != null) editor.clear();
    }
}
