import java.util.ArrayList;

public class Estoque {
    private int qtdeTab;
    private int qtdeDig;
    ArrayList<Jogo> jogos = new ArrayList<>();

    Estoque() {
        this.qtdeTab = 0;
        this.qtdeDig = 0;
    }

    public void adicionarJogoTab(Jogo jogo){
        jogos.add(jogo);
        qtdeTab++;
    }

    public void adicionarJogoDig(Jogo jogo){
        jogos.add(jogo);
        qtdeDig++;
    }

    public void removerJogoTab(int codigo){
        Jogo jogoAux = null;

        for(Jogo jogo : jogos){
            if(jogo.codigo == codigo){
                jogoAux = jogo;
            }
        }

        jogos.remove(jogoAux);
        qtdeTab--;
    }

    public void removerJogoDig(int codigo){
        Jogo jogoAux = null;

        for(Jogo jogo : jogos){
            if(jogo.codigo == codigo){
                jogoAux = jogo;
            }
        }

        jogos.remove(jogoAux);
        qtdeTab--;
    }

//    public String mostrarJogos(){
//
//    }
}
