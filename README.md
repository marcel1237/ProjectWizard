# README.md

# Project Wizard 🧙‍♂️

> Desktop Project Generator desenvolvido em Java 21, JavaFX e AtlantaFX.

O **Project Wizard** é uma aplicação desktop cujo objetivo é facilitar a criação de novos projetos através de uma interface gráfica moderna, inspirada em IDEs como IntelliJ IDEA, Rider e Visual Studio.

O projeto está sendo desenvolvido de forma incremental, utilizando pequenas etapas ("one-shot scripts") para permitir testes e validação contínua.

---

## 🎯 Objetivos

O Project Wizard pretende se tornar uma plataforma para geração de projetos, inicialmente para o ecossistema Java e posteriormente para outras tecnologias.

Tecnologias planejadas:

- Java
- JavaFX
- Spring Boot
- Maven
- Gradle
- Jakarta EE
- Quarkus
- Micronaut
- Kotlin
- Android
- .NET (planejado)

---

## 💻 Tecnologias Utilizadas

- Java 21 (LTS)
- JavaFX 21
- Maven
- AtlantaFX 2.1.0
- RichTextFX 0.11.7
- Ikonli (Material2 & FontAwesome5)
- SQLite (Backups Industriais)
- CSS
- Git

---

## 🎨 Interface

Atualmente o projeto possui:

- Tema AtlantaFX
- Primer Dark
- Menu Principal
- Toolbar
- Sidebar
- Workspace
- Dashboard
- Barra de Status
- Sistema de Navegação
- Wizard de Novo Projeto (em desenvolvimento)

---

## 📁 Estrutura do Projeto


```

src/main/java/com/projectwizard

core/
ApplicationContext
Constants
Version

core/navigation/
NavigationController
NavigationTarget

service/
DialogService
FileSystemService
ProjectService
SettingsService

theme/
ThemeManager
ThemeType

view/
MainWindow
WorkspacePane
NavigationPane
StatusBar
MainToolBar

view/dashboard/
view/newproject/
view/templates/
view/git/
view/github/
view/settings/
view/about/

```

---

## ⚙️ Recursos Atuais

### 📊 Dashboard
Tela inicial da aplicação.

---

### 🗂️ Sidebar
Permite acessar:

- Dashboard
- New Project
- Open Project
- Templates
- Git
- GitHub
- Settings
- About

---

### 🖥️ Workspace
Área principal onde as telas são carregadas dinamicamente.

---

### 🎨 Sistema de Temas
O projeto utiliza AtlantaFX.

Tema atual:
- Primer Dark

Temas planejados:
- Dracula
- Primer Light
- Nord Dark
- Nord Light
- Cupertino

---

## 🧙‍♂️ Wizard de Projetos

Em desenvolvimento.

Fluxo planejado:


```

Project

↓

Language

↓

Framework

↓

Dependencies

↓

Git

↓

Summary

```

### ☁️ Cloud Wizard

O primeiro subprojeto oficial de wizard está em [`cloudwizard/`](cloudwizard/). Ele fornece anotações e regras proprietárias para descrever capacidades cloud uma única vez e resolvê-las para nove providers oficiais: AWS, Google Cloud, Microsoft Azure, Oracle Cloud, IBM Cloud, Alibaba Cloud, Tencent Cloud, Huawei Cloud e Yandex Cloud.

As dezesseis capabilities e seus 144 bindings estão na [matriz oficial completa](cloudwizard/README.md). Os nove providers integram enum, SPI, rules engine, CLI, gerador e testes automatizados.

Anotações especializadas disponíveis no padrão:

- `@CloudStorage`;
- `@CloudFunction`;
- `@CloudDatabase`;
- `@CloudNoSqlDatabase`;
- `@CloudQueue`;
- `@CloudSecret`;
- `@CloudObservability`;
- `@CloudContainer`.
- `@CloudIdentity`.
- `@CloudApiGateway`.
- `@CloudCache`.
- `@CloudDns`.
- `@CloudLoadBalancer`.
- `@CloudContainerRegistry`.
- `@CloudKeyManagement`.
- `@CloudCertificate`.

O núcleo é independente dos SDKs proprietários e aceita novos provedores através de uma SPI Java.

Para pesquisa e planejamento de extensões, o subprojeto inclui um [asset com 88 nomes de providers](cloudwizard/assets/providers/README.md). Somente os nove providers oficiais possuem cobertura atribuída; todos os demais são registros `not-assessed`, sem avaliação ou referência ativa.

