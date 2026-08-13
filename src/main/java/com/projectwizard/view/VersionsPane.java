/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.sql.*;
import com.projectwizard.service.PersistenceService;

/**
 * Componente público { VersionsPane}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class VersionsPane extends VBox {
    private final TableView<VersionEntry> table = new TableView<>();

    public VersionsPane() {
        setPadding(new Insets(5));
        setSpacing(10);

        Label header = new Label("🕰️ Histórico de Builds & Versionamento");
        header.setStyle("-fx-font-weight: bold; -fx-text-fill: #dfe1e5;");

        TableColumn<VersionEntry, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().id));

        TableColumn<VersionEntry, String> dateCol = new TableColumn<>("Data/Hora");
        dateCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().timestamp));

        TableColumn<VersionEntry, String> descCol = new TableColumn<>("Descrição");
        descCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().description));

        table.getColumns().addAll(idCol, dateCol, descCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button refreshBtn = new Button("Atualizar");
        refreshBtn.setOnAction(e -> loadVersions());

        VBox.setVgrow(table, Priority.ALWAYS);
        getChildren().addAll(header, table, refreshBtn);
        
        loadVersions();
    }

    public void loadVersions() {
        String lastProject = new PersistenceService().getLastProjectPath();
        if (lastProject == null) return;
        
        String dbUrl = "jdbc:sqlite:" + lastProject + File.separator + ".projectwizard_backups.db";
        ObservableList<VersionEntry> data = FXCollections.observableArrayList();
        
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, timestamp, description FROM builds ORDER BY id DESC")) {
            
            while (rs.next()) {
                data.add(new VersionEntry(rs.getString("id"), rs.getString("timestamp"), rs.getString("description")));
            }
        } catch (SQLException e) {
            // DB might not be initialized yet
        }
        table.setItems(data);
    }

    public static class VersionEntry {
        String id, timestamp, description;
        VersionEntry(String i, String t, String d) { this.id = i; this.timestamp = t; this.description = d; }
    }
}
