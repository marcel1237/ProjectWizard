/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard.generator;

import com.projectwizard.cloudwizard.model.CloudCapability;
import com.projectwizard.cloudwizard.model.CloudProvider;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

/**
 * Componente público { CloudProjectRequest}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public record CloudProjectRequest(
        Path outputDirectory,
        String groupId,
        String artifactId,
        String applicationName,
        List<CloudProvider> providers,
        Set<CloudCapability> capabilities) {

    public CloudProjectRequest {
        if (outputDirectory == null) throw new IllegalArgumentException("Output directory is required");
        requireIdentifier(groupId, "groupId", true);
        requireIdentifier(artifactId, "artifactId", false);
        if (applicationName == null || applicationName.isBlank()) throw new IllegalArgumentException("Application name is required");
        providers = List.copyOf(providers);
        capabilities = Set.copyOf(capabilities);
        if (providers.isEmpty()) throw new IllegalArgumentException("At least one provider is required");
    }

    private static void requireIdentifier(String value, String label, boolean dotsAllowed) {
        String pattern = dotsAllowed ? "[a-zA-Z_][\\w]*(\\.[a-zA-Z_][\\w]*)*" : "[a-zA-Z_][\\w-]*";
        if (value == null || !value.matches(pattern)) throw new IllegalArgumentException("Invalid " + label + ": " + value);
    }
}
