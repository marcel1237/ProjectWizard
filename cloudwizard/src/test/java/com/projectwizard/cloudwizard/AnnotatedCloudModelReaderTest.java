/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.projectwizard.cloudwizard.annotation.AnnotatedCloudModelReader;
import com.projectwizard.cloudwizard.annotation.CloudApplication;
import com.projectwizard.cloudwizard.annotation.UnifiedCloudService;
import com.projectwizard.cloudwizard.annotation.CloudStorage;
import com.projectwizard.cloudwizard.annotation.CloudFunction;
import com.projectwizard.cloudwizard.annotation.CloudDatabase;
import com.projectwizard.cloudwizard.annotation.CloudNoSqlDatabase;
import com.projectwizard.cloudwizard.annotation.CloudQueue;
import com.projectwizard.cloudwizard.annotation.CloudSecret;
import com.projectwizard.cloudwizard.annotation.CloudObservability;
import com.projectwizard.cloudwizard.annotation.CloudContainer;
import com.projectwizard.cloudwizard.annotation.CloudIdentity;
import com.projectwizard.cloudwizard.annotation.CloudApiGateway;
import com.projectwizard.cloudwizard.annotation.CloudCache;
import com.projectwizard.cloudwizard.annotation.CloudDns;
import com.projectwizard.cloudwizard.annotation.CloudLoadBalancer;
import com.projectwizard.cloudwizard.annotation.CloudContainerRegistry;
import com.projectwizard.cloudwizard.annotation.CloudKeyManagement;
import com.projectwizard.cloudwizard.annotation.CloudCertificate;
import com.projectwizard.cloudwizard.model.CloudCapability;
import com.projectwizard.cloudwizard.model.CloudProvider;
import org.junit.jupiter.api.Test;

class AnnotatedCloudModelReaderTest {
    @CloudApplication(name = "orders", providers = {
            CloudProvider.AWS,
            CloudProvider.GOOGLE_CLOUD,
            CloudProvider.MICROSOFT_AZURE,
            CloudProvider.ORACLE_CLOUD,
            CloudProvider.IBM_CLOUD,
            CloudProvider.ALIBABA_CLOUD,
            CloudProvider.TENCENT_CLOUD,
            CloudProvider.HUAWEI_CLOUD,
            CloudProvider.YANDEX_CLOUD})
    @UnifiedCloudService(name = "events", capability = CloudCapability.MESSAGE_QUEUE)
    @UnifiedCloudService(name = "secrets", capability = CloudCapability.SECRET_MANAGER)
    private static final class OrdersApplication { }

    @CloudApplication(name = "complete", providers = {CloudProvider.AWS})
    @CloudStorage(name = "files")
    @CloudFunction(name = "worker")
    @CloudDatabase(name = "sql")
    @CloudNoSqlDatabase(name = "documents")
    @CloudQueue(name = "events")
    @CloudSecret(name = "credentials")
    @CloudObservability(name = "telemetry")
    @CloudContainer(name = "cluster")
    @CloudIdentity(name = "access")
    @CloudApiGateway(name = "public-api")
    @CloudCache(name = "sessions")
    @CloudDns(name = "public-zone")
    @CloudLoadBalancer(name = "public-entrypoint")
    @CloudContainerRegistry(name = "application-images")
    @CloudKeyManagement(name = "application-keys")
    @CloudCertificate(name = "public-tls")
    private static final class CompleteApplication { }

    @Test
    void readsRepeatableUnifiedAnnotations() {
        var specification = new AnnotatedCloudModelReader().read(OrdersApplication.class);
        assertEquals("orders", specification.name());
        assertEquals(9, specification.providers().size());
        assertEquals(2, specification.services().size());
    }

    @Test
    void readsEverySpecializedCloudAnnotation() {
        var specification = new AnnotatedCloudModelReader().read(CompleteApplication.class);
        assertEquals(16, specification.services().size());
        assertEquals(16, specification.services().stream().map(service -> service.capability()).distinct().count());
    }
}
