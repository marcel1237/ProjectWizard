/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard.cli;

import com.projectwizard.cloudwizard.generator.CloudProjectGenerator;
import com.projectwizard.cloudwizard.generator.CloudProjectRequest;
import com.projectwizard.cloudwizard.model.CloudProvider;
import java.nio.file.Path;
import java.util.List;
import com.projectwizard.cloudwizard.template.CloudTemplateCatalog;

/**
 * Componente público { CloudWizardCli}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public final class CloudWizardCli {
    private CloudWizardCli() { }

    public static void main(String[] args) throws Exception {
        if (args.length < 2 || args.length > 3) {
            System.err.println("Usage: CloudWizardCli <output-directory> <artifact-id> [template-id]");
            System.err.println("Templates: " + CloudTemplateCatalog.all().stream().map(template -> template.id()).toList());
            System.exit(2);
        }
        var template = CloudTemplateCatalog.require(args.length == 3 ? args[2] : "serverless-api");
        var request = new CloudProjectRequest(
                Path.of(args[0]),
                "com.example",
                args[1],
                args[1],
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
                template.capabilities());
        var generated = new CloudProjectGenerator().generate(request);
        System.out.println("Cloud project generated at " + generated.root());
    }
}
