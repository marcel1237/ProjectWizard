/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard.model;

import java.util.Objects;

/**
 * Componente público { ServiceBinding}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public record ServiceBinding(String logicalName, ProviderService service) {
    public ServiceBinding {
        if (logicalName == null || logicalName.isBlank()) throw new IllegalArgumentException("Logical name is required");
        Objects.requireNonNull(service, "service");
    }
}
