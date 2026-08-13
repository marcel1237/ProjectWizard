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
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Declares a portable managed distributed-cache requirement. */
@CloudRule(capability = CloudCapability.MANAGED_CACHE, description = "Portable managed distributed cache")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
public @interface CloudCache {
    String name();
    CloudProvider provider() default CloudProvider.AUTO;
    boolean required() default true;
}
