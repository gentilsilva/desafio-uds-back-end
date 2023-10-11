<details>
<summary> Objetivo e critérios do desafio </summary>

# Teste para candidatos à vaga de backend

> [![Logo UDS](https://raw.githubusercontent.com/uds-tecnologia/teste-programador-back-end/master/logo%20uds%20azul%20escuro.png)](https://www.uds.com.br)
>
> Esse teste é público. Candidatos para o teste devem implementar a aplicação solicitada e criar uma issue com um link para um repositório com a solução do mesmo.

## Objetivo do desafio
Criar uma api REST, que implemente os sequintes casos de uso:

### Produtos:
> **CRUD da entidade Produto:**
> - Id: UUID
> - Código: String
> - Nome: String
> - Preço Unitário: Double

> **Critérios de aceitação:**
> - Todos os atributos são obrigatórios.
> - ID, Código e Nome não podem se repetir.
> - Preço é monetário e deve ser maior que zero.
> - Deve ser possível pesquisar os produtos por qualquer atributo, exceto pelo id.

### Pessoa:
> **CRUD da entidade Pessoa:**
> - Id: UUID
> - Nome: String
> - CPF: String
> - Data Nascimento: Date

> **Critérios de aceitação:**
> - Todos os atributos são obrigatórios.
> - ID e Nome não podem se repetir.
> - CPF deve ser válido.
> - Deve ser possível pesquisar as pessoas por qualquer atributo, exceto pelo id.

### Pedido de Venda:
> **Permite incluir ou excluir um pedido de venda, sabendo que o Pedido:**
> - Id: UUID
> - Cliente: Pessoa
> - Número: Integer/Autoincremento
> - Emissão: Date,
> - Total: Double

> **Um pedido por ter um ou mais ItemPedido:**
> - Id: UUID
> - Produto: Produto
> - Quantidade: Double
> - Preço Unitário: Double - Recuperado do produto
> - Percentual de Desconto: Double
> - Total: Double

> **Critérios de aceitação:**
> - Todos os atributos são obrigatórios.
> - ID e Número não podem se repetir.
> - Todos os valores numéricos devem ser maior que zero, exceto Percentual de Desconto que pode ser maior ou igual a zero.
> - Preço e Totais são monetários.
> - Deve ser possível pesquisar os pedidos por qualquer atributo, exceto pelo id.

Não existe cadastro de usuários e/ou autenticação.

## Requisitos:
- A aplicação deve comportar-se como uma REST FULL Api.
- Backend desenvolvido em Laravel, em sua versão LTS.
- Processo de desenvolvimento versionado via Git em algum repositório público, de preferência no github.
- Relatório de cobertura dos testes unitários de backend.
- Readme que explique como rodar o projeto, como gerar o relatório de cobertura e como executar quaisquer scripts necessários.
- A aplicação deve possuir um script que popula o banco inicialmente com produtos, pessoas e pedidos fictícios para demonstração.

## Critérios de avaliação:
- Automatização de tarefas.
- Modelagem do banco de dados e das migrations.
- Organização do código: desacoplamento e legibilidade contam.
- Flexibilidade do sistema para adição/remoção de funcionalidades.

## Como vamos avaliar:
- Vamos rodar os testes, ler os testes, ver os relatórios dos testes, a gente gosta muito de testes.
- Vamos ler o código, e validar todos os conceitos de Clean Code, e Object Calisthenics.
- Vamos validar todas as classes de acordo com SOLID e KISS.

## O que nós gostamos:
- Boas práticas em Git e GitFlow.
- Hierarquia clara entre componentes.
- Arquitetura que favorece a escalabilidade do sistema.
- Testes claros e objetivos construidos sobre Baby Steps.
- Ambientes de desenvolvimento em Docker, utilizando compose.
- Códigos escritos para humanos.

</details>

<details>
<summary> Informações sobre o desenvolvimento </summary>

# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.4/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Flyway Migration](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#howto.data-initialization.migration-tool.flyway)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#using.devtools)
* [Validation](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#io.validation)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

</details>
