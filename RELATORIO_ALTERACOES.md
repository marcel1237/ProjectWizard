# Relatório de Alterações — ProjectWizard

Este documento registra as mudanças técnicas realizadas no projeto durante as sessões de manutenção. Deve ser atualizado sempre que houver uma nova alteração relevante no código, build, testes ou documentação.

## Estado atual

- Java: 21
- JavaFX: 21.0.3
- Build: Maven
- Testes: JUnit Jupiter 5.11.4
- Cobertura: JaCoCo 0.8.12
- Última validação: `./build-all.sh`
- Resultado: 13 testes executados nos dois projetos, sem falhas ou erros
- Pacote gerado: `target/ProjectWizard-1.0.0.jar`

## Alterações realizadas

### 1. Configuração Maven

Arquivo: `pom.xml`

- Removidas as propriedades redundantes `maven.compiler.source` e `maven.compiler.target`.
- Mantida a compilação com Java 21 por meio de `<release>21</release>`.
- Centralizadas as versões do Ikonli, plataforma IntelliJ e JUnit.
- Adicionado JUnit Jupiter 5.11.4.
- Adicionado Maven Surefire 3.5.3.
- Adicionado JaCoCo 0.8.12.
- Desabilitado o module path durante os testes para compatibilidade com o classpath atual.
- Removidas as dependências IntelliJ não utilizadas:
  - `java-analysis-impl`;
  - `analysis-impl`;
  - `core-impl`.
- Removido um repositório JetBrains duplicado.

### 2. Persistência

Arquivo: `src/main/java/com/projectwizard/service/PersistenceService.java`

- Implementada gravação por arquivo temporário e substituição atômica.
- Adicionado fallback quando o sistema de arquivos não suporta troca atômica.
- Definida codificação UTF-8 explícita.
- Aplicadas permissões de leitura e escrita somente para o proprietário quando suportado.
- Alterado o formato da lista de arquivos abertos para aceitar caminhos contendo vírgulas.
- A variável de ambiente `GEMINI_API_KEY` agora tem prioridade sobre o valor armazenado.
- Configurações numéricas inválidas deixam de interromper a aplicação.
- Exceções silenciosas foram substituídas por logging.

### 3. Backups SQLite

Arquivo: `src/main/java/com/projectwizard/service/BackupService.java`

- Tornado o singleton thread-safe.
- Adicionada verificação de inicialização.
- Implementado rollback quando qualquer arquivo falha.
- Fechados corretamente `ResultSet` e streams de diretórios.
- Tornados os filtros independentes do separador do sistema operacional.
- Excluídos do backup:
  - `target`;
  - `.git`;
  - `.idea`;
  - `javadoc`;
  - arquivos históricos `.bak` e `.pre`;
  - banco de backup e arquivos SQLite `-journal`, `-wal` e `-shm`.
- Adicionado limite de 50 MB por arquivo.
- Adaptada a gravação de BLOB à API suportada pelo `sqlite-jdbc`.
- Falhas agora são registradas e propagadas para a interface.

### 4. Editor

Arquivos:

- `src/main/java/com/projectwizard/view/editor/EditorPane.java`
- `src/main/java/com/projectwizard/view/editor/EditorHost.java`

Alterações:

- Criado executor único para o realce de sintaxe.
- Tarefas antigas são canceladas quando o documento muda.
- Adicionado versionamento para impedir aplicação de resultados obsoletos.
- Adicionado encerramento explícito do executor.
- O fechamento de uma aba libera os recursos do editor.
- O comando “Save As” passa a atualizar o arquivo associado à aba.

### 5. Terminal integrado

Arquivo: `src/main/java/com/projectwizard/view/TerminalPane.java`

- Adicionada verificação do estado do processo antes de enviar comandos.
- Adicionado método de encerramento.
- O writer é fechado e o processo Bash é finalizado ao encerrar o workspace.

### 6. Painel Gemini

Arquivo: `src/main/java/com/projectwizard/view/GeminiPane.java`

- Definida leitura UTF-8 da saída dos processos.
- Adicionado fechamento correto do reader.
- Adicionada espera pelo encerramento do comando externo.
- Códigos de saída diferentes de zero agora são tratados como erro.
- Remetente e mensagem passam por escape de HTML.
- Adicionado encerramento do executor do painel.

