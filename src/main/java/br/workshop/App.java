package br.workshop;

import java.util.Map;

/**
 * Classe principal do workshop.
 *
 * Por que existe um método saudacao() separado do main()?
 * Porque métodos que recebem e devolvem valores são fáceis de testar
 * (veja AppTest.java). Já o main() só imprime na tela.
 */
public class App {

    public static String saudacao(String nome) {
        return "Olá, " + nome + "!";
    }

    public static void main(String[] args) {
        System.out.println(saudacao("Workshop"));

        // TODO Atividade 1: descomente as duas linhas abaixo.
        // Elas usam a biblioteca Gson, que ainda NÃO está declarada no projeto.
        // Rode o build antes de adicionar a dependência e leia o erro:
        // ele é exatamente o problema que o gerenciador de dependências resolve.

        // com.google.gson.Gson gson = new com.google.gson.Gson();
        // System.out.println(gson.toJson(Map.of("mensagem", saudacao("Workshop"))));
    }
}
