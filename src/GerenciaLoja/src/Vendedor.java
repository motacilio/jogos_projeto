import java.time.LocalDate;

public class Vendedor extends Funcionario {
    protected double comissao;
    protected int vendasMes;

    Vendedor (String nome, String cpf, LocalDate dataNascimento, LocalDate dataAdmissao, double salario, double comissao) {
        super(nome, cpf, dataNascimento, dataAdmissao, salario);
        this.comissao = comissao;
        this.vendasMes = 0;
    }

    // Gets

    public double getComissao() {
        return comissao;
    }

    public int getVendasMes() {
        return vendasMes;
    }

    // Sets
    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public void setVendasMes(int vendasMes) {
        this.vendasMes = vendasMes;
    }

    // Métodos
    public void vender(double) {
        this.vendasMes++;
    }

}
