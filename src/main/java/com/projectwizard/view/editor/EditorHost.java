/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view.editor;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.projectwizard.core.ApplicationContext;
import com.projectwizard.service.PersistenceService;
import com.projectwizard.service.BackupService;

/**
 * Componente público { EditorHost}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class EditorHost extends BorderPane {
    private final TabPane tabPane = new TabPane();
    private final PersistenceService persistence = new PersistenceService();

    public EditorHost() {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        setupToolBar();
        setCenter(tabPane);

        // Salvar estado quando abas mudam
        tabPane.getTabs().addListener((javafx.collections.ListChangeListener.Change<? extends Tab> c) -> {
            saveState();
        });
    }

    private void saveState() {
        List<String> paths = tabPane.getTabs().stream()
                .filter(t -> t.getContent() instanceof EditorPane)
                .map(t -> ((EditorPane) t.getContent()).getFile().getAbsolutePath())
                .collect(Collectors.toList());
        persistence.saveOpenFiles(paths);
    }

    public void restoreState() {
        List<String> paths = persistence.getOpenFiles();
        for (String path : paths) {
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                openFile(file);
            }
        }
    }

    private void setupToolBar() {
        Button btnSave = new Button("💾 Save");
        Button btnSaveAs = new Button("📂 Save As...");
        btnSave.setOnAction(e -> handleSave());
        btnSaveAs.setOnAction(e -> handleSaveAs());
        setTop(new ToolBar(btnSave, btnSaveAs));
    }

    private void handleSave() {
        Tab selected = tabPane.getSelectionModel().getSelectedItem();
        if (selected != null && selected.getContent() instanceof EditorPane pane) {
            try {
                String content = pane.getText();
                File file = pane.getFile();
                ApplicationContext.getInstance().getFileSystemService().saveFile(file, content);
                BackupService.getInstance().performSaveLog(file, content);
            } catch (Exception ex) {
                ApplicationContext.getInstance().getDialogService().showError("Error", ex.getMessage());
            }
        }
    }

    private void handleSaveAs() {
        Tab selected = tabPane.getSelectionModel().getSelectedItem();
        if (selected != null && selected.getContent() instanceof EditorPane pane) {
            FileChooser chooser = new FileChooser();
            File file = chooser.showSaveDialog(getScene().getWindow());
            if (file != null) {
                try {
                    ApplicationContext.getInstance().getFileSystemService().saveFile(file, pane.getText());
                    pane.setFile(file);
                    selected.setText(file.getName());
                } catch (Exception ex) {
                    ApplicationContext.getInstance().getDialogService().showError("Error", ex.getMessage());
                }
            }
        }
    }

    // Sobrecarga para aceitar apenas o File (Lê o conteúdo automaticamente)
    public void openFile(File file) {
        try {
            String content = Files.readString(file.toPath());
            openFile(file, content);
        } catch (Exception e) {
            ApplicationContext.getInstance().getDialogService().showError("Read Error", e.getMessage());
        }
    }

    public void openFile(File file, String content) {
        // Verifica se o arquivo já está aberto para evitar duplicidade
        for (Tab tab : tabPane.getTabs()) {
            if (tab.getText().equals(file.getName())) {
                tabPane.getSelectionModel().select(tab);
                return;
            }
        }
        Tab tab = new Tab(file.getName());
        EditorPane pane = new EditorPane(file, content);
        tab.setContent(pane);
        tab.setOnClosed(event -> pane.dispose());
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
    }

    public void clear() {
        tabPane.getTabs().forEach(tab -> {
            if (tab.getContent() instanceof EditorPane pane) pane.dispose();
        });
        tabPane.getTabs().clear();
    }

    public int getOpenTabCount() {
        return tabPane.getTabs().size();
    }
}
