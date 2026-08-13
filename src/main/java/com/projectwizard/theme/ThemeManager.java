/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard.theme;

import atlantafx.base.theme.CupertinoDark;
import atlantafx.base.theme.CupertinoLight;
import atlantafx.base.theme.Dracula;
import atlantafx.base.theme.NordDark;
import atlantafx.base.theme.NordLight;
import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;

/**
 * Componente público { ThemeManager}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public final class ThemeManager {

    private static ThemeType currentTheme = ThemeType.PRIMER_DARK;

    private ThemeManager() {
    }

    public static ThemeType getCurrentTheme() {

        return currentTheme;

    }

    public static void apply(ThemeType theme) {

        currentTheme = theme;

        switch (theme) {

            case DRACULA ->
                Application.setUserAgentStylesheet(
                        new Dracula().getUserAgentStylesheet());

            case NORD_DARK ->
                Application.setUserAgentStylesheet(
                        new NordDark().getUserAgentStylesheet());

            case NORD_LIGHT ->
                Application.setUserAgentStylesheet(
                        new NordLight().getUserAgentStylesheet());

            case PRIMER_DARK ->
                Application.setUserAgentStylesheet(
                        new PrimerDark().getUserAgentStylesheet());

            case PRIMER_LIGHT ->
                Application.setUserAgentStylesheet(
                        new PrimerLight().getUserAgentStylesheet());

            case CUPERTINO_DARK ->
                Application.setUserAgentStylesheet(
                        new CupertinoDark().getUserAgentStylesheet());

            case CUPERTINO_LIGHT ->
                Application.setUserAgentStylesheet(
                        new CupertinoLight().getUserAgentStylesheet());

        }

    }

}