### 7. Ciclo de vida da aplicação

Arquivos:

- `src/main/java/com/projectwizard/workspace/ProjectWorkspace.java`
- `src/main/java/com/projectwizard/core/navigation/NavigationController.java`
- `src/main/java/com/projectwizard/view/MainWindow.java`

Alterações:

- Recursos do projeto anterior são liberados ao carregar outro projeto.
- Uma falha no backup cancela o build solicitado pela interface.
- A thread de build recebeu um nome identificável.
- Adicionado encerramento centralizado de processos, executores e editores.
- O fechamento da janela principal aciona esse encerramento.

### 8. PDF e logging

Arquivos:

- `src/main/java/com/projectwizard/service/PdfService.java`
- `src/main/java/com/projectwizard/view/sidebar/Sidebar.java`

Alterações:

- Corrigido o fechamento do stream criado por `Files.walk`.
- Substituído `printStackTrace()` por logging no serviço de PDF.
- Removida saída de depuração da navegação no console.

### 9. Scripts, documentação e Git

Arquivos:

- `build.sh`
- `run.sh`
- `README.md`
- `.gitignore`

Alterações:

- Removido o caminho fixo do JDK instalado no Linux.
- Os scripts agora verificam Java e Maven disponíveis no `PATH`.
- Corrigida a versão Java indicada no README de 17 para 21.
- Adicionadas exclusões para artefatos Maven, IntelliJ, banco de backup, temporários e PDF gerado.

### 10. Testes automatizados

Arquivos adicionados:

- `src/test/java/com/projectwizard/service/PersistenceServiceTest.java`
- `src/test/java/com/projectwizard/service/BackupServiceTest.java`
- `src/test/java/com/projectwizard/service/git/GitServiceTest.java`

Cenários cobertos:

- persistência de caminhos contendo vírgulas;
- incremento e leitura do contador de uso;
- criação transacional de backup;
- exclusão de arquivos gerados e internos do SQLite;
- leitura da branch e do remote a partir dos metadados Git.

### 11. Limpeza de artefatos e testes JavaFX

- Removidos 64 arquivos de `target` do índice Git com `git rm --cached`.
- Os arquivos locais não foram apagados e o Maven continua recriando `target` normalmente.
- Configurado `maven-jar-plugin` 3.4.2 com `com.projectwizard.Launcher` no manifesto.
- Adicionada infraestrutura para executar verificações na JavaFX Application Thread.
- Adicionados testes automatizados para a barra de status, sidebar e navegação entre telas.

## Validações

### Build final

