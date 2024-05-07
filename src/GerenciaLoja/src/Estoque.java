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
        qtdeTab += jogo.getQuantidade();
    }

    public void adicionarJogoDig(Jogo jogo){
        jogos.add(jogo);
        qtdeDig += jogo.getQuantidade();
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

    public int vender(int codigo){
        Jogo jogoAux = null;

        for(Jogo jogo : jogos){
            if(jogo.codigo == codigo){
                jogoAux = jogo;
            }
        }
        if(jogoAux.getQuantidade() > 0){
            jogoAux.setQuantidade(-1);
            return 0;
        }else {
            return 1;
        }
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
