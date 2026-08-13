/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class PersistenceServiceTest {
    @TempDir
    static Path home;

    @BeforeAll
    static void configureHome() {
        System.setProperty("user.home", home.toString());
    }

    @Test
    void preservesPathsContainingCommas() {
        PersistenceService service = new PersistenceService();
        List<String> paths = List.of("/tmp/project,one/Main.java", "/tmp/project two/App.java");
        service.saveOpenFiles(paths);
        assertEquals(paths, service.getOpenFiles());
    }

    @Test
    void countsUsage() {
        PersistenceService service = new PersistenceService();
        service.incrementUsage();
        service.incrementUsage();
        assertEquals(2, service.getDailyUsage());
    }
}
