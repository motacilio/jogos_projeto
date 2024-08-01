import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteEstoque {

    @Test
    public void testVerificaDisponibilidade () {
        Estoque e = Estoque.getInstance();

        Jogo jogo = new Digital("FIFA", 5,300.00, "Esportes", "EA", "Console", "Online");

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

        System.out.println("PASSOU NO TESTE DE DISPONIBILIDADE DE ESTOQUE!");

    }


    @Test
    public void testGetJogo() {
        Estoque e = Estoque.getInstance();

        Jogo jogo = new Digital("FIFA", 5,300.00, "Esportes", "EA", "Console", "Online");

        e.adicionarJogoDig(jogo);

        // Buscando o jogo cadastrado
        assertEquals(jogo, e.getJogo(jogo.getCodigo()));

        // Buscando um jogo que não foi cadastrado
        assertEquals(null, e.getJogo(jogo.getCodigo() + 1));

        System.out.println("PASSOU NO TESTE DE GET JOGO!");
    }

}
