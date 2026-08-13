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
 * Componente público { IbmCloudAdapter}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public final class IbmCloudAdapter extends AbstractCatalogAdapter {
    public IbmCloudAdapter() {
        super(CloudProvider.IBM_CLOUD);
        register(CloudCapability.OBJECT_STORAGE, "IBM Cloud Object Storage", "https://cloud.ibm.com/docs/cloud-object-storage");
        register(CloudCapability.SERVERLESS_FUNCTION, "IBM Cloud Code Engine", "https://cloud.ibm.com/docs/codeengine");
        register(CloudCapability.MANAGED_SQL, "IBM Cloud Databases for PostgreSQL", "https://cloud.ibm.com/docs/databases-for-postgresql");
        register(CloudCapability.NOSQL_DATABASE, "IBM Cloudant", "https://cloud.ibm.com/docs/Cloudant");
        register(CloudCapability.MESSAGE_QUEUE, "IBM Event Streams", "https://cloud.ibm.com/docs/EventStreams");
        register(CloudCapability.SECRET_MANAGER, "IBM Cloud Secrets Manager", "https://cloud.ibm.com/docs/secrets-manager");
        register(CloudCapability.OBSERVABILITY, "IBM Cloud Monitoring", "https://cloud.ibm.com/docs/monitoring");
        register(CloudCapability.CONTAINER_ORCHESTRATION, "IBM Cloud Kubernetes Service", "https://cloud.ibm.com/docs/containers");
        register(CloudCapability.IDENTITY_ACCESS_MANAGEMENT, "IBM Cloud Identity and Access Management", "https://cloud.ibm.com/docs/iam");
        register(CloudCapability.API_GATEWAY, "IBM API Connect", "https://www.ibm.com/products/api-connect");
        register(CloudCapability.MANAGED_CACHE, "IBM Cloud Databases for Redis", "https://cloud.ibm.com/docs/databases-for-redis");
        register(CloudCapability.MANAGED_DNS, "IBM Cloud DNS Services", "https://cloud.ibm.com/docs/dns-svcs");
        register(CloudCapability.MANAGED_LOAD_BALANCER, "IBM Cloud Load Balancer for VPC", "https://cloud.ibm.com/docs/vpc?topic=vpc-nlb-vs-elb");
        register(CloudCapability.CONTAINER_REGISTRY, "IBM Cloud Container Registry", "https://cloud.ibm.com/docs/Registry");
        register(CloudCapability.KEY_MANAGEMENT, "IBM Key Protect", "https://cloud.ibm.com/docs/key-protect");
        register(CloudCapability.CERTIFICATE_MANAGEMENT, "IBM Cloud Secrets Manager Certificates", "https://cloud.ibm.com/docs/secrets-manager?topic=secrets-manager-prepare-order-certificates");
    }
}
