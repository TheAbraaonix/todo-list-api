<h1 align="center">
  TODO List
</h1>


API REST para gerenciar tarefas (CRUD).

## Tecnologias
 
- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- SpringDoc OpenAPI 3
- H2 Database
- JUnit 5 e Mockito

## Práticas adotadas

- SOLID, DRY, YAGNI, KISS
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3
- Testes unitários

## Como Executar

- Clonar repositório git:
```
git clone https://github.com/TheAbraaonix/todo-list-api
```
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/todolist-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [postman](https://www.postman.com/):

### Criar Tarefa:
- **Endpoint:** `/todos`
- **Método HTTP:** POST
- **Descrição**: Cria uma nova tarefa.

Parâmetros da consulta:
- Nenhum.

Corpo da requisição:
```
{
    "nome": "Tarefa 1",
    "descricao": "Tarefa 1",
    "realizada": "false",
    "prioridade": 1
}
```


Exemplo de resposta:
```
[
    {
        "id": 1,
        "nome": "Tarefa 1",
        "descricao": "Tarefa 1",
        "realizada": false,
        "prioridade": 1
    }
]
```

### Listar tarefas:
- **Endpoint:** `/todos`
- **Método HTTP:** GET
- **Descrição**: Lista todas as tarefas.

Parâmetros da consulta:
- Nenhum.

Corpo da requisição:
```
{}
```


Exemplo de resposta:
```
[
    {
        "id": 1,
        "nome": "Tarefa 1",
        "descricao": "Tarefa 1",
        "realizada": false,
        "prioridade": 1
    }
]
```

### Listar tarefa específica:
- **Endpoint:** `/todos/{id}`
- **Método HTTP:** GET
- **Descrição**: Lista uma tarefa específica.

Parâmetros da consulta:
- `id` (obrigatório)

Corpo da requisição:
```
{}
```


Exemplo de resposta:
```
[
    {
        "id": 1,
        "nome": "Tarefa 1",
        "descricao": "Tarefa 1",
        "realizada": false,
        "prioridade": 1
    }
]
```

### Editar tarefa:
- **Endpoint:** `/todos/{id}`
- **Método HTTP:** PUT
- **Descrição**: Edita uma tarefa específica.

Parâmetros da consulta:
- `id` (obrigatório)


Corpo da requisição:
```
{
    "nome": "Tarefa 1",
    "descricao": "Tarefa 1",
    "realizada": "true",
    "prioridade": 2
}
```

Exemplo de resposta:
```
[
    {
        "id": 1,
        "nome": "Tarefa 1",
        "descricao": "Tarefa 1",
        "realizada": true,
        "prioridade": 2
    }
]
```
### Deletar tarefa:
- **Endpoint:** `/todos/{id}`
- **Método HTTP:** DELETE
- **Descrição**: Deleta uma tarefa específica.

Parâmetros da consulta:
- `id` (obrigatório)

Corpo da requisição:
```
{}
```

Exemplo de resposta:
```

```