# Explorando Padrões de Projetos na Prática com Java

Repositório com as implementações dos padrões de projeto explorados no Lab "Explorando Padrões de Projetos na Prática com Java". Este projeto demonstra uma implementação completa e robusta de múltiplos padrões do GoF (Gang of Four) usando o Spring Framework.

## 🎯 Padrões de Projeto Implementados

### Padrões Originais

- **Singleton** - Gerenciado pelo Spring IoC Container
- **Strategy/Repository** - Interface de serviços e repositórios Spring Data
- **Facade** - Controladores REST abstraindo complexidade

### Padrões Adicionados

- **Builder** - Construção fluente de objetos Cliente
- **Observer** - Sistema de notificações para eventos de cliente
- **Command** - Operações como objetos com suporte a undo/redo
- **Factory** - Criação de validadores especializados
- **Decorator** - Adição de funcionalidades aos serviços
- **Template Method** - Processamento de diferentes tipos de clientes

## 🚀 Tecnologias Utilizadas

- **Java 11**
- **Spring Boot 2.5.4**
- **Spring Data JPA**
- **Spring Web**
- **OpenFeign**
- **H2 Database**
- **Swagger/OpenAPI**

## 📋 Funcionalidades

### Operações CRUD Básicas

- Criar, ler, atualizar e deletar clientes
- Integração automática com API ViaCEP para endereços
- Validação de dados

### Funcionalidades Avançadas

- **Sistema de Notificações**: Logs e emails automáticos
- **Histórico de Comandos**: Undo/Redo de operações
- **Validação Flexível**: Diferentes tipos de validadores
- **Cache Inteligente**: Decorator com cache simples
- **Processamento Especializado**: Templates para clientes padrão e VIP

## 🔧 Como Executar

1. **Clone o repositório:**

```bash
git clone https://github.com/Rychardsson/padroes-projeto-spring.git
cd padroes-projeto-spring
```

2. **Execute a aplicação:**

```bash
./mvnw spring-boot:run
```

3. **Acesse a aplicação:**

- API: `http://localhost:8080`
- H2 Console: `http://localhost:8080/h2-console`
- Swagger (se disponível): `http://localhost:8080/swagger-ui.html`

## 📚 Documentação

Para exemplos detalhados de uso e explicação dos padrões, consulte:

- [PADROES_PROJETO.md](PADROES_PROJETO.md) - Documentação completa dos padrões

## 🛠️ Endpoints da API

### Endpoints Básicos

- `GET /clientes` - Listar todos os clientes
- `GET /clientes/{id}` - Buscar cliente por ID
- `POST /clientes` - Criar cliente
- `PUT /clientes/{id}` - Atualizar cliente
- `DELETE /clientes/{id}` - Remover cliente

### Endpoints Avançados (Demonstração dos Padrões)

- `POST /clientes-avancado/builder` - Builder Pattern
- `POST /clientes-avancado/command` - Command Pattern
- `POST /clientes-avancado/undo` - Desfazer comando
- `POST /clientes-avancado/validar/{tipo}` - Factory Pattern
- `POST /clientes-avancado/processar/vip/{id}` - Template Method

## 💡 Destaques da Implementação

### 1. Arquitetura Modular

Cada padrão foi implementado em seu próprio package, facilitando manutenção e compreensão.

### 2. Integração Seamless

Os padrões trabalham juntos naturalmente através da injeção de dependência do Spring.

### 3. Exemplos Práticos

Cada padrão resolve um problema real no domínio de gestão de clientes.

### 4. Observabilidade

Sistema completo de logs e notificações para acompanhar as operações.

## 🎓 Valor Educacional

Este projeto demonstra:

- Como aplicar padrões do GoF em aplicações reais
- Integração de múltiplos padrões em um sistema coeso
- Uso das abstrações do Spring Framework
- Boas práticas de arquitetura de software

## 🤝 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para:

- Implementar novos padrões
- Melhorar os existentes
- Adicionar testes
- Melhorar a documentação

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

---

**Desenvolvido com 💙 para demonstrar a beleza e utilidade dos Padrões de Projeto**
