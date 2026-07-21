# LICENSE

## ⚖️ Licenciamento Conjunto Obrigatório (Penta Co-Licensing)

Este projeto adota uma abordagem de licenciamento robusta e inovadora para garantir a máxima proteção do código-fonte, incentivar a colaboração industrial, simplificar a concessão de patentes e impedir o fechamento do ecossistema em ambientes de nuvem.

Para utilizar, modificar ou distribuir este software (no todo ou em parte), você **DEVE cumprir os termos e obrigações de cinco licenças simultaneamente**:

1. **Apache License v2.0 (Apache-2.0)** - Fornece uma licença permissiva focada em contribuições comerciais seguras, incluindo uma concessão clara e expressa de direitos de patente dos contribuidores para os usuários.
2. **Eclipse Public License v2.0 (EPL-2.0)** - Garante a proteção dos arquivos centrais e facilita a criação de um ecossistema de plugins comerciais/proprietários em arquivos separados.
3. **Mozilla Public License v2.0 (MPL-2.0)** - Protege os arquivos de código-fonte originais, exigindo que qualquer modificação neles permaneça pública.
4. **GNU Affero General Public License v3.0 (AGPL-3.0-only)** - Atua como *Licença Secundária e Combinada*, garantindo o "copyleft de rede" (se a IDE for modificada e executada na nuvem como serviço/SaaS, o código modificado deve ser liberado).
5. **ProjectWizard License v1.0 (PWL-1.0)** - Licença autoral proprietária aberta criada especificamente para o ecossistema ProjectWizard por **Marcel Aparecido de Andrade**, permitindo uso pessoal, educacional e comercial, preservando o copyright original do autor, a proteção do núcleo da IDE, a identidade da marca ProjectWizard e a obrigação de manter acessível o código-fonte das modificações do núcleo distribuídas aos destinatários.

---

### 📦 Especificidades do Ecossistema Java

Como o **ProjectWizard** é desenvolvido em **Java**, as seguintes regras de conformidade se aplicam à estrutura do projeto:

* **Arquivos de Código (`.java`):** Todo arquivo criado no projeto deve conter o cabeçalho SPDX padrão nas primeiras linhas para fins de auditoria automática.
* **Pacotes (`package`):** A separação lógica por pacotes Java é utilizada para delimitar o que faz parte do núcleo do ProjectWizard (sujeito ao co-licenciamento estrito) e o que são extensões ou drivers externos através de APIs.
* **Compilação e Artefatos (`.jar` / `.war`):** A distribuição do binário compilado não isenta o distribuidor de fornecer o código-fonte correspondente sob os termos da AGPL-3.0, MPL-2.0 e EPL-2.0 caso o software seja modificado, bem como a devida atribuição exigida pela Apache-2.0.

---

### 🔍 Identificadores SPDX
Nos termos da Seção 1.13 da EPL 2.0, da Seção 3.3 da MPL 2.0, dos termos de atribuição e patentes da Apache 2.0 e das cláusulas complementares da ProjectWizard License v1.0, a conformidade é rastreada através da seguinte tag padrão de mercado:

```text
SPDX-License-Identifier: Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND AGPL-3.0-only AND LicenseRef-PWL-1.0
```

---

## 📄 Termo Integral de Outorga Conjunta

