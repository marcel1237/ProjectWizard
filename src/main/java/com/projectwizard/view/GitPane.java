package com.projectwizard.view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GitPane extends VBox {
    private final Label branchLabel = new Label("🌿 Branch: unknown");
    private final ListView<String> commitList = new ListView<>();
    private String projectPath;

    public GitPane() {
        setPadding(new Insets(10));
        setSpacing(10);

        HBox header = new HBox();
        Button refreshBtn = new Button("🔄");
        refreshBtn.setOnAction(e -> refresh());
        header.getChildren().addAll(branchLabel, new Region(), refreshBtn);
        HBox.setHgrow(header.getChildren().get(1), Priority.ALWAYS);

        VBox.setVgrow(commitList, Priority.ALWAYS);

        HBox actions = new HBox(10);
        Button btnCommit = new Button("Commit");
        Button btnPush = new Button("Push");
        Button btnPull = new Button("Pull");

        btnCommit.setOnAction(e -> handleCommit());
        btnPush.setOnAction(e -> executeGit("push"));
        btnPull.setOnAction(e -> executeGit("pull"));

        actions.getChildren().addAll(btnCommit, btnPush, btnPull);

        getChildren().addAll(header, new Label("📜 History:"), commitList, actions);
    }

    public void setProjectPath(String path) {
        this.projectPath = path;
        refresh();
    }

    public void refresh() {
        if (projectPath == null) return;
        
        // Branch
        new Thread(() -> {
            String branch = runCommand("git rev-parse --abbrev-ref HEAD");
            Platform.runLater(() -> branchLabel.setText("🌿 Branch: " + branch));
            
            // History
            List<String> history = runCommandList("git log --oneline -n 20");
            Platform.runLater(() -> {
                commitList.getItems().clear();
                commitList.getItems().addAll(history);
            });
        }).start();
    }

    private void handleCommit() {
        TextInputDialog dialog = new TextInputDialog("WIP");
        dialog.setTitle("Git Commit");
        dialog.setHeaderText("Enter commit message:");
        dialog.showAndWait().ifPresent(msg -> {
            new Thread(() -> {
                runCommand("git add .");
                runCommand("git commit -m \"" + msg + "\"");
                refresh();
            }).start();
        });
    }

    private void executeGit(String command) {
        new Thread(() -> {
            runCommand("git " + command);
            refresh();
        }).start();
    }

    private String runCommand(String cmd) {
        try {
            Process p = Runtime.getRuntime().exec(cmd, null, new File(projectPath));
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            return r.readLine();
        } catch (Exception e) {
            return "error";
        }
    }

    private List<String> runCommandList(String cmd) {
        List<String> result = new ArrayList<>();
        try {
            Process p = Runtime.getRuntime().exec(cmd, null, new File(projectPath));
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = r.readLine()) != null) result.add(line);
        } catch (Exception e) {}
        return result;
    }
}
