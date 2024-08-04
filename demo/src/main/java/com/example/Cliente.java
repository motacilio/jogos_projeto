package com.example;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um cliente.
 */
public class Cliente extends Pessoa {
    protected int numCompras;
    protected int numAluguel;
    List<Aluguel> alugueis = new ArrayList<>();
    List<Jogo> compras = new ArrayList<>();

    /**
     * Construtor para criar um novo cliente.
     *
     * @param nome o nome do cliente.
     * @param cpf o CPF do cliente.
     */
    Cliente(String nome, String cpf) {
        super(nome, cpf);
        this.numCompras = 0;
        this.numAluguel = 0;
    }

    /**
     * Adiciona uma compra ao cliente.
     *
     * @param jogo o jogo comprado.
     * @param quantidade a quantidade de jogos comprados.
     */
    public void adicionarCompra(Jogo jogo, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            compras.add(jogo);
        }
        numCompras += quantidade;
    }

    /**
     * Calcula a quantidade de atrasos nos aluguéis.
     *
     * @return o número de atrasos.
     */
    public int qtdeAtrasos() {
        int num = 0;
        for (Aluguel aluguel : alugueis) {
            if (aluguel.atraso()) {
                num++;
            }
        }
        return num;
    }

    /**
     * Devolve um jogo alugado e calcula a multa, se houver.
     *
     * @param estoque o estoque de jogos.
     * @param codigo o código do aluguel.
     * @return o valor da multa ou -1 se o aluguel não for encontrado.
     */
    public double devolver(Estoque estoque, int codigo) {
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getCodigo() == codigo) {
                if (aluguel.atraso()) {
                    double multa = aluguel.verificarMulta();
                    estoque.adicionarQuantidadeEstoque(aluguel.getJogo().getCodigo(), 1);
                    alugueis.remove(aluguel);
                    return multa;
                }
                estoque.adicionarQuantidadeEstoque(aluguel.getJogo().getCodigo(), 1);
                alugueis.remove(aluguel);
                return 0;
            }
        }
        return -1;
    }

    /**
     * Calcula o valor total das multas por atraso.
     *
     * @return o valor total das multas.
     */
    public double valorMulta() {
        double multa = 0;
        if (qtdeAtrasos() > 0) {
            for (Aluguel aluguel : alugueis) {
                if (aluguel.atraso()) {
                    multa = aluguel.verificarMulta();
                }
            }
        }
        return multa;
    }

    /**
     * Exibe os nomes dos jogos alugados.
     */
    public void mostrarAlugueis() {
        for (Aluguel aluguel : alugueis) {
            System.out.println(aluguel.jogo.getNome());
        }
    }

    /**
     * Adiciona um novo aluguel ao cliente.
     *
     * @param aluguel o novo aluguel.
     */
    public void adicionarAluguel(Aluguel aluguel) {
        if (aluguel != null) {
            this.alugueis.add(aluguel);
        } else {
            JOptionPane.showMessageDialog(null, "Falha ao adicionar aluguel");
        }
    }

    /**
     * Obtém um aluguel pelo código.
     *
     * @param codAluguel o código do aluguel.
     * @return o aluguel correspondente ou null se não for encontrado.
     */
    public Aluguel obterAluguel(int codAluguel) {
        for (Aluguel a : alugueis) {
            if (a.getCodigo() == codAluguel) {
                return a;
            }
        }
        return null;
    }

    /**
     * Retorna o número de compras do cliente.
     *
     * @return o número de compras.
     */
    public int getNumCompras() {
        return numCompras;
    }

    /**
     * Retorna o número de aluguéis do cliente.
     *
     * @return o número de aluguéis.
     */
    public int getNumAluguel() {
        return numAluguel;
    }

    /**
     * Retorna a lista de aluguéis do cliente.
     *
     * @return a lista de aluguéis.
     */
    public List<Aluguel> getAlugueis() {
        return alugueis;
    }

    /**
     * Define o número de compras do cliente.
     *
     * @param numCompras o novo número de compras.
     */
    public void setNumCompras(int numCompras) {
        this.numCompras = numCompras;
    }

    /**
     * Define o número de aluguéis do cliente.
     *
     * @param numAluguel o novo número de aluguéis.
     */
    public void setNumAluguel(int numAluguel) {
        this.numAluguel = numAluguel;
    }

    /**
     * Define a lista de aluguéis do cliente.
     *
     * @param alugueis a nova lista de aluguéis.
     */
    public void setAlugueis(List<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }
}
