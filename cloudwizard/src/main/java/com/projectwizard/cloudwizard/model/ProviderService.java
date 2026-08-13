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
 * Componente público { ProviderService}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public record ProviderService(
        CloudProvider provider,
        CloudCapability capability,
        String serviceName,
        String documentationUrl) {

    public ProviderService {
        Objects.requireNonNull(provider, "provider");
        Objects.requireNonNull(capability, "capability");
        if (serviceName == null || serviceName.isBlank()) throw new IllegalArgumentException("Service name is required");
        documentationUrl = documentationUrl == null ? "" : documentationUrl;
    }
}