```text
===============================================================================
TERMO DE OUTORGA DE LICENÇA CONJUNTA E OBRIGATÓRIA (PENTA CO-LICENSING)
===============================================================================

Projeto: ProjectWizard
Linguagem Principal: Java
Detentor dos Direitos Autorais: Marcel Aparecido de Andrade

Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.

Este software e seu código-fonte são distribuídos de forma conjunta e obrigatória
sob os termos de cinco licenças de software livre, código aberto e licença autoral
proprietária aberta distintas.
Para qualquer tipo de uso, cópia, modificação, fusão, publicação, distribuição ou
sublicenciamento deste software, o usuário DEVE cumprir cumulativamente e
simultaneamente todas as obrigações, restrições e termos contidos nas seguintes licenças:

1. APACHE LICENSE - v 2.0 (Apache-2.0)
   Disponível em: https://www.apache.org/licenses/LICENSE-2.0

2. ECLIPSE PUBLIC LICENSE - v 2.0 (EPL-2.0)
   Disponível em: https://eclipse.org

3. MOZILLA PUBLIC LICENSE - v 2.0 (MPL-2.0)
   Disponível em: https://mozilla.org

4. GNU AFFERO GENERAL PUBLIC LICENSE - v 3.0 (AGPL-3.0-only)
   Disponível em: https://gnu.org

5. PROJECTWIZARD LICENSE - v 1.0 (PWL-1.0)
   Licença autoral criada por Marcel Aparecido de Andrade para o ecossistema
   ProjectWizard, preservando o copyright original, a proteção do núcleo da IDE,
   a identidade da marca ProjectWizard e as regras específicas de uso comercial,
   plugins e distribuição de modificações do núcleo.

-------------------------------------------------------------------------------
CLÁUSULA DE COMPATIBILIDADE E COMBINAÇÃO (ESTRUTURA JURÍDICA)
-------------------------------------------------------------------------------

Nos termos da Seção 1.13 ("Secondary License") da Eclipse Public License v 2.0 e
da Seção 3.3 ("Larger Works") da Mozilla Public License v 2.0, o outorgante original
deste software, Marcel Aparecido de Andrade, declara formalmente a GNU Affero General
Public License v 3.0 como "Licença Secundária" e aplicável.

Adicionalmente, os termos de proteção de marcas, atribuição e concessão de patentes
da Apache License v 2.0 são aplicados de forma cumulativa e indissociável a todo o
conjunto da obra.

Os termos complementares da ProjectWizard License v1.0 (PWL-1.0) são aplicados
de forma cumulativa para garantir a preservação do copyright original de Marcel
Aparecido de Andrade, a proteção da identidade do ProjectWizard e as regras
específicas do ecossistema da IDE.

Isso garante que:
a) O código permanece sob a proteção de arquivos da EPL-2.0, MPL-2.0, os termos de
   patentes da Apache-2.0 e as cláusulas autorais complementares da PWL-1.0.
b) Qualquer trabalho maior, derivado ou disponibilizado via rede/nuvem herda de
   forma mandatória o copyleft de rede estabelecido pela AGPL-3.0-only.

A falha em cumprir os termos de qualquer uma das cinco licenças listadas acima
resultará na revogação automática e imediata de todos os direitos concedidos por este
termo, constituindo violação de direitos autorais (copyright) e quebra de acordos de licença.

===============================================================================
```

---

### ☕ Cabeçalho Padrão para Arquivos de Código Java

Copie e cole este bloco no topo de **todos** os novos arquivos `.java` criados no projeto **ProjectWizard**:

```java
/*
 * Copyright (C) 2026 Marcel Aparecido de Andrade.
 * ProjectWizard - Java IDE
 *
 * SPDX-License-Identifier: Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND AGPL-3.0-only AND LicenseRef-PWL-1.0
 *
 * Este código-fonte é sujeito aos termos conjuntos da Apache License v. 2.0,
 * da Eclipse Public License v. 2.0, da Mozilla Public License v. 2.0, da
 * GNU Affero General Public License v. 3.0 e da ProjectWizard License v. 1.0.
 * A conformidade com todas as cinco licenças é obrigatória para uso e distribuição.
 */
```


# ProjectWizard License v1.0 (PWL-1.0)

**Identificador SPDX:** `LicenseRef-PWL-1.0`  
**Autor e Detentor dos Direitos Autorais:** Marcel Aparecido de Andrade  
**Projeto Oficial:** ProjectWizard IDE  
**Copyright (c) 2026 Marcel Aparecido de Andrade.** Todos os direitos reservados.

---

### 1. Concessão Geral de Uso
O detentor dos direitos autorais concede a qualquer pessoa ou entidade uma licença mundial, não exclusiva, perpétua, isenta de royalties e gratuita para:
* Usar, compilar e executar o software para qualquer finalidade;
* Estudar o código-fonte e entender seu funcionamento;
* Modificar, adaptar, refatorar e traduzir o código-fonte;
* Distribuir cópias do software original ou de suas versões modificadas.

A presente concessão está condicionada ao cumprimento integral e contínuo de todas as cláusulas deste termo e do arranjo multi-licença do projeto.

---

### 2. Atribuição Obrigatória e Preservação de Direitos
Toda e qualquer redistribuição do software, com ou sem modificações, em formato binário ou código-fonte, deve obrigatoriamente incluir e preservar:
1. O aviso de copyright original intacto: `Copyright (c) 2026 Marcel Aparecido de Andrade`;
2. A referência explícita ao nome da IDE e do projeto: **ProjectWizard**;
3. O texto integral deste arquivo de licença e do termo de licenciamento conjunto do projeto;
4. O cabeçalho SPDX padrão no topo de todos os arquivos de código-fonte (`.java`).

---

### 3. Proteção e Copyleft do Núcleo (Core)
Qualquer alteração, melhoria, correção de bugs ou adição realizada no **núcleo (Core) do ProjectWizard** deve obrigatoriamente permanecer pública e ser disponibilizada em formato de código-fonte para os destinatários ou usuários da versão modificada.  
Esta cláusula tem por objetivo impedir o fechamento proprietário do motor principal da IDE.

