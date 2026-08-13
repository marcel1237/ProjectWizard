# Cloud Wizard ☁️🧙

Cloud Wizard é o primeiro wizard oficial e comercial do ProjectWizard. Seu objetivo é oferecer um padrão Java proprietário para descrever aplicações cloud sem acoplar o domínio diretamente às APIs de um único fornecedor.

> **Produto proprietário e pago. Não é open source.** O código, as anotações, o rules engine, os adaptadores, templates e geradores somente podem ser usados com licença comercial válida. Consulte [LICENSE.md](LICENSE.md).

## Princípio

O código declara a capacidade necessária. O motor de regras escolhe o serviço correspondente entre os nove providers oficiais:

| Capacidade unificada | AWS | Google Cloud | Microsoft Azure | Oracle Cloud | IBM Cloud |
|---|---|---|---|---|---|
| `OBJECT_STORAGE` | Amazon S3 | Google Cloud Storage | Azure Blob Storage | OCI Object Storage | IBM Cloud Object Storage |
| `SERVERLESS_FUNCTION` | AWS Lambda | Google Cloud Functions | Azure Functions | OCI Functions | IBM Cloud Code Engine |
| `MANAGED_SQL` | Amazon RDS | Google Cloud SQL | Azure SQL Database | Oracle Autonomous Database | IBM Cloud Databases for PostgreSQL |
| `NOSQL_DATABASE` | Amazon DynamoDB | Google Firestore | Azure Cosmos DB | Oracle NoSQL Database Cloud Service | IBM Cloudant |
| `MESSAGE_QUEUE` | Amazon SQS | Google Pub/Sub | Azure Service Bus | OCI Queue | IBM Event Streams |
| `SECRET_MANAGER` | AWS Secrets Manager | Google Secret Manager | Azure Key Vault | OCI Vault | IBM Cloud Secrets Manager |
| `OBSERVABILITY` | Amazon CloudWatch | Google Cloud Operations | Azure Monitor | OCI Monitoring | IBM Cloud Monitoring |
| `CONTAINER_ORCHESTRATION` | Amazon EKS | Google Kubernetes Engine | Azure Kubernetes Service | Oracle Kubernetes Engine | IBM Cloud Kubernetes Service |
| `IDENTITY_ACCESS_MANAGEMENT` | AWS IAM | Google Cloud IAM | Azure RBAC e Microsoft Entra ID | OCI IAM | IBM Cloud IAM |
| `API_GATEWAY` | Amazon API Gateway | Google Cloud API Gateway | Azure API Management | OCI API Gateway | IBM API Connect |
| `MANAGED_CACHE` | Amazon ElastiCache | Google Cloud Memorystore | Azure Managed Redis | OCI Cache | IBM Cloud Databases for Redis |
| `MANAGED_DNS` | Amazon Route 53 | Google Cloud DNS | Azure DNS | OCI DNS and Traffic Management | IBM Cloud DNS Services |
| `MANAGED_LOAD_BALANCER` | AWS Elastic Load Balancing | Google Cloud Load Balancing | Azure Load Balancer | OCI Load Balancer | IBM Cloud Load Balancer for VPC |
| `CONTAINER_REGISTRY` | Amazon Elastic Container Registry | Google Artifact Registry | Azure Container Registry | OCI Container Registry | IBM Cloud Container Registry |
| `KEY_MANAGEMENT` | AWS Key Management Service | Google Cloud KMS | Azure Key Vault Managed HSM | OCI Key Management Service | IBM Key Protect |
| `CERTIFICATE_MANAGEMENT` | AWS Certificate Manager | Google Cloud Certificate Manager | Azure Key Vault Certificates | OCI Certificates | IBM Secrets Manager Certificates |

