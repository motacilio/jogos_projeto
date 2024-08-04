import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.After;
import org.junit.Before;

public class TesteAluguel {
    @Test
    public void testVerificarMulta() {
        iniciaTestes();

        


        finalizaTestes();
    }

    @Before
    public void iniciaTestes() {
        System.out.println("===== INÍCIO DOS TESTES DE ALUGUEL ======");
    }

    @After
    public void finalizaTestes() {
        System.out.println("======= FIM DOS TESTES DE ALUGUEL =======\n");
    }
}
