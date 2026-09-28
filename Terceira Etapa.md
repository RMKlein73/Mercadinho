# 🛒 Sistema Mercadinho

Nesta etapa, foram adicionados **testes unitários** e uma **pipeline de integração contínua** ao sistema.

## 🧪 Testes Unitários

Foram utilizados **JUnit 5** e **Maven** para testar as principais funcionalidades do sistema.

Os testes verificam:

* Cadastro e dados de clientes;
* Dados e alterações de produtos;
* Login e logout de usuários;
* Autenticação de clientes e administradores;
* Compras e controle de estoque;
* Validação de quantidades.

Os testes podem ser executados com:

```bash
mvn test
```

## ⚙️ Pipeline

Foi criada uma pipeline utilizando **GitHub Actions**, responsável por executar os testes automaticamente quando novas alterações são enviadas ao repositório.

A pipeline:

* Configura o Java 21;
* Executa os testes com Maven;
* Verifica se o projeto está funcionando corretamente.

Caso algum teste falhe, a pipeline informa o erro.

## 🛠️ Ferramentas

**JUnit 5** — Testes unitários

**Maven** — Gerenciamento e execução dos testes

**GitHub Actions** — Automação da pipeline

**Java 21** — Desenvolvimento do sistema

## 👨‍💻 Autor

**Rafael Klein**