```text
mvn -o -q clean verify

Tests run: 6
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

### Verificação de empacotamento

```text
Building jar: target/ProjectWizard-1.0.0.jar
Main-Class: com.projectwizard.Launcher
BUILD SUCCESS
```

Após o empacotamento, `git ls-files target` retorna zero arquivos. Isso confirma que o Maven continua produzindo o pacote localmente, enquanto `target` permanece fora do versionamento.

O aviso do `sqlite-jdbc` sobre `StaticLoggerBinder` é informativo. Na ausência de uma implementação SLF4J, a biblioteca utiliza logging NOP e o funcionamento não é afetado.

## Pendências conhecidas

- A cobertura JavaFX atual verifica componentes e navegação básica; fluxos completos com janelas, seletores de arquivos e processos externos ainda exigem testes de integração específicos.
- O JAR gerado é um artefato Maven convencional. As dependências continuam sendo fornecidas pelo Maven/plug-in JavaFX; uma distribuição nativa autocontida com `jpackage` ainda não foi configurada.

## Cloud Wizard

Subprojeto: `cloudwizard/`

- Criado o primeiro wizard oficial do ProjectWizard.
- Definido um vocabulário cloud independente de fornecedor, expandido para nove capacidades.
- Criadas as anotações `@CloudApplication`, `@UnifiedCloudService` e `@CloudRule`.
- Implementado leitor de anotações para gerar o modelo cloud unificado.
- Implementado motor de regras que transforma capacidades lógicas em serviços concretos.
- Criados adaptadores para AWS, Google Cloud, Microsoft Azure, Oracle Cloud e IBM Cloud.
- Registrados adaptadores pela SPI padrão do Java.
- Implementado gerador transacional de projetos Java 21/Maven.
- O POM gerado declara Compiler 3.14.0 e Surefire 3.5.3 para executar JUnit 5 corretamente.
- Criada CLI inicial para geração fora da interface gráfica.
- Adicionado o cartão Cloud Wizard ao catálogo visual do ProjectWizard.
- Criado `build-all.sh` para validar o software principal e os wizards oficiais.

### Capacidades e serviços iniciais

| Capacidade | AWS | Google Cloud | Azure | Oracle Cloud | IBM Cloud |
|---|---|---|---|---|---|
| Armazenamento de objetos | Amazon S3 | Google Cloud Storage | Azure Blob Storage | OCI Object Storage | IBM Cloud Object Storage |
| Funções serverless | AWS Lambda | Google Cloud Functions | Azure Functions | OCI Functions | IBM Cloud Code Engine |
| SQL gerenciado | Amazon RDS | Google Cloud SQL | Azure SQL Database | Oracle Autonomous Database | IBM Cloud Databases for PostgreSQL |
| Banco NoSQL | Amazon DynamoDB | Google Firestore | Azure Cosmos DB | Oracle NoSQL Database Cloud Service | IBM Cloudant |
| Mensageria | Amazon SQS | Google Pub/Sub | Azure Service Bus | OCI Queue | IBM Event Streams |
| Segredos | AWS Secrets Manager | Google Secret Manager | Azure Key Vault | OCI Vault | IBM Cloud Secrets Manager |
| Observabilidade | Amazon CloudWatch | Google Cloud Operations | Azure Monitor | OCI Monitoring | IBM Cloud Monitoring |
| Kubernetes gerenciado | Amazon EKS | Google Kubernetes Engine | Azure Kubernetes Service | Oracle Kubernetes Engine | IBM Cloud Kubernetes Service |
| Identidade e acesso (IAM) | AWS IAM | Google Cloud IAM | Azure RBAC e Microsoft Entra ID | OCI IAM | IBM Cloud IAM |

### Validação do Cloud Wizard

```text
mvn clean verify
Tests run: 6
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

O ProjectWizard principal executou 7 testes, incluindo a verificação de que o Cloud Wizard aparece no catálogo. O Cloud Wizard executa 6 testes. Um teste de matriz verifica os 45 bindings oficiais (cinco providers × nove capacidades), e outro valida os 12 templates.

Também foi executado um teste de ponta a ponta: a CLI gerou um projeto temporário, o Maven compilou esse projeto e o teste de resolução cloud gerado foi executado com sucesso (`Tests run: 1`, `BUILD SUCCESS`).

### Nona capability, anotações e templates

- Verificada em documentação oficial a presença de gestão de identidade e acesso nas cinco corporações.
- Criada `IDENTITY_ACCESS_MANAGEMENT`, elevando a matriz para 45 bindings (cinco provedores × nove capabilities).
- Criada a anotação especializada `@CloudIdentity` e integrada ao leitor, gerador, rules engine e testes.
- O padrão agora possui nove anotações especializadas, além de `@CloudApplication`, `@CloudRule` e `@UnifiedCloudService`.
- Criado o pacote `template`, com catálogo imutável de 12 arquiteturas reutilizáveis.
- Adicionados exemplos em `cloudwizard/templates/` para storage, site estático, APIs, eventos, dados, Kubernetes, IoT, batch, SaaS e catálogo completo.
- A CLI aceita um terceiro argumento opcional `template-id` e usa `serverless-api` por padrão.

### Documentação integral e Javadoc

- Criado `DOCUMENTACAO_PROJETO.html` na raiz com visão geral, arquitetura, matriz multicloud, anotações, templates, build, qualidade, limites e manutenção.
- Adicionados cabeçalhos SPDX aos fontes Java do projeto principal que ainda não os possuíam.
- Todos os tipos Java de produção agora possuem documentação Javadoc de tipo ou documentação de pacote.
- Adicionados `package-info.java` e páginas `overview.html` nos dois projetos.
- Configurado `maven-javadoc-plugin` 3.11.2 no ciclo `verify` da raiz e do Cloud Wizard.
- Os portais de API são atualizados em `javadoc/apidocs/` e `cloudwizard/javadoc/apidocs/` durante o build.
- Atualizados `README.md`, `cloudwizard/README.md` e este relatório como conjunto de documentação viva.

