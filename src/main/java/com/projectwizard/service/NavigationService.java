/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.service;

import com.projectwizard.view.WorkspacePane;
import javafx.scene.Node;

/**
 * Componente público { NavigationService}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class NavigationService {

    private WorkspacePane workspace;

    public void setWorkspace(WorkspacePane workspace) {
        this.workspace = workspace;
    }

    public void navigate(Node node) {
        if (workspace != null) {
            workspace.setContent(node);
        }
    }

}
