import java.io.Serializable;
import java.time.LocalDate;

public class Vendedor extends Funcionario implements Serializable {
    protected double comissao;
    protected int vendasMes;

    Vendedor (String nome, String cpf, double salario) {
        super(nome, cpf, salario);
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
    public void vender(double valorVenda) {
        this.vendasMes++;
        comissao += 0.05 * valorVenda;
        this.modificarSalario();
    }

    private void modificarSalario(){
        this.salario += comissao;
    }
}
