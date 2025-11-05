# Projeto Gerador de Álbuns (Full-Stack)

Este é um projeto web full-stack que permite ao usuário sortear um álbum de música baseado em um gênero musical de sua escolha.

O projeto foi construído utilizando uma arquitetura desacoplada (Monorepo), com uma API RESTful no back-end e uma aplicação SPA (Single Page Application) no front-end.

## Tecnologias Utilizadas

### 1. Back-end (API)
**(Pasta: `/album-generator`)**

* **Java 17**
* **Spring Boot 3**
    * **Spring Web:** Para a criação dos endpoints RESTful.
    * **Spring Data JPA:** Para a persistência e acesso aos dados.
* **H2 Database:** Banco de dados em memória utilizado para desenvolvimento.

### 2. Front-end (UI)
**(Pasta: `/album-generator-ui`)**

* **React 18**
* **Vite:** Como ferramenta de build e servidor de desenvolvimento.
* **JavaScript (ES6+)**
* **CSS Básico**

## Funcionalidades Atuais

* **API:**
    * `GET /api/genres`: Lista todos os gêneros musicais cadastrados.
    * `POST /api/genres`: Cadastra um novo gênero.
    * `POST /api/albums`: Cadastra um novo álbum, associando-o a um gênero.
    * `GET /api/albums/random?genreId={id}`: **(Endpoint principal)** Sorteia e retorna um álbum aleatório com base no ID do gênero fornecido.

* **Front-end:**
    * Exibe botões para cada gênero cadastrado.
    * Ao clicar em um gênero, consome o endpoint de sorteio da API.
    * Exibe o título, artista e imagem de capa do álbum sorteado.

## Como Executar (Ambiente Local)

### Pré-requisitos
* JDK 17 (ou superior)
* Node.js (v18 ou superior)

### 1. Rodando o Back-end (Java/Spring)

1.  Navegue até a pasta do back-end: `cd album-generator`
2.  Inicie a aplicação Spring Boot (utilizando o VS Code, IntelliJ ou terminal).
3.  O servidor estará rodando em `http://localhost:8080`.
4.  (Obrigatório) Use o arquivo `teste.http` (ou outra ferramenta de API) para cadastrar gêneros e álbuns no banco H2, pois ele é reiniciado a cada execução.

### 2. Rodando o Front-end (React/Vite)

1.  Em um **novo terminal**, navegue até a pasta do front-end: `cd album-generator-ui`
2.  Instale as dependências: `npm install`
3.  Inicie o servidor de desenvolvimento: `npm run dev`
4.  A aplicação estará disponível em `http://localhost:5173`.
