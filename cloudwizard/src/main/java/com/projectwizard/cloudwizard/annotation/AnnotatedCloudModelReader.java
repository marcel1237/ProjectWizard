/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard.annotation;

import com.projectwizard.cloudwizard.model.CloudProjectSpec;
import com.projectwizard.cloudwizard.model.CloudServiceSpec;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import com.projectwizard.cloudwizard.model.CloudProvider;

/**
 * Componente público { AnnotatedCloudModelReader}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public final class AnnotatedCloudModelReader {
    public CloudProjectSpec read(Class<?> applicationType) {
        CloudApplication application = applicationType.getAnnotation(CloudApplication.class);
        if (application == null) {
            throw new IllegalArgumentException(applicationType.getName() + " is not annotated with @CloudApplication");
        }

        List<CloudServiceSpec> services = new ArrayList<>();
        collect(applicationType, services);
        Arrays.stream(applicationType.getDeclaredFields()).forEach(field -> collect(field, services));
        Arrays.stream(applicationType.getDeclaredMethods()).forEach(method -> collect(method, services));

        return new CloudProjectSpec(
                application.name(),
                List.of(application.providers()),
                application.portability(),
                services);
    }

    private void collect(AnnotatedElement element, List<CloudServiceSpec> services) {
        for (UnifiedCloudService annotation : element.getAnnotationsByType(UnifiedCloudService.class)) {
            services.add(new CloudServiceSpec(
                    annotation.name(), annotation.capability(), annotation.provider(), annotation.required()));
        }
        for (Annotation annotation : element.getAnnotations()) {
            CloudRule rule = annotation.annotationType().getAnnotation(CloudRule.class);
            if (rule != null) services.add(readSpecialized(annotation, rule));
        }
    }

    private CloudServiceSpec readSpecialized(Annotation annotation, CloudRule rule) {
        try {
            String name = (String) annotation.annotationType().getMethod("name").invoke(annotation);
            CloudProvider provider = (CloudProvider) annotation.annotationType().getMethod("provider").invoke(annotation);
            boolean required = (boolean) annotation.annotationType().getMethod("required").invoke(annotation);
            return new CloudServiceSpec(name, rule.capability(), provider, required);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException exception) {
            throw new IllegalArgumentException(
                    "Specialized cloud annotation must declare name(), provider() and required(): "
                            + annotation.annotationType().getName(), exception);
        }
    }
}
