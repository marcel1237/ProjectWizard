/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.service;

import java.io.*;
import java.util.Properties;
import java.util.List;
import java.util.Collections;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Componente público { PersistenceService}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class PersistenceService {
    private static final Logger LOGGER = Logger.getLogger(PersistenceService.class.getName());

    private static final String CONFIG_FILE = System.getProperty("user.home") + File.separator + ".projectwizard.properties";
    private static final String LAST_PROJECT_KEY = "last.project.path";
    private static final String OPEN_FILES_KEY = "open.files";
    private static final String GEMINI_API_KEY = "gemini.api.key";
    private static final String USAGE_DAILY_KEY = "usage.daily";

    public void saveLastProjectPath(String path) {
        Properties props = loadProperties();
        props.setProperty(LAST_PROJECT_KEY, path);
        saveProperties(props);
    }

    public void saveOpenFiles(List<String> filePaths) {
        Properties props = loadProperties();
        props.setProperty(OPEN_FILES_KEY, String.join("\n", filePaths));
        saveProperties(props);
    }

    public String getLastProjectPath() {
        return loadProperties().getProperty(LAST_PROJECT_KEY);
    }

    public List<String> getOpenFiles() {
        String files = loadProperties().getProperty(OPEN_FILES_KEY);
        if (files == null || files.isEmpty()) return Collections.emptyList();
        return files.lines().filter(path -> !path.isBlank()).toList();
    }

    public String getGeminiApiKey() {
        String environmentKey = System.getenv("GEMINI_API_KEY");
        return environmentKey != null && !environmentKey.isBlank()
                ? environmentKey
                : loadProperties().getProperty(GEMINI_API_KEY, "");
    }

    public void saveGeminiApiKey(String key) {
        Properties props = loadProperties();
        props.setProperty(GEMINI_API_KEY, key);
        saveProperties(props);
    }

    public void incrementUsage() {
        Properties props = loadProperties();
        int current = parseUsage(props.getProperty(USAGE_DAILY_KEY));
        props.setProperty(USAGE_DAILY_KEY, String.valueOf(current + 1));
        saveProperties(props);
    }

    public int getDailyUsage() {
        return parseUsage(loadProperties().getProperty(USAGE_DAILY_KEY));
    }

    private int parseUsage(String value) {
        try {
            return Math.max(0, Integer.parseInt(value == null ? "0" : value));
        } catch (NumberFormatException exception) {
            LOGGER.log(Level.WARNING, "Invalid usage counter in configuration", exception);
            return 0;
        }
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        File file = new File(CONFIG_FILE);
        if (file.exists()) {
            try (var reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
                props.load(reader);
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "Could not read configuration", e);
            }
        }
        return props;
    }

    private void saveProperties(Properties props) {
        Path destination = Path.of(CONFIG_FILE);
        Path temporary = destination.resolveSibling(destination.getFileName() + ".tmp");
        try (var writer = Files.newBufferedWriter(temporary, StandardCharsets.UTF_8)) {
            props.store(writer, "ProjectWizard Settings");
            restrictPermissions(temporary);
            try {
                Files.move(temporary, destination, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            } catch (AtomicMoveNotSupportedException ignored) {
                Files.move(temporary, destination, StandardCopyOption.REPLACE_EXISTING);
            }
            restrictPermissions(destination);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Could not save configuration", e);
            try { Files.deleteIfExists(temporary); } catch (IOException ignored) { }
        }
    }


    private void restrictPermissions(Path file) {
        try {
            Files.setPosixFilePermissions(file, Set.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE));
        } catch (UnsupportedOperationException | IOException ignored) {
            file.toFile().setReadable(false, false);
            file.toFile().setWritable(false, false);
            file.toFile().setReadable(true, true);
            file.toFile().setWritable(true, true);
        }
    }
}
