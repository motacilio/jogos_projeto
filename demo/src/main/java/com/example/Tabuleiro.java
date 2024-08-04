package com.example;

public class Tabuleiro extends Jogo{
    protected int qtdeJogadores;

    Tabuleiro(String nome, int quantidade, double valor, String genero, String empresa, int qtdeJogadores){
        super(nome, quantidade, valor, genero, empresa);
        this.qtdeJogadores = qtdeJogadores;
    }

    @Override
    public String mostraInfo(){
        String s = String.format("""
                Código:%d
                Nome:%s
                Tipo: Tabuleiro
                Quantidade de Jogadores: %d
                Gênero: %s
                Empresa: %s
                Quantidade: %d
                Valor:%.2f""", getCodigo(),getNome(), getQtdeJogadores(), getGenero(), getEmpresa(), getQuantidade(), getValor());

        return(s);
    }

    public int getQtdeJogadores() {
        return qtdeJogadores;
    }
}
