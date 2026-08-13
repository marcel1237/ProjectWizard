/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard.model;

import java.util.List;

/**
 * Componente público { ResolutionPlan}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public record ResolutionPlan(List<ServiceBinding> bindings, List<String> warnings) {
    public ResolutionPlan {
        bindings = List.copyOf(bindings);
        warnings = List.copyOf(warnings);
    }
}
