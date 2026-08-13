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
 * Componente público { OracleCloudAdapter}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public final class OracleCloudAdapter extends AbstractCatalogAdapter {
    public OracleCloudAdapter() {
        super(CloudProvider.ORACLE_CLOUD);
        register(CloudCapability.OBJECT_STORAGE, "OCI Object Storage", "https://docs.oracle.com/en-us/iaas/Content/Object/home.htm");
        register(CloudCapability.SERVERLESS_FUNCTION, "OCI Functions", "https://docs.oracle.com/en-us/iaas/Content/Functions/home.htm");
        register(CloudCapability.MANAGED_SQL, "Oracle Autonomous Database", "https://docs.oracle.com/en/cloud/paas/autonomous-database/");
        register(CloudCapability.NOSQL_DATABASE, "Oracle NoSQL Database Cloud Service", "https://docs.oracle.com/en/cloud/paas/nosql-cloud/");
        register(CloudCapability.MESSAGE_QUEUE, "OCI Queue", "https://docs.oracle.com/en-us/iaas/Content/queue/home.htm");
        register(CloudCapability.SECRET_MANAGER, "OCI Vault", "https://docs.oracle.com/en-us/iaas/Content/KeyManagement/home.htm");
        register(CloudCapability.OBSERVABILITY, "OCI Monitoring", "https://docs.oracle.com/en-us/iaas/Content/Monitoring/home.htm");
        register(CloudCapability.CONTAINER_ORCHESTRATION, "Oracle Kubernetes Engine", "https://docs.oracle.com/en-us/iaas/Content/ContEng/home.htm");
        register(CloudCapability.IDENTITY_ACCESS_MANAGEMENT, "OCI Identity and Access Management", "https://docs.oracle.com/en-us/iaas/Content/Identity/home.htm");
        register(CloudCapability.API_GATEWAY, "OCI API Gateway", "https://docs.oracle.com/en-us/iaas/Content/APIGateway/home.htm");
        register(CloudCapability.MANAGED_CACHE, "OCI Cache", "https://docs.oracle.com/en-us/iaas/Content/ocicache/overview.htm");
        register(CloudCapability.MANAGED_DNS, "OCI DNS and Traffic Management", "https://docs.oracle.com/en-us/iaas/Content/DNS/home.htm");
        register(CloudCapability.MANAGED_LOAD_BALANCER, "OCI Load Balancer", "https://docs.oracle.com/en-us/iaas/Content/Balance/home.htm");
        register(CloudCapability.CONTAINER_REGISTRY, "OCI Container Registry", "https://docs.oracle.com/en-us/iaas/Content/Registry/home.htm");
        register(CloudCapability.KEY_MANAGEMENT, "OCI Key Management Service", "https://docs.oracle.com/en-us/iaas/Content/KeyManagement/home.htm");
        register(CloudCapability.CERTIFICATE_MANAGEMENT, "OCI Certificates", "https://docs.oracle.com/en-us/iaas/Content/certificates/overview.htm");
    }
}
