# Workshop 1 – Pré-requisitos para os alunos

*Automatizando Builds e Dependências na Prática com Apache Maven e Gradle*

## Por que isso importa

O workshop tem só 50 minutos entre teoria e prática. Se a gente gastar 15 desses minutos instalando Java, sobra pouco tempo para o que interessa: entender como as ferramentas de build funcionam. Por isso, **faça tudo deste documento antes do dia do workshop** e confirme com o teste final.

Tempo estimado: **20 a 30 minutos** (a maior parte é download).

## Checklist rápido

- [ ] JDK 17 ou superior instalado
- [ ] Variável `JAVA_HOME` configurada
- [ ] Maven instalado **ou** disposição de usar o wrapper (`mvnw`)
- [ ] Gradle via wrapper (`gradlew`) já baixado
- [ ] VS Code (com Java Extension Pack) **ou** IntelliJ IDEA Community
- [ ] Git instalado
- [ ] Repositório template clonado
- [ ] Teste final executado com sucesso

## 1. JDK 17 ou superior

**Por quê:** tanto o Maven quanto o Gradle rodam sobre a JVM, e o projeto do workshop é compilado para Java 17. Com uma versão anterior, a compilação falha.

Baixe uma distribuição do JDK (por exemplo, Temurin ou OpenJDK) e instale. Depois confirme no terminal:

```
java -version
javac -version
```

Ambos devem mostrar versão **17 ou maior**. Se aparecer `javac: command not found`, você instalou só o JRE: instale o JDK.

### Configurar JAVA_HOME

O `JAVA_HOME` diz às ferramentas onde o JDK está.

**Windows:** Painel de Controle → Variáveis de Ambiente → nova variável de sistema `JAVA_HOME` apontando para a pasta do JDK (ex.: `C:\Program Files\Eclipse Adoptium\jdk-17...`). Adicione `%JAVA_HOME%\bin` ao `Path`.

**Linux / macOS:** adicione ao `~/.bashrc` ou `~/.zshrc`:

```
export JAVA_HOME=/caminho/do/seu/jdk
export PATH=$JAVA_HOME/bin:$PATH
```

Feche e abra o terminal e teste com `echo $JAVA_HOME` (Windows: `echo %JAVA_HOME%`).

## 2. Apache Maven

**Por quê:** é a primeira ferramenta que vamos usar.

O template do workshop já inclui o **Maven Wrapper** (`mvnw`), então a instalação global é recomendada, mas não obrigatória. Se quiser instalar: baixe o Maven no site oficial, descompacte, adicione a pasta `bin` ao `Path` e teste:

```
mvn -version
```

## 3. Gradle

Não precisa instalar nada além do JDK: usaremos o **Gradle Wrapper** (`gradlew`), que baixa a versão correta do Gradle sozinho na primeira execução. Isso é uma boa prática do mercado e evita o clássico "na minha máquina funciona".

> **Importante:** esse primeiro download pode levar alguns minutos. Faça o teste final deste documento em casa, com boa internet, para que o download já esteja pronto no dia.

## 4. IDE

Escolha uma:

- **IntelliJ IDEA Community** (gratuito), já vem com suporte a Maven e Gradle.
- **VS Code** com o **Extension Pack for Java** (instale pela aba de extensões).

Abra a IDE ao menos uma vez antes do workshop para ela terminar configurações iniciais.

## 5. Git

Usaremos para clonar o repositório template e, na tarefa de casa, para entregar seu projeto no GitHub. Teste com `git --version`. Se ainda não tem conta no GitHub, crie uma.

## 6. Clonar o repositório template

```
git clone [<LINK-DO-REPOSITORIO-TEMPLATE>](https://github.com/anaclarsy/workshop-build.git)
cd <NOME-DA-PASTA>
```

## Teste final (obrigatório)

Dentro da pasta do template, rode os comandos abaixo. Todos devem terminar com **BUILD SUCCESS** (Maven) ou **BUILD SUCCESSFUL** (Gradle).

**Linux / macOS:**

```
./mvnw compile
./gradlew --version
```

**Windows:**

```
mvnw.cmd compile
gradlew.bat --version
```

Se tudo funcionou, você está pronto. Nesse ponto o Maven já baixou suas dependências básicas e o Gradle já baixou sua distribuição, que é exatamente o que queremos adiantar.

## Problemas comuns

| Sintoma | Causa provável | O que fazer |
|---|---|---|
| `java: command not found` | JDK fora do `Path` | Revise o passo do `JAVA_HOME` e reabra o terminal |
| `release version 17 not supported` | JDK antigo em uso | Confira `java -version` e `JAVA_HOME` |
| `Permission denied: ./gradlew` | Arquivo sem permissão de execução | `chmod +x gradlew mvnw` |
| Download travado ou erro de SSL | Rede da faculdade/empresa com proxy | Tente por outra rede (ex.: celular) |
| `mvn` não reconhecido | Maven fora do `Path` | Use `./mvnw` (wrapper) no lugar |

Bom workshop!
