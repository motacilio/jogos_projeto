import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.After;
import org.junit.Before;

public class TesteVendedor {
    
    @Test
    public void testVender() {
        iniciaTestes();
        Estoque e = Estoque.getInstance();
        Vendedor vend = new Vendedor("João", "9678", 2000);
        Cliente cliente = new Cliente("Jonas", "5673");

        Jogo jogo = new Digital("Minecraft", 15, 20, "Aventura", "Mojang", "PC", "Online");
        e.adicionarJogoDig(jogo);
        
        // Venda quando o valor é menor que 0
        assertEquals(false, vend.vender(cliente, jogo.getCodigo(), -1, e));
        
        // Venda quando o valor é 0
        assertEquals(false, vend.vender(cliente, jogo.getCodigo(), 0, e));
        
        // Venda quando o valor é Maior que 0 e Menor que a quantidade do jogo
        assertEquals(true, vend.vender(cliente, jogo.getCodigo(), (jogo.getQuantidade() - 1), e));

        // Venda quando o valor é Igual a Quantidade do jogo
        assertEquals(true, vend.vender(cliente, jogo.getCodigo(), jogo.getQuantidade(), e));

        // Venda quando o valor é Maior que a Quantidade do jogo
        assertEquals(false, vend.vender(cliente, jogo.getCodigo(), jogo.getQuantidade(), e));

        System.out.println("Passou no Teste de Vender!");
        finalizaTestes();
    }

    @Test
    public void testProcessarAluguel() {
        iniciaTestes();
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
        finalizaTestes();
    }
    
    @Before
    public void iniciaTestes() {
        System.out.println("===== INÍCIO DOS TESTES DE VENDEDOR ======");
    }

    @After
    public void finalizaTestes() {
        System.out.println("======= FIM DOS TESTES DE VENDEDOR =======\n");
    }
    
}