### Validação desta expansão

```text
./build-all.sh
ProjectWizard: Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
Cloud Wizard: Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
Javadoc ProjectWizard: BUILD SUCCESS
Javadoc Cloud Wizard: BUILD SUCCESS
Total: 13 testes
```

## Histórico

### 2026-08-04 — Décima primeira capability: cache gerenciado

- Adicionada `MANAGED_CACHE` ao vocabulário unificado, elevando o núcleo para onze capabilities.
- Criada a anotação proprietária `@CloudCache` e integrada ao leitor e ao gerador.
- Mapeados Amazon ElastiCache, Google Cloud Memorystore, Azure Managed Redis, OCI Cache e IBM Cloud Databases for Redis.
- Ampliada a matriz oficial de 50 para 55 bindings e atualizados testes e templates relacionados a APIs, Kubernetes e SaaS.
- Atualizadas as quatro clouds públicas asiáticas/eurasiáticas para `11/11-documental` com Tair, TencentDB for Redis, Huawei DCS e Yandex Managed Valkey.
- Registrada a SAP como `10/11-documental`, sem cache Redis/Valkey BTP nativo equivalente confirmado.
- Reclassificadas as quatro clouds no catálogo CSV como `PUBLIC_CLOUD_CANDIDATE`, preservando sua separação do runtime oficial.
- Identificada `MANAGED_DNS` como candidata técnica à capability número 12; a decisão permanece informativa até nova autorização.
- Atualizados assets, READMEs, modelo de negócios, documentação HTML e relatório permanente.
- Validado `mvn clean verify`: 46 fontes compilados, 6 testes aprovados, 55 bindings confirmados, JAR e Javadoc gerados com `BUILD SUCCESS`.

### 2026-08-04 — Décima capability: API Gateway

- Adicionada `API_GATEWAY` ao vocabulário unificado, elevando o núcleo para dez capabilities.
- Criada a anotação proprietária especializada `@CloudApiGateway` e integrada ao leitor e ao gerador.
- Mapeados Amazon API Gateway, Google Cloud API Gateway, Azure API Management, OCI API Gateway e IBM API Connect.
- Ampliada a matriz oficial de 45 para 50 bindings e atualizados os testes de catálogo, anotações e geração.
- Incorporada a capability aos templates de APIs, Kubernetes, SaaS e ao template completo.
- Atualizados o manifesto SAP e a matriz asiática para `10/10-documental`, sem adicioná-los ao runtime.
- Atualizados catálogo CSV, template de provider externo, READMEs, modelo de negócios e documentação HTML.
- Identificada `MANAGED_CACHE` como candidata técnica à capability número 11; a decisão permanece apenas informativa até nova autorização.
- Validado `mvn clean verify`: 45 fontes compilados, 6 testes aprovados, 50 bindings confirmados, JAR e Javadoc gerados com `BUILD SUCCESS`.

### 2026-08-04 — Tabela de clouds públicas da Ásia

- Criada `cloudwizard/assets/providers/PROVIDERS_CLOUD_PUBLICAS_ASIA.md` como tabela documental separada.
- Mapeadas as nove capabilities para Alibaba Cloud, Tencent Cloud, Huawei Cloud e Yandex Cloud.
- Classificadas as quatro como candidatas técnicas com cobertura documental `9/9`, sem promoção ao núcleo oficial.
- Registrada a natureza eurasiática da Yandex e as ressalvas jurídicas, comerciais, regionais e de residência de dados.
- Mantidos enum, SPI, runtime e total do catálogo inalterados.
- Atualizados os READMEs, modelo de negócios e documentação HTML viva.

### 2026-08-04 — Avaliação da Alcatel-Lucent Enterprise

- Avaliada a plataforma Rainbow da Alcatel-Lucent Enterprise como UCaaS/CPaaS especializada.
- Registradas coberturas parciais para armazenamento de arquivos, mensageria, credenciais, observabilidade e identidade no domínio Rainbow.
- Confirmada a ausência de equivalentes horizontais documentados para serverless genérico, SQL, NoSQL e Kubernetes gerenciado.
- Adicionada ao asset como `EVALUATED_EXTERNAL` e `specialized`, sem alteração de enum, SPI ou runtime.
- Ampliado o catálogo de descoberta de 87 para 88 providers e atualizadas todas as contagens documentais.

