import java.awt.dnd.DragGestureEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    protected int numCompras;
    protected int numAluguel;
    List<Aluguel> alugueis = new ArrayList<>();

    Cliente (String nome, String cpf) {
        super(nome, cpf);
        this.numCompras = 0;
        this.numAluguel = 0;
    }

    // Métodos Extras

    private int qtdeAtrasos(){
        int num = 0;
        for(Aluguel aluguel : alugueis){
            if(aluguel.atraso()){
                num++;
            }
        }
        return num;
    }

    public boolean alugar(Estoque estoque, int codigo) {
        Jogo jogoAux = null;

        if(this.qtdeAtrasos() > 0){
            return false;
        }

        for(Jogo jogo : estoque.jogos){
            if(jogo.codigo == codigo){
                jogoAux = jogo;
                break;
            }
        }

        Aluguel aluguelAux = new Aluguel(jogoAux, estoque);

        int quantidade = jogoAux.getQuantidade();

        if(quantidade < 10){
            return false;
        }else{
            alugueis.add(aluguelAux);
            jogoAux.quantidade--;
        }

        return true;
    }

    public double devolver(Estoque estoque, int codigo) {
        double multa = 0;
        if(qtdeAtrasos() > 0){
            for(Aluguel aluguel : alugueis){
                if(aluguel.atraso()){
                    multa = aluguel.verificarMulta();
                }
            }
            return multa;
        }else{
            return 0;
        }

    }

    public int renovarAluguel(int codigo, Estoque estoque) {
        if(this.qtdeAtrasos() > 0){
            return 1;
        }else{
            this.alugar(estoque, codigo);
        }
        return 0;
    }

    public void comprar(Estoque estoque, int codigo, int quant) {
        estoque.vender(codigo, quant);
    }

    public void mostrarAlugueis() {
        for(Aluguel aluguel: alugueis){
            System.out.println(aluguel.jogo.getNome());
        }
    }

    // Métodos Gets

    public int getNumCompras() {
        return numCompras;
    }

    public int getNumAluguel() {
        return numAluguel;
    }

    public List<Aluguel> getAlugueis() {
        return alugueis;
    }

    // Métodos Sets
    public void setNumCompras(int numCompras) {
        this.numCompras = numCompras;
    }

    public void setNumAluguel(int numAluguel) {
        this.numAluguel = numAluguel;
    }

    public void setAlugueis(List<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }


}
