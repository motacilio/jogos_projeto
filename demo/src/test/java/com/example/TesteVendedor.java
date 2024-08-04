package com.example;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TesteVendedor {

    @BeforeEach
    public void iniciaTestes() {
        System.out.println("===== INÍCIO DOS TESTES DE VENDEDOR ======");
    }

    @AfterEach
    public void finalizaTestes() {
        System.out.println("======= FIM DOS TESTES DE VENDEDOR =======\n");
    }

    @Test
    public void testVender() {
        Estoque e = Estoque.getInstance();
        Vendedor vend = new Vendedor("João", "9678", 2000);
        Cliente cliente = new Cliente("Jonas", "5673");

        Jogo jogo = new Digital("Minecraft", 15, 20, "Aventura", "Mojang", "PC", "Online");
        e.adicionarJogoDig(jogo);

        // Venda quando o valor é menor que 0
        assertEquals(false, vend.vender(cliente, jogo.getCodigo(), -1, e));

        // Venda quando o valor é 0
        assertEquals(false, vend.vender(cliente, jogo.getCodigo(), 0, e));

        // Venda quando o valor é maior que 0 e menor que a quantidade do jogo
        assertEquals(true, vend.vender(cliente, jogo.getCodigo(), jogo.getQuantidade() - 1, e));

        // Venda quando o valor é igual a quantidade do jogo
        assertEquals(true, vend.vender(cliente, jogo.getCodigo(), jogo.getQuantidade(), e));

        // Venda quando o valor é maior que a quantidade do jogo
        assertEquals(false, vend.vender(cliente, jogo.getCodigo(), jogo.getQuantidade() + 1, e));

        System.out.println("Passou no Teste de Vender!");
    }

    @Test
    public void testProcessarAluguel() {
        Estoque e = Estoque.getInstance();
        Vendedor vend = new Vendedor("João", "9678", 2000);
        Cliente cliente = new Cliente("Jonas", "5673");

        Jogo jogo = new Digital("The Legend of Zelda: Breath of the Wild", 12, 59.99, "Aventura", "Nintendo", "Nintendo Switch", "Offline");
        e.adicionarJogoDig(jogo);

        // Aluguel quando o jogo não existe
        assertNull(vend.processarAluguel(cliente, jogo.getCodigo() + 1, e));

        // Aluguel quando o jogo existe
        assertNotNull(vend.processarAluguel(cliente, jogo.getCodigo(), e));

        System.out.println("Passou no Teste de Processar Aluguel!");
    }
}