---

### 4. Plugins, Extensões e Módulos Externos
Módulos, plugins, temas, drivers, geradores de código, integrações e extensões desenvolvidos para operar em conjunto com o ProjectWizard por meio de suas **APIs públicas**, interfaces ou barramentos de eventos são considerados obras separadas.  
Seus autores têm total liberdade para distribuir e comercializar tais extensões sob qualquer licença de sua escolha, incluindo licenças abertas, permissivas ou estritamente proprietárias.

---

### 5. Uso Comercial e Serviços
É expressamente permitido o uso comercial do ProjectWizard e de suas variantes, incluindo:
* Venda de serviços de consultoria, suporte técnico e treinamento;
* Integração do software em infraestruturas corporativas e privadas;
* Venda de distribuições customizadas da IDE e pacotes comerciais.

A comercialização ou cobrança por serviços não transfere, em hipótese alguma, a titularidade do código-fonte original ou a propriedade intelectual de Marcel Aparecido de Andrade.

---

### 6. Cláusula de Uso em Nuvem, SaaS e Ambientes Remotos
Se o ProjectWizard (ou qualquer obra modificada baseada no seu núcleo) for executado como um serviço em nuvem, ambiente remoto, SaaS (*Software as a Service*) ou PaaS acessível por rede, o operador do serviço fica obrigado a disponibilizar o código-fonte completo das modificações feitas no núcleo a todos os usuários que interagirem remotamente com a aplicação.

---

### 7. Concessão e Proteção Defensiva de Patentes
Cada contribuidor concede aos usuários e distribuidores uma licença de patente irrevogável, não exclusiva e isenta de royalties para fazer, usar, vender ou distribuir suas contribuições dentro do escopo do ProjectWizard.  
Se qualquer entidade iniciar um litígio de patentes contra o autor ou distribuidores do projeto alegando infração, todas as licenças de patentes concedidas sob esta licença a tal entidade serão automaticamente rescindidas.

---

### 8. Marcas e Identidade Visual
Esta licença **não concede** quaisquer direitos sobre as marcas registradas, logotipos, elementos gráficos, nomes de produtos ou identidade visual associados ao **ProjectWizard**.  
É proibido utilizar o nome "ProjectWizard" para promover, endossar ou nomear produtos derivados sem a autorização prévia e expressa por escrito do detentor dos direitos autorais.

---

### 9. Relação com o Licenciamento Conjunto (Penta Co-Licensing)
Esta licença (PWL-1.0) opera de forma integrada e complementar ao modelo de **Penta Co-Licensing** do ecossistema ProjectWizard.  
A observância desta licença não isenta o usuário do cumprimento simultâneo dos termos da **Apache License v2.0**, **Eclipse Public License v2.0**, **Mozilla Public License v2.0** e **GNU Affero General Public License v3.0 (AGPL-3.0-only)** aplicados ao projeto. Em caso de conflito interpretativo entre as cláusulas de copyleft de rede, prevalecerá a interpretação que garanta maior proteção ao código-fonte público e à rede.

---

### 10. Licença das Contribuições
Salvo declaração expressa em contrário firmada pelo autor, toda contribuição enviada para o repositório oficial do ProjectWizard será automaticamente licenciada sob os termos da ProjectWizard License v1.0 (PWL-1.0) e do conjunto de co-licenciamento do projeto.

---

### 11. Rescisão
O descumprimento material de qualquer cláusula desta licença encerrará imediatamente e de forma automática todos os direitos e permissões concedidos ao infrator.  
Os direitos poderão ser reestabelecidos se o infrator cessar a violação e corrigir a não conformidade no prazo de até 30 (trinta) dias após a notificação oficial do detentor dos direitos.

---

### 12. Isenção de Garantia e Limitação de Responsabilidade
O SOFTWARE É FORNECIDO "COMO ESTÁ" (*AS IS*), SEM GARANTIA DE QUALQUER TIPO, EXPRESSA OU IMPLÍCITA, INCLUINDO, MAS NÃO SE LIMITANDO, ÀS GARANTIAS DE COMERCIALIZAÇÃO, ADEQUAÇÃO A UM PROPÓSITO ESPECÍFICO E NÃO VIOLAÇÃO DE DIREITOS.  
EM NENHUMA HIPÓTESE O AUTOR (MARCEL APARECIDO DE ANDRADE) OU OS CONTRIBUIDORES SERÃO RESPONSÁVEIS POR QUALQUER RECLAMAÇÃO, DANOS DIRETO, INDIRETO, INCIDENTAL, ESPECIAL OU CONSEQUENTIAL DECORRENTE DO USO, INABILIDADE DE USO OU OUTRAS OPERAÇÕES COM ESTE SOFTWARE.

