/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
 * Cloud Wizard e software proprietario, comercial e pago.
 * Uso, copia, modificacao ou distribuicao somente sob licenca valida.
 * SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
 * Consulte LICENSE.md na raiz do subprojeto Cloud Wizard.
 */
package com.projectwizard.cloudwizard.provider;

import com.projectwizard.cloudwizard.model.CloudCapability;
import com.projectwizard.cloudwizard.model.CloudProvider;

/**
 * Componente público { AwsCloudAdapter}. Consulte a documentação do pacote para seu papel na arquitetura.
 */
public final class AwsCloudAdapter extends AbstractCatalogAdapter {
    public AwsCloudAdapter() {
        super(CloudProvider.AWS);
        register(CloudCapability.OBJECT_STORAGE, "Amazon S3", "https://docs.aws.amazon.com/s3/");
        register(CloudCapability.SERVERLESS_FUNCTION, "AWS Lambda", "https://docs.aws.amazon.com/lambda/");
        register(CloudCapability.MANAGED_SQL, "Amazon RDS", "https://docs.aws.amazon.com/rds/");
        register(CloudCapability.NOSQL_DATABASE, "Amazon DynamoDB", "https://docs.aws.amazon.com/dynamodb/");
        register(CloudCapability.MESSAGE_QUEUE, "Amazon SQS", "https://docs.aws.amazon.com/sqs/");
        register(CloudCapability.SECRET_MANAGER, "AWS Secrets Manager", "https://docs.aws.amazon.com/secretsmanager/");
        register(CloudCapability.OBSERVABILITY, "Amazon CloudWatch", "https://docs.aws.amazon.com/cloudwatch/");
        register(CloudCapability.CONTAINER_ORCHESTRATION, "Amazon EKS", "https://docs.aws.amazon.com/eks/");
        register(CloudCapability.IDENTITY_ACCESS_MANAGEMENT, "AWS Identity and Access Management", "https://docs.aws.amazon.com/IAM/");
        register(CloudCapability.API_GATEWAY, "Amazon API Gateway", "https://docs.aws.amazon.com/apigateway/");
        register(CloudCapability.MANAGED_CACHE, "Amazon ElastiCache", "https://docs.aws.amazon.com/elasticache/");
        register(CloudCapability.MANAGED_DNS, "Amazon Route 53", "https://docs.aws.amazon.com/Route53/");
        register(CloudCapability.MANAGED_LOAD_BALANCER, "AWS Elastic Load Balancing", "https://docs.aws.amazon.com/elasticloadbalancing/");
        register(CloudCapability.CONTAINER_REGISTRY, "Amazon Elastic Container Registry", "https://docs.aws.amazon.com/ecr/");
        register(CloudCapability.KEY_MANAGEMENT, "AWS Key Management Service", "https://docs.aws.amazon.com/kms/");
        register(CloudCapability.CERTIFICATE_MANAGEMENT, "AWS Certificate Manager", "https://docs.aws.amazon.com/acm/");
    }
}
