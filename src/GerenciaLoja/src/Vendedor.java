import java.io.Serializable;


public class Vendedor extends Funcionario implements Serializable {
    protected double comissao;
    protected int vendasMes;

    Vendedor (String nome, String cpf, double salario) {
        super(nome, cpf, salario);
        this.vendasMes = 0;
    }

// PAREI TENTANDO FAZER O VENDER DO VENDEDOR
    // Métodos
    public boolean vender(int codigo, int quantidade, Estoque estoque) {

        estoque.vender(codigo, quantidade);

        return true;
    }



    private void modificarSalario(){
        this.salario += comissao;
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
}
