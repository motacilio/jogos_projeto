
import java.io.Serializable;
import java.time.LocalDate;

public class Aluguel implements Serializable{
    protected static int codigoAtual = 0;
    protected int codigo;
    protected Cliente cliente;
    protected Jogo jogo;
    protected final int tempoDevDias = 3;
    protected LocalDate dataAluguel;
    //protected int diasDeAtraso = 0;

    Aluguel (Jogo jogo) {
        this.codigo = 10 + codigoAtual;
        this.jogo = jogo;
        this.dataAluguel = LocalDate.now();
        codigoAtual += 10;
    }

    // Método para calcular a data de devolução, private pois nada fora usa
    private LocalDate dataDevolucao() {
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

    public String mostraAluguel(){
        StringBuilder s = new StringBuilder();
        s.append("Código: " + codigo + "\nCliente: " + cliente.getNome() + " - CPF: " + cliente.getCpf() + "\n"+
        "Jogo: " + jogo.getNome() + "\nData de aluguel: " + dataAluguel + "\nAtraso: " + atraso() + "\nMulta:" + verificarMulta() + "\n");
        return s.toString();
    }


    public Jogo getJogo(){
        return jogo;
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDate getDataAluguel () {
        return this.dataAluguel;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public void setDataAluguel (int ano, int mes, int dia) {
        this.dataAluguel = LocalDate.of(ano, mes, dia);
    }

    public static void setCodigoAtual(int codigo){
        Aluguel.codigoAtual = codigo;
    }

}
