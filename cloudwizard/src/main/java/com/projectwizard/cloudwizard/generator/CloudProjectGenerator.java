/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard.generator;

import com.projectwizard.cloudwizard.model.CloudCapability;
import com.projectwizard.cloudwizard.model.CloudProvider;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.projectwizard.cloudwizard.template.CloudTemplate;

/**
 * Componente público { CloudProjectGenerator}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public final class CloudProjectGenerator {
    public GeneratedProject generate(
            Path outputDirectory,
            String groupId,
            String artifactId,
            String applicationName,
            List<CloudProvider> providers,
            CloudTemplate template) throws IOException {
        return generate(new CloudProjectRequest(
                outputDirectory, groupId, artifactId, applicationName, providers, template.capabilities()));
    }

    public GeneratedProject generate(CloudProjectRequest request) throws IOException {
        Path root = request.outputDirectory().toAbsolutePath().normalize().resolve(request.artifactId()).normalize();
        if (!root.startsWith(request.outputDirectory().toAbsolutePath().normalize())) {
            throw new IllegalArgumentException("Artifact path escapes the output directory");
        }
        if (Files.exists(root)) throw new IOException("Destination already exists: " + root);

        String packagePath = request.groupId().replace('.', '/');
        Path source = root.resolve("src/main/java").resolve(packagePath).resolve("Application.java");
        Path test = root.resolve("src/test/java").resolve(packagePath).resolve("CloudApplicationTest.java");
        Path pom = root.resolve("pom.xml");
        Path readme = root.resolve("README.md");
        List<Path> files = List.of(pom, source, test, readme);

        try {
            write(pom, pom(request));
            write(source, application(request));
            write(test, test(request));
            write(readme, readme(request));
            return new GeneratedProject(root, files);
        } catch (IOException | RuntimeException exception) {
            deleteTree(root);
            throw exception;
        }
    }

    private void write(Path file, String content) throws IOException {
        Files.createDirectories(file.getParent());
        Files.writeString(file, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
    }

    private String pom(CloudProjectRequest request) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <project xmlns="http://maven.apache.org/POM/4.0.0">
                    <modelVersion>4.0.0</modelVersion>
                    <groupId>%s</groupId>
                    <artifactId>%s</artifactId>
                    <version>0.1.0-SNAPSHOT</version>
                    <properties>
                        <maven.compiler.release>21</maven.compiler.release>
                        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                    </properties>
                    <dependencies>
                        <dependency>
                            <groupId>com.projectwizard</groupId>
                            <artifactId>cloudwizard</artifactId>
                            <version>0.1.0-SNAPSHOT</version>
                        </dependency>
                        <dependency>
                            <groupId>org.junit.jupiter</groupId>
                            <artifactId>junit-jupiter</artifactId>
                            <version>5.11.4</version>
                            <scope>test</scope>
                        </dependency>
                    </dependencies>
                    <build>
                        <plugins>
                            <plugin>
                                <groupId>org.apache.maven.plugins</groupId>
                                <artifactId>maven-compiler-plugin</artifactId>
                                <version>3.14.0</version>
                            </plugin>
                            <plugin>
                                <groupId>org.apache.maven.plugins</groupId>
                                <artifactId>maven-surefire-plugin</artifactId>
                                <version>3.5.3</version>
                            </plugin>
                        </plugins>
                    </build>
                </project>
                """.formatted(request.groupId(), request.artifactId());
    }

    private String application(CloudProjectRequest request) {
        String providers = request.providers().stream()
                .map(provider -> "CloudProvider." + provider.name())
                .collect(Collectors.joining(", "));
        String annotations = request.capabilities().stream().sorted(Comparator.comparing(Enum::name))
                .map(capability -> "@" + annotationName(capability) + "(name = \"" + logicalName(capability) + "\")")
                .collect(Collectors.joining("\n"));
        return """
                package %s;

                import com.projectwizard.cloudwizard.annotation.*;
                import com.projectwizard.cloudwizard.model.CloudProvider;

                @CloudApplication(name = "%s", providers = {%s})
                %s
                public final class Application {
                    private Application() { }
                }
                """.formatted(request.groupId(), escape(request.applicationName()), providers, annotations);
    }

    private String test(CloudProjectRequest request) {
        return """
                package %s;

                import static org.junit.jupiter.api.Assertions.assertFalse;
                import com.projectwizard.cloudwizard.annotation.AnnotatedCloudModelReader;
                import com.projectwizard.cloudwizard.rule.CloudRuleEngine;
                import org.junit.jupiter.api.Test;

                class CloudApplicationTest {
                    @Test
                    void cloudRulesResolve() {
                        var spec = new AnnotatedCloudModelReader().read(Application.class);
                        assertFalse(CloudRuleEngine.standard().resolve(spec).bindings().isEmpty());
                    }
                }
                """.formatted(request.groupId());
    }

    private String readme(CloudProjectRequest request) {
        return "# " + request.applicationName() + "\n\nGenerated by Cloud Wizard.\n\nProviders: "
                + request.providers() + "\n\nCapabilities: " + request.capabilities() + "\n";
    }

    private String logicalName(CloudCapability capability) {
        return capability.name().toLowerCase().replace('_', '-');
    }

    private String annotationName(CloudCapability capability) {
        return switch (capability) {
            case OBJECT_STORAGE -> "CloudStorage";
            case SERVERLESS_FUNCTION -> "CloudFunction";
            case MANAGED_SQL -> "CloudDatabase";
            case NOSQL_DATABASE -> "CloudNoSqlDatabase";
            case MESSAGE_QUEUE -> "CloudQueue";
            case SECRET_MANAGER -> "CloudSecret";
            case OBSERVABILITY -> "CloudObservability";
            case CONTAINER_ORCHESTRATION -> "CloudContainer";
            case IDENTITY_ACCESS_MANAGEMENT -> "CloudIdentity";
            case API_GATEWAY -> "CloudApiGateway";
            case MANAGED_CACHE -> "CloudCache";
            case MANAGED_DNS -> "CloudDns";
            case MANAGED_LOAD_BALANCER -> "CloudLoadBalancer";
            case CONTAINER_REGISTRY -> "CloudContainerRegistry";
            case KEY_MANAGEMENT -> "CloudKeyManagement";
            case CERTIFICATE_MANAGEMENT -> "CloudCertificate";
        };
    }

    private String escape(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private void deleteTree(Path root) {
        if (!Files.exists(root)) return;
        try (var paths = Files.walk(root)) {
            List<Path> ordered = paths.sorted(Comparator.reverseOrder()).toList();
            for (Path path : ordered) Files.deleteIfExists(path);
        } catch (IOException ignored) { }
    }
}
