public class Tabuleiro extends Jogo{
    protected int qtdeJogadores;

    Tabuleiro(String nome, int quantidade, double valor, String genero, String empresa, int qtdeJogadores){
        super(nome, quantidade, valor, genero, empresa);
        this.qtdeJogadores = qtdeJogadores;
    }

    public String mostraInfo(){
        String s = String.format("""
                Nome:%s
                Tipo: Tabuleiro
                Quantidade de Jogadores: %d
                Gênero: %s
                Empresa: %s
                Quantidade: %d
                Valor:%.2f""", getNome(), getQtdeJogadores(), getGenero(), getEmpresa(), getQuantidade(), getValor());

        return(s);
    }

    public int getQtdeJogadores() {
        return qtdeJogadores;
    }
}
