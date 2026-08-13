/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard.model;

/**
 * Componente público { CloudCapability}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public enum CloudCapability {
    OBJECT_STORAGE,
    SERVERLESS_FUNCTION,
    MANAGED_SQL,
    NOSQL_DATABASE,
    MESSAGE_QUEUE,
    SECRET_MANAGER,
    OBSERVABILITY,
    CONTAINER_ORCHESTRATION,
    IDENTITY_ACCESS_MANAGEMENT,
    API_GATEWAY,
    MANAGED_CACHE,
    MANAGED_DNS,
    MANAGED_LOAD_BALANCER,
    CONTAINER_REGISTRY,
    KEY_MANAGEMENT,
    CERTIFICATE_MANAGEMENT
}
