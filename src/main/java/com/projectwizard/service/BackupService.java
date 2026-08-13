/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.service;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Componente público { BackupService}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class BackupService {
    private static final Logger LOGGER = Logger.getLogger(BackupService.class.getName());
    private static final long MAX_BACKUP_FILE_SIZE = 50L * 1024 * 1024;
    private static final BackupService INSTANCE = new BackupService();
    private String projectPath;
    private String dbUrl;

    private BackupService() {}

    public static BackupService getInstance() {
        return INSTANCE;
    }

    public void init(String projectPath) {
        this.projectPath = projectPath;
        this.dbUrl = "jdbc:sqlite:" + projectPath + File.separator + ".projectwizard_backups.db";

        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("CREATE TABLE IF NOT EXISTS builds (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "timestamp DATETIME, " +
                        "description TEXT)");

            stmt.execute("CREATE TABLE IF NOT EXISTS build_files (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "build_id INTEGER, " +
                        "file_path TEXT, " +
                        "content BLOB, " +
                        "FOREIGN KEY(build_id) REFERENCES builds(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS saves (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "file_name TEXT, " +
                        "file_path TEXT, " +
                        "timestamp DATETIME, " +
                        "change_summary TEXT, " +
                        "content BLOB)");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not initialize backup database", e);
        }
    }

    public void performBuildBackup(String manualDesc) {
        if (dbUrl == null) throw new IllegalStateException("Backup service was not initialized");
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            conn.setAutoCommit(false);
            try {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String desc = manualDesc != null ? manualDesc : "Build Backup";
                String insertBuild = "INSERT INTO builds (timestamp, description) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertBuild, Statement.RETURN_GENERATED_KEYS)) {
                    pstmt.setString(1, timestamp);
                    pstmt.setString(2, desc);
                    pstmt.executeUpdate();
                    try (ResultSet rs = pstmt.getGeneratedKeys()) {
                        if (!rs.next()) throw new SQLException("No build id was generated");
                        backupFiles(conn, rs.getLong(1));
                    }
                }
                conn.commit();
            } catch (Exception exception) {
                conn.rollback();
                throw exception;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Build backup failed and was rolled back", e);
            throw new IllegalStateException("Could not create backup", e);
        }
    }

    private void backupFiles(Connection conn, long buildId) throws IOException, SQLException {
        Path root = Paths.get(projectPath);
        String insertFile = "INSERT INTO build_files (build_id, file_path, content) VALUES (?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(insertFile);
             Stream<Path> paths = Files.walk(root)) {
            paths
                .filter(Files::isRegularFile)
                .filter(p -> {
                    Path relative = root.relativize(p);
                    for (Path part : relative) {
                        String name = part.toString();
                        if (name.equals("target") || name.equals(".git") || name.equals(".idea") || name.equals("javadoc")) return false;
                    }
                    String name = p.getFileName().toString();
                    return !name.contains(".bak") && !name.contains(".pre")
                            && !name.startsWith(".projectwizard_backups.db");
                })
                .forEachOrdered(p -> {
                    try {
                        long size = Files.size(p);
                        if (size > MAX_BACKUP_FILE_SIZE) {
                            LOGGER.warning(() -> "Skipping backup file larger than 50 MB: " + p);
                            return;
                        }
                        pstmt.setLong(1, buildId);
                        pstmt.setString(2, root.relativize(p).toString());
                        // sqlite-jdbc does not implement setBinaryStream; bounded byte[] is required.
                        pstmt.setBytes(3, Files.readAllBytes(p));
                        pstmt.executeUpdate();
                    } catch (Exception e) {
                        throw new BackupFileException(e);
                    }
                });
        } catch (BackupFileException exception) {
            if (exception.getCause() instanceof IOException io) throw io;
            if (exception.getCause() instanceof SQLException sql) throw sql;
            throw exception;
        }
    }

    public void performSaveLog(File file, String content) {
        if (dbUrl == null) return;
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            String relPath = Paths.get(projectPath).relativize(file.toPath()).toString();
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            
            String sql = "INSERT INTO saves (file_name, file_path, timestamp, change_summary, content) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, file.getName());
                pstmt.setString(2, relPath);
                pstmt.setString(3, timestamp);
                pstmt.setString(4, "Manual Save");
                pstmt.setBytes(5, content.getBytes());
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Could not record saved file", e);
        }
    }

    private static final class BackupFileException extends RuntimeException {
        private BackupFileException(Exception cause) { super(cause); }
    }
}
