<!--
  Cloud Wizard - Copyright (c) 2026. Todos os direitos reservados.
  SPDX-License-Identifier: LicenseRef-CloudWizard-Commercial-1.0
-->

# Catálogo de referência de providers

Este diretório é um **asset de descoberta** para usuários que desejam pesquisar adapters e integrações. Ele não registra providers no runtime e não certifica equivalência entre produtos.

## Referências e níveis de confiança

1. **Núcleo oficial** — AWS, Google Cloud, Microsoft Azure, Oracle Cloud, IBM Cloud, Alibaba Cloud, Tencent Cloud, Huawei Cloud e Yandex Cloud. São os nove providers implementados, testados e mantidos pelo Cloud Wizard.
2. **Descoberta sem avaliação** — todas as demais organizações do catálogo são somente nomes opcionais para pesquisa futura, com cobertura `not-assessed` e sem equivalência ou referência ativa. Seu uso depende de adapter customizado e mantido pelo developer/usuário.

## Arquivos

- [`provider-catalog.csv`](provider-catalog.csv): catálogo amplo e processável por planilhas e programas;
- [`PROVIDERS_CLOUD_PUBLICAS_ASIA.md`](PROVIDERS_CLOUD_PUBLICAS_ASIA.md): matriz operacional complementar de quatro dos nove providers oficiais;
- [`external-provider-template.properties`](external-provider-template.properties): modelo seguro para iniciar a avaliação de outro provider.

## Campos do CSV

| Campo | Significado |
|---|---|
| `id` | Identificador sugerido e estável para descoberta |
| `provider` | Nome comercial ou comunitário conhecido |
| `organization` | Organização responsável |
| `segment` | Tipo predominante da oferta |
| `relationship` | Relação com o Cloud Wizard |
| `coverage` | Cobertura conhecida das dezesseis capabilities |
| `known_capabilities` | Capacidades observadas ou `REQUIRES_VALIDATION` |
| `official_url` | Página inicial oficial para pesquisa |
| `adapter_owner` | Responsável por validar e manter um futuro adapter |
| `notes` | Limites e contexto essenciais |

Cobertura documental não é certificação. Todo provider externo exige validação de APIs, disponibilidade regional, preços, quotas, segurança, IAM, residência de dados, SLA, termos e equivalência semântica. Marcas pertencem aos respectivos titulares; a presença no catálogo não indica afiliação ou endosso.
