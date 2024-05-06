public class Tabuleiro extends Jogo{
    protected int qtdeJogadores;

    Tabuleiro(String nome, int quantidade, double valor, String genero, String empresa, int qtdeJogadores){
        super(nome, quantidade, valor, genero, empresa);
        this.qtdeJogadores = qtdeJogadores;
    }

    public void mostrarInfo(){
        String s = String.format("""
                Nome:%s
                Tipo: Tabuleiro
                Quantidade de Jogadores: %d
                Gênero: %s
                Empresa: %s
                Quantidade: %d
                Valor:%f""", getNome(), getQtdeJogadores(), getGenero(), getEmpresa(), getQuantidade(), getValor());

        System.out.println(s);
    }

    public int getQtdeJogadores() {
        return qtdeJogadores;
    }
}
