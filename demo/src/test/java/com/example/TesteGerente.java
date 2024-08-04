package com.example;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TesteGerente {

    @BeforeEach
    public void iniciaTestes() {
        System.out.println("===== INÍCIO DOS TESTES DE GERENTE ======");
    }

    @AfterEach
    public void finalizaTestes() {
        System.out.println("======= FIM DOS TESTES DE GERENTE =======\n");
    }

    @Test
    public void testModificarSalario() {
        Gerente gerente = new Gerente("Bruno", "04592452100", 5000.00);
        gerente.setComissao(1500.00);

        assertEquals(6500.00, gerente.modificarSalario(), 0.0001);

        System.out.println("PASSOU NO TESTE DE MODIFICAR SALARIO!");
    }
}
