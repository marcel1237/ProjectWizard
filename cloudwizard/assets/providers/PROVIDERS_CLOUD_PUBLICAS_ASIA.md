<!--
  Cloud Wizard - Copyright (c) 2026. Todos os direitos reservados.
  SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
-->

# Providers oficiais Alibaba, Tencent, Huawei e Yandex

Esta matriz operacional complementa a tabela principal dos nove providers oficiais. Alibaba Cloud, Tencent Cloud, Huawei Cloud e Yandex Cloud possuem cobertura das dezesseis capabilities e adapters registrados no enum, SPI e rules engine, com testes de conformidade no runtime.

> A Yandex Cloud é classificada mais precisamente como eurasiática e possui concentração operacional na Rússia. Ela participa deste agrupamento por seu mercado regional, com análise jurídica, comercial, de sanções e residência de dados obrigatória antes de qualquer integração.

## Matriz de capabilities

| Capability | Alibaba Cloud | Tencent Cloud | Huawei Cloud | Yandex Cloud |
|---|---|---|---|---|
| `OBJECT_STORAGE` | Object Storage Service (OSS) | Cloud Object Storage (COS) | Object Storage Service (OBS) | Object Storage |
| `SERVERLESS_FUNCTION` | Function Compute | Serverless Cloud Function (SCF) | FunctionGraph | Cloud Functions |
| `MANAGED_SQL` | ApsaraDB RDS | TencentDB for MySQL/PostgreSQL | RDS/GaussDB | Managed PostgreSQL/MySQL |
| `NOSQL_DATABASE` | Tablestore/ApsaraDB for MongoDB | TencentDB for MongoDB | Document Database Service (DDS) | YDB/Managed MongoDB |
| `MESSAGE_QUEUE` | Simple Message Queue/ApsaraMQ | TDMQ/Cloud Message Queue | Distributed Message Service (DMS) | Message Queue |
| `SECRET_MANAGER` | KMS Secrets Manager | Secrets Manager | Cloud Secret Management Service (CSMS/DEW) | Lockbox |
| `OBSERVABILITY` | CloudMonitor/Simple Log Service | Cloud Monitor/Cloud Log Service | Cloud Eye/AOM/LTS | Monitoring/Monium |
| `CONTAINER_ORCHESTRATION` | Container Service for Kubernetes (ACK) | Tencent Kubernetes Engine (TKE) | Cloud Container Engine (CCE) | Managed Kubernetes |
| `IDENTITY_ACCESS_MANAGEMENT` | Resource Access Management (RAM) | Cloud Access Management (CAM) | Identity and Access Management (IAM) | Identity and Access Management |
| `API_GATEWAY` | API Gateway/Cloud-native API Gateway | API Gateway | API Gateway (APIG) | Yandex API Gateway |
| `MANAGED_CACHE` | Tair (Redis compatible) | TencentDB for Redis | Distributed Cache Service (DCS) | Managed Service for Valkey |
| `MANAGED_DNS` | Alibaba Cloud DNS | DNSPod/Cloud DNS | Domain Name Service (DNS) | Yandex Cloud DNS |
| `MANAGED_LOAD_BALANCER` | Server Load Balancer (SLB) | Cloud Load Balancer (CLB) | Elastic Load Balance (ELB) | Application Load Balancer |
| `CONTAINER_REGISTRY` | Container Registry (ACR) | Tencent Container Registry (TCR) | SoftWare Repository for Container (SWR) | Container Registry |
| `KEY_MANAGEMENT` | Key Management Service (KMS) | Key Management Service (KMS) | Key Management Service (KMS) | Key Management Service (KMS) |
| `CERTIFICATE_MANAGEMENT` | Certificate Management Service | SSL Certificate Service | Cloud Certificate Manager (CCM) | Certificate Manager |

## Estado oficial

| Provider | Natureza | Cobertura documental | Estado no Cloud Wizard | Ressalva principal |
|---|---|---:|---|---|
| Alibaba Cloud | Cloud pública asiática/global | 16/16 | Oficial | Validar disponibilidade e termos por região |
| Tencent Cloud | Cloud pública asiática/global | 16/16 | Oficial | Validar disponibilidade e termos por região |
| Huawei Cloud | Cloud pública asiática/global | 16/16 | Oficial | Validar disponibilidade e termos por região |
| Yandex Cloud | Cloud pública eurasiática | 16/16 | Oficial condicionada | Validar sanções, pagamentos, legislação e residência de dados |

## Regra de manutenção

Cada provider oficial deve manter dezesseis bindings revisados, registro SPI, geração de projetos, testes automatizados, validação regional, análise jurídica e responsável formal pela manutenção.
