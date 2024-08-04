package com.example;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TesteCliente {

    @BeforeEach
    public void iniciaTestes() {
        System.out.println("===== INÍCIO DOS TESTES DE CLIENTE ======");
    }

    @AfterEach
    public void finalizaTestes() {
        System.out.println("======= FIM DOS TESTES DE CLIENTE =======\n");
    }

    @Test 
    public void testObterAluguel() {
        Estoque e = Estoque.getInstance();
        Vendedor vend = new Vendedor("João", "9678", 2000);
        Cliente cliente = new Cliente("Jonas", "5673");

        Jogo jogo =  new Digital("The Legend of Zelda: Breath of the Wild", 12, 59.99, "Aventura", "Nintendo", "Nintendo Switch", "Offline");
        e.adicionarJogoDig(jogo);

        Aluguel aluguel = vend.processarAluguel(cliente, jogo.getCodigo(), e);

        // Quando o código fornecido não existe
        assertNull(cliente.obterAluguel(aluguel.getCodigo() + 1));

        // Quando o código fornecido existe
        assertEquals(aluguel, cliente.obterAluguel(aluguel.getCodigo()));

        System.out.println("Passou no teste de Obter Aluguel do Cliente!");
    }

    @Test
    public void testQtdeAtrasos() {
        Estoque e = Estoque.getInstance();
        Vendedor vend = new Vendedor("João", "9678", 2000);
        Cliente cliente = new Cliente("Jonas", "5673");

        Jogo jogo =  new Digital("The Legend of Zelda: Breath of the Wild", 12, 59.99, "Aventura", "Nintendo", "Nintendo Switch", "Offline");
        e.adicionarJogoDig(jogo);
        
        Aluguel aluguel = vend.processarAluguel(cliente, jogo.getCodigo(), e);

        int maxDiasMeses2024[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int mes = aluguel.getDataAluguel().getMonthValue();
        int dia = aluguel.getDataAluguel().getDayOfMonth();

        for(int i = 0; i < 5; i++) {
            if (dia > 1) {
                dia--;
            }
            else {
                dia = maxDiasMeses2024[mes - 1];
                mes--;
            }
        }
        
        // Quando não há atrasos
        assertEquals(0, cliente.qtdeAtrasos());

        aluguel.setDataAluguel(2024, mes, dia);

        // Quando há atrasos
        assertEquals(1, cliente.qtdeAtrasos());

        System.out.println("Passou no Teste de Quantidade de Atrasos!");
    }

    @Test
    public void testDevolver() {
        Estoque e = Estoque.getInstance();
        Vendedor vend = new Vendedor("João", "9678", 2000);
        Cliente cliente = new Cliente("Jonas", "5673");

        Jogo jogo =  new Digital("God of War", 10, 39.99, "Ação/Aventura", "Santa Monica Studio", "PlayStation 4", "Offline");
        e.adicionarJogoDig(jogo);

        Aluguel aluguel = vend.processarAluguel(cliente, jogo.getCodigo(), e);
        
        int maxDiasMeses2024[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int mes = aluguel.getDataAluguel().getMonthValue();
        int dia = aluguel.getDataAluguel().getDayOfMonth();

        for(int i = 0; i < 5; i++) {
            if (dia > 1) {
                dia--;
            }
            else {
                dia = maxDiasMeses2024[mes - 1];
                mes--;
            }
        }

        // Quando não há o código de aluguel informado
        assertEquals(-1, cliente.devolver(e, aluguel.getCodigo() + 1));

        // Quando não há multa
        assertEquals(0, cliente.devolver(e, aluguel.getCodigo()));

        aluguel = vend.processarAluguel(cliente, jogo.getCodigo(), e);
        
        aluguel.setDataAluguel(2024, mes, dia);
        
        // Quando há atrasos no aluguel
        assertEquals(7.0, cliente.devolver(e, aluguel.getCodigo()));

        System.out.println("Passou no Teste de Devolver Jogo Alugado!");
    }
}
