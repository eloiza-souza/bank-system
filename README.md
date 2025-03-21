# Sistema Bancário RESTful com Spring Boot

## Descrição do programa
Este projeto é uma API RESTful para um sistema bancário, desenvolvida com **Java 17**, **Spring Boot**, **PostgreSQL** e **JPA**. A API permite realizar operações bancárias como cadastro de contas, depósitos, transferências, saques e consulta de saldo. 
O objetivo de seu desenvolvimento foi implementa conceitos de **programação orientada a eventos**, **threads** e **concorrência**.

### Programação orientada a eventos
O projeto utiliza o **Spring Events** para implementar a programação orientada a eventos. Por exemplo:
- Quando uma conta é criada, um evento `AccountCreatedEvent` é disparado.
- Listeners como `AccountEventListener` reagem a esses eventos para realizar ações secundárias, como envio de notificações.

[Repositório com mais explicações e exemplos sobre Programação Orientada a Eventos](https://github.com/eloiza-souza/POE)

[Repositório com exemplo de kafka](https://github.com/eloiza-souza/kafka)


### Threads e concorrência
- O processamento de eventos é realizado de forma **assíncrona** utilizando a anotação `@Async` e um executor configurado no projeto.
- Operações críticas, como transferências e saques, utilizam transações (`@Transactional`) para garantir consistência e evitar problemas de concorrência.
- O pool de threads é configurado para gerenciar tarefas assíncronas, garantindo eficiência e escalabilidade.

[Repositório com mais explicações e exemplos de uso de threads](https://github.com/eloiza-souza/threads)

[Repositório com mais explicações e exemplos sobre concorrência](https://github.com/eloiza-souza/concurrency)


### Funcionalidades principais
- **Cadastro de contas**: Criação de novas contas bancárias.
- **Depósitos**: Adição de saldo em uma conta existente.
- **Saques**: Retirada de saldo de uma conta, com validação de saldo suficiente.
- **Transferências**: Transferência de valores entre contas.
- **Consulta de saldo**: Verificação do saldo atual de uma conta.


## Instruções para executar o código

### Dependências necessárias
Certifique-se de ter as seguintes ferramentas instaladas:
- **Java 17** ou superior
- **Maven** para gerenciamento de dependências
- **PostgreSQL** como banco de dados relacional

### Passos para execução
1. **Clone o repositório**:
```bash
    git clone https://github.com/eloiza-souza/bank-system.git
    cd bank-system
```

2. **Configure o banco de dados**:
- Certifique-se de que o PostgreSQL está rodando na porta padrão (`5432`).
- Crie um banco de dados chamado `banking_system`.
- Atualize o arquivo `application.properties` com as credenciais do banco de dados:
```properties
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
```

3. **Instale as dependências**:
Execute o comando Maven para baixar as dependências:
```bash
  mvn clean install
```

4. **Execute a aplicação**:
Inicie o servidor Spring Boot:
```bash
  mvn spring-boot:run
```

5. **Acesse a API**:
A API estará disponível em `http://localhost:8080/api/v1/accounts`.

### Exemplos de entrada e saída

#### Criar uma conta
- **Requisição**:
```http
    POST /api/v1/accounts?accountNumber=123456&ownerName=João Silva
```
- **Resposta**:
```http
    Status: 200 OK
```

#### Depositar em uma conta
- **Requisição**:
```http
    POST /api/v1/accounts/123456/deposit?amount=100.00
```
- **Resposta**:
```http
    Status: 200 OK
```

#### Transferir entre contas
- **Requisição**:
```http
    POST /api/v1/accounts/transfer?fromAccount=123456&toAccount=654321&amount=50.00
```
- **Resposta**:
```http
    Status: 200 OK
```

#### Consultar saldo
- **Requisição**:
```http
    GET /api/v1/accounts/123456/balance
```
- **Resposta**:
```json
    {
        "balance": 100.00
    }
```

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests. 😊
