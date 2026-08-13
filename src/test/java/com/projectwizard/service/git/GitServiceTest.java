/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.service.git;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class GitServiceTest {
    @TempDir
    Path project;

    @Test
    void readsBranchAndRemoteFromGitMetadata() throws Exception {
        Path git = Files.createDirectories(project.resolve(".git"));
        Files.writeString(git.resolve("HEAD"), "ref: refs/heads/main\n");
        Files.writeString(git.resolve("config"), "[remote \"origin\"]\n    url = https://example.test/repo.git\n");

        GitService service = new GitService(project.toFile());

        assertTrue(service.isGitRepository());
        assertEquals("main", service.getCurrentBranch());
        assertEquals("https://example.test/repo.git", service.getRemoteUrl());
    }
}