### 2026-08-04 — Asset amplo de providers externos

- Criado `cloudwizard/assets/providers/provider-catalog.csv` com 87 exemplos estruturados.
- Incluídas todas as organizações analisadas: SAP, Apple, Intel, HPE, Dell, AMD, NVIDIA e Red Hat, além das cinco clouds oficiais.
- Acrescentados exemplos de clouds públicas e regionais, edge, PaaS, dados, identidade e plataformas privadas.
- Mantida a SAP BTP como única referência externa principal e criado seu manifesto documental de nove capabilities.
- Criado template de manifesto externo com todos os bindings inicialmente marcados como não verificados.
- Separados claramente núcleo oficial, referência externa, avaliados externos e candidatos de descoberta.
- Nenhum provider foi acrescentado ao enum, SPI ou runtime; a responsabilidade pelos adapters externos continua com seus autores e usuários.
- Atualizados os READMEs, o modelo de negócios e a documentação HTML viva.
- Validado o CSV: 87 IDs únicos e 10 campos consistentes; validado `mvn clean verify`: 6 testes aprovados e `BUILD SUCCESS`.

### 2026-08-04 — Modernização e estabilização inicial

- Analisada a arquitetura e a configuração Maven.
- Modernizado o pipeline de build e testes.
- Corrigidos persistência, backup, concorrência do editor e ciclo de vida de processos.
- Adicionados quatro testes automatizados.
- Confirmado build Maven limpo sem falhas.
- Criado este relatório permanente de alterações.

### 2026-08-04 — Limpeza do repositório e cobertura JavaFX

- Removidos artefatos de `target` do versionamento sem afetar o empacotamento.
- Adicionados testes de componentes e navegação JavaFX.
- Explicitada a geração do JAR e a classe principal no manifesto Maven.

### 2026-08-04 — Fundação do Cloud Wizard

- Criado o primeiro subprojeto de wizard do ProjectWizard.
- Definidos anotações, modelo unificado, rules engine e SPI de provedores.
- Implementados catálogos AWS e Google Cloud.
- Implementados gerador Maven e CLI.
- Integrado o Cloud Wizard ao catálogo visual.
- Builds do projeto principal e do subprojeto concluídos sem falhas.

### 2026-08-04 — Expansão multicloud

- Adicionados Microsoft Azure, Oracle Cloud Infrastructure e IBM Cloud ao padrão unificado.
- Implementados três novos adaptadores com sete capacidades cada.
- Atualizados SPI, CLI, gerador, testes, catálogo visual e tabelas de documentação.
- Validado projeto gerado contendo os cinco provedores; compilação e teste das rules concluídos com sucesso.

### 2026-08-04 — IAM, templates e documentação integral

- Confirmada a nona capability comum: gestão de identidade e acesso.
- Implementados capability, bindings, anotação especializada e cobertura automatizada.
- Criado catálogo com 12 templates e exemplos declarativos.
- Adicionados cabeçalhos, Javadocs, documentação HTML integral e geração automática no build.

### 2026-08-04 — Separação comercial do Cloud Wizard

- Mantido o licenciamento aberto do ProjectWizard na raiz.
- Reclassificado o subprojeto Cloud Wizard como produto proprietário, comercial e pago.
- Criada `cloudwizard/LICENSE.md`, Cloud Wizard Commercial License Agreement 1.0.
- Adotado o identificador `LicenseRef-CloudWizard-Commercial-1.0` em todos os fontes Java do subprojeto.
- Adicionados avisos proprietários ao POM, SPI, overview Javadoc e 12 templates.
- Criada `cloudwizard/ANALISE_LICENCIAMENTO.md` com riscos da licença cumulativa da raiz, limites da relicença e providências comerciais.
- Atualizados README principal, README do Cloud Wizard e documentação HTML para explicitar a separação.
- Licenças de dependências e ferramentas de terceiros foram preservadas e não são abrangidas pela reivindicação proprietária.
- Executado `mvn clean verify` no subprojeto após a migração: 6 testes aprovados, JAR e Javadoc gerados com `BUILD SUCCESS`.

