package com.example;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que representa um aluguel de jogo.
 */
public class Aluguel implements Serializable {
    protected static int codigoAtual = 0;
    protected int codigo;
    protected Cliente cliente;
    protected Jogo jogo;
    protected final int tempoDevDias = 3;
    protected LocalDate dataAluguel;

    /**
     * Construtor para criar um novo aluguel.
     *
     * @param jogo o jogo a ser alugado.
     */
    Aluguel(Jogo jogo) {
        this.codigo = 10 + codigoAtual;
        this.jogo = jogo;
        this.dataAluguel = LocalDate.now();
        codigoAtual += 10;
    }

    /**
     * Calcula a data de devolução do aluguel.
     *
     * @return a data de devolução.
     */
    private LocalDate dataDevolucao() {
        return dataAluguel.plusDays(tempoDevDias);
    }

    /**
     * Verifica se houve atraso na devolução do jogo.
     *
     * @return true se houve atraso, false caso contrário.
     */
    public boolean atraso() {
        return LocalDate.now().isAfter(dataDevolucao());
    }

    /**
     * Calcula a multa por atraso na devolução do jogo.
     *
     * @return o valor da multa.
     */
    public double verificarMulta() {
        if (atraso()) {
            long diasDeAtraso = LocalDate.now().toEpochDay() - dataDevolucao().toEpochDay();
            return 3.5 * diasDeAtraso;
        } else {
            return 0;
        }
    }

    /**
     * Retorna as informações do aluguel em formato de string.
     *
     * @return uma string com as informações do aluguel.
     */
    public String mostraAluguel() {
        StringBuilder s = new StringBuilder();
        s.append("Código: ").append(codigo)
         .append("\nCliente: ").append(cliente.getNome()).append(" - CPF: ").append(cliente.getCpf())
         .append("\nJogo: ").append(jogo.getNome())
         .append("\nData de aluguel: ").append(dataAluguel)
         .append("\nAtraso: ").append(atraso())
         .append("\nMulta: ").append(verificarMulta()).append("\n");
        return s.toString();
    }

    /**
     * Retorna o jogo alugado.
     *
     * @return o jogo alugado.
     */
    public Jogo getJogo() {
        return jogo;
    }

    /**
     * Retorna o código do aluguel.
     *
     * @return o código do aluguel.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Retorna a data do aluguel.
     *
     * @return a data do aluguel.
     */
    public LocalDate getDataAluguel() {
        return this.dataAluguel;
    }

    /**
     * Define o cliente que realizou o aluguel.
     *
     * @param cliente o cliente que realizou o aluguel.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Define a data do aluguel.
     *
     * @param ano o ano do aluguel.
     * @param mes o mês do aluguel.
     * @param dia o dia do aluguel.
     */
    public void setDataAluguel(int ano, int mes, int dia) {
        this.dataAluguel = LocalDate.of(ano, mes, dia);
    }

    /**
     * Define o código atual de aluguel.
     *
     * @param codigo o novo código atual.
     */
    public static void setCodigoAtual(int codigo) {
        Aluguel.codigoAtual = codigo;
    }
}
