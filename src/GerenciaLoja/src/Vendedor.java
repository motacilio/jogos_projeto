
public class Vendedor extends Funcionario {
    protected double comissao;

    Vendedor (String nome, String cpf, double salario) {
        super(nome, cpf, salario);
    }

    // Métodos
    public boolean vender(Cliente cliente, int codigoJogo, int quantidade, Estoque estoque){
        if(estoque.verificarDisponibilidade(codigoJogo, quantidade)){
            estoque.removerQuantidadeEstoque(codigoJogo, quantidade);
            double valorVenda = quantidade * obterValorJogo(codigoJogo, estoque);
            this.comissao += 0.05 * valorVenda;
            modificarSalario();
            this.comissao = 0;


            for (Jogo jogo : estoque.getJogos()) {
                if (jogo.getCodigo() == codigoJogo) {
                    cliente.adicionarCompra(jogo, quantidade);
                    break;
                }
            }

            return true;
        }
        return false;
    }

    public Aluguel processarAluguel(Cliente cliente, int codigoJogo, Estoque estoque){
        if(estoque.verificarDisponibilidade(codigoJogo, 1)){
            Jogo jogo = estoque.getJogo(codigoJogo);
            Aluguel aluguel = new Aluguel(jogo);
            aluguel.setCliente(cliente);
            cliente.adicionarAluguel(aluguel);
            estoque.removerQuantidadeEstoque(codigoJogo, 1);
            cliente.setNumAluguel(cliente.getNumAluguel() + 1);

            return aluguel;
        }else{
            return null;
        }
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
