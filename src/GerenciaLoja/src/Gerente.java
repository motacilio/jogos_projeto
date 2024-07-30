import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Gerente extends Funcionario implements Serializable {

    protected double comissao;

    Gerente(String nome, String cpf, double salario){
        super(nome, cpf, salario);
    }

    public Gerente(){}

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public void modificarSalario(){
        this.salario += comissao;
    }

    void cadstrarJogo(Estoque estoque, int opcao){
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

            Tabuleiro jogoTabNovo = new Tabuleiro(nome.getText(), 
                                        Integer.parseInt(quantidade.getText()), Integer.parseInt(valor.getText()), 
                                        genero.getText(), empresa.getText(), Integer.parseInt(qnt_jogadores.getText()));
            estoque.adicionarJogoTab(jogoTabNovo);
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

            Digital jogoDigNovo = new Digital(nome.getText(), Integer.parseInt(quantidade.getText()), 
                                              Double.parseDouble(valor.getText()), genero.getText(),
                                              empresa.getText(), plataforma.getText(), 
                                              conectividade.getText());
            estoque.adicionarJogoDig(jogoDigNovo);
        } 
    }

    public void excluirJogo(Estoque estoque, int opcao){
            estoque.removerJogo(codigo);
    };
}
