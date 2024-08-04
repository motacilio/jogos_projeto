package com.example;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * A classe Gerente representa um funcionário que é gerente, com habilidades adicionais
 * para modificar o estoque de jogos e alterar salários.
 */
public class Gerente extends Funcionario {

    protected double comissao;

    /**
     * Construtor para criar um novo gerente com o nome, CPF e salário especificados.
     *
     * @param nome o nome do gerente.
     * @param cpf o CPF do gerente.
     * @param salario o salário do gerente.
     */
    Gerente(String nome, String cpf, double salario) {
        super(nome, cpf, salario);
    }

    /**
     * Construtor padrão.
     */
    public Gerente() {}

    /**
     * Define a comissão do gerente.
     *
     * @param comissao a nova comissão do gerente.
     */
    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    /**
     * Modifica o salário do gerente adicionando a comissão.
     *
     * @return o novo salário do gerente.
     */
    public double modificarSalario() {
        this.salario += comissao;
        return this.salario;
    }

    /**
     * Cadastra um novo jogo no estoque.
     *
     * @param estoque o estoque onde o jogo será adicionado.
     * @param opcao a opção para determinar o tipo de jogo (1 para Tabuleiro, 2 para Digital).
     * @return true se o jogo foi cadastrado com sucesso, false se a operação foi cancelada.
     * @throws IOException se ocorrer um erro de E/S ao acessar os arquivos do estoque.
     */
    public boolean cadastrarJogo(Estoque estoque, int opcao) throws IOException {
        if (estoque.getJogos() == null) {
            Jogo.setCodigoAtual(0);
        }

        Jogo.setCodigoAtual(estoque.jogos.get(estoque.jogos.size() - 1).getCodigo());

        if (opcao == 1) {
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
                JOptionPane.OK_CANCEL_OPTION
            );

            if (op == JOptionPane.CANCEL_OPTION) {
                return false;
            }

            Tabuleiro jogoTabNovo = new Tabuleiro(nome.getText(),
                    Integer.parseInt(quantidade.getText()), Double.parseDouble(valor.getText()),
                    genero.getText(), empresa.getText(), Integer.parseInt(qnt_jogadores.getText()));
            estoque.adicionarJogoTab(jogoTabNovo);

            return true;
        } else if (opcao == 2) {
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
                JOptionPane.OK_CANCEL_OPTION
            );

            if (op == JOptionPane.CANCEL_OPTION) {
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

    /**
     * Atualiza as informações de um jogo existente no estoque.
     *
     * @param estoque o estoque onde o jogo será atualizado.
     * @param codigo o código do jogo a ser atualizado.
     * @return true se a atualização foi concluída.
     */
    public boolean atualizarJogo(Estoque estoque, int codigo) {
        Jogo jogo = estoque.getJogo(codigo);

        loop:
        while (true) {
            String[] message = {"Adicionar quantidade", "Alterar nome", "Alterar valor", "Voltar"};

            int op = JOptionPane.showOptionDialog(
                null, jogo.mostraInfo(), null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, message, null);

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

    /**
     * Exclui um jogo do estoque.
     *
     * @param estoque o estoque de onde o jogo será removido.
     * @param codigo o código do jogo a ser removido.
     */
    public void excluirJogo(Estoque estoque, int codigo) {
        estoque.removerJogo(codigo);
    }
}
