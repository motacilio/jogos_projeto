package com.example;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

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

    public boolean cadastrarJogo(Estoque estoque, int opcao) throws IOException{
        if(estoque.getJogos() == null){
            Jogo.setCodigoAtual(0);
        }

        Jogo.setCodigoAtual(estoque.jogos.getLast().getCodigo());

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
                                    Integer.parseInt(quantidade.getText()), Double.parseDouble(valor.getText()), 
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

    public boolean atualizarJogo(Estoque estoque, int codigo){
        Jogo jogo = estoque.getJogo(codigo);

        loop:
        while (true) {
            String[] message = {"Adicionar quantidade", "Alterar nome", "Alterar valor", "Voltar"};

            int op = JOptionPane.showOptionDialog(
            null, jogo.mostraInfo(),null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, message, null);

            switch (op) {
                case 0:
                    String quant = JOptionPane.showInputDialog("Digite a quantidade a ser adicionada");
                    jogo.somarQuant(Integer.parseInt(quant));
                    continue loop;
                case 1:
                    String nome = JOptionPane.showInputDialog("Digite o novo nome");
                    jogo.setNome(nome);
                    continue loop;
                case 2:
                    String valor = JOptionPane.showInputDialog("Digite o novo valor");
                    jogo.setValor(Double.parseDouble(valor));
                    continue loop;
                case 3:
                    return true;
                default:
                    break;
            }
        }
    }

    public void excluirJogo(Estoque estoque, int codigo){
            estoque.removerJogo(codigo);
    }
}

