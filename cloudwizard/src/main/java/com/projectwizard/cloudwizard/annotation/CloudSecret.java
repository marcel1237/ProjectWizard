/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard.annotation;

import com.projectwizard.cloudwizard.model.CloudCapability;
import com.projectwizard.cloudwizard.model.CloudProvider;
import java.lang.annotation.*;

@CloudRule(capability = CloudCapability.SECRET_MANAGER, description = "Portable secret manager")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
/**
 * Componente público { CloudSecret}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public @interface CloudSecret {
    String name();
    CloudProvider provider() default CloudProvider.AUTO;
    boolean required() default true;
}
