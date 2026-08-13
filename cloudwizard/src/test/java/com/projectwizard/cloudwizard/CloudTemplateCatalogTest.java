/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.projectwizard.cloudwizard.model.CloudCapability;
import com.projectwizard.cloudwizard.template.CloudTemplateCatalog;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class CloudTemplateCatalogTest {
    @Test
    void catalogContainsTwelveValidUniqueTemplates() {
        var templates = CloudTemplateCatalog.all();
        assertEquals(12, templates.size());
        assertEquals(12, new HashSet<>(templates.stream().map(template -> template.id()).toList()).size());
        assertFalse(templates.stream().anyMatch(template -> template.capabilities().isEmpty()));
        assertEquals(CloudCapability.values().length,
                CloudTemplateCatalog.require("cloud-native-full").capabilities().size());
    }
}
