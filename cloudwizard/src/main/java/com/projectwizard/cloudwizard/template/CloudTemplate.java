/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard.template;

import com.projectwizard.cloudwizard.model.CloudCapability;
import java.util.Set;

/** Immutable definition of a reusable Cloud Wizard project preset. */
public record CloudTemplate(String id, String name, String description, Set<CloudCapability> capabilities) {
    public CloudTemplate {
        if (id == null || !id.matches("[a-z][a-z0-9-]*")) throw new IllegalArgumentException("Invalid template id: " + id);
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Template name is required");
        description = description == null ? "" : description;
        capabilities = Set.copyOf(capabilities);
        if (capabilities.isEmpty()) throw new IllegalArgumentException("A template needs at least one capability");
    }
}
