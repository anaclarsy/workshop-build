# Workshop 1 – Roteiro de atividades hands-on

*Automatizando Builds e Dependências na Prática com Apache Maven e Gradle*

**Duração:** 30 minutos (20:00 – 50:00) · **Formato:** em duplas ou individual

## Antes de começar

Você vai trabalhar neste repositório, que já contém uma classe `App.java`, um teste simples e os arquivos `pom.xml` (Maven) e `build.gradle` (Gradle). **O código Java é o mesmo nas duas ferramentas**: o que muda é só a forma de descrever o build. Guarde essa ideia, ela é o fio condutor das três atividades.

Abra o projeto na sua IDE e deixe um terminal aberto na raiz da pasta.

> **Comandos no Windows:** use `mvnw.cmd` e `gradlew.bat` no lugar de `./mvnw` e `./gradlew`. Se você instalou o Maven, pode usar apenas `mvn`.

## Atividade 1 – Adicionando uma dependência no Maven (8 min)

**Pergunta que vamos responder:** como usar uma biblioteca de terceiros sem baixar nenhum `.jar` manualmente?

### Passo a passo

1. Abra `src/main/java/br/workshop/App.java`. No método `main`, **descomente as duas últimas linhas**. Elas usam a classe `Gson`, que ainda não existe no projeto.
2. Rode `./mvnw compile` e **leia o erro**. Ele deve dizer que o pacote `com.google.gson` não existe. Esse é o problema que a dependência vai resolver.
3. Abra o `pom.xml`, localize o comentário `TODO Atividade 1` e adicione dentro de `<dependencies>`:

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.11.0</version>
</dependency>
```

4. Rode `./mvnw compile` novamente. Observe no terminal as linhas `Downloading from central`.
5. Rode a classe `App` pela IDE (ou pelo botão de run do `main`). A saída esperada é um JSON:

```
Olá, Workshop!
{"mensagem":"Olá, Workshop!"}
```

### Para entender o que aconteceu

- Os três campos (`groupId`, `artifactId`, `version`) formam a **coordenada** da biblioteca, o endereço dela no Maven Central.
- Veja onde o Maven guardou o arquivo: pasta `~/.m2/repository/com/google/code/gson/gson/2.11.0/` (Windows: `C:\Users\SEU_USUARIO\.m2\repository\...`).
- Rode `./mvnw dependency:tree`. Você declarou **uma** dependência, mas a árvore pode mostrar mais de uma. Essas extras são as **dependências transitivas**: o Maven buscou sozinho o que o Gson precisa para funcionar.

### Checkpoint

- [ ] `mvnw compile` terminou com BUILD SUCCESS
- [ ] Vi o JSON na saída
- [ ] Consigo explicar o que é uma dependência transitiva

## Atividade 2 – Executando o ciclo de vida do build (10 min)

**Pergunta que vamos responder:** o que acontece, e em que ordem, quando mandamos o Maven "empacotar" o projeto?

### Passo a passo

1. Rode `./mvnw test`. Leia a lista de plugins que aparece no log (`resources`, `compile`, `testCompile`, `surefire`). Cada linha é uma etapa do ciclo de vida sendo executada.
2. Rode `./mvnw package`. Repare que **os testes rodaram de novo, antes do empacotamento**. As fases do ciclo de vida são encadeadas: pedir `package` executa tudo que vem antes dele.
3. Liste a pasta `target/`. Você deve encontrar `workshop-build-1.0.0.jar`.
4. **Faça uma previsão antes de executar:** o JAR vai funcionar? Escreva "sim" ou "não" e o motivo.
5. Execute:

```
java -jar target/workshop-build-1.0.0.jar
```

### O que você deve observar

O programa **falha** com `NoClassDefFoundError: com/google/gson/Gson`. Isso não é um erro seu: é a descoberta central desta atividade.

Para investigar, rode `jar tf target/workshop-build-1.0.0.jar` e procure o Gson na lista. Ele não está lá. O JAR gerado contém apenas **as suas classes**; as bibliotecas ficaram no `~/.m2`, e o `java -jar` não sabe onde procurá-las.

### Para pensar

Como você resolveria isso para entregar o programa a alguém que não tem o Maven instalado? A resposta é o **Fat JAR** (ou Executable JAR), e ele será o seu desafio na tarefa de casa.

### Checkpoint

- [ ] Executei `test` e `package` e entendi a ordem das fases
- [ ] Encontrei o JAR em `target/`
- [ ] Reproduzi o erro e sei explicar por que ele acontece

## Atividade 3 – A mesma tarefa no Gradle (12 min)

**Pergunta que vamos responder:** o que muda quando descrevemos o mesmo build em uma DSL em vez de XML?

### Passo a passo

1. Abra o `build.gradle`. Ao lado dele, mantenha o `pom.xml` aberto. Compare a estrutura dos dois.
2. Se quiser ver o problema de novo, comente as linhas do Gson no `App.java` e descomente de novo depois: sem a dependência declarada no `build.gradle`, o `./gradlew build` falha na compilação, igual na Atividade 1.
3. No `build.gradle`, localize o comentário `TODO Atividade 3` e adicione dentro de `dependencies { }`:

```groovy
implementation 'com.google.code.gson:gson:2.11.0'
```

4. Rode `./gradlew build`. Cronometre o tempo total que aparece no final (`BUILD SUCCESSFUL in ...`).
5. **Rode o mesmo comando uma segunda vez** e compare o tempo e as mensagens. Procure por `UP-TO-DATE`.
6. Execute a aplicação: `./gradlew run`.
7. Opcional: rode `./gradlew --status` para ver o Gradle Daemon em execução.

### Para entender o que aconteceu

- A dependência que ocupou 5 linhas de XML no Maven ocupou **1 linha** aqui. O formato `grupo:artefato:versão` é a mesma coordenada, escrita de forma compacta.
- No Gradle, `implementation` cumpre o papel de um escopo (como o `compile` do Maven). Já `testImplementation` equivale ao escopo `test`.
- Na segunda execução, o Gradle reaproveitou o resultado das tarefas que não mudaram (**cache incremental**) e o Daemon já estava "quente" na memória. Por isso ficou mais rápido.

### Preencha a tabela comparativa

| | Maven | Gradle |
|---|---|---|
| Linhas para declarar o Gson | | |
| Formato do arquivo | | |
| Tempo do 1º build | | |
| Tempo do 2º build | | |
| Onde fica o JAR gerado | | |
| Qual achei mais legível? Por quê? | | |

### Checkpoint

- [ ] Vi o build passar com a dependência declarada
- [ ] Notei a diferença de tempo entre a 1ª e a 2ª execução
- [ ] Completei a tabela

## Se você terminou antes

1. No Maven, mude a versão do Gson para `2.8.0` e rode `./mvnw dependency:tree`. O que mudou?
2. Rode `./mvnw dependency:resolve` e `./gradlew dependencies`. Compare como cada ferramenta mostra a árvore de dependências.
3. Descubra como mudar o escopo do Gson no Maven para `provided` e preveja o que acontece ao executar o JAR.

## Tarefa de casa

**Criador de Artefato e Automação de Publicação Local.** Crie do zero um projeto Java simples (por exemplo, validador de CPF/e-mail ou calculadora de datas) e configure as duas ferramentas.

**Maven** (pasta `/maven-project`):

- adicionar JUnit 5 como dependência;
- escrever **ao menos 2 testes unitários**;
- configurar o Maven Shade Plugin (ou Assembly Plugin) para gerar um **Fat JAR executável**. Este é o problema que você encontrou na Atividade 2.

**Gradle** (pasta `/gradle-project`):

- recriar a mesma estrutura em `build.gradle` (Groovy ou Kotlin DSL);
- garantir que a task `build` execute os testes;
- publicar o artefato no repositório local (`~/.m2/repository`) com `publishToMavenLocal`.

**Entrega:** link do repositório no GitHub com as duas pastas e um `README.md` contendo prints dos comandos de build e testes executados com sucesso nas duas ferramentas.

> **Dica:** consulte a documentação oficial do plugin que escolher. Aprender a ler a documentação de uma ferramenta faz parte do objetivo.