Uma [matriz operacional complementar](cloudwizard/assets/providers/PROVIDERS_CLOUD_PUBLICAS_ASIA.md) detalha Alibaba Cloud, Tencent Cloud, Huawei Cloud e Yandex Cloud, todos integrantes do núcleo oficial `16/16`.

---

## 📦 Templates Planejados

### ☕ Java
- Java Console
- Java Library

### 🦊 JavaFX
- Desktop Application
- MVVM
- MVP

### 🍃 Spring Boot
- REST API
- MVC
- CRUD
- Clean Architecture

### 🛠️ Maven
- Empty Project

### 🐘 Gradle
- Empty Project

---

## 🐙 Git

Planejado:

- `git init`
- `git clone`
- `git status`
- `git commit`
- `git push`

---

## 🐈 GitHub

Planejado:

- Login
- Criar Repositório
- Publicar Projeto
- Clonar
- Abrir Pull Requests

---

## 📐 Arquitetura

O projeto está migrando para uma arquitetura organizada por módulos.


```

core/

theme/

service/

view/

generator/

template/

wizard/

git/

github/

```

---

## 🗺️ Roadmap

### 🏁 Fase 1
- Interface
- Tema
- Sidebar
- Workspace

✔ **Concluído**

---

### 🚀 Fase 2
- Navegação
- Wizard
- Templates

⏳ **Em desenvolvimento**

---

### ⚙️ Fase 3
- Engine de geração

📋 **Planejado**

---

### 🌿 Fase 4
- Integração Git

📋 **Planejado**

---

### ☁️ Fase 5
- Integração GitHub

📋 **Planejado**

---

### 🛒 Fase 6
- Marketplace de Templates

📋 **Planejado**

---

## 📋 Requisitos

* Java 21 (LTS)
* Maven 3.9+
* Git
* Shell Bash (Linux)

---

## ⚡ Executando

```bash
mvn clean javafx:run

```

---

## 📦 Build e Empacotamento

O primeiro wizard oficial está em `cloudwizard/`, com nove providers oficiais, dezesseis capacidades portáveis, dezesseis anotações especializadas e doze templates de arquitetura. **O Cloud Wizard é um produto proprietário, comercial e pago, licenciado separadamente do ProjectWizard aberto.** Consulte `cloudwizard/LICENSE.md`, `cloudwizard/MODELO_NEGOCIOS.md`, `DOCUMENTACAO_PROJETO.html` e `RELATORIO_ALTERACOES.md`.

```bash
./build-all.sh
```

Esse comando valida o ProjectWizard e o Cloud Wizard. Cada `mvn verify` também atualiza a respectiva documentação Javadoc em `javadoc/`.

```bash
mvn clean package

```

Para validar e empacotar o ProjectWizard e todos os wizards oficiais:

```bash
./build-all.sh
```

---

## 🧹 Limpeza do Projeto

```bash
mvn clean

```

---

## 📈 Estado Atual

O projeto encontra-se em desenvolvimento ativo.

As funcionalidades são implementadas incrementalmente através de scripts "one-shot", permitindo evolução gradual da arquitetura e testes frequentes.

---

## ⚖️ Licença

Este projeto adota o modelo **Hexa Co-Licensing** (Licenciamento Conjunto de Seis Camadas) para garantir proteção industrial, copyleft de rede e liberdade de extensões:

**SPDX-License-Identifier:** `BSD-3-Clause AND Apache-2.0 AND EPL-2.0 AND MPL-2.0 AND UPL-1.0 AND AGPL-3.0-only AND LicenseRef-PWL-3.0`

### 🛡️ Uso Final e Distribuições Oficiais
Para usuários regulares, aplicam-se os termos permissivos: **PWL-3.0, Apache-2.0, EPL-2.0, MPL-2.0 e UPL-1.0**.

### 🛠️ Modificação e Obras Derivadas
Qualquer modificação ou redistribuição ativa obrigatoriamente as cláusulas de atribuição da **BSD-3-Clause** e o copyleft de rede da **AGPL-3.0**.

Para detalhes completos, consulte o arquivo [LICENSE.md](LICENSE.md).

Essa licença cobre o ProjectWizard principal. O subprojeto `cloudwizard/` é proprietário, comercial e pago, sob [licença independente](cloudwizard/LICENSE.md); os termos abertos da raiz não concedem direitos sobre ele.
