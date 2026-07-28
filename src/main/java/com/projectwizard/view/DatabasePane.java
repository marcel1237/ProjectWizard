package com.projectwizard.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.chart.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabasePane extends VBox {
    private final ComboBox<String> dbTypeCombo = new ComboBox<>();
    private final ComboBox<String> tableSelector = new ComboBox<>();
    private final TextField sqlInput = new TextField();
    private final TableView<ObservableList<String>> tableView = new TableView<>();
    private final Button chartBtn = new Button("Gráfico");
    private String currentDbPath;
    private Connection connection;

    public DatabasePane() {
        setPadding(new Insets(6));
        setSpacing(5);

        // Toolbar
        HBox toolbar = new HBox(5);
        dbTypeCombo.getItems().addAll("SQLite", "DuckDB (CLI)", "Redis (CLI)");
        dbTypeCombo.setValue("SQLite");

        Button openBtn = new Button("Abrir DB");
        openBtn.setOnAction(e -> handleOpenDB());

        Button runBtn = new Button("Executar SQL");
        runBtn.setOnAction(e -> handleExecuteSQL());
        
        chartBtn.setStyle("-fx-background-color: #8e44ad; -fx-text-fill: white; -fx-font-weight: bold;");
        chartBtn.setOnAction(e -> showChartMenu());

        toolbar.getChildren().addAll(new Label("Tipo:"), dbTypeCombo, openBtn, chartBtn, new Label("Tabela:"), tableSelector);
        
        sqlInput.setPromptText("Comando SQL...");
        sqlInput.setOnAction(e -> handleExecuteSQL());

        VBox.setVgrow(tableView, Priority.ALWAYS);
        getChildren().addAll(toolbar, sqlInput, tableView);
    }

    private void handleOpenDB() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(getScene().getWindow());
        if (file != null) {
            currentDbPath = file.getAbsolutePath();
            if ("SQLite".equals(dbTypeCombo.getValue())) {
                try {
                    if (connection != null) connection.close();
                    connection = DriverManager.getConnection("jdbc:sqlite:" + currentDbPath);
                    updateTableList();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateTableList() throws SQLException {
        tableSelector.getItems().clear();
        DatabaseMetaData md = connection.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", new String[]{"TABLE"});
        while (rs.next()) {
            tableSelector.getItems().add(rs.getString(3));
        }
        tableSelector.setOnAction(e -> loadTable(tableSelector.getValue()));
    }

    private void loadTable(String tableName) {
        if (tableName == null) return;
        executeSql("SELECT * FROM " + tableName);
    }

    private void handleExecuteSQL() {
        executeSql(sqlInput.getText());
    }

    private void executeSql(String sql) {
        if (connection == null) return;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            tableView.getColumns().clear();
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();

            for (int i = 1; i <= colCount; i++) {
                final int j = i - 1;
                TableColumn<ObservableList<String>, String> col = new TableColumn<>(rsmd.getColumnName(i));
                col.setCellValueFactory(param -> new javafx.beans.property.SimpleStringProperty(param.getValue().get(j)));
                tableView.getColumns().add(col);
            }

            ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= colCount; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tableView.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showChartMenu() {
        ContextMenu menu = new ContextMenu();
        MenuItem barChartItem = new MenuItem("Gráfico de Barras");
        barChartItem.setOnAction(e -> showBarChart());
        menu.getItems().add(barChartItem);
        menu.show(chartBtn, javafx.geometry.Side.BOTTOM, 0, 0);
    }

    private void showBarChart() {
        Stage stage = new Stage();
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (ObservableList<String> row : tableView.getItems()) {
            if (row.size() >= 2) {
                try {
                    series.getData().add(new XYChart.Data<>(row.get(0), Double.parseDouble(row.get(1))));
                } catch (NumberFormatException e) {}
            }
        }
        bc.getData().add(series);
        stage.setScene(new Scene(bc, 800, 600));
        stage.show();
    }
}
