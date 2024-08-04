package com.example;

/**
 * A classe Vendedor representa um funcionário que é responsável por vender jogos e processar aluguéis.
 */
public class Vendedor extends Funcionario {
    protected double comissao;

    /**
     * Construtor para criar um novo vendedor com os atributos especificados.
     *
     * @param nome o nome do vendedor.
     * @param cpf o CPF do vendedor.
     * @param salario o salário do vendedor.
     */
    Vendedor(String nome, String cpf, double salario) {
        super(nome, cpf, salario);
    }

    /**
     * Realiza a venda de um jogo para um cliente, atualizando o estoque e a comissão do vendedor.
     *
     * @param cliente o cliente que está comprando o jogo.
     * @param codigoJogo o código do jogo a ser vendido.
     * @param quantidade a quantidade do jogo a ser vendida.
     * @param estoque o estoque onde os jogos estão armazenados.
     * @return true se a venda foi bem-sucedida, false caso contrário.
     */
    public boolean vender(Cliente cliente, int codigoJogo, int quantidade, Estoque estoque) {
        if (estoque.verificarDisponibilidade(codigoJogo, quantidade)) {
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

    /**
     * Processa o aluguel de um jogo para um cliente, atualizando o estoque e o histórico de aluguéis do cliente.
     *
     * @param cliente o cliente que está alugando o jogo.
     * @param codigoJogo o código do jogo a ser alugado.
     * @param estoque o estoque onde os jogos estão armazenados.
     * @return o objeto Aluguel criado, ou null se o jogo não estiver disponível.
     */
    public Aluguel processarAluguel(Cliente cliente, int codigoJogo, Estoque estoque) {
        if (estoque.verificarDisponibilidade(codigoJogo, 1)) {
            Jogo jogo = estoque.getJogo(codigoJogo);
            Aluguel aluguel = new Aluguel(jogo);
            aluguel.setCliente(cliente);
            cliente.adicionarAluguel(aluguel);
            estoque.removerQuantidadeEstoque(codigoJogo, 1);
            cliente.setNumAluguel(cliente.getNumAluguel() + 1);

            return aluguel;
        } else {
            return null;
        }
    }

    /**
     * Obtém o valor de um jogo com base no código fornecido.
     *
     * @param codigo o código do jogo.
     * @param estoque o estoque onde os jogos estão armazenados.
     * @return o valor do jogo, ou 0 se o jogo não for encontrado.
     */
    private double obterValorJogo(int codigo, Estoque estoque) {
        for (Jogo jogo : estoque.getJogos()) {
            if (jogo.getCodigo() == codigo) {
                return jogo.getValor();
            }
        }
        return 0;
    }

    /**
     * Atualiza o salário do vendedor adicionando a comissão acumulada.
     */
    private void modificarSalario() {
        this.salario += comissao;
    }

    // Gets

    /**
     * Obtém a comissão acumulada do vendedor.
     *
     * @return a comissão acumulada.
     */
    public double getComissao() {
        return comissao;
    }

    // Sets

    /**
     * Define a comissão acumulada do vendedor.
     *
     * @param comissao a nova comissão acumulada.
     */
    public void setComissao(double comissao) {
        this.comissao = comissao;
    }
}
