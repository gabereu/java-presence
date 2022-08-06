# Sistema de Presença

Implementação do sistema de presença utilizando java

## Desafio
Sistema de presenças para funcionários com possibilidade de gestão via administradores

## Fluxo esperado
- [x] Usuário consegue 
  - [x] cadastrar sua presença
  - [x] Usuário consegue visualizar suas presenças registradas
- [x] Administrador consegue 
  - [x] visualizar todas as presenças
  - [x] alterar uma presença
  - [x] criar uma presença
  - [x] deletar uma presença

## Como rodar

- testes: `mvn test`
  - visualiar reports em `target/code-coverage/index.html`

- testes de mutação: `mvn test-compile org.pitest:pitest-maven:mutationCoverag`
  - visualiar reports em `target/pit-reports/{test_date}/index.html`