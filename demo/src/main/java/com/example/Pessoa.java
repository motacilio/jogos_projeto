package com.example;

import java.io.Serializable;

/**
 * A classe Pessoa representa uma pessoa genérica com atributos básicos como nome, CPF e código.
 */
public class Pessoa implements Serializable {

    protected static int codigoAtual = 0;
    protected int codigo;
    protected String nome;
    protected String cpf;

    /**
     * Construtor para criar uma nova pessoa com os atributos especificados.
     *
     * @param nome o nome da pessoa.
     * @param cpf o CPF da pessoa.
     */
    Pessoa(String nome, String cpf) {
        this.codigo = ++codigoAtual;
        this.nome = nome;
        this.cpf = cpf;
    }

    /**
     * Construtor padrão.
     */
    Pessoa() {}

    /**
     * Obtém o nome da pessoa.
     *
     * @return o nome da pessoa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o CPF da pessoa.
     *
     * @return o CPF da pessoa.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Obtém o código da pessoa.
     *
     * @return o código da pessoa.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Define o código atual utilizado para identificar pessoas.
     *
     * @param c o novo código atual.
     */
    public static void setCodigoAtual(int c) {
        codigoAtual = c;
    }

    /**
     * Define o CPF da pessoa.
     *
     * @param cpf o novo CPF da pessoa.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Define o nome da pessoa.
     *
     * @param nome o novo nome da pessoa.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
