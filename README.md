# Spring CLI

Spring CLI é uma ferramenta de linha de comando que permite gerar rapidamente o esqueleto básico de projetos Spring Boot, ajudando a inicializar novos projetos de maneira eficiente e organizada.

## Funcionalidades

- Interface interativa para criar novos projetos Spring Boot.
- Configuração inicial simplificada, incluindo:
    - Escolha do nome do projeto e do `artifactId`.
    - Seleção do gerenciador de dependências (Maven).
      - Suporte a Gradle em breve...
    - Inclusão e remoção de dependências populares durante a criação.
- Geração automática do arquivo de configuração (`pom.xml` ou `build.gradle`).

## Instalação

1. Certifique-se de ter o Java 17 ou superior instalado.
2. Clone o repositório na sua máquina.

    ````bash
    git clone https://github.com/andre-xizto/spring-cli.git
    ````

3. Crie uma imagem nativa para o seu OS
    ````bash
    mvn -Pnative package
    ````
4. Adicione o executável ao seu `PATH` para facilitar o uso global:

   ```bash
   mv spring-cli /usr/local/bin/spring-cli
   chmod +x /usr/local/bin/spring-cli
   ```

## Uso

### Criar um novo projeto

Execute o comando abaixo para iniciar a interface interativa:

```bash
spring-cli
```

A interface será exibida da seguinte forma:

```
> SPRING-CLI

What's your group id?
What's your artifact id?
What's your project's name?
What's your project's description?
What's your package name?
What's your build tool? [MAVEN|GRADLE]
What java version you prefer?
> SELECT WHAT DEPENDENCIES YOU WILL USE


0 - terminate
1 - [ ] Spring Boot Starter Web
2 - [ ] Spring Data JPA

> Generating project...
> Project ready at <CURRENT FOLDER>
```

### Dependências Suportadas

- **Spring Web**: Para desenvolvimento de aplicações web.
- **Spring Data JPA**: Para acesso a dados com JPA e Hibernate.

## Estrutura Gerada

A CLI cria a seguinte estrutura básica:

```
my-project/
├── src/
│   ├── main/
│   │   ├── java/com/exemplo/app/
│   │   │   ├── Application.java
│   │   └── resources/
│   │       ├── application.properties
│   └── test/
│       └── java/com/exemplo/app/
│           └── ApplicationTests.java
├── pom.xml
└── README.md
```

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests no [repositório oficial](https://github.com/andre-xizto/spring-cli).

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.

---

**Desenvolvido por André Xisto**

