import javax.swing.*;

public class Gerente extends Funcionario {

    protected double comissao;

    Gerente(String nome, String cpf, double salario){
        super(nome, cpf, salario);
    }

    public Gerente(){}

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public double modificarSalario(){
        this.salario += comissao;
        return this.salario;
    }

// Colocar a Entrada de dados dos jogos na Main ?
    public boolean cadstrarJogo(Estoque estoque, int opcao){
        if(opcao == 1){
            JTextField nome = new JTextField();
            JTextField quantidade = new JTextField();
            JTextField valor = new JTextField();
            JTextField genero = new JTextField();
            JTextField empresa = new JTextField();
            JTextField qnt_jogadores = new JTextField();

            Object[] message = {
            "Nome:", nome, "Quantidade:", quantidade,
            "Valor:", valor, "Gênero:", genero,
            "Empresa:", empresa, "Quantidade De Jogadores:", qnt_jogadores
            };

            int op = JOptionPane.showConfirmDialog(
            null, 
            message, 
            "Adicionar Jogo", 
            JOptionPane.OK_CANCEL_OPTION);

            if(op == JOptionPane.CANCEL_OPTION){
                return false;
            }

            Tabuleiro jogoTabNovo = new Tabuleiro(nome.getText(), 
                                        Integer.parseInt(quantidade.getText()), Integer.parseInt(valor.getText()), 
                                        genero.getText(), empresa.getText(), Integer.parseInt(qnt_jogadores.getText()));
            estoque.adicionarJogoTab(jogoTabNovo);
            return true;
        } else if(opcao == 2){
            JTextField nome = new JTextField();
            JTextField quantidade = new JTextField();
            JTextField valor = new JTextField();
            JTextField genero = new JTextField();
            JTextField empresa = new JTextField();
            JTextField plataforma = new JTextField();
            JTextField conectividade = new JTextField();

            Object[] message = {
            "Nome:", nome, "Quantidade:", quantidade,
            "Valor:", valor, "Gênero:", genero,
            "Empresa:", empresa, "Plataforma:", plataforma,
            "Conectividade", conectividade
            };

            int op = JOptionPane.showConfirmDialog(
            null, 
            message, 
            "Adicionar Jogo", 
            JOptionPane.OK_CANCEL_OPTION);

            if(op == JOptionPane.CANCEL_OPTION){
                return false;
            }

            Digital jogoDigNovo = new Digital(nome.getText(), Integer.parseInt(quantidade.getText()), 
                                              Double.parseDouble(valor.getText()), genero.getText(),
                                              empresa.getText(), plataforma.getText(), 
                                              conectividade.getText());
            estoque.adicionarJogoDig(jogoDigNovo);
            return true;
        } 
        return true;
    }

    public void excluirJogo(Estoque estoque, int codigo){
            estoque.removerJogo(codigo);
    }
}
