#!/usr/bin/env bash

set -e

echo "=========================================="
echo " ProjectWizard - Penta License Update"
echo "=========================================="

FILE="LICENSE.md"

# Backup
cp "$FILE" "$FILE.bak20_1"

# Sobrescreve usando CAT
cat > "$FILE" <<'EOF'
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
EOF

echo
echo "=========================================="
echo " LICENSE.md atualizado com PWL-1.0"
echo "=========================================="
echo
echo "Backup: $FILE.bak20_1"
echo
echo "Agora o projeto possui Penta Co-Licensing:"
echo "  - Apache-2.0"
echo "  - EPL-2.0"
echo "  - MPL-2.0"
echo "  - AGPL-3.0-only"
echo "  - PWL-1.0 (sua licença autoral)"
echo
echo "Próximo passo:"
echo "  git add LICENSE.md"
echo "  git commit -m 'feat: add ProjectWizard License as fifth license (PWL-1.0)'"
