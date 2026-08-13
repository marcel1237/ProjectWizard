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
 * Componente público { GoogleCloudAdapter}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public final class GoogleCloudAdapter extends AbstractCatalogAdapter {
    public GoogleCloudAdapter() {
        super(CloudProvider.GOOGLE_CLOUD);
        register(CloudCapability.OBJECT_STORAGE, "Google Cloud Storage", "https://cloud.google.com/storage/docs");
        register(CloudCapability.SERVERLESS_FUNCTION, "Google Cloud Functions", "https://cloud.google.com/functions/docs");
        register(CloudCapability.MANAGED_SQL, "Google Cloud SQL", "https://cloud.google.com/sql/docs");
        register(CloudCapability.NOSQL_DATABASE, "Google Firestore", "https://cloud.google.com/firestore/docs");
        register(CloudCapability.MESSAGE_QUEUE, "Google Pub/Sub", "https://cloud.google.com/pubsub/docs");
        register(CloudCapability.SECRET_MANAGER, "Google Secret Manager", "https://cloud.google.com/secret-manager/docs");
        register(CloudCapability.OBSERVABILITY, "Google Cloud Operations", "https://cloud.google.com/products/operations");
        register(CloudCapability.CONTAINER_ORCHESTRATION, "Google Kubernetes Engine", "https://cloud.google.com/kubernetes-engine/docs");
        register(CloudCapability.IDENTITY_ACCESS_MANAGEMENT, "Google Cloud IAM", "https://cloud.google.com/iam/docs");
        register(CloudCapability.API_GATEWAY, "Google Cloud API Gateway", "https://cloud.google.com/api-gateway/docs");
        register(CloudCapability.MANAGED_CACHE, "Google Cloud Memorystore", "https://cloud.google.com/memorystore/docs");
        register(CloudCapability.MANAGED_DNS, "Google Cloud DNS", "https://cloud.google.com/dns/docs");
        register(CloudCapability.MANAGED_LOAD_BALANCER, "Google Cloud Load Balancing", "https://cloud.google.com/load-balancing/docs");
        register(CloudCapability.CONTAINER_REGISTRY, "Google Artifact Registry", "https://cloud.google.com/artifact-registry/docs");
        register(CloudCapability.KEY_MANAGEMENT, "Google Cloud Key Management Service", "https://cloud.google.com/kms/docs");
        register(CloudCapability.CERTIFICATE_MANAGEMENT, "Google Cloud Certificate Manager", "https://cloud.google.com/certificate-manager/docs");
    }
}
