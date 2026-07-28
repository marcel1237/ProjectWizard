package com.projectwizard.workspace;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import com.projectwizard.model.Project;
import com.projectwizard.service.git.GitService;

public class ProjectHeader extends VBox {

    private Project project;
    private GitService gitService;
    private Runnable onToggleEast;
    private Runnable onToggleSouth;
    private Runnable onBuildRequested;

    public ProjectHeader(Project project) {
        this.project = project;
        this.gitService = new GitService(project.getRootDirectory());

        setStyle("-fx-background-color: #2b2d30; -fx-border-color: #3c3f41; -fx-border-width: 0 0 1 0;");
        setPadding(new Insets(10, 20, 10, 20));
        setSpacing(5);

        getChildren().addAll(createTitleRow(), createInfoRow());
    }

    public void setOnToggleEast(Runnable callback) { this.onToggleEast = callback; }
    public void setOnToggleSouth(Runnable callback) { this.onToggleSouth = callback; }
    public void setOnBuildRequested(Runnable callback) { this.onBuildRequested = callback; }

    private HBox createTitleRow() {
        HBox hbox = new HBox(15);
        hbox.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("📁 " + project.getName());
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        titleLabel.setStyle("-fx-text-fill: #dfe1e5;");

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox actions = new HBox(8);
        Button btnBuild = new Button("🔨 Backup + Build");
        btnBuild.setStyle("-fx-background-color: #2e7d32; -fx-text-fill: white; -fx-font-weight: bold;");
        btnBuild.setOnAction(e -> { if(onBuildRequested != null) onBuildRequested.run(); });

        Button btnSouth = new Button("⬇ South Pane");
        Button btnEast = new Button("➡ East Pane");
        
        btnSouth.setOnAction(e -> { if(onToggleSouth != null) onToggleSouth.run(); });
        btnEast.setOnAction(e -> { if(onToggleEast != null) onToggleEast.run(); });

        actions.getChildren().addAll(btnBuild, btnSouth, btnEast);

        hbox.getChildren().addAll(titleLabel, spacer, actions);
        return hbox;
    }

    private HBox createInfoRow() {
        HBox hbox = new HBox(20);
        hbox.setAlignment(Pos.CENTER_LEFT);

        Label pathLabel = new Label("📍 " + project.getRootDirectory().getAbsolutePath());
        pathLabel.setStyle("-fx-text-fill: #888; -fx-font-size: 11;");
        hbox.getChildren().add(pathLabel);

        return hbox;
    }
}
