package com.projectwizard.workspace;

import java.io.File;
import java.nio.file.Files;

import javafx.geometry.Insets;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;

import com.projectwizard.model.Project;
import com.projectwizard.view.editor.EditorHost;
import com.projectwizard.view.explorer.PackageExplorer;
import com.projectwizard.view.explorer.FileTreeCell;

public class ProjectWorkspace extends BorderPane {

    private Project currentProject;
    private PackageExplorer explorer;
    private EditorHost editor;
    private ProjectHeader header;

    public ProjectWorkspace() {
        setPadding(new Insets(0));
    }

    public void loadProject(File projectRoot) {
        currentProject = new Project(projectRoot);
        header = new ProjectHeader(currentProject);
        setTop(header);

        explorer = new PackageExplorer();
        explorer.setCellFactory(v -> new FileTreeCell());
        explorer.openProject(projectRoot);

        editor = new EditorHost();

        setupFilePreview();

        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(explorer, editor);
        splitPane.setDividerPositions(0.25);

        setCenter(splitPane);
    }

    private void setupFilePreview() {
        explorer.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                TreeItem<File> selected = explorer.getSelectionModel().getSelectedItem();
                if (selected != null && selected.getValue() != null) {
                    File file = selected.getValue();
                    if (file.isFile()) {
                        previewFile(file);
                    }
                }
            }
        });
    }

    private void previewFile(File file) {
        try {
            if (isTextFile(file)) {
                String content = new String(Files.readAllBytes(file.toPath()));
                if (content.length() > 10000) {
                    content = content.substring(0, 10000) + 
                        "\n\n... [Truncated: " + (content.length() - 10000) + " chars] ...";
                }
                editor.setContent(content);
                editor.setTitle(file.getName() + " (" + formatSize(file.length()) + ")");
            } else {
                editor.setContent("[Binary file]");
                editor.setTitle(file.getName());
            }
        } catch (Exception e) {
            editor.setContent("❌ Error: " + e.getMessage());
        }
    }

    private boolean isTextFile(File file) {
        String name = file.getName().toLowerCase();
        String[] exts = {".java", ".xml", ".json", ".properties", ".yml", ".yaml",
                         ".md", ".txt", ".html", ".fxml", ".css", ".scss", ".sql", ".sh"};
        for (String ext : exts) {
            if (name.endsWith(ext)) return true;
        }
        return false;
    }

    private String formatSize(long bytes) {
        if (bytes <= 0) return "0 B";
        final String[] units = {"B", "KB", "MB", "GB"};
        int digitGroups = (int) (Math.log10(bytes) / Math.log10(1024));
        return String.format("%.1f %s", bytes / Math.pow(1024, digitGroups), units[digitGroups]);
    }

    public Project getCurrentProject() {
        return currentProject;
    }

}