### 2026-08-04 — Avaliação da Apple como provider

- Pesquisada a documentação oficial de CloudKit, CloudKit Web Services e Apple Business.
- Confirmada cobertura contextual para banco NoSQL por registros e cobertura parcial para assets, notificações, credenciais, telemetria e identidades.
- Não encontrados equivalentes Apple de propósito geral para serverless functions, SQL gerenciado, mensageria, secret manager e Kubernetes.
- A Apple não foi adicionada ao enum, SPI ou rules engine, evitando representar equivalências inexistentes.
- Registrada como possibilidade futura de provider especializado, condicionada ao suporte explícito a matrizes parciais.

### 2026-08-04 — SAP BTP e avaliação da Intel

- Adicionada `SAP_BTP` como sexto provider completo.
- Criado `SapCloudAdapter` com nove serviços: BTP Object Store, Kyma Functions, HANA Cloud, HANA Document Store, Event Mesh, Credential Store, Cloud Logging, Kyma Runtime e Authorization and Trust Management.
- Atualizados enum, SPI, rules engine, CLI, gerador, testes e matrizes documentais.
- Ampliada a matriz automatizada de 45 para 54 bindings.
- Avaliada a Intel Tiber Developer Cloud: confirmados compute para IA, armazenamento de arquivos e Intel Kubernetes Service.
- A Intel não foi adicionada porque não foram encontrados equivalentes oficiais para todas as nove capabilities obrigatórias.
- Executado `mvn clean verify`: 6 testes aprovados, 54 bindings confirmados, JAR e Javadoc gerados com `BUILD SUCCESS`.

### 2026-08-04 — Reposicionamento da SAP e modelo de negócios

- Restaurado o núcleo para cinco providers oficiais e 45 bindings: AWS, Google Cloud, Azure, OCI e IBM Cloud.
- Removidos `SAP_BTP`, `SapCloudAdapter`, registro SPI, fallback, geração e expectativas de teste.
- Mantida a SAP exclusivamente nas tabelas como referência de provider adicional.
- Definido que adapters adicionais são responsabilidade de seus autores e usuários, salvo certificação ou contrato.
- Criado `cloudwizard/MODELO_NEGOCIOS.md` com proposta de valor, clientes, ofertas, receitas, política de providers, certificação, métricas, riscos e próximas decisões.
- Validado `mvn clean verify`: 6 testes aprovados, 45 bindings confirmados, JAR e Javadoc gerados com `BUILD SUCCESS`.

### 2026-08-04 — Anotações especializadas e Kubernetes

- Adicionada `CONTAINER_ORCHESTRATION` como oitava capacidade compartilhada.
- Mapeados EKS, GKE, AKS, OKE e IBM Cloud Kubernetes Service.
- Criadas oito anotações especializadas, uma para cada capacidade.
- Atualizado o leitor para descobrir anotações através da meta-anotação `@CloudRule`.
- Atualizado o gerador para produzir as anotações especializadas.
- Ampliada a matriz automatizada de regras de 35 para 40 bindings.
- Validado de ponta a ponta um projeto gerado com `@CloudContainer`, `@CloudStorage`, `@CloudFunction` e os cinco provedores.
### 2026-08-04 — Capability 12: DNS gerenciado e avaliação da número 13

