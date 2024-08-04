package com.example;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TesteAluguel {
    @Test
    public void testVerificarMulta() {
        Estoque e = Estoque.getInstance();
        Vendedor vend = new Vendedor("João", "9678", 2000);
        Cliente cliente = new Cliente("Jonas", "5673");

        Jogo jogo = new Digital("The Legend of Zelda: Breath of the Wild", 12, 59.99, "Aventura", "Nintendo", "Nintendo Switch", "Offline");
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
        assertEquals(0, aluguel.verificarMulta());

        aluguel.setDataAluguel(2024, mes, dia);

        // Quando há atrasos
        assertEquals(7, aluguel.verificarMulta());

        System.out.println("Passou no Teste de Verificar Multa!");
    }

    @BeforeEach
    public void iniciaTestes() {
        System.out.println("===== INÍCIO DOS TESTES DE ALUGUEL ======");
    }

    @AfterEach
    public void finalizaTestes() {
        System.out.println("======= FIM DOS TESTES DE ALUGUEL =======\n");
    }
}