| Capacidade unificada | Alibaba Cloud | Tencent Cloud | Huawei Cloud | Yandex Cloud |
|---|---|---|---|---|
| `OBJECT_STORAGE` | Object Storage Service | Cloud Object Storage | Object Storage Service | Object Storage |
| `SERVERLESS_FUNCTION` | Function Compute | Serverless Cloud Function | FunctionGraph | Cloud Functions |
| `MANAGED_SQL` | ApsaraDB RDS | TencentDB | Relational Database Service | Managed PostgreSQL |
| `NOSQL_DATABASE` | Tablestore | TencentDB for MongoDB | Document Database Service | Managed YDB |
| `MESSAGE_QUEUE` | Simple Message Queue | TDMQ | Distributed Message Service | Message Queue |
| `SECRET_MANAGER` | KMS Secrets Manager | Secrets Manager | Cloud Secret Management Service | Lockbox |
| `OBSERVABILITY` | CloudMonitor | Cloud Monitor | Cloud Eye | Monitoring |
| `CONTAINER_ORCHESTRATION` | ACK | Tencent Kubernetes Engine | Cloud Container Engine | Managed Kubernetes |
| `IDENTITY_ACCESS_MANAGEMENT` | RAM | CAM | IAM | IAM |
| `API_GATEWAY` | API Gateway | API Gateway | API Gateway | API Gateway |
| `MANAGED_CACHE` | Tair | TencentDB for Redis | Distributed Cache Service | Managed Valkey |
| `MANAGED_DNS` | Cloud DNS | DNSPod | Domain Name Service | Cloud DNS |
| `MANAGED_LOAD_BALANCER` | Server Load Balancer | Cloud Load Balancer | Elastic Load Balance | Application Load Balancer |
| `CONTAINER_REGISTRY` | Container Registry | Tencent Container Registry | SoftWare Repository for Container | Container Registry |
| `KEY_MANAGEMENT` | Key Management Service | Key Management Service | Key Management Service | Key Management Service |
| `CERTIFICATE_MANAGEMENT` | Certificate Management Service | SSL Certificate Service | Cloud Certificate Manager | Certificate Manager |

## Anotações especializadas

Cada capacidade possui uma anotação expressiva, e `@UnifiedCloudService` continua disponível para extensões genéricas:

| Anotação | Capacidade |
|---|---|
| `@CloudStorage` | `OBJECT_STORAGE` |
| `@CloudFunction` | `SERVERLESS_FUNCTION` |
| `@CloudDatabase` | `MANAGED_SQL` |
| `@CloudNoSqlDatabase` | `NOSQL_DATABASE` |
| `@CloudQueue` | `MESSAGE_QUEUE` |
| `@CloudSecret` | `SECRET_MANAGER` |
| `@CloudObservability` | `OBSERVABILITY` |
| `@CloudContainer` | `CONTAINER_ORCHESTRATION` |
| `@CloudIdentity` | `IDENTITY_ACCESS_MANAGEMENT` |
| `@CloudApiGateway` | `API_GATEWAY` |
| `@CloudCache` | `MANAGED_CACHE` |
| `@CloudDns` | `MANAGED_DNS` |
| `@CloudLoadBalancer` | `MANAGED_LOAD_BALANCER` |
| `@CloudContainerRegistry` | `CONTAINER_REGISTRY` |
| `@CloudKeyManagement` | `KEY_MANAGEMENT` |
| `@CloudCertificate` | `CERTIFICATE_MANAGEMENT` |

As anotações especializadas são marcadas com `@CloudRule`. O leitor usa esse metadado para descobrir a capacidade, mantendo o padrão extensível.

## Exemplo

```java
@CloudApplication(
    name = "orders",
    providers = {
        CloudProvider.AWS,
        CloudProvider.GOOGLE_CLOUD,
        CloudProvider.MICROSOFT_AZURE,
        CloudProvider.ORACLE_CLOUD,
        CloudProvider.IBM_CLOUD,
        CloudProvider.ALIBABA_CLOUD,
        CloudProvider.TENCENT_CLOUD,
        CloudProvider.HUAWEI_CLOUD,
        CloudProvider.YANDEX_CLOUD
    }
)
@CloudStorage(name = "documents")
@CloudQueue(name = "events")
@CloudContainer(name = "application-cluster")
@CloudIdentity(name = "application-access")
@CloudApiGateway(name = "public-api")
@CloudCache(name = "sessions")
@CloudDns(name = "public-zone")
@CloudLoadBalancer(name = "public-entrypoint")
@CloudContainerRegistry(name = "application-images")
@CloudKeyManagement(name = "application-keys")
@CloudCertificate(name = "public-tls")
public final class OrdersApplication {
}
```

O `AnnotatedCloudModelReader` transforma as anotações em um modelo independente. O `CloudRuleEngine` gera um `ResolutionPlan` com os bindings concretos de cada provedor.

## Arquitetura

```text
annotations → cloud model → rule engine → provider adapters
                                     ├── AWS
                                     ├── Google Cloud
                                     ├── Microsoft Azure
                                     ├── Oracle Cloud
                                     ├── IBM Cloud
                                     ├── Alibaba Cloud
                                     ├── Tencent Cloud
                                     ├── Huawei Cloud
                                     └── Yandex Cloud
```

