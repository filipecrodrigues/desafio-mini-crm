# Mini CRM

Este projeto é um desafio técnico de implementação de um Mini CRM utilizando Spring Boot e 
Spring Data JPA. O objetivo é praticar conceitos de REST API, persistência de dados e boas práticas de modelagem.

## Tecnologias Utilizadas

-   Java 17+
-   Spring Boot
-   Spring Web
-   Spring Data JPA
-   Banco de Dados H2
-   Swagger / Springdoc OpenAPI


## Estrutura do Projeto

    src/main/java/io/github/filipecrodrigues
        controller/
        entity/
        repository/

    src/main/resources/
        application.properties

### Modelagem

## Cliente
    id (Long) 
    nome (String) 
    email (String)


## Contato
    
    id (Long) tipo (ex.: "telefone", "email") 
    valor (String) 
    cliente (Cliente relacionado) 
    Relacionamento: 1 Cliente → N Contatos (OneToMany / ManyToOne) 

### Endpoints obrigatórios

## Cliente

    POST /clientes → cria um cliente (sem contatos) → 201 Created 
    GET /clientes → lista todos os clientes (com contatos, se mapeado)

## Contato

    POST /contatos?clienteId={id} ou 
    POST /clientes/{id}/contatos → cria contato vinculado a cliente → 404 Not Found se cliente não existir 
    GET /clientes/{id}/contatos → lista os contatos de um cliente → 404 Not Found se não existir

### Regras de resposta
    201 Created → criação bem-sucedida 
    200 OK → buscas com sucesso 404 Not Found → recurso não encontrado 
    400 Bad Request → requisição inválida


## Como Executar o Projeto

    mvn spring-boot:run

A aplicação iniciará em:

    http://localhost:8080

### Acessar Swagger:

    http://localhost:8080/swagger-ui.html

## Melhorias Futuras

-   Implementação de testes automatizados
-   Autenticação e autorização
-   Dockerização do projeto

