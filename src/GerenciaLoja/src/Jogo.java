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

    public String mostraInfo(){
        String s = ("Nome do jogo: " + nome + "\nValor: " + valor + "\nQuantidade do jogo: " + quantidade +
                '\n' + "Genero: " + genero + "\nEmpresa: " + empresa);

    return s;
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

    public static void setCodigoAtual(int codigoAtual) {
        Jogo.codigoAtual = codigoAtual;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade -= quantidade;
    }
}
