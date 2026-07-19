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
                ApplicationContext.getInstance().getFileSystemService().saveFile(pane.getFile(), pane.getText());
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
        tab.setContent(new EditorPane(file, content));
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
    }

    public void clear() {
        tabPane.getTabs().clear();
    }

    public int getOpenTabCount() {
        return tabPane.getTabs().size();
    }
}
