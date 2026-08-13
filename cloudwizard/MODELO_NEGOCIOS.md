# Modelo de Negócios — Cloud Wizard

Versão inicial: 4 de agosto de 2026.

> Documento estratégico em evolução. Valores, metas financeiras e condições finais serão definidos em propostas e Pedidos comerciais.

## 1. Posicionamento

Cloud Wizard é um produto proprietário e pago para padronizar a criação de software destinado às principais clouds públicas. O núcleo oficial usa nove providers como referência de mercado:

1. Amazon Web Services;
2. Google Cloud;
3. Microsoft Azure;
4. Oracle Cloud Infrastructure;
5. IBM Cloud;
6. Alibaba Cloud;
7. Tencent Cloud;
8. Huawei Cloud;
9. Yandex Cloud.

Esses providers formam a matriz oficial porque oferecem um catálogo horizontal completo para as dezesseis capabilities do produto e representam modelos consolidados de desenvolvimento em cloud pública. O Cloud Wizard não é afiliado, patrocinado nem certificado por essas empresas, salvo acordo futuro por escrito.

## 2. Proposta de valor

- declarar intenção cloud uma vez por anotações Java;
- reduzir acoplamento prematuro a SDKs de um fornecedor;
- comparar serviços equivalentes em uma linguagem comum;
- gerar projetos, testes e documentação com padrões repetíveis;
- acelerar provas de conceito, modernizações e estratégias multicloud;
- permitir que consultorias criem catálogos e templates governados;
- registrar diferenças e responsabilidades em vez de prometer equivalência absoluta.

## 3. Clientes

- desenvolvedores e arquitetos cloud;
- equipes de plataforma e engenharia interna;
- startups que precisam adiar decisões de fornecedor;
- consultorias e integradores multicloud;
- empresas com requisitos de portabilidade, soberania ou aquisição;
- provedores regionais e plataformas empresariais interessados em adapters próprios;
- instituições de ensino por licenciamento específico.

## 4. Produtos comercializáveis

| Oferta | Conteúdo | Cliente típico |
|---|---|---|
| Developer | IDE/CLI, providers oficiais e templates padrão para um usuário | Desenvolvedor individual |
| Team | Assentos compartilhados pela mesma entidade, catálogo e suporte | Equipes de produto |
| Enterprise | Governança, ambientes, integrações, SLA e suporte ampliado | Grandes empresas |
| Provider Adapter Kit | SPI, validação, testes de conformidade e documentação para provider adicional | Cloud regional ou plataforma empresarial |
| Certification Program | Revisão técnica e selo de compatibilidade por versão | Providers e parceiros |
| OEM/White-label | Incorporação autorizada em IDE, portal ou produto de terceiro | Fabricantes e plataformas |
| Professional Services | Discovery, modelagem, migração, templates e capacitação | Empresas e consultorias |

## 5. Receitas

- assinatura por desenvolvedor/mês ou ano;
- assinatura Team por quantidade de assentos;
- contrato Enterprise por organização, ambientes e suporte;
- licença OEM baseada em distribuição, uso ou receita;
- taxa de desenvolvimento e manutenção de adapters;
- certificação inicial e renovação por versão;
- marketplace com participação sobre templates e extensões;
- serviços profissionais, treinamento e suporte premium.

Nenhum preço está definido neste documento. Preço, moeda, impostos, prazo, métricas, suporte e limites constam do Pedido comercial previsto em `LICENSE.md`.

## 6. Política de providers

### Providers oficiais

AWS, Google Cloud, Microsoft Azure, Oracle Cloud, IBM Cloud, Alibaba Cloud, Tencent Cloud, Huawei Cloud e Yandex Cloud são mantidos pelo produto. Cada versão deve passar pela matriz automatizada completa de 144 bindings.

### Catálogo de descoberta

O asset [`assets/providers/provider-catalog.csv`](assets/providers/provider-catalog.csv) oferece 88 nomes para descoberta de mercado. Somente os nove providers mantidos ocupam `OFFICIAL_CORE`. Todas as outras organizações usam `DISCOVERY_ASSET`, cobertura `not-assessed` e `REQUIRES_VALIDATION`; não existe referência externa principal nem grupo de plataformas avaliadas.