- `annotation`: contrato público declarativo;
- `model`: vocabulário cloud unificado;
- `rule`: resolução e validação de portabilidade;
- `provider`: catálogo e SPI para provedores;
- `generator`: criação transacional da estrutura de novos projetos;
- `template`: catálogo de arquiteturas reutilizáveis;
- `cli`: ponto inicial para automação fora da interface gráfica.

## Build

```bash
cd cloudwizard
mvn clean verify
```

O acesso ao repositório ou a capacidade de compilar não concede licença de produção, redistribuição ou exploração comercial.

## Gerador via CLI

```bash
java -jar target/cloudwizard-0.1.0-SNAPSHOT.jar /diretorio/de/saida nome-do-projeto [template-id]
```

O gerador inicial cria um projeto Java 21/Maven, aplicação anotada, teste das regras e documentação do projeto.

## Templates prontos

| ID | Arquitetura |
|---|---|
| `minimal-storage` | Armazenamento mínimo com IAM |
| `static-site` | Site estático observável |
| `serverless-api` | API serverless orientada a eventos |
| `portable-web-api` | API em contêiner com SQL |
| `event-driven` | Serviço orientado a eventos |
| `data-platform` | Fundação de plataforma de dados |
| `microservice-kubernetes` | Microsserviço Kubernetes |
| `secure-file-processing` | Processamento seguro de arquivos |
| `iot-backend` | Backend para IoT |
| `batch-processing` | Processamento em lote |
| `multi-cloud-saas` | Fundação SaaS multicloud |
| `cloud-native-full` | Todas as quinze capacidades |

Os exemplos declarativos ficam em `templates/`. Sem informar um ID, a CLI utiliza `serverless-api`.

## Documentação de API

`mvn verify` também atualiza `javadoc/apidocs/index.html`. A documentação geral do produto está em `../DOCUMENTACAO_PROJETO.html`.

## Licenciamento comercial

- Licenças previstas: Developer, Team, Enterprise, OEM/Redistribution e Evaluation.
- Preço, prazo, assentos, empresas autorizadas, suporte e Source Access são definidos em Pedido comercial.
- Projetos novos gerados para o cliente pertencem ao cliente; componentes identificáveis do Cloud Wizard incorporados continuam licenciados.
- Distribuição do runtime, biblioteca, API, templates ou produto white-label exige licença OEM específica.
- Dependências de terceiros preservam suas próprias licenças.

Identificador dos fontes: `LicenseRef-CloudWizard-Commercial-1.0`.

## Modelo de negócios e providers

AWS, Google Cloud, Microsoft Azure, Oracle Cloud, IBM Cloud, Alibaba Cloud, Tencent Cloud, Huawei Cloud e Yandex Cloud compõem a matriz oficial de mercado, mantida e testada pelo Cloud Wizard.

Adapters externos e seus mapeamentos são responsabilidade de seus autores e usuários, salvo certificação ou contrato específico. Consulte [MODELO_NEGOCIOS.md](MODELO_NEGOCIOS.md) para ofertas comerciais, receitas, política de providers, certificação e critérios de entrada no núcleo.

### Asset de descoberta de providers

O diretório [`assets/providers/`](assets/providers/) contém um catálogo processável com **88 exemplos**. Somente os nove providers do núcleo possuem cobertura e estado oficial. Todos os demais registros são pontos de descoberta `not-assessed`, sem avaliação, equivalência ou referência ativa.

Alibaba Cloud, Tencent Cloud, Huawei Cloud e Yandex Cloud possuem uma [matriz operacional complementar](assets/providers/PROVIDERS_CLOUD_PUBLICAS_ASIA.md) e integram enum, SPI, rules engine, CLI, geração e testes. A Yandex mantém ressalvas jurídicas e regionais obrigatórias.

## Extensibilidade

Novos provedores implementam `CloudProviderAdapter`. Os adaptadores são registrados pela SPI padrão do Java em `META-INF/services`, sem alteração da API de anotações.

Providers fora das nove referências oficiais são sempre opcionais e customizados pelo developer/usuário. O Cloud Wizard não lhes atribui cobertura automática: o autor escolhe os bindings, registra a implementação na SPI e assume validação, manutenção, disponibilidade regional, segurança e conformidade.

## Escopo desta primeira versão

Esta fundação padroniza intenção, resolução e geração. Ela ainda não provisiona infraestrutura nem substitui Terraform, Pulumi, CloudFormation ou Deployment Manager. Integrações de provisionamento devem consumir o `ResolutionPlan` em módulos futuros.
