# API de Usuários

Este projeto é uma API de gerenciamento de usuários desenvolvida em Java utilizando o framework Spring Boot e o sistema de build Gradle. A API permite realizar operações CRUD (Create, Read, Update, Delete) em usuários, além de gerenciar endereços e telefones associados a esses usuários.

## Tecnologias Utilizadas

- **Java**
- **Spring Boot**
- **Gradle**
- **Lombok**
- **Spring Security**

## Estrutura do Projeto

### Pacotes Principais

- `com.felipe.API_usuario.business.dto`: Contém as classes DTO (Data Transfer Object) utilizadas para transferir dados entre as camadas da aplicação.
- `com.felipe.API_usuario.controller`: Contém os controladores REST que expõem os endpoints da API.
- `com.felipe.API_usuario.business`: Contém a lógica de negócios e os serviços da aplicação.
- `com.felipe.API_usuario.infrastructure.security`: Contém classes relacionadas à segurança, como a geração de tokens JWT.

### Classes Principais

#### `UsuarioDTO`

Representa um usuário com os seguintes atributos:
- `nome`: Nome do usuário.
- `email`: Email do usuário.
- `senha`: Senha do usuário.
- `endereco`: Lista de endereços associados ao usuário.
- `telefone`: Lista de telefones associados ao usuário.

#### `TelefoneDTO`

Representa um telefone com os seguintes atributos:
- `id`: Identificador do telefone.
- `numero`: Número do telefone.
- `ddd`: DDD do telefone.

#### `UsuarioController`

Controlador REST que expõe os endpoints para gerenciamento de usuários. Principais endpoints:
- `POST /usuario`: Salva um novo usuário.
- `POST /usuario/login`: Realiza login e retorna um token JWT.
- `GET /usuario`: Busca um usuário por email.
- `DELETE /usuario/{email}`: Deleta um usuário por email.
- `PUT /usuario`: Atualiza os dados de um usuário.
- `PUT /usuario/endereco`: Atualiza um endereço de um usuário.
- `PUT /usuario/telefone`: Atualiza um telefone de um usuário.
- `POST /usuario/endereco`: Cadastra um novo endereço para um usuário.
- `POST /usuario/telefone`: Cadastra um novo telefone para um usuário.

## Configuração e Execução

### Pré-requisitos

- Java 11 ou superior
- Gradle

### Passos para Executar

1. Clone o repositório:
   ```sh
   git clone <URL_DO_REPOSITORIO>
   cd API_usuario