Alibaba Cloud, Tencent Cloud, Huawei Cloud e Yandex Cloud integram o núcleo e possuem uma [matriz operacional complementar](assets/providers/PROVIDERS_CLOUD_PUBLICAS_ASIA.md). A Yandex é eurasiática e exige controles adicionais relacionados ao mercado aplicável.

Outras clouds podem implementar `CloudProviderAdapter` ou contratar o Provider Adapter Kit. A existência de um adapter não significa certificação. Salvo contrato específico:

- qualquer provider fora das nove referências oficiais é opcional e somente entra em um projeto por decisão e adapter customizado do developer/usuário;

- o autor do adapter responde por mapeamentos, atualização e suporte;
- o usuário responde por validar disponibilidade regional, preços, quotas e requisitos legais;
- o usuário deve revisar segurança, IAM, persistência, continuidade e equivalência semântica;
- nomes semelhantes não garantem comportamento, SLA ou modelo de consistência equivalente;
- o Cloud Wizard não responde por mudanças, indisponibilidade ou termos de terceiros.

### Certificação futura

Um adapter poderá receber estado `Community`, `Verified` ou `Certified`:

| Estado | Responsável | Requisitos mínimos |
|---|---|---|
| Community | Autor/usuário externo | Compila, declara origem e fornece testes |
| Verified | Cloud Wizard | Matriz técnica revisada e testes automatizados |
| Certified | Cloud Wizard + provider | Acordo comercial, validação conjunta, suporte e renovação |

## 7. Critérios de entrada no núcleo

Um provider só deve entrar no núcleo oficial quando:

1. possuir equivalentes documentados para as dezesseis capabilities;
2. oferecer APIs ou serviços utilizáveis por clientes externos;
3. manter documentação estável e URLs oficiais;
4. passar por testes de 100% da matriz;
5. definir responsável por manutenção e incidentes;
6. ter análise jurídica de marcas, API e termos de uso;
7. demonstrar demanda comercial ou parceria estratégica.

## 8. Canais

- venda direta pelo site e inside sales;
- parceiros, consultorias e integradores;
- marketplace próprio de templates/adapters;
- acordos com providers e programas de parceiros;
- conteúdo técnico, documentação e versões de avaliação;
- treinamentos e comunidades de arquitetura cloud.

## 9. Métricas

- receita recorrente anual e mensal;
- usuários e organizações licenciadas;
- conversão Evaluation → Developer/Team;
- retenção e expansão de assentos;
- projetos gerados e matrizes resolvidas;
- adapters certificados e receita do marketplace;
- tempo médio para atualizar mudanças dos providers;
- incidentes de equivalência e satisfação do suporte.

## 10. Riscos e controles

| Risco | Controle proposto |
|---|---|
| Serviços cloud não são semanticamente idênticos | Rules explícitas, warnings e documentação por binding |
| APIs e nomes mudam | Versionamento do catálogo e rotina de revisão |
| Uso indevido de marcas | Nomes apenas descritivos e política de não afiliação |
| Adapter comunitário incorreto | Estados de confiança e responsabilidade declarada |
| Código proprietário exposto | Controle de acesso, licença, chaves e auditoria |
| Dependência de terceiros | SBOM, avisos e testes por versão |
| Expectativa de provisionamento automático | Separar resolução lógica de execução de infraestrutura |

## 11. Próximas decisões

1. definir preços e limites de cada plano;
2. especificar o formato técnico de adapters externos sem ampliar o enum fechado;
3. criar manifesto com provider ID, versão, mantenedor e estado de confiança;
4. criar programa de certificação e suíte de conformidade;
5. preparar Pedido comercial, SLA, política de suporte e termos de marketplace;
6. validar o modelo jurídico, tributário e de marcas antes das vendas.
7. revisar periodicamente URLs, nomes, aquisições, descontinuações e restrições regionais do catálogo de descoberta.
