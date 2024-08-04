import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    protected int numCompras;
    protected int numAluguel;
    List<Aluguel> alugueis = new ArrayList<>();
    List<Jogo> compras = new ArrayList<>();

    Cliente (String nome, String cpf) {
        super(nome, cpf);
        this.numCompras = 0;
        this.numAluguel = 0;
    }

    // Métodos Extras

    public void adicionarCompra(Jogo jogo, int quantidade){
        for(int i = 0; i < quantidade; i++){
            compras.add(jogo);
        }
        numCompras += quantidade;
    }

    public int qtdeAtrasos(){
        int num = 0;
        for(Aluguel aluguel : alugueis){
            if(aluguel.atraso()){
                num++;
            }
        }
        return num;
    }

    // public boolean solicitarAluguel(int codigo, Vendedor vendedor, Estoque estoque){
    //     return vendedor.processarAluguel(this, codigo, estoque);
    // }

    public double devolver(Estoque estoque, int codigo) {
        for(Aluguel aluguel: alugueis){
            if(aluguel.getCodigo() == codigo){
                if(aluguel.atraso()){
                    double multa = aluguel.verificarMulta();
                    estoque.adicionarQuantidadeEstoque(aluguel.getJogo().getCodigo(), 1);  
                    alugueis.remove(aluguel);
                    return multa;
                }
                estoque.adicionarQuantidadeEstoque(aluguel.getJogo().getCodigo(), 1); 
                alugueis.remove(aluguel);
                return 0;
            }
        }
        return -1;
    }

   /* public int renovarAluguel(int codigoAluguel) {
        for(Aluguel aluguel: alugueis){
            if(aluguel.atraso()){
                JOptionPane.showConfirmDialog(null, String.format("Você terá que pagar R$%f de multa para renovar", aluguel.verificarMulta()));
                this.devolver(Estoque.getInstance(), codigoAluguel);

            }
        }
    } */

    public double valorMulta(){
        double multa = 0;
        if(qtdeAtrasos() > 0){
            for(Aluguel aluguel : alugueis){
                if(aluguel.atraso()){
                    multa = aluguel.verificarMulta();
                }
            }
        }
        return multa;
    }


    public void mostrarAlugueis() {
        for(Aluguel aluguel: alugueis){
            System.out.println(aluguel.jogo.getNome());
        }
    }

    public void adicionarAluguel(Aluguel aluguel){
        if(aluguel != null){
            this.alugueis.add(aluguel);
        } else{
            JOptionPane.showMessageDialog(null, "Falha ao adicionar aluguel");
        }
    }

    public Aluguel obterAluguel(int codAluguel) {
        for (Aluguel a:alugueis) {
            if (a.getCodigo() == codAluguel) {
                return a;
            }
        }
        
        return null;
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
