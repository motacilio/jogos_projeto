import java.util.ArrayList;

public class Jogo {
    protected static int codigoAtual = 0;
    protected int codigo;
    protected int quantidade;
    protected String nome;
    protected String genero;
    protected String empresa;
    protected double valor;

    Jogo(String nome, int quantidade, double valor, String genero, String empresa){
        this.codigo = codigoAtual++;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.genero = genero;
        this.empresa = empresa;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public String getEmpresa() {
        return empresa;
    }

    public double getValor() {
        return valor;
    }
}
