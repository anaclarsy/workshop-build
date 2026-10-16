package br.workshop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Teste unitário simples (JUnit 5).
 *
 * Ele será executado pelo Maven na fase "test" (mvn test) e pelo Gradle
 * na task "test". O mesmo teste roda nas duas ferramentas.
 */
class AppTest {

    @Test
    void saudacaoDeveIncluirONome() {
        assertEquals("Olá, Ana!", App.saudacao("Ana"));
    }
}
