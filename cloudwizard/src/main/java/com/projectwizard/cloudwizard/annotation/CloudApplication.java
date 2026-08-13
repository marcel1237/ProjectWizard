/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard.annotation;

import com.projectwizard.cloudwizard.model.CloudProvider;
import com.projectwizard.cloudwizard.model.PortabilityMode;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
/**
 * Componente público { CloudApplication}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public @interface CloudApplication {
    String name();
    CloudProvider[] providers();
    PortabilityMode portability() default PortabilityMode.STRICT;
}
