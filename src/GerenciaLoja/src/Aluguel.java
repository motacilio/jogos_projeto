import java.time.LocalDate;

public class Aluguel {
    protected final int tempoDevolucaoDias = 3;
    protected static int countCodigo = 0;
    protected int codigo;
    protected LocalDate dataAluguel;
    protected int codigoJogo;

    Aluguel (int codigoJogo, LocalDate dataAluguel) {
        this.codigo = ++countCodigo;
        this.codigoJogo = codigoJogo;
        this.dataAluguel = dataAluguel;
    }


    // Métodos Gets

    // Métodos Sets

    // Métodos Extras
    public boolean atraso(){
        return dataAluguel.plusDays(tempoDevolucaoDias).isAfter(LocalDate.now());
    }

    public double verificarMulta() {

    }

    public LocalDate dataDevolucao() {

    }

}
