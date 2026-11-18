# Helpdesk API

API REST para gerenciamento de chamados de suporte, desenvolvida com Java 21 e Spring Boot 3. O projeto demonstra uma arquitetura em camadas simples, persistência relacional, validação de entrada, documentação OpenAPI e testes automatizados.

## Funcionalidades
- Criar, listar, consultar, atualizar e excluir chamados
- Filtrar chamados por status
- Prioridades LOW, MEDIUM, HIGH e CRITICAL
- Status OPEN, IN_PROGRESS, RESOLVED e CLOSED
- Validação de dados com Jakarta Bean Validation
- Persistência PostgreSQL com Spring Data JPA
- Documentação Swagger/OpenAPI
- Testes de integração com H2
- Docker Compose e pipeline Maven no GitHub Actions

## Requisitos
Java 21, Maven 3.9+ ou Docker com Docker Compose.

## Executar com Docker
```bash
docker compose up --build
```
API: http://localhost:8080  
Swagger: http://localhost:8080/swagger-ui.html

## Executar localmente
Inicie um PostgreSQL, configure `DB_URL`, `DB_USERNAME` e `DB_PASSWORD` (ou use os valores locais padrão) e execute:
```bash
mvn spring-boot:run
```

## Endpoints
| Método | Rota | Descrição |
|---|---|---|
| GET | /api/v1/tickets | Lista chamados; aceita ?status=OPEN |
| GET | /api/v1/tickets/{id} | Consulta chamado |
| POST | /api/v1/tickets | Cria chamado |
| PATCH | /api/v1/tickets/{id} | Atualiza campos |
| DELETE | /api/v1/tickets/{id} | Exclui chamado |

Exemplo de criação:
```json
{
  "title": "Falha no acesso à internet",
  "description": "Usuário sem conectividade desde o início do expediente",
  "requester": "Maria Silva",
  "priority": "HIGH"
}
```

## Testes
```bash
mvn test
mvn verify
```

## Estrutura
- `ticket`: entidade, DTOs, enumerações, repositório, serviço e controller
- `config`: configuração OpenAPI
- `src/test`: testes de integração da API

## Considerações
Este é um projeto demonstrativo para portfólio. Antes de produção, acrescente autenticação/autorização, controle de acesso por perfil, paginação, auditoria, migrações com Flyway ou Liquibase, política de retenção e gestão segura de credenciais. O Compose usa credenciais locais de desenvolvimento; não reutilize em produção.