===============================================================================  
**ProjectWizard IDE — Sustentando o Futuro do Desenvolvimento Java Moderno.**


```text
===============================================================================
TERMO DE OUTORGA DE LICENÇA CONJUNTA E OBRIGATÓRIA (PENTA CO-LICENSING)
===============================================================================

Projeto: ProjectWizard
Linguagem Principal: Java
Detentor dos Direitos Autorais: Marcel Aparecido de Andrade
Identificador SPDX: Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND AGPL-3.0-only AND LicenseRef-PWL-1.0

Copyright (C) 2026 Marcel Aparecido de Andrade. Todos os direitos reservados.

Este software e seu código-fonte são distribuídos de forma conjunta e obrigatória
sob os termos de cinco licenças de software livre, código aberto e licença autoral
proprietária aberta distintas.

Para qualquer tipo de uso, cópia, modificação, fusão, publicação, distribuição,
execução em nuvem (SaaS) ou sublicenciamento deste software, o usuário DEVE cumprir
cumulativamente e simultaneamente todas as obrigações, restrições e termos contidos
nas seguintes licenças:

1. APACHE LICENSE - v 2.0 (Apache-2.0)
   Disponível em: https://www.apache.org/licenses/LICENSE-2.0
   Foco: Concessão expressa de patentes e segurança em contribuições comerciais.

2. ECLIPSE PUBLIC LICENSE - v 2.0 (EPL-2.0)
   Disponível em: https://www.eclipse.org/legal/epl-2.0/
   Foco: Proteção de arquivos centrais e suporte a ecossistema de plugins comerciais.

3. MOZILLA PUBLIC LICENSE - v 2.0 (MPL-2.0)
   Disponível em: https://www.mozilla.org/en-US/MPL/2.0/
   Foco: Proteção dos arquivos de código-fonte originais e modificações públicas.

4. GNU AFFERO GENERAL PUBLIC LICENSE - v 3.0 (AGPL-3.0-only)
   Disponível em: https://www.gnu.org/licenses/agpl-3.0.html
   Foco: Copyleft de rede/nuvem (SaaS) para o código-fonte modificado.

5. PROJECTWIZARD LICENSE - v 1.0 (PWL-1.0)
   Licença autoral proprietária aberta criada por Marcel Aparecido de Andrade para o
   ecossistema ProjectWizard IDE. Preserva o copyright original, a identidade visual
   e marca ProjectWizard, a obrigação de manter aberto o código-fonte do núcleo (Core),
   e os direitos de desenvolvimento e comercialização de plugins e extensões.

-------------------------------------------------------------------------------
CLÁUSULA DE COMPATIBILIDADE E COMBINAÇÃO (ESTRUTURA JURÍDICA)
-------------------------------------------------------------------------------

Nos termos da Seção 1.13 ("Secondary License") da Eclipse Public License v 2.0 e
da Seção 3.3 ("Larger Works") da Mozilla Public License v 2.0, o outorgante original
deste software, Marcel Aparecido de Andrade, declara formalmente a GNU Affero General
Public License v 3.0 como "Licença Secundária" e integralmente aplicável.

Adicionalmente, os termos de proteção de marcas, atribuição obrigatória e concessão
defensiva de patentes da Apache License v 2.0 e da ProjectWizard License v 1.0 são
aplicados de forma cumulativa e indissociável a todo o conjunto da obra.

Os termos específicos da ProjectWizard License v 1.0 (PWL-1.0) garantem que:
a) O titular do copyright original (Marcel Aparecido de Andrade) retém os direitos de
   atribuição e a proteção exclusiva da marca e logotipos "ProjectWizard".
b) Os componentes do núcleo (Core) da IDE permaneçam com o código-fonte acessível
   caso sejam modificados e redistribuídos ou oferecidos via rede/SaaS.
c) Módulos, extensões e plugins desenvolvidos por terceiros através das APIs públicas
   do ProjectWizard possam ser distribuídos sob qualquer modelo de licenciamento
   (inclusive comercial/proprietário).

Isso garante que:
a) O código permanece sob a proteção de arquivos da EPL-2.0, MPL-2.0, da PWL-1.0 e
   da concessão de patentes da Apache-2.0.
b) Qualquer trabalho maior, derivado ou disponibilizado via rede/nuvem (SaaS/PaaS)
   herda de forma mandatória o copyleft de rede estabelecido pela AGPL-3.0-only.

A falha em cumprir os termos de qualquer uma das cinco licenças listadas acima
resultará na revogação automática e imediata de todos os direitos concedidos por este
termo, constituindo violação de direitos autorais (copyright) e quebra de contratos
de licença de software.

===============================================================================
```
