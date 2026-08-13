/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.projectwizard.cloudwizard.model.CloudCapability;
import com.projectwizard.cloudwizard.model.CloudProjectSpec;
import com.projectwizard.cloudwizard.model.CloudProvider;
import com.projectwizard.cloudwizard.model.CloudServiceSpec;
import com.projectwizard.cloudwizard.model.PortabilityMode;
import com.projectwizard.cloudwizard.rule.CloudRuleEngine;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class ProviderCatalogTest {
    @Test
    void everyProviderSupportsTheEntireInitialStandard() {
        List<CloudProvider> providers = Arrays.stream(CloudProvider.values())
                .filter(provider -> provider != CloudProvider.AUTO)
                .toList();
        List<CloudServiceSpec> services = Arrays.stream(CloudCapability.values())
                .map(capability -> new CloudServiceSpec(capability.name(), capability, CloudProvider.AUTO, true))
                .toList();

        var project = new CloudProjectSpec("complete-standard", providers, PortabilityMode.STRICT, services);
        var plan = CloudRuleEngine.standard().resolve(project);

        assertEquals(providers.size() * CloudCapability.values().length, plan.bindings().size());
        assertEquals(144, plan.bindings().size());
    }
}
