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
import java.util.Optional;
import java.util.Set;

/**
 * Componente público { CloudProviderAdapter}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public interface CloudProviderAdapter {
    CloudProvider provider();
    Set<CloudCapability> capabilities();
    Optional<ProviderService> resolve(CloudCapability capability);
}
