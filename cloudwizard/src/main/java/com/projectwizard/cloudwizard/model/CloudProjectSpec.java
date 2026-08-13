/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard.model;

import java.util.List;
import java.util.Objects;

/**
 * Componente público { CloudProjectSpec}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public record CloudProjectSpec(
        String name,
        List<CloudProvider> providers,
        PortabilityMode portability,
        List<CloudServiceSpec> services) {

    public CloudProjectSpec {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Project name is required");
        providers = List.copyOf(Objects.requireNonNull(providers, "providers"));
        services = List.copyOf(Objects.requireNonNull(services, "services"));
        portability = portability == null ? PortabilityMode.STRICT : portability;
        if (providers.isEmpty() || providers.contains(CloudProvider.AUTO)) {
            throw new IllegalArgumentException("At least one concrete cloud provider is required");
        }
    }
}
