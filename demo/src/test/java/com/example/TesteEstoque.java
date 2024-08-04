package com.example;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TesteEstoque {

    @BeforeEach
    public void iniciaTestes() {
        System.out.println("===== INÍCIO DOS TESTES DE ESTOQUE ======");
    }

    @AfterEach
    public void finalizaTestes() {
        System.out.println("======= FIM DOS TESTES DE ESTOQUE =======");
    }

    @Test
    public void testRemoverJogo() {
        Estoque e = Estoque.getInstance();

        Jogo jogo = new Tabuleiro("Catan", 15, 44.99, "Estratégia", "Kosmos", 4);
        e.adicionarJogoTab(jogo);

        int codJogoTab = jogo.getCodigo();
        
        // Removendo um jogo que não seja o escolhido
        e.removerJogo(codJogoTab + 1);
        assertEquals(jogo, e.getJogo(codJogoTab));
        
        // Removendo o jogo correto
        e.removerJogo(codJogoTab);
        assertEquals(null, e.getJogo(codJogoTab));

        jogo = new Digital("Minecraft", 15, 16, "Aventura", "Mojang", "PC", "Online");
        e.adicionarJogoDig(jogo);

        int codJogoDig = jogo.getCodigo();

        // Removendo um jogo que não seja o escolhido
        e.removerJogo(codJogoDig + 1);
        assertEquals(jogo, e.getJogo(codJogoDig));

        // Removendo o jogo escolhido
        e.removerJogo(codJogoDig);
        assertEquals(null, e.getJogo(codJogoDig));

        System.out.println("Passou no Teste de Remover Jogo!");
    }

    @Test
    public void testVerificaDisponibilidade() {
        Estoque e = Estoque.getInstance();

        Jogo jogo = new Digital("FIFA", 5, 300.00, "Esportes", "EA", "Console", "Online");

        e.adicionarJogoDig(jogo);

        // Jogo no estoque e Quantidade menos que o disponível
        assertEquals(true, e.verificarDisponibilidade(jogo.getCodigo(), 4));

        // Jogo no estoque e Quantidade igual ao do estoque
        assertEquals(true, e.verificarDisponibilidade(jogo.getCodigo(), 5));

        // Jogo no estoque e Quantidade maior que do estoque
        assertEquals(false, e.verificarDisponibilidade(jogo.getCodigo(), 6));

        // Sem o jogo
        assertEquals(false, e.verificarDisponibilidade(-1, 7));
        assertEquals(false, e.verificarDisponibilidade(-1, 5));

        System.out.println("Passou no Teste de Disponibilidade de Estoque!");
    }

    @Test
    public void testGetJogo() {
        Estoque e = Estoque.getInstance();

        Jogo jogo = new Digital("FIFA", 5, 300.00, "Esportes", "EA", "Console", "Online");

        e.adicionarJogoDig(jogo);

        // Buscando o jogo cadastrado
        assertEquals(jogo, e.getJogo(jogo.getCodigo()));

        // Buscando um jogo que não foi cadastrado
        assertEquals(null, e.getJogo(jogo.getCodigo() + 1));

        System.out.println("Passou no Teste de Get Jogo!");
    }

    @Test
    public void testRemoverQuantidadeEstoque() {
        Estoque e = Estoque.getInstance();

        Jogo jogo = new Digital("FIFA", 2, 300.00, "Esportes", "EA", "Console", "Online");

        e.adicionarJogoDig(jogo);
        int codJogo = jogo.getCodigo();

        e.removerQuantidadeEstoque(codJogo, 1);

        assertEquals(1, jogo.getQuantidade());

        System.out.println("Passou no Teste de Remover Quantidade do Estoque!");
    }

    @Test
    public void testAdicionarQuantidadeEstoque() {
        Estoque e = Estoque.getInstance();

        Jogo jogo = new Digital("FIFA", 2, 300.00, "Esportes", "EA", "Console", "Online");

        e.adicionarJogoDig(jogo);
        int codJogo = jogo.getCodigo();

        e.adicionarQuantidadeEstoque(codJogo, 1);

        assertEquals(3, jogo.getQuantidade());

        System.out.println("Passou no Teste de Adicionar Quantidade no Estoque!");
    }

    @Test
    public void testAdicionarJogoTab() {
        Estoque e = Estoque.getInstance();

        Jogo jogo = new Tabuleiro("Catan", 10, 44.99, "Estratégia", "Kosmos", 4);
        e.adicionarJogoTab(jogo);
        int codJogo = jogo.getCodigo();

        // Verificando se o jogo foi adicionado
        assertEquals(jogo, e.getJogo(codJogo));

        System.out.println("Passou no Teste de Adicionar Jogo de Tabuleiro!");
    }

    @Test
    public void testAdicionarJogoDig() {
        Estoque e = Estoque.getInstance();

        Jogo jogo = new Digital("Minecraft", 15, 20, "Aventura", "Mojang", "PC", "Online");
        e.adicionarJogoDig(jogo);
        int codJogo = jogo.getCodigo();
        
        // Verificando se o jogo foi adicionado
        assertEquals(jogo, e.getJogo(codJogo));

        System.out.println("Passou no Teste de Adicionar Jogo Digital!");
    }
}

