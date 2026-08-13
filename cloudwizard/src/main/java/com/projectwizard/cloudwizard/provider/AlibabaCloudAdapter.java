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

/** Official catalog adapter for Alibaba Cloud. */
public final class AlibabaCloudAdapter extends AbstractCatalogAdapter {
    public AlibabaCloudAdapter() {
        super(CloudProvider.ALIBABA_CLOUD);
        register(CloudCapability.OBJECT_STORAGE, "Alibaba Object Storage Service", "https://www.alibabacloud.com/help/en/oss/");
        register(CloudCapability.SERVERLESS_FUNCTION, "Alibaba Function Compute", "https://www.alibabacloud.com/help/en/function-compute/");
        register(CloudCapability.MANAGED_SQL, "Alibaba ApsaraDB RDS", "https://www.alibabacloud.com/help/en/rds/");
        register(CloudCapability.NOSQL_DATABASE, "Alibaba Tablestore", "https://www.alibabacloud.com/help/en/tablestore/");
        register(CloudCapability.MESSAGE_QUEUE, "Alibaba Simple Message Queue", "https://www.alibabacloud.com/help/en/mns/");
        register(CloudCapability.SECRET_MANAGER, "Alibaba KMS Secrets Manager", "https://www.alibabacloud.com/help/en/kms/");
        register(CloudCapability.OBSERVABILITY, "Alibaba CloudMonitor", "https://www.alibabacloud.com/help/en/cloudmonitor/");
        register(CloudCapability.CONTAINER_ORCHESTRATION, "Alibaba ACK", "https://www.alibabacloud.com/help/en/ack/");
        register(CloudCapability.IDENTITY_ACCESS_MANAGEMENT, "Alibaba RAM", "https://www.alibabacloud.com/help/en/ram/");
        register(CloudCapability.API_GATEWAY, "Alibaba API Gateway", "https://www.alibabacloud.com/help/en/api-gateway/");
        register(CloudCapability.MANAGED_CACHE, "Alibaba Tair", "https://www.alibabacloud.com/help/en/redis/");
        register(CloudCapability.MANAGED_DNS, "Alibaba Cloud DNS", "https://www.alibabacloud.com/help/en/alidns/");
        register(CloudCapability.MANAGED_LOAD_BALANCER, "Alibaba Server Load Balancer", "https://www.alibabacloud.com/help/en/slb/");
        register(CloudCapability.CONTAINER_REGISTRY, "Alibaba Cloud Container Registry", "https://www.alibabacloud.com/help/en/acr/");
        register(CloudCapability.KEY_MANAGEMENT, "Alibaba Cloud Key Management Service", "https://www.alibabacloud.com/help/en/kms/");
        register(CloudCapability.CERTIFICATE_MANAGEMENT, "Alibaba Cloud Certificate Management Service", "https://www.alibabacloud.com/help/en/ssl-certificate/");
    }
}
