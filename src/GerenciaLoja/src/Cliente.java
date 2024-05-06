import java.awt.dnd.DragGestureEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    protected int numCompras;
    protected int numAluguel;
    List<Aluguel> alugueis = new ArrayList<>();

    Cliente (String nome, String cpf, LocalDate dataNascimento) {
        super(nome, cpf, dataNascimento);
        this.numCompras = 0;
        this.numAluguel = 0;
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
        Aluguel aluguelAux = new Aluguel(codigo, LocalDate.now());

        if(this.qtdeAtrasos() > 0){
            return false;
        }

        for(Jogo jogo : estoque.jogos){
            if(jogo.codigo == codigo){
                jogoAux = jogo;
                break;
            }
        }

        int quantidade = jogoAux.getQuantidade();

        if(quantidade < 10){
            System.out.println("Impossível alugar!");
        }else{
            alugueis.add(aluguelAux);
            jogoAux.quantidade--;
        }

        return true;
    }

    private void devolver() {

    }

    public void renovarAluguel() {

    }

    public void comprar() {

    }

    private  int mostrarQntdAtrasos() {

    }

    public String mostrarAlugueis() {

    }


}
