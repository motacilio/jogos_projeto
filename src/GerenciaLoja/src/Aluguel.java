import java.io.Serializable;
import java.time.LocalDate;

public class Aluguel implements Serializable{
    protected static int countCodigo = 0;
    protected int codigo;
    protected Cliente cliente;
    protected Jogo jogo;
    protected final int tempoDevDias = 3;
    protected LocalDate dataAluguel;
    //protected int diasDeAtraso = 0;

    Aluguel (Jogo jogo) {
        this.codigo = ++countCodigo;
        this.jogo = jogo;

        // // Código para Testes de Aluguel em Atraso
        //     // Considerando que a Loja foi fundada em 01/07/2024 até a Data Atual 
        //         int maxDiasMeses2024[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //         final int min = 7;
        //         final int maxMeses = LocalDate.now().getMonthValue();

        //         final int diaDeHoje = LocalDate.now().getDayOfMonth();
        //         maxDiasMeses2024[maxMeses - 1] = diaDeHoje;


        //         int randomMes = min + (int) (Math.random() * (maxMeses - min + 1));
        //         int randomDia = 1 + (int) (Math.random() * (maxDiasMeses2024[randomMes - 1] - min + 1));

        //         this.dataAluguel = LocalDate.of(2024, randomMes, randomDia);

        this.dataAluguel = LocalDate.now();
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

}
