/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade e Colaboradores.
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
 */
package com.projectwizard;

/**
 * Classe de entrada alternativa para evitar problemas com o Java Module System (JPMS).
 * Ao iniciar a aplicação por uma classe que não estende 'Application', o JavaFX
 * permite que dependências não-modulares funcionem corretamente no Classpath.
 */
public class Launcher {
    public static void main(String[] args) {
        Main.main(args);
    }
}