- Implementada `MANAGED_DNS` como a décima segunda capability portável, com a anotação proprietária `@CloudDns`.
- Adicionados bindings oficiais para Amazon Route 53, Google Cloud DNS, Azure DNS, OCI DNS and Traffic Management e IBM Cloud DNS Services, elevando a matriz automatizada para 60 bindings.
- Atualizados leitor de anotações, gerador, templates, testes, READMEs, documentação HTML, modelo de negócios e assets de providers.
- As candidatas Alibaba Cloud, Tencent Cloud, Huawei Cloud e Yandex Cloud passaram a `12/12-documental`; a SAP BTP permanece em `10/12-documental`, sem equivalentes nativos confirmados para cache e DNS gerenciados.
- Verificada `MANAGED_LOAD_BALANCER` como candidata técnica à capability número 13. AWS Elastic Load Balancing, Google Cloud Load Balancing, Azure Load Balancer, OCI Load Balancer e IBM Cloud Load Balancers for VPC atendem ao contrato comum.
- As quatro candidatas públicas também atendem documentalmente por Alibaba Server Load Balancer, Tencent Cloud Load Balancer, Huawei Elastic Load Balance e Yandex Application Load Balancer.
- A SAP possui um BTP Load Balancer interno ao runtime para custom domains e roteamento, mas não foi confirmado como serviço horizontal configurável pelo cliente; portanto, não recebe binding pleno. Plataformas especializadas continuam parciais ou fora do escopo, enquanto clouds como Cloudflare, Akamai e DigitalOcean merecem avaliação individual futura.
- A capability número 13 permanece apenas recomendada e não foi adicionada ao código nesta etapa.
- Validado `mvn clean verify`: 47 fontes compilados, 6 testes aprovados, 60 bindings confirmados, JAR e Javadoc gerados com `BUILD SUCCESS`.
### 2026-08-04 — Capability 13: load balancer e avaliação da número 14

- Implementada `MANAGED_LOAD_BALANCER` como a décima terceira capability portável, acompanhada da anotação proprietária `@CloudLoadBalancer`.
- Registrados AWS Elastic Load Balancing, Google Cloud Load Balancing, Azure Load Balancer, OCI Load Balancer e IBM Cloud Load Balancer for VPC, elevando a matriz oficial para 65 bindings.
- Atualizados gerador, templates de API/Kubernetes/SaaS, testes, matrizes de providers, READMEs, documentação HTML e modelo de negócios.
- Alibaba Cloud, Tencent Cloud, Huawei Cloud e Yandex Cloud passaram a `13/13-documental` com SLB, CLB, ELB e Application Load Balancer, respectivamente.
- A SAP BTP passou a `10/13-documental`: seu BTP Load Balancer é componente interno para custom domains e rotas, não um serviço horizontal de load balancing administrável pelo cliente.
- Verificada `CONTAINER_REGISTRY` como candidata técnica à capability número 14. As cinco oficiais oferecem Amazon ECR, Google Artifact Registry, Azure Container Registry, OCI Container Registry e IBM Cloud Container Registry.
- As quatro candidatas públicas também atendem por Alibaba Container Registry, Tencent Container Registry, Huawei SoftWare Repository for Container e Yandex Container Registry.
- Entre os externos avaliados, Red Hat OpenShift possui registry integrado e NVIDIA NGC funciona como catálogo/registry especializado; SAP requer registry externo nos fluxos documentados. Apple, Intel, HPE, Dell, AMD e Alcatel-Lucent Enterprise não demonstraram um serviço público horizontal equivalente no escopo já avaliado.
- A capability número 14 permanece recomendada e não foi adicionada ao código nesta etapa.
- Validado `mvn clean verify`: 48 fontes compilados, 6 testes aprovados, 65 bindings confirmados, JAR e Javadoc gerados com `BUILD SUCCESS`.
### 2026-08-04 — Núcleo ampliado para nove clouds públicas

- Promovidas Alibaba Cloud, Tencent Cloud, Huawei Cloud e Yandex Cloud ao grupo principal, ao lado de AWS, Google Cloud, Microsoft Azure, Oracle Cloud e IBM Cloud.
- Adicionados `ALIBABA_CLOUD`, `TENCENT_CLOUD`, `HUAWEI_CLOUD` e `YANDEX_CLOUD` ao enum `CloudProvider`.
- Criados quatro adapters proprietários completos, cada um com bindings para as treze capabilities atuais.
- Registrados os adapters na SPI e no fallback do `CloudRuleEngine`; CLI, projetos gerados e exemplos agora usam os nove providers.
- Ampliada a matriz automatizada de 65 para 117 bindings e os testes passaram a conferir os nove catálogos.
- Reclassificados os 79 nomes restantes do catálogo para `DISCOVERY_ASSET`, `not-assessed` e `REQUIRES_VALIDATION`, sem avaliação, cobertura ou referência ativa.
- Removido o manifesto documental específico da SAP; somente as nove clouds do núcleo permanecem como referências técnicas.
- Atualizados README principal, README do Cloud Wizard, modelo de negócios, documentação HTML e assets de providers.
- Validado `mvn clean verify`: 52 fontes compilados, 6 testes aprovados, 117 bindings confirmados, JAR e Javadoc gerados com `BUILD SUCCESS`.
### 2026-08-04 — Capability 14: container registry nas nove clouds

