/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

/**
 * Componente público { FileSystemService}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class FileSystemService {
    public void saveFile(File file, String content) throws Exception {
        if (file == null) return;
        Files.writeString(file.toPath(), content, StandardCharsets.UTF_8);
    }
}
