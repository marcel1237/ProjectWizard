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

/**
 * Componente público { AzureCloudAdapter}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public final class AzureCloudAdapter extends AbstractCatalogAdapter {
    public AzureCloudAdapter() {
        super(CloudProvider.MICROSOFT_AZURE);
        register(CloudCapability.OBJECT_STORAGE, "Azure Blob Storage", "https://learn.microsoft.com/azure/storage/blobs/");
        register(CloudCapability.SERVERLESS_FUNCTION, "Azure Functions", "https://learn.microsoft.com/azure/azure-functions/");
        register(CloudCapability.MANAGED_SQL, "Azure SQL Database", "https://learn.microsoft.com/azure/azure-sql/database/");
        register(CloudCapability.NOSQL_DATABASE, "Azure Cosmos DB", "https://learn.microsoft.com/azure/cosmos-db/");
        register(CloudCapability.MESSAGE_QUEUE, "Azure Service Bus", "https://learn.microsoft.com/azure/service-bus-messaging/");
        register(CloudCapability.SECRET_MANAGER, "Azure Key Vault", "https://learn.microsoft.com/azure/key-vault/");
        register(CloudCapability.OBSERVABILITY, "Azure Monitor", "https://learn.microsoft.com/azure/azure-monitor/");
        register(CloudCapability.CONTAINER_ORCHESTRATION, "Azure Kubernetes Service", "https://learn.microsoft.com/azure/aks/");
        register(CloudCapability.IDENTITY_ACCESS_MANAGEMENT, "Azure RBAC and Microsoft Entra ID", "https://learn.microsoft.com/azure/role-based-access-control/");
        register(CloudCapability.API_GATEWAY, "Azure API Management", "https://learn.microsoft.com/azure/api-management/");
        register(CloudCapability.MANAGED_CACHE, "Azure Managed Redis", "https://learn.microsoft.com/azure/redis/");
        register(CloudCapability.MANAGED_DNS, "Azure DNS", "https://learn.microsoft.com/azure/dns/");
        register(CloudCapability.MANAGED_LOAD_BALANCER, "Azure Load Balancer", "https://learn.microsoft.com/azure/load-balancer/");
        register(CloudCapability.CONTAINER_REGISTRY, "Azure Container Registry", "https://learn.microsoft.com/azure/container-registry/");
        register(CloudCapability.KEY_MANAGEMENT, "Azure Key Vault Managed HSM", "https://learn.microsoft.com/azure/key-vault/managed-hsm/");
        register(CloudCapability.CERTIFICATE_MANAGEMENT, "Azure Key Vault Certificates", "https://learn.microsoft.com/azure/key-vault/certificates/");
    }
}
