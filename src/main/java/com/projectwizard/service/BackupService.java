package com.projectwizard.service;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class BackupService {
    private static BackupService instance;
    private String projectPath;
    private String dbUrl;

    private BackupService() {}

    public static BackupService getInstance() {
        if (instance == null) instance = new BackupService();
        return instance;
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
            e.printStackTrace();
        }
    }

    public void performBuildBackup(String manualDesc) {
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            conn.setAutoCommit(false);
            
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String desc = manualDesc != null ? manualDesc : "Build Backup";

            String insertBuild = "INSERT INTO builds (timestamp, description) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertBuild, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, timestamp);
                pstmt.setString(2, desc);
                pstmt.executeUpdate();
                
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    long buildId = rs.getLong(1);
                    backupFiles(conn, buildId);
                }
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void backupFiles(Connection conn, long buildId) throws IOException, SQLException {
        Path root = Paths.get(projectPath);
        String insertFile = "INSERT INTO build_files (build_id, file_path, content) VALUES (?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(insertFile)) {
            Files.walk(root)
                .filter(Files::isRegularFile)
                .filter(p -> {
                    String path = p.toString();
                    return !path.contains("/target/") && 
                           !path.contains("/.git/") && 
                           !path.contains("/.idea/") && 
                           !path.contains("/javadoc/") && 
                           !path.contains(".bak") &&
                           !path.contains(".pre") &&
                           !path.endsWith(".projectwizard_backups.db");
                })
                .forEach(p -> {
                    try {
                        byte[] content = Files.readAllBytes(p);
                        pstmt.setLong(1, buildId);
                        pstmt.setString(2, root.relativize(p).toString());
                        pstmt.setBytes(3, content);
                        pstmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
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
            e.printStackTrace();
        }
    }
}
