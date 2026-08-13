# Análise de licenciamento — separação ProjectWizard / Cloud Wizard

Data: 4 de agosto de 2026.

> Análise técnica e documental, não parecer jurídico. Recomenda-se validação por advogado antes da primeira comercialização.

## Decisão registrada

- **ProjectWizard, na raiz:** permanece sob o modelo aberto declarado em `../LICENSE.md`.
- **Cloud Wizard, em `cloudwizard/`:** passa a ser produto proprietário, comercial e pago sob `LicenseRef-CloudWizard-Commercial-1.0`.
- A mera presença do código-fonte no mesmo repositório não concede direito de uso do Cloud Wizard.
- Dependências e ferramentas de terceiros conservam suas próprias licenças.

## Análise da licença atual da raiz

O identificador da raiz usa:

```text
BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0
```

Em SPDX, `AND` significa que **todas** as licenças se aplicam ao mesmo material. Isso cria uma carga de conformidade elevada e não representa escolha entre alternativas. O texto da PWL, por outro lado, descreve gatilhos condicionais conforme uso, modificação ou redistribuição; essa lógica não fica plenamente expressa pela linha SPDX cumulativa.

Outros riscos documentais:

1. a PWL afirma ser “100% OSD Compliant”, mas conformidade alegada não equivale a aprovação pela Open Source Initiative;
2. declarar licenças como “integralmente compatíveis” no texto próprio não altera unilateralmente os termos das licenças originais;
3. EPL e MPL possuem mecanismos e escopos próprios, enquanto a expressão `AND` tenta acumulá-los sobre a obra inteira;
4. o texto alterna “uso oficial”, “desenvolvimento interno” e “obras derivadas”, deixando dúvida sobre qual conjunto se aplica a cada cópia;
5. o identificador `LicenseRef-PWL-3.0` é tecnicamente válido para licença fora da lista SPDX, desde que o texto correspondente seja disponibilizado — o que a raiz já faz.

Recomendação futura para a raiz: escolher uma licença aberta reconhecida ou obter revisão jurídica de uma licença própria única, evitando afirmar compatibilidade sem uma matriz formal por arquivo e dependência. Essa recomendação não altera a raiz nesta mudança.

## Validade da migração proprietária

A migração é segura somente para material cujo titular detenha todos os direitos necessários. Antes de comercializar:

- obter cessão ou autorização de cada colaborador cujo código tenha sido incorporado ao Cloud Wizard;
- manter inventário de código copiado ou derivado e das dependências de terceiros;
- verificar se alguma versão do Cloud Wizard já foi entregue sob licença aberta.

Uma licença aberta já concedida sobre cópias anteriores normalmente não é retirada retroativamente. O titular pode oferecer versões futuras sob licença proprietária se tiver direitos para relicenciar, mas destinatários de versões anteriores conservam os direitos já recebidos conforme os respectivos termos.

## Estrutura adotada no Cloud Wizard

Foi escolhida uma licença comercial única com:

- modalidades Developer, Team, Enterprise, OEM e Evaluation;
- licença condicionada a Pedido válido e pago;
- Source Access separado do direito de distribuição;
- proteção de código, arquitetura, rules, adapters, anotações e templates;
- direito do cliente sobre arquivos novos gerados para seu domínio;
- reserva de licença sobre componentes Cloud Wizard incorporados;
- confidencialidade, controle de assentos e auditoria proporcional;
- garantia limitada, limites de responsabilidade e ressalva de direitos obrigatórios;
- reconhecimento de licenças de terceiros;
- término e eliminação das cópias após encerramento;
- legislação brasileira e foro sujeito às regras obrigatórias.

## Providências comerciais recomendadas

1. constituir/identificar formalmente a pessoa física ou jurídica licenciadora;
2. criar modelos de Pedido, proposta, nota fiscal, DPA, SLA e política de suporte;
3. definir preços, métricas e processo de emissão/revogação de chaves;
4. registrar marca e considerar o registro de programa de computador no INPI;
5. produzir SBOM e arquivo de avisos de terceiros a cada versão;
6. restringir o acesso ao repositório e registrar o aceite contratual;
7. revisar a licença para relações de consumo, vendas internacionais, tributos e proteção de dados.

## Referências normativas

- Lei brasileira nº 9.609/1998: proteção e contratos de licença de programa de computador.
- INPI: registro de programa de computador e comprovação de autoria/titularidade.
- SPDX: uso de `LicenseRef-` para licenças fora da lista padronizada.
