import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteGerente {

    @Test
    public void testModificarSalario() {
        Gerente gerente = new Gerente("Bruno", "04592452100", 5000.00);
        gerente.setComissao(1500.00);

        assertEquals(6500.00, gerente.modificarSalario(), 0.0001);

        System.out.println("FIM DO TESTE DE MODIFICAR SALÁRIO!");
    }

}