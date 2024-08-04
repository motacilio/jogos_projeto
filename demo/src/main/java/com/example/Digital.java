package com.example;

/**
 * Classe que representa um jogo digital.
 */
public class Digital extends Jogo {
    protected String plataforma;
    protected String conectividade;

    /**
     * Construtor para criar um novo jogo digital.
     *
     * @param nome o nome do jogo.
     * @param quantidade a quantidade disponível.
     * @param valor o valor do jogo.
     * @param genero o gênero do jogo.
     * @param empresa a empresa que desenvolveu o jogo.
     * @param plataforma a plataforma em que o jogo está disponível.
     * @param conectividade a conectividade necessária para o jogo.
     */
    Digital(String nome, int quantidade, double valor, String genero, String empresa, String plataforma, String conectividade) {
        super(nome, quantidade, valor, genero, empresa);
        this.conectividade = conectividade;
        this.plataforma = plataforma;
    }

    /**
     * Método que retorna uma string com as informações do jogo.
     *
     * @return uma string formatada com as informações do jogo.
     */
    public String mostraInfo() {
        String s = String.format("""
                Código: %d
                Nome: %s
                Tipo: Digital
                Plataforma: %s
                Conectividade: %s
                Gênero: %s
                Empresa: %s
                Quantidade: %d
                Valor: %.2f""", getCodigo(), getNome(), getPlataforma(), getConectividade(), getGenero(), getEmpresa(), getQuantidade(), getValor());

        return s;
    }

    /**
     * Retorna a plataforma do jogo.
     *
     * @return a plataforma do jogo.
     */
    public String getPlataforma() {
        return plataforma;
    }

    /**
     * Retorna a conectividade do jogo.
     *
     * @return a conectividade do jogo.
     */
    public String getConectividade() {
        return conectividade;
    }
}
