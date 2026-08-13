/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view;

import com.projectwizard.core.navigation.NavigationController;

import com.projectwizard.view.dashboard.DashboardView;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * Componente público { WorkspacePane}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class WorkspacePane extends BorderPane {

    private static WorkspacePane instance;

    public WorkspacePane() {

        instance = this;

        NavigationController.getInstance().setWorkspace(this);

        showDashboard();

    }

    public static WorkspacePane getInstance() {

        return instance;

    }

    public void setContent(Node node) {

        setCenter(node);

    }

    public void showDashboard() {

        setContent(new DashboardView());

    }

}
