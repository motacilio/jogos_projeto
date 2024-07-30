import java.util.ArrayList;

public class Estoque {
   private static Estoque instance;

    private int qtdeTab;
    private int qtdeDig;
    ArrayList<Jogo> jogos = new ArrayList<>();

    private Estoque() {
        this.qtdeTab = 0;
        this.qtdeDig = 0;
    }

    public static synchronized Estoque getInstance(){
        if(instance == null){
            instance = new Estoque();
        }
        return instance;
    }

    public void adicionarJogoTab(Jogo jogo){
        jogos.add(jogo);
        qtdeTab += jogo.getQuantidade();
    }

    public void adicionarJogoDig(Jogo jogo){
        jogos.add(jogo);
        qtdeDig += jogo.getQuantidade();
    }


    public void removerJogo(int codigo){
        Jogo jogoAux = null;

        for(Jogo jogo : jogos){
            if(jogo.codigo == codigo){
                jogoAux = jogo;
            }
        }

        jogos.remove(jogoAux);

        if(jogoAux instanceof Tabuleiro)
            qtdeTab--;
        else
            qtdeDig--;
    }


    public boolean verificarDisponibilidade(int codigo, int quantidade){
        for(Jogo jogo: jogos){
            if(jogo.getCodigo() == codigo && jogo.getQuantidade() >= quantidade){
               return true;
            }
        }
        return false;
    }


    public void atualizarEstoque(int codigo, int quantidade){
        for(Jogo jogo: jogos){
            if(jogo.getCodigo() == codigo){
                jogo.setQuantidade(jogo.getQuantidade() - quantidade);
                break;
            }
        }
    }

    public Jogo getJogo(int codigo) {
        for (Jogo jogo : jogos) {
            if (jogo.getCodigo() == codigo) {
                return jogo;
            }
        }
        return null;
    }

    public String mostrarJogos(){
        StringBuilder s = new StringBuilder();

        for(Jogo jogo : jogos){
            s.append("Jogos Digitais\n\n");
            if(jogo instanceof Digital)
                s.append(jogo.mostraInfo()).append("\n");
        }

        for(Jogo jogo : jogos){
            s.append("\n\nJogos de Tabuleiro\n\n");
            if(jogo instanceof Tabuleiro)
                s.append(jogo.mostraInfo()).append("\n");
        }

        return s.toString();
    }

    public ArrayList<Jogo> getJogos() {
        return jogos;
    }
}
