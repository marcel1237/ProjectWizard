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
 * Componente público { CloudServiceSpec}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public record CloudServiceSpec(
        String name,
        CloudCapability capability,
        CloudProvider provider,
        boolean required) {

    public CloudServiceSpec {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Service name is required");
        Objects.requireNonNull(capability, "capability");
        provider = provider == null ? CloudProvider.AUTO : provider;
    }
}
