# Sistema de Agendamento – API REST
API REST desenvolvida para gerenciamento de agendamentos em uma barbearia. O sistema permite o cadastro e controle de horários, aplicando validações de regras de negócio como prevenção de conflitos de agenda
#
# Tecnologias usadas

- Java
- Spring Data JPA
- H2
- Maven
- Lombok

# Funcionalidades

- Cadastro de agendamentos

- Listagem de horários

- Atualização de registros

- Remoção de agendamentos

- Validação de conflitos de horário


# Regras de negocios

- Não é permitido fazer agendamentos em horários ocupados
- Não é permitido fazer agendamentos em horários fora do expediente

# Estrutura do projeto
Arquitetura em camadas:
- Controller → Responsável pelos endpoints REST
- Service → Contém regras de negócio
- Repository → Comunicação com banco de dados
- Entity → Modelagem das tabelas

# Objetivo do projeto
Projeto desenvolvido com fins de estudo, com foco em aprendizado de desenvolvimento back-end, APIs REST, arquitetura em camadas e integração com banco de dados
