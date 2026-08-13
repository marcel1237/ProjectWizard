/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.core;

import com.projectwizard.service.*;

/**
 * Componente público { ApplicationContext}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class ApplicationContext {
    private static ApplicationContext instance;
    private final ProjectService projectService = new ProjectService();
    private final FileSystemService fileSystemService = new FileSystemService();
    private final DialogService dialogService = new DialogService();

    private ApplicationContext() {}

    public static synchronized ApplicationContext getInstance() {
        if (instance == null) instance = new ApplicationContext();
        return instance;
    }

    public String getApplicationName() { return "Project Wizard"; }
    public String getVersion() { return "1.0.0"; }
    
    public ProjectService getProjectService() { return projectService; }
    public FileSystemService getFileSystemService() { return fileSystemService; }
    public DialogService getDialogService() { return dialogService; }
}
