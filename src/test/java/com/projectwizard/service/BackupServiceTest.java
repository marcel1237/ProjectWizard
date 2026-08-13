/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.DriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class BackupServiceTest {
    @TempDir
    Path project;

    @Test
    void backsUpRegularProjectFilesAndExcludesBuildOutput() throws Exception {
        Files.writeString(project.resolve("Main.java"), "class Main {}");
        Files.createDirectories(project.resolve("target"));
        Files.writeString(project.resolve("target/generated.txt"), "ignored");

        BackupService service = BackupService.getInstance();
        service.init(project.toString());
        service.performBuildBackup("test");

        String url = "jdbc:sqlite:" + project.resolve(".projectwizard_backups.db");
        try (var connection = DriverManager.getConnection(url);
             var statement = connection.createStatement()) {
            try (var result = statement.executeQuery("SELECT COUNT(*) FROM builds")) {
                result.next();
                assertEquals(1, result.getInt(1));
            }
            try (var result = statement.executeQuery("SELECT file_path FROM build_files")) {
                result.next();
                assertEquals("Main.java", result.getString(1));
                assertFalse(result.next());
            }
        }
    }
}
