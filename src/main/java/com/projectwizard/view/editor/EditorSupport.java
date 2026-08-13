/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view.editor;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Componente público { EditorSupport}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public final class EditorSupport {

    private static final String[] TEXT_EXTENSIONS = {
        ".java", ".xml", ".json", ".properties", ".yml", ".yaml",
        ".md", ".txt", ".html", ".fxml", ".css", ".scss", ".sql", ".sh",
        ".gradle", ".kt", ".groovy", ".toml", ".ini", ".cfg", ".log",
        ".js", ".jsx", ".ts", ".tsx", ".py", ".pyw", ".bat", ".cmd"
    };

    private static final int MAX_PREVIEW_CHARS = 50_000;

    private EditorSupport() {
    }

    public static boolean isTextFile(File file) {
        if (file == null) {
            return false;
        }
        String name = file.getName().toLowerCase();
        for (String ext : TEXT_EXTENSIONS) {
            if (name.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    public static String readFileContent(File file) throws Exception {
        if (!isTextFile(file)) {
            return "[Binary file: " + file.getName() + "]";
        }
        String content = Files.readString(file.toPath(), StandardCharsets.UTF_8);
        if (content.length() > MAX_PREVIEW_CHARS) {
            int remaining = content.length() - MAX_PREVIEW_CHARS;
            content = content.substring(0, MAX_PREVIEW_CHARS)
                    + "\n\n... [Truncated: " + remaining + " chars] ...";
        }
        return content;
    }

    public static String formatSize(long bytes) {
        if (bytes <= 0) {
            return "0 B";
        }
        final String[] units = {"B", "KB", "MB", "GB"};
        int digitGroups = (int) (Math.log10(bytes) / Math.log10(1024));
        digitGroups = Math.min(digitGroups, units.length - 1);
        return String.format("%.1f %s", bytes / Math.pow(1024, digitGroups), units[digitGroups]);
    }

}
