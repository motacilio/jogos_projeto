package com.example;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings("unused")
public class Funcionario extends Pessoa implements Serializable {

    private static int matriculaAtual = 1;
    protected int matricula;
    protected double salario;

    /**
     * Construtor para criar um novo funcionário com o nome, CPF e salário especificados.
     *
     * @param nome o nome do funcionário.
     * @param cpf o CPF do funcionário.
     * @param salario o salário do funcionário.
     */
    Funcionario(String nome, String cpf, double salario) {
        super(nome, cpf);
        this.salario = salario;
        matricula = matriculaAtual++;
    }

    /**
     * Construtor padrão.
     */
    Funcionario() {}

    /**
     * Exibe as informações do funcionário no console.
     */
    public void mostrarInfo() {
        System.out.println("Nome: " + this.getNome() + "\nCPF: " + this.getCpf() +
                "\nSalario: " + this.getSalario());
    }

    /**
     * Retorna a matrícula do funcionário.
     *
     * @return a matrícula do funcionário.
     */
    public int getMatricula() {
        return matricula;
    }

    /**
     * Retorna o salário do funcionário.
     *
     * @return o salário do funcionário.
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Define o salário do funcionário.
     *
     * @param salario o novo salário do funcionário.
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * Define a matrícula do funcionário.
     *
     * @param matricula a nova matrícula do funcionário.
     */
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    /**
     * Define o valor estático da matrícula atual.
     *
     * @param matricula a nova matrícula atual.
     */
    public static void setMatriculaAtual(int matricula) {
        matriculaAtual = matricula;
    }
}
