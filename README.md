# Workshop 1 – Automatizando Builds e Dependências (Maven e Gradle)

Repositório modelo para as atividades hands-on. O código Java (`src/`) é o mesmo
nas duas ferramentas; só muda a forma de descrever o build.

## Estrutura

```
workshop-build/
├── pom.xml            # build descrito em XML (Maven)
├── build.gradle       # build descrito em DSL Groovy (Gradle)
├── settings.gradle    # nome do projeto no Gradle
├── docs/              # pré-requisitos e roteiro das atividades
├── mvnw, mvnw.cmd     # Maven Wrapper
├── gradlew, gradlew.bat  # Gradle Wrapper
└── src/
    ├── main/java/br/workshop/App.java
    └── test/java/br/workshop/AppTest.java
```

## Antes do workshop (teste de pré-requisitos)

Linux / macOS:

```
./mvnw compile
./gradlew --version
```

Windows:

```
mvnw.cmd compile
gradlew.bat --version
```

Os dois comandos devem terminar com sucesso. Isso também adianta o download das ferramentas.

## Comandos usados no workshop

| Ferramenta | Comando | O que faz |
|---|---|---|
| Maven | `./mvnw compile` | Compila o código |
| Maven | `./mvnw test` | Compila e executa os testes |
| Maven | `./mvnw package` | Gera o JAR em `target/` |
| Maven | `./mvnw dependency:tree` | Mostra as dependências (inclusive as transitivas) |
| Gradle | `./gradlew build` | Compila, testa e gera o JAR em `build/libs/` |
| Gradle | `./gradlew run` | Executa a aplicação |

## Documentos

- [Pré-requisitos](docs/pre-requisitos.md): o que instalar e testar antes do workshop
- [Roteiro de atividades](docs/atividades.md): passo a passo das três atividades e da tarefa de casa
