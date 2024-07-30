import java.io.Serializable;
import java.time.LocalDate;

public class Vendedor extends Funcionario implements Serializable {
    protected double comissao;

    Vendedor (String nome, String cpf, double salario) {
        super(nome, cpf, salario);
    }

    // Métodos
    public boolean vender(Cliente cliente, int codigo, int quantidade, Estoque estoque){
        if(estoque.verificarDisponibilidade(codigo, quantidade)){
            estoque.atualizarEstoque(codigo, quantidade);
            double valorVenda = quantidade * obterValorJogo(codigo, estoque);
            this.comissao += 0.05 * valorVenda;
            modificarSalario();
            this.comissao = 0;

            for (Jogo jogo : estoque.getJogos()) {
                if (jogo.getCodigo() == codigo) {
                    cliente.adicionarCompra(jogo, quantidade);
                    break;
                }
            }

            return true;
        }
        return false;
    }

    public boolean processarAluguel(Cliente cliente, int codigo, Estoque estoque){
        if(estoque.verificarDisponibilidade(codigo, 1)){
            Jogo jogo = estoque.getJogo(codigo);
            Aluguel aluguel = new Aluguel(jogo);

            cliente.getAlugueis().add(aluguel);
            estoque.atualizarEstoque(codigo, 1);
            cliente.setNumAluguel(cliente.getNumAluguel() + 1);

            return true;
        }
        return false;
    }

    private double obterValorJogo(int codigo, Estoque estoque){
        for(Jogo jogo: estoque.getJogos()){
            if(jogo.getCodigo() == codigo){
                return jogo.getValor();
            }
        }
        return 0;
    }


    private void modificarSalario(){
        this.salario += comissao;
    }

    // Gets

    public double getComissao() {
        return comissao;
    }

    // Sets
    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

}
