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

/** Official catalog adapter for Yandex Cloud. */
public final class YandexCloudAdapter extends AbstractCatalogAdapter {
    public YandexCloudAdapter() {
        super(CloudProvider.YANDEX_CLOUD);
        register(CloudCapability.OBJECT_STORAGE, "Yandex Object Storage", "https://yandex.cloud/en/docs/storage/");
        register(CloudCapability.SERVERLESS_FUNCTION, "Yandex Cloud Functions", "https://yandex.cloud/en/docs/functions/");
        register(CloudCapability.MANAGED_SQL, "Yandex Managed PostgreSQL", "https://yandex.cloud/en/docs/managed-postgresql/");
        register(CloudCapability.NOSQL_DATABASE, "Yandex Managed YDB", "https://yandex.cloud/en/docs/ydb/");
        register(CloudCapability.MESSAGE_QUEUE, "Yandex Message Queue", "https://yandex.cloud/en/docs/message-queue/");
        register(CloudCapability.SECRET_MANAGER, "Yandex Lockbox", "https://yandex.cloud/en/docs/lockbox/");
        register(CloudCapability.OBSERVABILITY, "Yandex Monitoring", "https://yandex.cloud/en/docs/monitoring/");
        register(CloudCapability.CONTAINER_ORCHESTRATION, "Yandex Managed Kubernetes", "https://yandex.cloud/en/docs/managed-kubernetes/");
        register(CloudCapability.IDENTITY_ACCESS_MANAGEMENT, "Yandex Identity and Access Management", "https://yandex.cloud/en/docs/iam/");
        register(CloudCapability.API_GATEWAY, "Yandex API Gateway", "https://yandex.cloud/en/docs/api-gateway/");
        register(CloudCapability.MANAGED_CACHE, "Yandex Managed Valkey", "https://yandex.cloud/en/docs/managed-redis/");
        register(CloudCapability.MANAGED_DNS, "Yandex Cloud DNS", "https://yandex.cloud/en/docs/dns/");
        register(CloudCapability.MANAGED_LOAD_BALANCER, "Yandex Application Load Balancer", "https://yandex.cloud/en/docs/application-load-balancer/");
        register(CloudCapability.CONTAINER_REGISTRY, "Yandex Container Registry", "https://yandex.cloud/en/docs/container-registry/");
        register(CloudCapability.KEY_MANAGEMENT, "Yandex Key Management Service", "https://yandex.cloud/en/docs/kms/");
        register(CloudCapability.CERTIFICATE_MANAGEMENT, "Yandex Certificate Manager", "https://yandex.cloud/en/docs/certificate-manager/");
    }
}
