# 🛒 Sistema Mercadinho

Sistema desenvolvido para gerenciamento de produtos, clientes e compras de um mercadinho.

## 💻 Desenvolvimento

Nesta etapa, foi desenvolvido o sistema funcional utilizando **Java Swing**, com integração ao banco de dados **SQLite**.

Foram implementadas as telas de login, cadastro de clientes, gerenciamento de produtos e realização de compras, além do controle de acesso entre clientes e administradores.

### 👤 Clientes

* Cadastro de clientes;
* Login de clientes;
* Acesso à área do cliente;
* Visualização dos produtos disponíveis;
* Seleção de produtos;
* Realização de compras;
* Atualização automática do estoque após a compra.

### 🔐 Administradores

* Login de administrador;
* Acesso à área administrativa;
* Cadastro de produtos;
* Atualização de produtos;
* Exclusão de produtos;
* Visualização do estoque.

### 🗄️ Banco de Dados

O sistema utiliza **SQLite** para armazenar as informações de clientes, administradores e produtos.

Também foi implementada a conexão com o banco através de **JDBC**, além da inicialização automática das tabelas e dos produtos iniciais.

## 🛠️ Ferramentas

**Java** — Desenvolvimento do sistema

**Java Swing** — Desenvolvimento das interfaces gráficas

**SQLite** — Banco de dados

**JDBC** — Comunicação entre o sistema e o banco de dados

**Maven** — Gerenciamento do projeto e dependências

**NetBeans** — Ambiente de desenvolvimento

## 📁 Estrutura

O projeto foi organizado utilizando as camadas `model` e `view`.

**Model**

* Classes responsáveis pelos dados;
* DAOs responsáveis pelas operações no banco;
* Conexão com o SQLite;
* Controle de sessão.

**View**

* Telas de login;
* Cadastro de clientes;
* Área do cliente;
* Área administrativa;
* Cadastro e gerenciamento de produtos;
* Visualização e compra de produtos.

## 👨‍💻 Autor

**Rafael Klein**
