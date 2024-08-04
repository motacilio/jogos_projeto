package com.example;

import java.io.Serializable;

/**
 * A classe abstrata Jogo representa um jogo genérico, com atributos comuns para jogos
 * de tabuleiro e digitais.
 */
public abstract class Jogo implements Serializable {

    protected static int codigoAtual = 0;
    protected final int codigo;
    protected int quantidade;
    protected String nome;
    protected String genero;
    protected String empresa;
    protected double valor;

    /**
     * Construtor para criar um novo jogo com os atributos especificados.
     *
     * @param nome o nome do jogo.
     * @param quantidade a quantidade disponível do jogo.
     * @param valor o valor do jogo.
     * @param genero o gênero do jogo.
     * @param empresa a empresa que produz o jogo.
     */
    Jogo(String nome, int quantidade, double valor, String genero, String empresa) {
        this.codigo = ++codigoAtual;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.genero = genero;
        this.empresa = empresa;
    }

    /**
     * Método abstrato para mostrar as informações do jogo.
     *
     * @return uma string contendo as informações do jogo.
     */
    public abstract String mostraInfo();

    /**
     * Obtém o código atual utilizado para identificar jogos.
     *
     * @return o código atual.
     */
    public static int getCodigoAtual() {
        return codigoAtual;
    }

    /**
     * Obtém o código do jogo.
     *
     * @return o código do jogo.
     */
    public int getCodigo() {
        return this.codigo;
    }

    /**
     * Obtém a quantidade disponível do jogo.
     *
     * @return a quantidade disponível.
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Obtém o nome do jogo.
     *
     * @return o nome do jogo.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o gênero do jogo.
     *
     * @return o gênero do jogo.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Obtém a empresa que produz o jogo.
     *
     * @return a empresa que produz o jogo.
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Obtém o valor do jogo.
     *
     * @return o valor do jogo.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o código atual utilizado para identificar jogos.
     *
     * @param codigoAtual o novo código atual.
     */
    public static void setCodigoAtual(int codigoAtual) {
        Jogo.codigoAtual = codigoAtual;
    }

    /**
     * Define o nome do jogo.
     *
     * @param nome o novo nome do jogo.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define o gênero do jogo.
     *
     * @param genero o novo gênero do jogo.
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Define a empresa que produz o jogo.
     *
     * @param empresa a nova empresa que produz o jogo.
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * Define o valor do jogo.
     *
     * @param valor o novo valor do jogo.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Define a quantidade disponível do jogo.
     *
     * @param quantidade a nova quantidade disponível.
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Adiciona uma quantidade ao estoque do jogo.
     *
     * @param quantidade a quantidade a ser adicionada.
     */
    public void somarQuant(int quantidade) {
        this.quantidade += quantidade;
    }
}
