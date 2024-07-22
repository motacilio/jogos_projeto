import java.time.LocalDate;

public class Aluguel {
    protected static int countCodigo = 0;
    protected int codigo;

    protected Jogo jogo;

    protected final int tempoDevDias = 3;
    protected LocalDate dataAluguel;
    protected int diasDeAtraso = 0;

    Aluguel (Jogo jogo, Estoque estoque) {
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
        LocalDate dataDevolucao = dataDevolucao();
        return LocalDate.now().isAfter(dataDevolucao);
    }

    // Método para calcular a multa
    public double verificarMulta() {
        if (atraso()) {
            diasDeAtraso = (int) dataDevolucao().until(LocalDate.now()).getDays();
            if(diasDeAtraso < -1){
                return 0;
            }else{
                return 3.50 * diasDeAtraso;
            }
        } else {
            return 0;
        }
    }
}
