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

/** Official catalog adapter for Tencent Cloud. */
public final class TencentCloudAdapter extends AbstractCatalogAdapter {
    public TencentCloudAdapter() {
        super(CloudProvider.TENCENT_CLOUD);
        register(CloudCapability.OBJECT_STORAGE, "Tencent Cloud Object Storage", "https://www.tencentcloud.com/document/product/436");
        register(CloudCapability.SERVERLESS_FUNCTION, "Tencent Serverless Cloud Function", "https://www.tencentcloud.com/document/product/583");
        register(CloudCapability.MANAGED_SQL, "TencentDB", "https://www.tencentcloud.com/document/product/236");
        register(CloudCapability.NOSQL_DATABASE, "TencentDB for MongoDB", "https://www.tencentcloud.com/document/product/240");
        register(CloudCapability.MESSAGE_QUEUE, "Tencent TDMQ", "https://www.tencentcloud.com/document/product/1110");
        register(CloudCapability.SECRET_MANAGER, "Tencent Secrets Manager", "https://www.tencentcloud.com/document/product/1140");
        register(CloudCapability.OBSERVABILITY, "Tencent Cloud Monitor", "https://www.tencentcloud.com/document/product/248");
        register(CloudCapability.CONTAINER_ORCHESTRATION, "Tencent Kubernetes Engine", "https://www.tencentcloud.com/document/product/457");
        register(CloudCapability.IDENTITY_ACCESS_MANAGEMENT, "Tencent Cloud Access Management", "https://www.tencentcloud.com/document/product/598");
        register(CloudCapability.API_GATEWAY, "Tencent API Gateway", "https://www.tencentcloud.com/document/product/628");
        register(CloudCapability.MANAGED_CACHE, "TencentDB for Redis", "https://www.tencentcloud.com/document/product/239");
        register(CloudCapability.MANAGED_DNS, "Tencent DNSPod", "https://www.tencentcloud.com/document/product/1157");
        register(CloudCapability.MANAGED_LOAD_BALANCER, "Tencent Cloud Load Balancer", "https://www.tencentcloud.com/document/product/214");
        register(CloudCapability.CONTAINER_REGISTRY, "Tencent Container Registry", "https://www.tencentcloud.com/document/product/1051");
        register(CloudCapability.KEY_MANAGEMENT, "Tencent Cloud Key Management Service", "https://www.tencentcloud.com/document/product/573");
        register(CloudCapability.CERTIFICATE_MANAGEMENT, "Tencent Cloud SSL Certificate Service", "https://intl.cloud.tencent.com/document/product/1007");
    }
}
