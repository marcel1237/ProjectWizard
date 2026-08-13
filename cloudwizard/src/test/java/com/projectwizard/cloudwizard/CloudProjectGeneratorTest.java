/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.projectwizard.cloudwizard.generator.CloudProjectGenerator;
import com.projectwizard.cloudwizard.generator.CloudProjectRequest;
import com.projectwizard.cloudwizard.model.CloudCapability;
import com.projectwizard.cloudwizard.model.CloudProvider;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.EnumSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CloudProjectGeneratorTest {
    @TempDir
    Path output;

    @Test
    void generatesACompilableMavenProjectStructure() throws Exception {
        var request = new CloudProjectRequest(
                output, "com.example", "orders-cloud", "Orders Cloud",
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
                EnumSet.allOf(CloudCapability.class));

        var project = new CloudProjectGenerator().generate(request);

        assertTrue(Files.isRegularFile(project.root().resolve("pom.xml")));
        assertTrue(Files.readString(project.root().resolve("pom.xml")).contains("maven-surefire-plugin"));
        Path application = project.root().resolve("src/main/java/com/example/Application.java");
        assertTrue(Files.readString(application).contains("@CloudDatabase"));
        assertTrue(Files.readString(application).contains("@CloudSecret"));
        assertTrue(Files.readString(application).contains("@CloudStorage"));
        assertTrue(Files.readString(application).contains("@CloudFunction"));
        assertTrue(Files.readString(application).contains("@CloudNoSqlDatabase"));
        assertTrue(Files.readString(application).contains("@CloudQueue"));
        assertTrue(Files.readString(application).contains("@CloudObservability"));
        assertTrue(Files.readString(application).contains("@CloudContainer"));
        assertTrue(Files.readString(application).contains("@CloudIdentity"));
        assertTrue(Files.readString(application).contains("@CloudApiGateway"));
        assertTrue(Files.readString(application).contains("@CloudCache"));
        assertTrue(Files.readString(application).contains("@CloudDns"));
        assertTrue(Files.readString(application).contains("@CloudLoadBalancer"));
        assertTrue(Files.readString(application).contains("@CloudContainerRegistry"));
        assertTrue(Files.readString(application).contains("@CloudKeyManagement"));
        assertTrue(Files.readString(application).contains("@CloudCertificate"));
        assertTrue(Files.readString(application).contains("CloudProvider.AWS"));
        assertTrue(Files.readString(application).contains("CloudProvider.GOOGLE_CLOUD"));
        assertTrue(Files.readString(application).contains("CloudProvider.MICROSOFT_AZURE"));
        assertTrue(Files.readString(application).contains("CloudProvider.ORACLE_CLOUD"));
        assertTrue(Files.readString(application).contains("CloudProvider.IBM_CLOUD"));
        assertTrue(Files.readString(application).contains("CloudProvider.ALIBABA_CLOUD"));
        assertTrue(Files.readString(application).contains("CloudProvider.TENCENT_CLOUD"));
        assertTrue(Files.readString(application).contains("CloudProvider.HUAWEI_CLOUD"));
        assertTrue(Files.readString(application).contains("CloudProvider.YANDEX_CLOUD"));
    }
}
