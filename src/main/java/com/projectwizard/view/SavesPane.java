package com.projectwizard.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.sql.*;
import com.projectwizard.service.PersistenceService;

public class SavesPane extends VBox {
    private final TableView<SaveEntry> table = new TableView<>();

    public SavesPane() {
        setPadding(new Insets(5));
        setSpacing(10);

        Label header = new Label("💾 Log de Alterações (Saves Individuais)");
        header.setStyle("-fx-font-weight: bold; -fx-text-fill: #dfe1e5;");

        TableColumn<SaveEntry, String> fileCol = new TableColumn<>("Arquivo");
        fileCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().fileName));
        
        TableColumn<SaveEntry, String> dateCol = new TableColumn<>("Data/Hora");
        dateCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().timestamp));

        TableColumn<SaveEntry, String> changeCol = new TableColumn<>("Mudança");
        changeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().summary));

        table.getColumns().addAll(fileCol, dateCol, changeCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button refreshBtn = new Button("Atualizar Log");
        refreshBtn.setOnAction(e -> loadSaves());

        VBox.setVgrow(table, Priority.ALWAYS);
        getChildren().addAll(header, table, refreshBtn);
        
        loadSaves();
    }

    public void loadSaves() {
        String lastProject = new PersistenceService().getLastProjectPath();
        if (lastProject == null) return;
        
        String dbUrl = "jdbc:sqlite:" + lastProject + File.separator + ".projectwizard_backups.db";
        ObservableList<SaveEntry> data = FXCollections.observableArrayList();
        
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT file_name, timestamp, change_summary FROM saves ORDER BY id DESC LIMIT 200")) {
            
            while (rs.next()) {
                data.add(new SaveEntry(rs.getString("file_name"), rs.getString("timestamp"), rs.getString("change_summary")));
            }
        } catch (SQLException e) {
            // DB might not be initialized yet
        }
        table.setItems(data);
    }

    public static class SaveEntry {
        String fileName, timestamp, summary;
        SaveEntry(String f, String t, String s) { this.fileName = f; this.timestamp = t; this.summary = s; }
    }
}
