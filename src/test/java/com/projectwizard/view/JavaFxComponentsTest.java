/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.projectwizard.view.dashboard.DashboardView;
import com.projectwizard.view.newproject.NewProjectView;
import com.projectwizard.view.sidebar.Sidebar;
import com.projectwizard.view.projecttype.ProjectTypeView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.FlowPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JavaFxComponentsTest {
    @BeforeAll
    static void initializeJavaFx() throws Exception {
        FxTestSupport.startToolkit();
    }

    @Test
    void statusBarShowsReadyStateAndVersion() throws Exception {
        FxTestSupport.onFxThread(() -> {
            StatusBar statusBar = new StatusBar();
            assertEquals("Ready", ((Label) statusBar.getChildren().getFirst()).getText());
            assertEquals("1.0.0", ((Label) statusBar.getChildren().getLast()).getText());
            return null;
        });
    }

    @Test
    void sidebarExposesAllNavigationActions() throws Exception {
        FxTestSupport.onFxThread(() -> {
            WorkspacePane workspace = new WorkspacePane();
            Sidebar sidebar = new Sidebar();

            assertEquals(8, sidebar.getChildren().size());
            assertInstanceOf(DashboardView.class, workspace.getCenter());

            ((Button) sidebar.getChildren().get(1)).fire();
            assertInstanceOf(NewProjectView.class, workspace.getCenter());
            return null;
        });
    }

    @Test
    void projectCatalogContainsCloudWizard() throws Exception {
        FxTestSupport.onFxThread(() -> {
            ProjectTypeView catalog = new ProjectTypeView();
            SplitPane split = (SplitPane) catalog.getCenter();
            ScrollPane scroll = (ScrollPane) split.getItems().getFirst();
            FlowPane cards = (FlowPane) scroll.getContent();
            assertTrue(cards.getChildren().stream().anyMatch(card -> containsLabel(card, "Cloud Wizard")));
            return null;
        });
    }

    private boolean containsLabel(Node node, String text) {
        if (node instanceof Label label && text.equals(label.getText())) return true;
        if (node instanceof Parent parent) {
            return parent.getChildrenUnmodifiable().stream().anyMatch(child -> containsLabel(child, text));
        }
        return false;
    }
}