- Implementada `CONTAINER_REGISTRY` como a décima quarta capability comum às nove clouds públicas oficiais.
- Criada a anotação proprietária `@CloudContainerRegistry` e integrado seu uso ao leitor baseado em `@CloudRule` e ao gerador de projetos.
- Registrados Amazon ECR, Google Artifact Registry, Azure Container Registry, OCI Container Registry, IBM Cloud Container Registry, Alibaba ACR, Tencent TCR, Huawei SWR e Yandex Container Registry.
- Ampliada a matriz oficial de 117 para 126 bindings e atualizados testes, templates de contêiner, catálogos, READMEs, modelo de negócios e documentação HTML.
- Mantidos os outros 79 nomes como opções estritamente customizadas: `DISCOVERY_ASSET`, `not-assessed` e `REQUIRES_VALIDATION`, sem nova avaliação nesta etapa.
- Explicitado que providers externos somente participam por adapter SPI criado e mantido pelo developer/usuário.
- Validado `mvn clean verify`: 53 fontes compilados, 6 testes aprovados, 126 bindings confirmados, JAR e Javadoc gerados com `BUILD SUCCESS`.
### 2026-08-04 — Capability 15: gestão de chaves criptográficas

- Verificada nas nove clouds e implementada `KEY_MANAGEMENT`, distinta de `SECRET_MANAGER` por tratar chaves criptográficas, rotação, políticas e operações de criptografia.
- Criada `@CloudKeyManagement` e adicionados bindings para AWS KMS, Google Cloud KMS, Azure Key Vault Managed HSM, OCI KMS, IBM Key Protect, Alibaba KMS, Tencent KMS, Huawei KMS e Yandex KMS.
- Ampliada a matriz oficial de 126 para 135 bindings; atualizados gerador, testes, template completo, processamento seguro de arquivos, catálogos e documentação viva.
- Nenhum provider externo foi avaliado.
- Validado `mvn clean verify`: 54 fontes compilados, 6 testes aprovados, 135 bindings confirmados, JAR e Javadoc gerados com `BUILD SUCCESS`.
### 2026-08-04 — Capability 16: gerenciamento de certificados

- Verificada nas nove clouds e implementada `CERTIFICATE_MANAGEMENT` para emissão ou importação, armazenamento, acompanhamento do ciclo de vida e integração de certificados TLS/X.509 com serviços cloud.
- Criada `@CloudCertificate` e adicionados bindings para AWS Certificate Manager, Google Cloud Certificate Manager, Azure Key Vault Certificates, OCI Certificates, IBM Secrets Manager Certificates, Alibaba Certificate Management Service, Tencent SSL Certificate Service, Huawei Cloud Certificate Manager e Yandex Certificate Manager.
- Ampliada a matriz oficial de 135 para 144 bindings; atualizados gerador, testes, templates, catálogos, matriz asiática, READMEs, modelo de negócios e documentação HTML.
- Nenhum provider externo foi avaliado.
- Validado `mvn clean verify`: 55 fontes compilados, 6 testes aprovados, 144 bindings confirmados, JAR e Javadoc gerados com `BUILD SUCCESS`.
### 2026-08-04 — Interrupção da sequência na candidata 17

- Avaliada `CONTENT_DELIVERY_NETWORK` como candidata à capability 17, exigindo CDN pública gerenciada, origem configurável, cache em edge e HTTPS.
- AWS CloudFront, Google Cloud CDN e Azure Front Door satisfazem o contrato, mas a documentação oficial atual da Oracle não demonstrou um serviço OCI geral e autônomo equivalente.
- As opções Oracle encontradas são CDN embutida em produtos específicos, como Oracle Content Management e Media Streams, ou integração com CDN adquirida de terceiros; elas não formam o mesmo contrato horizontal.
- Conforme a regra de interrupção solicitada, a verificação parou imediatamente nessa divergência. `CONTENT_DELIVERY_NETWORK` não foi adicionada e os demais providers não foram avaliados para essa candidata.
