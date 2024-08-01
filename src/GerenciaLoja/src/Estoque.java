import java.util.ArrayList;

import javax.swing.JOptionPane;

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
        System.out.println("Código do jogo:"+jogo.getCodigo()+", static:"+Jogo.getCodigoAtual());
        qtdeTab += jogo.getQuantidade();
    }

    public void adicionarJogoDig(Jogo jogo){
        jogos.add(jogo);
        System.out.println("Código do jogo:"+jogo.getCodigo()+", static:" + Jogo.getCodigoAtual());
        qtdeDig += jogo.getQuantidade();
    }


    public void removerJogo(int codigo){
        Jogo jogoAux = getJogo(codigo);

        if(jogoAux == null){
            JOptionPane.showMessageDialog(null,"Esse jogo não existe");
            return;
        }else{
            jogos.remove(jogoAux);

        if(jogoAux instanceof Tabuleiro)
            qtdeTab -= jogoAux.getQuantidade();
        else
            qtdeDig -= jogoAux.getQuantidade();
        }
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
                int novaQuantidade = jogo.getQuantidade() - quantidade;
                if(novaQuantidade >= 0){
                    jogo.setQuantidade(novaQuantidade);
                } else{
                    throw new IllegalArgumentException("Quantidade insuficiente no estoque!");
                }
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
        s.append("Jogos Digitais:" + this.qtdeDig + "\n\n");
        for(Jogo jogo : jogos){
            if(jogo instanceof Digital)
                s.append(jogo.mostraInfo()).append("\n\n");
        }

        s.append("\n\nJogos Digitais:" + this.qtdeTab + "\n\n");
        for(Jogo jogo : jogos){
            if(jogo instanceof Tabuleiro)
                s.append(jogo.mostraInfo()).append("\n");
        }

        return s.toString();
    }

    public ArrayList<Jogo> getJogos() {
        return jogos;
    }

    public int getQTab(){
        return qtdeTab;
    }
    public int getQDig(){
        return qtdeDig;
    }
}
