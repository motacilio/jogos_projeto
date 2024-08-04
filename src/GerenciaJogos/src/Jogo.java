

import java.io.Serializable;

/**
 * Classe abstrata que representa um jogo.
 * Atributos comuns a todos os jogos, como código, nome, quantidade, gênero, empresa e valor.
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
     * Construtor que inicializa um jogo com as informações fornecidas.
     *
     * @param nome o nome do jogo
     * @param quantidade a quantidade disponível do jogo
     * @param valor o valor do jogo
     * @param genero o gênero do jogo
     * @param empresa a empresa que produziu o jogo
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
     * Deve ser implementado pelas subclasses para fornecer detalhes específicos.
     *
     * @return uma string com as informações do jogo
     */
    public abstract String mostraInfo();

    public static int getCodigoAtual() {
        return codigoAtual;
    }

    public int getCodigo() {
        return this.codigo;
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
        this.quantidade = quantidade;
    }

    public void somarQuant(int quantidade) {
        this.quantidade += quantidade;
    }
}
