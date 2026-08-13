/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard.template;

import static com.projectwizard.cloudwizard.model.CloudCapability.*;

import java.util.EnumSet;
import java.util.List;

/** Built-in catalog of portable multi-cloud project templates. */
public final class CloudTemplateCatalog {
    private static final List<CloudTemplate> TEMPLATES = List.of(
            template("minimal-storage", "Minimal Storage", "Object storage with portable access control", OBJECT_STORAGE, IDENTITY_ACCESS_MANAGEMENT),
            template("static-site", "Static Site", "Static assets with managed DNS, monitoring and access control", OBJECT_STORAGE, MANAGED_DNS, OBSERVABILITY, IDENTITY_ACCESS_MANAGEMENT),
            template("serverless-api", "Serverless API", "Functions with NoSQL, cache, messaging and a managed API gateway", SERVERLESS_FUNCTION, NOSQL_DATABASE, MANAGED_CACHE, MESSAGE_QUEUE, SECRET_MANAGER, OBSERVABILITY, IDENTITY_ACCESS_MANAGEMENT, API_GATEWAY),
            template("portable-web-api", "Portable Web API", "Container API backed by managed SQL, cache, registry, DNS, load balancer and API gateway", CONTAINER_ORCHESTRATION, CONTAINER_REGISTRY, MANAGED_SQL, MANAGED_CACHE, MANAGED_DNS, MANAGED_LOAD_BALANCER, SECRET_MANAGER, OBSERVABILITY, IDENTITY_ACCESS_MANAGEMENT, API_GATEWAY),
            template("event-driven", "Event-driven Service", "Queue-driven functions with NoSQL persistence", SERVERLESS_FUNCTION, MESSAGE_QUEUE, NOSQL_DATABASE, SECRET_MANAGER, OBSERVABILITY, IDENTITY_ACCESS_MANAGEMENT),
            template("data-platform", "Data Platform", "Storage, SQL and event ingestion foundation", OBJECT_STORAGE, MANAGED_SQL, MESSAGE_QUEUE, SECRET_MANAGER, OBSERVABILITY, IDENTITY_ACCESS_MANAGEMENT),
            template("microservice-kubernetes", "Kubernetes Microservice", "Managed Kubernetes microservice foundation", CONTAINER_ORCHESTRATION, CONTAINER_REGISTRY, MANAGED_SQL, MANAGED_CACHE, MANAGED_DNS, MANAGED_LOAD_BALANCER, MESSAGE_QUEUE, SECRET_MANAGER, OBSERVABILITY, IDENTITY_ACCESS_MANAGEMENT, API_GATEWAY),
            template("secure-file-processing", "Secure File Processing", "Event-driven processing of protected objects", OBJECT_STORAGE, SERVERLESS_FUNCTION, MESSAGE_QUEUE, SECRET_MANAGER, IDENTITY_ACCESS_MANAGEMENT),
            template("iot-backend", "IoT Backend", "Event ingestion and NoSQL device state", SERVERLESS_FUNCTION, NOSQL_DATABASE, MESSAGE_QUEUE, OBSERVABILITY, IDENTITY_ACCESS_MANAGEMENT),
            template("batch-processing", "Batch Processing", "Container jobs operating on object storage", CONTAINER_ORCHESTRATION, CONTAINER_REGISTRY, OBJECT_STORAGE, MESSAGE_QUEUE, SECRET_MANAGER, OBSERVABILITY, IDENTITY_ACCESS_MANAGEMENT),
            template("multi-cloud-saas", "Multi-cloud SaaS", "Full SaaS foundation across providers", CONTAINER_ORCHESTRATION, CONTAINER_REGISTRY, OBJECT_STORAGE, MANAGED_SQL, NOSQL_DATABASE, MANAGED_CACHE, MANAGED_DNS, MANAGED_LOAD_BALANCER, MESSAGE_QUEUE, SECRET_MANAGER, OBSERVABILITY, IDENTITY_ACCESS_MANAGEMENT, API_GATEWAY),
            new CloudTemplate("cloud-native-full", "Cloud Native Full", "All capabilities in the current standard", EnumSet.allOf(com.projectwizard.cloudwizard.model.CloudCapability.class)));

    private CloudTemplateCatalog() { }

    public static List<CloudTemplate> all() {
        return TEMPLATES;
    }

    public static CloudTemplate require(String id) {
        return TEMPLATES.stream().filter(template -> template.id().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown cloud template: " + id));
    }

    private static CloudTemplate template(String id, String name, String description,
                                          com.projectwizard.cloudwizard.model.CloudCapability first,
                                          com.projectwizard.cloudwizard.model.CloudCapability... remaining) {
        EnumSet<com.projectwizard.cloudwizard.model.CloudCapability> capabilities = EnumSet.of(first, remaining);
        return new CloudTemplate(id, name, description, capabilities);
    }
}
