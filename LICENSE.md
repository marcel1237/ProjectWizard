# LICENSE

## ⚖️ Licenciamento Conjunto Obrigatório (Hexa Co-Licensing)

Este projeto adota uma abordagem de licenciamento robusta e inovadora para garantir a máxima proteção do código-fonte, incentivar a colaboração industrial, simplificar a concessão de patentes e impedir o fechamento do ecossistema em ambientes de nuvem e aplicações locais.

Para utilizar, modificar ou distribuir este software (no todo ou em parte), você **DEVE cumprir os termos e obrigações de seis licenças simultaneamente**:

1. **[BSD 3-Clause License (BSD-3-Clause)](https://opensource.org/licenses/BSD-3-Clause)** - Garante a permissão geral de uso, a preservação ostensiva de copyright e proíbe o uso não autorizado do nome do autor/projeto para promoção ou endosso de produtos derivados.
2. **[Apache License v2.0 (Apache-2.0)](https://www.apache.org/licenses/LICENSE-2.0)** - Fornece uma licença permissiva focada em contribuições comerciais seguras, incluindo uma concessão clara e expressa de direitos de patente dos contribuidores para os usuários.
3. **[Eclipse Public License v2.0 (EPL-2.0)](https://www.eclipse.org/legal/epl-2.0/)** - Garante a proteção dos arquivos centrais e facilita a criação de um ecossistema de plugins comerciais/proprietários em arquivos separados.
4. **[Mozilla Public License v2.0 (MPL-2.0)](https://www.mozilla.org/en-US/MPL/2.0/)** - Protege os arquivos de código-fonte originais, exigindo que qualquer modificação neles permaneça pública.
5. **[GNU General Public License v3.0 (GPL-3.0-only)](https://www.gnu.org/licenses/gpl-3.0.html)** - Assegura a liberdade do software e o copyleft forte para qualquer redistribuição física ou digital de versões modificadas do software.
6. **[GNU Affero General Public License v3.0 (AGPL-3.0-only)](https://www.gnu.org/licenses/agpl-3.0.html)** - Atua como *Licença Secundária e Combinada*, garantindo o "copyleft de rede" (se o motor for modificado e executado na nuvem como serviço/SaaS, o código modificado deve ser liberado).

---

### 📦 Especificidades do Ecossistema Poliglota

Como o **ProjectWizard** adota uma arquitetura multi-linguagem, as seguintes regras de conformidade se aplicam à estrutura do projeto:

* **Arquivos de Código (`.py`, `.ts`, `.cpp`, `.rs`, `.go`, `.java`, `.cs`, etc.):** Todo arquivo criado no projeto deve conter o cabeçalho SPDX padrão nas primeiras linhas para fins de auditoria automática.
* **Módulos e Espaços de Nomes (`package`, `namespace`, `crate`, `module`):** A separação lógica por módulos é utilizada para delimitar o que faz parte do núcleo do ProjectWizard (sujeito ao co-licenciamento estrito) e o que são extensões ou drivers externos através de APIs, FFI ou chamadas RPC.
* **Compilação e Artefatos Binários:** A distribuição de binários compilados, executáveis nativos, pacotes ou contêineres não isenta o distribuidor de fornecer o código-fonte correspondente sob os termos da GPL-3.0, AGPL-3.0, MPL-2.0 e EPL-2.0 caso o software seja modificado, bem como a devida atribuição exigida pela Apache-2.0 e BSD-3-Clause.

---

### 🔍 Identificadores SPDX
Nos termos da Seção 1.13 da EPL 2.0, da Seção 3.3 da MPL 2.0, dos termos de atribuição e patentes da Apache 2.0 e das cláusulas de salvaguarda de marca da BSD 3-Clause, a conformidade é rastreada através da seguinte tag padrão de mercado:

```text
SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND GPL-3.0-only AND AGPL-3.0-only
```

---

## 📄 Termo Integral de Outorga Conjunta

```text

ProjectWizard License v1.0 (PWL-1.0)

Identificador SPDX: LicenseRef-PWL-1.0
Autor e Detentor dos Direitos Autorais: Marcel Aparecido de Andrade
Projeto Oficial: ProjectWizard IDE
Copyright (c) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.
1. Concessão Geral de Uso

O detentor dos direitos autorais concede a qualquer pessoa ou entidade uma licença mundial, não exclusiva, perpétua, isenta de royalties e gratuita para:

    Usar, compilar e executar o software para qualquer finalidade;
    Estudar o código-fonte e entender seu funcionamento;
    Modificar, adaptar, refatorar e traduzir o código-fonte;
    Distribuir cópias do software original ou de suas versões modificadas.

A presente concessão está condicionada ao cumprimento integral e contínuo de todas as cláusulas deste termo e do arranjo multi-licença do projeto.
2. Atribuição Obrigatória e Preservação de Direitos

Toda e qualquer redistribuição do software, com ou sem modificações, em formato binário ou código-fonte, deve obrigatoriamente incluir e preservar:

    O aviso de copyright original intacto: Copyright (c) 2026 Marcel Aparecido de Andrade;
    A referência explícita ao nome da IDE e do projeto: ProjectWizard;
    O texto integral deste arquivo de licença e do termo de licenciamento conjunto do projeto;
    O cabeçalho SPDX padrão no topo de todos os arquivos de código-fonte (.java).

3. Proteção e Copyleft do Núcleo (Core)

Qualquer alteração, melhoria, correção de bugs ou adição realizada no núcleo (Core) do ProjectWizard deve obrigatoriamente permanecer pública e ser disponibilizada em formato de código-fonte para os destinatários ou usuários da versão modificada.
Esta cláusula tem por objetivo impedir o fechamento proprietário do motor principal da IDE.
4. Plugins, Extensões e Módulos Externos

Módulos, plugins, temas, drivers, geradores de código, integrações e extensões desenvolvidos para operar em conjunto com o ProjectWizard por meio de suas APIs públicas, interfaces ou barramentos de eventos são considerados obras separadas.
Seus autores têm total liberdade para distribuir e comercializar tais extensões sob qualquer licença de sua escolha, incluindo licenças abertas, permissivas ou estritamente proprietárias.
5. Uso Comercial e Serviços

É expressamente permitido o uso comercial do ProjectWizard e de suas variantes, incluindo:

    Venda de serviços de consultoria, suporte técnico e treinamento;
    Integração do software em infraestruturas corporativas e privadas;
    Venda de distribuições customizadas da IDE e pacotes comerciais.

A comercialização ou cobrança por serviços não transfere, em hipótese alguma, a titularidade do código-fonte original ou a propriedade intelectual de Marcel Aparecido de Andrade.
6. Cláusula de Uso em Nuvem, SaaS e Ambientes Remotos

Se o ProjectWizard (ou qualquer obra modificada baseada no seu núcleo) for executado como um serviço em nuvem, ambiente remoto, SaaS (Software as a Service) ou PaaS acessível por rede, o operador do serviço fica obrigado a disponibilizar o código-fonte completo das modificações feitas no núcleo a todos os usuários que interagirem remotamente com a aplicação.
7. Concessão e Proteção Defensiva de Patentes

Cada contribuidor concede aos usuários e distribuidores uma licença de patente irrevogável, não exclusiva e isenta de royalties para fazer, usar, vender ou distribuir suas contribuições dentro do escopo do ProjectWizard.
Se qualquer entidade iniciar um litígio de patentes contra o autor ou distribuidores do projeto alegando infração, todas as licenças de patentes concedidas sob esta licença a tal entidade serão automaticamente rescindidas.
8. Marcas e Identidade Visual

Esta licença não concede quaisquer direitos sobre as marcas registradas, logotipos, elementos gráficos, nomes de produtos ou identidade visual associados ao ProjectWizard.
É proibido utilizar o nome "ProjectWizard" para promover, endossar ou nomear produtos derivados sem a autorização prévia e expressa por escrito do detentor dos direitos autorais.
9. Relação com o Licenciamento Conjunto (Penta Co-Licensing)

Esta licença (PWL-1.0) opera de forma integrada e complementar ao modelo de Penta Co-Licensing do ecossistema ProjectWizard.
A observância desta licença não isenta o usuário do cumprimento simultâneo dos termos da Apache License v2.0, Eclipse Public License v2.0, Mozilla Public License v2.0 e GNU Affero General Public License v3.0 (AGPL-3.0-only) aplicados ao projeto. Em caso de conflito interpretativo entre as cláusulas de copyleft de rede, prevalecerá a interpretação que garanta maior proteção ao código-fonte público e à rede.
10. Licença das Contribuições

Salvo declaração expressa em contrário firmada pelo autor, toda contribuição enviada para o repositório oficial do ProjectWizard será automaticamente licenciada sob os termos da ProjectWizard License v1.0 (PWL-1.0) e do conjunto de co-licenciamento do projeto.
11. Rescisão

O descumprimento material de qualquer cláusula desta licença encerrará imediatamente e de forma automática todos os direitos e permissões concedidos ao infrator.
Os direitos poderão ser reestabelecidos se o infrator cessar a violação e corrigir a não conformidade no prazo de até 30 (trinta) dias após a notificação oficial do detentor dos direitos.
12. Isenção de Garantia e Limitação de Responsabilidade

O SOFTWARE É FORNECIDO "COMO ESTÁ" (AS IS), SEM GARANTIA DE QUALQUER TIPO, EXPRESSA OU IMPLÍCITA, INCLUINDO, MAS NÃO SE LIMITANDO, ÀS GARANTIAS DE COMERCIALIZAÇÃO, ADEQUAÇÃO A UM PROPÓSITO ESPECÍFICO E NÃO VIOLAÇÃO DE DIREITOS.
EM NENHUMA HIPÓTESE O AUTOR (MARCEL APARECIDO DE ANDRADE) OU OS CONTRIBUIDORES SERÃO RESPONSÁVEIS POR QUALQUER RECLAMAÇÃO, DANOS DIRETO, INDIRETO, INCIDENTAL, ESPECIAL OU CONSEQUENTIAL DECORRENTE DO USO, INABILIDADE DE USO OU OUTRAS OPERAÇÕES COM ESTE SOFTWARE.

===============================================================================
ProjectWizard IDE — Sustentando o Futuro do Desenvolvimento Java Moderno.


===============================================================================
TERMO DE OUTORGA DE LICENÇA CONJUNTA E OBRIGATÓRIA (HEXA CO-LICENSING)
===============================================================================

Projeto: ProjectWizard
Arquitetura: Poliglota / Multi-Linguagem
Detentor dos Direitos Autorais: Marcel Aparecido de Andrade
Identificador SPDX: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND GPL-3.0-only AND AGPL-3.0-only

Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.

Este software e seu código-fonte são distribuídos de forma conjunta e obrigatória
sob os termos de seis licenças de software livre e código aberto distintas.

Para qualquer tipo de uso, cópia, modificação, fusão, publicação, distribuição,
execução em nuvem (SaaS) ou sublicenciamento deste software, o usuário DEVE cumprir
cumulativamente e simultaneamente todas as obrigações, restrições e termos contidos
nas seguintes licenças:

1. BSD 3-CLAUSE LICENSE (BSD-3-Clause)
   Disponível em: [https://opensource.org/licenses/BSD-3-Clause](https://opensource.org/licenses/BSD-3-Clause)
   Foco: Permissão de uso, preservação de copyright e proteção do nome contra endossos.

2. APACHE LICENSE - v 2.0 (Apache-2.0)
   Disponível em: [https://www.apache.org/licenses/LICENSE-2.0](https://www.apache.org/licenses/LICENSE-2.0)
   Foco: Concessão expressa de patentes e segurança em contribuições comerciais.

3. ECLIPSE PUBLIC LICENSE - v 2.0 (EPL-2.0)
   Disponível em: [https://www.eclipse.org/legal/epl-2.0/](https://www.eclipse.org/legal/epl-2.0/)
   Foco: Proteção de arquivos centrais e suporte a ecossistema de plugins comerciais.

4. MOZILLA PUBLIC LICENSE - v 2.0 (MPL-2.0)
   Disponível em: [https://www.mozilla.org/en-US/MPL/2.0/](https://www.mozilla.org/en-US/MPL/2.0/)
   Foco: Proteção dos arquivos de código-fonte originais e modificações públicas.

5. GNU GENERAL PUBLIC LICENSE - v 3.0 (GPL-3.0-only)
   Disponível em: [https://www.gnu.org/licenses/gpl-3.0.html](https://www.gnu.org/licenses/gpl-3.0.html)
   Foco: Copyleft forte para redistribuição local e pacotes instaláveis.

6. GNU AFFERO GENERAL PUBLIC LICENSE - v 3.0 (AGPL-3.0-only)
   Disponível em: [https://www.gnu.org/licenses/agpl-3.0.html](https://www.gnu.org/licenses/agpl-3.0.html)
   Foco: Copyleft de rede/nuvem (SaaS) para o código-fonte modificado.

-------------------------------------------------------------------------------
CLÁUSULA DE COMPATIBILIDADE E COMBINAÇÃO (ESTRUTURA JURÍDICA)
-------------------------------------------------------------------------------

Nos termos da Seção 1.13 ("Secondary License") da Eclipse Public License v 2.0 e
da Seção 3.3 ("Larger Works") da Mozilla Public License v 2.0, o outorgante original
deste software, Marcel Aparecido de Andrade, declara formalmente a GNU General
Public License v 3.0 e a GNU Affero General Public License v 3.0 como "Licenças
Secundárias" e integralmente aplicáveis.

Adicionalmente, os termos de proteção de marcas, atribuição obrigatória e concessão
defensiva de patentes da Apache License v 2.0 e da BSD 3-Clause License são aplicados
de forma cumulativa e indissociável a todo o conjunto da obra.

Isso garante que:
a) O titular do copyright original (Marcel Aparecido de Andrade) retém os direitos de
   atribuição e a proteção contra o uso não autorizado de seu nome em derivados (BSD-3-Clause).
b) Os componentes do núcleo (Core) da IDE permaneçam com o código-fonte acessível
   caso sejam modificados e redistribuídos ou oferecidos via rede/SaaS (EPL-2.0, MPL-2.0, AGPL-3.0).
c) Módulos, extensões e plugins desenvolvidos por terceiros através das APIs públicas
   do ProjectWizard possam ser distribuídos sob qualquer modelo de licenciamento
   (inclusive comercial/proprietário).
d) O código permanece sob a proteção de arquivos da EPL-2.0, MPL-2.0, da BSD-3-Clause e
   da concessão de patentes da Apache-2.0.
e) Qualquer trabalho maior, derivado ou disponibilizado via rede/nuvem (SaaS/PaaS)
   herda de forma mandatória o copyleft estabelecido pela GPL-3.0-only e pela AGPL-3.0-only.

A falha em cumprir os termos de qualquer uma das seis licenças listadas acima
resultará na revogação automática e imediata de todos os direitos concedidos por este
termo, constituindo violação de direitos autorais (copyright) e quebra de contratos
de licença de software.

===============================================================================
```

---

### ☕ Cabeçalho Padrão para Arquivos de Código Java

Copie e cole este bloco no topo de **todos** os novos arquivos `.java` criados no projeto **ProjectWizard**:

```java
/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade.
 * ProjectWizard IDE - Ultimate Toolkit
 *
 * SPDX-License-Identifier: BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND GPL-3.0-only AND AGPL-3.0-only
 *
 * Este código-fonte é sujeito aos termos conjuntos da BSD 3-Clause License,
 * da Apache License v. 2.0, da Eclipse Public License v. 2.0, da Mozilla Public
 * License v. 2.0, da GNU General Public License v. 3.0 e da GNU Affero General
 * Public License v. 3.0.
 * A conformidade com todas as seis licenças é obrigatória para uso e distribuição.
 */
```

