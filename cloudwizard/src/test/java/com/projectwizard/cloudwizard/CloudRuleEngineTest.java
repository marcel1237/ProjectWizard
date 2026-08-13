/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.projectwizard.cloudwizard.model.CloudCapability;
import com.projectwizard.cloudwizard.model.CloudProjectSpec;
import com.projectwizard.cloudwizard.model.CloudProvider;
import com.projectwizard.cloudwizard.model.CloudServiceSpec;
import com.projectwizard.cloudwizard.model.PortabilityMode;
import com.projectwizard.cloudwizard.rule.CloudRuleEngine;
import java.util.List;
import org.junit.jupiter.api.Test;

class CloudRuleEngineTest {
    @Test
    void resolvesOnePortableCapabilityForAwsAndGoogleCloud() {
        var project = new CloudProjectSpec(
                "portable-app",
                List.of(
                        CloudProvider.AWS,
                        CloudProvider.GOOGLE_CLOUD,
                        CloudProvider.MICROSOFT_AZURE,
                        CloudProvider.ORACLE_CLOUD,
                        CloudProvider.IBM_CLOUD,
                        CloudProvider.ALIBABA_CLOUD,
                        CloudProvider.TENCENT_CLOUD,
                        CloudProvider.HUAWEI_CLOUD,
                        CloudProvider.YANDEX_CLOUD),
                PortabilityMode.STRICT,
                List.of(new CloudServiceSpec("documents", CloudCapability.OBJECT_STORAGE, CloudProvider.AUTO, true)));

        var plan = CloudRuleEngine.standard().resolve(project);

        assertEquals(9, plan.bindings().size());
        assertTrue(plan.bindings().stream().anyMatch(binding -> binding.service().serviceName().equals("Amazon S3")));
        assertTrue(plan.bindings().stream().anyMatch(binding -> binding.service().serviceName().equals("Google Cloud Storage")));
        assertTrue(plan.bindings().stream().anyMatch(binding -> binding.service().serviceName().equals("Azure Blob Storage")));
        assertTrue(plan.bindings().stream().anyMatch(binding -> binding.service().serviceName().equals("OCI Object Storage")));
        assertTrue(plan.bindings().stream().anyMatch(binding -> binding.service().serviceName().equals("IBM Cloud Object Storage")));
        assertTrue(plan.bindings().stream().anyMatch(binding -> binding.service().serviceName().equals("Alibaba Object Storage Service")));
        assertTrue(plan.bindings().stream().anyMatch(binding -> binding.service().serviceName().equals("Tencent Cloud Object Storage")));
        assertTrue(plan.bindings().stream().anyMatch(binding -> binding.service().serviceName().equals("Huawei Object Storage Service")));
        assertTrue(plan.bindings().stream().anyMatch(binding -> binding.service().serviceName().equals("Yandex Object Storage")));
    }
}
