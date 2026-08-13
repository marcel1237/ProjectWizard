/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard.provider;

import com.projectwizard.cloudwizard.model.CloudCapability;
import com.projectwizard.cloudwizard.model.CloudProvider;
import com.projectwizard.cloudwizard.model.ProviderService;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/** Base reutilizável dos adaptadores que mantêm um catálogo capability-serviço. */
abstract class AbstractCatalogAdapter implements CloudProviderAdapter {
    private final CloudProvider provider;
    private final Map<CloudCapability, ProviderService> catalog = new EnumMap<>(CloudCapability.class);

    protected AbstractCatalogAdapter(CloudProvider provider) {
        this.provider = provider;
    }

    protected final void register(CloudCapability capability, String serviceName, String documentationUrl) {
        catalog.put(capability, new ProviderService(provider, capability, serviceName, documentationUrl));
    }

    @Override
    public final CloudProvider provider() {
        return provider;
    }

    @Override
    public final Set<CloudCapability> capabilities() {
        return Set.copyOf(catalog.keySet());
    }

    @Override
    public final Optional<ProviderService> resolve(CloudCapability capability) {
        return Optional.ofNullable(catalog.get(capability));
    }
}
