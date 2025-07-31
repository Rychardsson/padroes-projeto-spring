# Exemplos de Uso dos Padrões de Projeto

Este documento demonstra como usar os padrões de projeto implementados neste projeto Spring Boot.

## Padrões Implementados

### 1. Singleton Pattern
**Implementação**: Gerenciado automaticamente pelo Spring IoC Container
**Uso**: Todos os `@Service`, `@Repository` e `@Component` são singletons por padrão

### 2. Strategy Pattern
**Implementação**: Interface `ClienteService` com implementação `ClienteServiceImpl`
**Uso**: Permite diferentes implementações de serviços de cliente

### 3. Facade Pattern
**Implementação**: `ClienteRestController` 
**Uso**: Abstrai a complexidade das integrações (banco H2 + API ViaCEP)

### 4. Repository Pattern
**Implementação**: Spring Data JPA repositories
**Uso**: `ClienteRepository` e `EnderecoRepository`

### 5. Builder Pattern
**Implementação**: `ClienteBuilder`
**Exemplo de uso**:
```java
Cliente cliente = ClienteBuilder.umCliente()
    .comNome("João Silva")
    .comCep("01310-100")
    .build();
```

### 6. Observer Pattern
**Implementação**: `ClienteSubject` com observadores
**Uso**: Notificações automáticas quando clientes são criados/atualizados/removidos
- `LoggingClienteObserver`: Registra logs
- `EmailNotificationObserver`: Simula envio de emails

### 7. Command Pattern
**Implementação**: `Command` interface com `CommandInvoker`
**Exemplo de uso**:
```java
InserirClienteCommand command = new InserirClienteCommand(clienteService, cliente);
commandInvoker.executarComando(command);
commandInvoker.undo(); // Desfaz o comando
```

### 8. Factory Pattern
**Implementação**: `ClienteValidatorFactory`
**Exemplo de uso**:
```java
ClienteValidator validator = validatorFactory.createValidator(ValidatorType.COMPLETO);
boolean isValid = validator.isValid(cliente);
```

### 9. Decorator Pattern
**Implementação**: `ClienteServiceDecorator` com implementações:
- `ValidatedClienteServiceDecorator`: Adiciona validação
- `CachedClienteServiceDecorator`: Adiciona cache simples

### 10. Template Method Pattern
**Implementação**: `ClienteProcessor` com implementações:
- `ClientePadraoProcessor`: Processamento padrão
- `ClienteVipProcessor`: Processamento VIP com benefícios extras

## Endpoints da API

### Endpoints Básicos (ClienteRestController)
- `GET /clientes` - Lista todos os clientes
- `GET /clientes/{id}` - Busca cliente por ID
- `POST /clientes` - Cria novo cliente
- `PUT /clientes/{id}` - Atualiza cliente
- `DELETE /clientes/{id}` - Remove cliente

### Endpoints Avançados (ClienteAvancadoController)

#### Builder Pattern
- `POST /clientes-avancado/builder?nome=João&cep=01310-100`

#### Command Pattern
- `POST /clientes-avancado/command` - Inserir com command
- `PUT /clientes-avancado/command/{id}` - Atualizar com command
- `POST /clientes-avancado/undo` - Desfazer último comando
- `POST /clientes-avancado/redo` - Refazer comando
- `GET /clientes-avancado/historico` - Ver histórico de comandos

#### Factory Pattern (Validação)
- `POST /clientes-avancado/validar/NOME` - Validar apenas nome
- `POST /clientes-avancado/validar/CEP` - Validar apenas CEP
- `POST /clientes-avancado/validar/COMPLETO` - Validação completa

#### Template Method Pattern
- `POST /clientes-avancado/processar/padrao/{id}` - Processamento padrão
- `POST /clientes-avancado/processar/vip/{id}` - Processamento VIP

## Exemplo de JSON para Cliente

```json
{
    "nome": "João Silva",
    "endereco": {
        "cep": "01310-100"
    }
}
```

## Como Executar

1. Execute a aplicação Spring Boot:
```bash
./mvnw spring-boot:run
```

2. Acesse o Swagger UI (se disponível):
```
http://localhost:8080/swagger-ui.html
```

3. Teste os endpoints usando curl, Postman ou similar.

## Benefícios dos Padrões Implementados

- **Singleton**: Gerenciamento eficiente de recursos
- **Strategy**: Flexibilidade para diferentes implementações
- **Facade**: Interface simples para operações complexas
- **Repository**: Abstração do acesso a dados
- **Builder**: Construção fluente de objetos complexos
- **Observer**: Notificações automáticas e desacopladas
- **Command**: Operações como objetos, suporte a undo/redo
- **Factory**: Criação centralizada de objetos relacionados
- **Decorator**: Adição de funcionalidades sem modificar código existente
- **Template Method**: Reutilização de algoritmos com variações específicas
