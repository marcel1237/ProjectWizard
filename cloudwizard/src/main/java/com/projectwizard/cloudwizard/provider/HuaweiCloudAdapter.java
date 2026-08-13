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

/** Official catalog adapter for Huawei Cloud. */
public final class HuaweiCloudAdapter extends AbstractCatalogAdapter {
    public HuaweiCloudAdapter() {
        super(CloudProvider.HUAWEI_CLOUD);
        register(CloudCapability.OBJECT_STORAGE, "Huawei Object Storage Service", "https://support.huaweicloud.com/intl/en-us/obs/");
        register(CloudCapability.SERVERLESS_FUNCTION, "Huawei FunctionGraph", "https://support.huaweicloud.com/intl/en-us/functiongraph/");
        register(CloudCapability.MANAGED_SQL, "Huawei Relational Database Service", "https://support.huaweicloud.com/intl/en-us/rds/");
        register(CloudCapability.NOSQL_DATABASE, "Huawei Document Database Service", "https://support.huaweicloud.com/intl/en-us/dds/");
        register(CloudCapability.MESSAGE_QUEUE, "Huawei Distributed Message Service", "https://support.huaweicloud.com/intl/en-us/dms/");
        register(CloudCapability.SECRET_MANAGER, "Huawei Cloud Secret Management Service", "https://support.huaweicloud.com/intl/en-us/dew/");
        register(CloudCapability.OBSERVABILITY, "Huawei Cloud Eye", "https://support.huaweicloud.com/intl/en-us/ces/");
        register(CloudCapability.CONTAINER_ORCHESTRATION, "Huawei Cloud Container Engine", "https://support.huaweicloud.com/intl/en-us/cce/");
        register(CloudCapability.IDENTITY_ACCESS_MANAGEMENT, "Huawei Identity and Access Management", "https://support.huaweicloud.com/intl/en-us/iam/");
        register(CloudCapability.API_GATEWAY, "Huawei API Gateway", "https://support.huaweicloud.com/intl/en-us/apig/");
        register(CloudCapability.MANAGED_CACHE, "Huawei Distributed Cache Service", "https://support.huaweicloud.com/intl/en-us/dcs/");
        register(CloudCapability.MANAGED_DNS, "Huawei Domain Name Service", "https://support.huaweicloud.com/intl/en-us/dns/");
        register(CloudCapability.MANAGED_LOAD_BALANCER, "Huawei Elastic Load Balance", "https://support.huaweicloud.com/intl/en-us/elb/");
        register(CloudCapability.CONTAINER_REGISTRY, "Huawei SoftWare Repository for Container", "https://support.huaweicloud.com/intl/en-us/swr/");
        register(CloudCapability.KEY_MANAGEMENT, "Huawei Cloud Key Management Service", "https://support.huaweicloud.com/intl/en-us/kms/");
        register(CloudCapability.CERTIFICATE_MANAGEMENT, "Huawei Cloud Certificate Manager", "https://support.huaweicloud.com/intl/en-us/productdesc-ccm/");
    }
}
