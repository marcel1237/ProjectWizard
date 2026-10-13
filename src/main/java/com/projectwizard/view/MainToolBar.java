/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.view;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Separator;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import com.projectwizard.core.navigation.NavigationController;
import com.projectwizard.core.navigation.NavigationTarget;

/**
 * Componente público { MainToolBar}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public class MainToolBar extends ToolBar {

    public MainToolBar() {

        Button newProject = new Button("New Project");
        Button open = new Button("Open");

        open.setOnAction(e -> NavigationController.getInstance().navigate(NavigationTarget.OPEN_PROJECT));

        Button workspace = new Button("📂 Workspace");
        workspace.setOnAction(e -> NavigationController.getInstance().navigate(NavigationTarget.WORKSPACE));

        Button browser = new Button("🌐 Browser");
        browser.setOnAction(e -> NavigationController.getInstance().navigate(NavigationTarget.BROWSER));

        MenuButton cloudWizard = new MenuButton("🪄 Cloud Wizard");

        Menu templatesMenu = new Menu("📦 Cloud Templates");
        templatesMenu.getItems().addAll(
                new MenuItem("cloud-native-full (Full SaaS)"),
                new MenuItem("serverless-api (API + Cache + Queue)"),
                new MenuItem("event-driven (Functions + Queue)"),
                new MenuItem("iot-backend (Device State)"),
                new MenuItem("multi-cloud-saas (Cross-provider)")
        );

        Menu capabilitiesMenu = new Menu("⚡ Cloud Capabilities (16)");
        capabilitiesMenu.getItems().addAll(
                new MenuItem("@CloudStorage (Object Storage)"),
                new MenuItem("@CloudFunction (Serverless Function)"),
                new MenuItem("@CloudDatabase (Managed SQL)"),
                new MenuItem("@CloudNoSqlDatabase (NoSQL Database)"),
                new MenuItem("@CloudQueue (Message Queue)"),
                new MenuItem("@CloudSecret (Secret Manager)"),
                new MenuItem("@CloudObservability (Observability)"),
                new MenuItem("@CloudContainer (Container Orchestration)"),
                new MenuItem("@CloudIdentity (IAM)"),
                new MenuItem("@CloudApiGateway (API Gateway)"),
                new MenuItem("@CloudCache (Managed Cache)"),
                new MenuItem("@CloudDns (Managed DNS)"),
                new MenuItem("@CloudLoadBalancer (Load Balancer)"),
                new MenuItem("@CloudContainerRegistry (Container Registry)"),
                new MenuItem("@CloudKeyManagement (Key Management)"),
                new MenuItem("@CloudCertificate (Certificate Management)")
        );

        Menu providersMenu = new Menu("☁️ Official Providers (9)");
        providersMenu.getItems().addAll(
                new MenuItem("AWS (Amazon Web Services)"),
                new MenuItem("Google Cloud"),
                new MenuItem("Microsoft Azure"),
                new MenuItem("Oracle Cloud (OCI)"),
                new MenuItem("IBM Cloud"),
                new MenuItem("Alibaba Cloud"),
                new MenuItem("Tencent Cloud"),
                new MenuItem("Huawei Cloud"),
                new MenuItem("Yandex Cloud")
        );

        MenuItem generateAction = new MenuItem("🚀 Generate Cloud Project...");
        generateAction.setOnAction(e -> NavigationController.getInstance().navigate(NavigationTarget.NEW_PROJECT));

        cloudWizard.getItems().addAll(templatesMenu, capabilitiesMenu, providersMenu, new SeparatorMenuItem(), generateAction);

        Button settings = new Button("⚙ Settings");
        settings.setOnAction(e -> NavigationController.getInstance().navigate(NavigationTarget.SETTINGS));

        getItems().addAll(
                newProject, open,
                new Separator(),
                workspace, browser, cloudWizard,
                new Separator(),
                settings
        );
    }
}
