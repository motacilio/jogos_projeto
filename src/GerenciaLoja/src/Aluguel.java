import java.time.LocalDate;

public class Aluguel {
    protected static int countCodigo = 0;
    protected int codigo;

    protected Jogo jogo;
    protected final int tempoDevDias = 3;
    protected LocalDate dataAluguel;
    //protected int diasDeAtraso = 0;

    Aluguel (Jogo jogo) {
        this.codigo = ++countCodigo;
        this.jogo = jogo;
        this.dataAluguel = LocalDate.now();
    }

    // Método para calcular a data de devolução
    public LocalDate dataDevolucao() {
        return dataAluguel.plusDays(tempoDevDias);
    }

    // Método para verificar se houve atraso na devolução
    public boolean atraso(){
        return LocalDate.now().isAfter(dataDevolucao());
    }

    // Método para calcular a multa
    public double verificarMulta() {
        if (atraso()) {
            long diasDeAtraso = LocalDate.now().toEpochDay() - dataDevolucao().toEpochDay();
            return 3.5 * diasDeAtraso;
        } else {
            return 0;
        }
    }

    public void renovar(){
        this.dataAluguel = LocalDate.now();
    }

    public int getCodigo() {
        return codigo;
    }


}
