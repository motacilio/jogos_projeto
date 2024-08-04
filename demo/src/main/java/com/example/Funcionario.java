package com.example;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings("unused")
public class Funcionario extends Pessoa implements Serializable {

    private static int matriculaAtual = 1;
    protected int matricula;
    protected double salario;

    Funcionario (String nome, String cpf, double salario) {
        super(nome, cpf);
        this.salario = salario;
        matricula = matriculaAtual++;
    }

    Funcionario(){}


    public void mostrarInfo(){
        System.out.println("Nome: " + this.getNome() + "\nCPF: " + this.getCpf()+
                "\nSalario: " + this.getSalario());
    }

    public int getMatricula() {
        return matricula;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public static void setMatriculaAtual(int matricula){
        matriculaAtual = matricula;
    }
}

