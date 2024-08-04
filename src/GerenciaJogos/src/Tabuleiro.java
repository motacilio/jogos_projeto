
/**
 * Classe que representa um jogo de tabuleiro, extendendo a classe {@link Jogo}.
 * Adiciona a característica específica de quantidade de jogadores.
 */
public class Tabuleiro extends Jogo {
    protected int qtdeJogadores;

    /**
     * Construtor que inicializa um jogo de tabuleiro com as informações fornecidas.
     *
     * @param nome o nome do jogo
     * @param quantidade a quantidade disponível do jogo
     * @param valor o valor do jogo
     * @param genero o gênero do jogo
     * @param empresa a empresa que produziu o jogo
     * @param qtdeJogadores a quantidade de jogadores para o jogo de tabuleiro
     */
    Tabuleiro(String nome, int quantidade, double valor, String genero, String empresa, int qtdeJogadores) {
        super(nome, quantidade, valor, genero, empresa);
        this.qtdeJogadores = qtdeJogadores;
    }

    /**
     * Retorna uma representação textual das informações do jogo de tabuleiro.
     * 
     * @return uma string com as informações do jogo de tabuleiro
     */
    @Override
    public String mostraInfo() {
        String s = String.format("""
                Código:%d
                Nome:%s
                Tipo: Tabuleiro
                Quantidade de Jogadores: %d
                Gênero: %s
                Empresa: %s
                Quantidade: %d
                Valor:%.2f""",
                getCodigo(), getNome(), getQtdeJogadores(), getGenero(), getEmpresa(), getQuantidade(), getValor());

        return s;
    }
    public int getQtdeJogadores() {
        return qtdeJogadores;
    }
}
