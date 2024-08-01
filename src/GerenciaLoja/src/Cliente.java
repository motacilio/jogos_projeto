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

    private int qtdeAtrasos(){
        int num = 0;
        for(Aluguel aluguel : alugueis){
            if(aluguel.atraso()){
                num++;
            }
        }
        return num;
    }

    public boolean solicitarAluguel(int codigo, Vendedor vendedor, Estoque estoque){
        return vendedor.processarAluguel(this, codigo, estoque);
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

    public int renovarAluguel(int codigoAluguel) {
        if (this.qtdeAtrasos() > 0) {
            return 1;
            // cliente possui alugueis atrasados
        }

        for (Aluguel aluguel : alugueis) {
            if (aluguel.getCodigo() == codigoAluguel) {
                aluguel.renovar();
                //Sucesso
                return 0;
            }
        }
        //Aluguel nao encontrado
        return 2;
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
