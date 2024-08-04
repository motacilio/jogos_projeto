package com.example;

/**
 * A classe Tabuleiro representa um jogo de tabuleiro com uma quantidade específica de jogadores.
 */
public class Tabuleiro extends Jogo {
    protected int qtdeJogadores;

    /**
     * Construtor para criar um novo jogo de tabuleiro com os atributos especificados.
     *
     * @param nome o nome do jogo.
     * @param quantidade a quantidade disponível do jogo.
     * @param valor o valor do jogo.
     * @param genero o gênero do jogo.
     * @param empresa a empresa que produziu o jogo.
     * @param qtdeJogadores a quantidade de jogadores necessária para o jogo.
     */
    Tabuleiro(String nome, int quantidade, double valor, String genero, String empresa, int qtdeJogadores) {
        super(nome, quantidade, valor, genero, empresa);
        this.qtdeJogadores = qtdeJogadores;
    }

    /**
     * Exibe as informações do jogo de tabuleiro em formato de string.
     *
     * @return uma string com as informações do jogo de tabuleiro.
     */
    @Override
    public String mostraInfo() {
        String s = String.format("""
                Código:%d
                Nome:%s
                Tipo: Tabuleiro
                Quantidade de Jogadores: %d
                Gênero: %s
                Empresa: %s
                Quantidade: %d
                Valor:%.2f""", getCodigo(), getNome(), getQtdeJogadores(), getGenero(), getEmpresa(), getQuantidade(), getValor());

        return s;
    }

    /**
     * Obtém a quantidade de jogadores necessária para o jogo de tabuleiro.
     *
     * @return a quantidade de jogadores.
     */
    public int getQtdeJogadores() {
        return qtdeJogadores;
    }
}
