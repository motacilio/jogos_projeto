import java.time.LocalDate;
import java.util.Scanner;

public class Gerente extends Funcionario {

    protected double bonificacao;

    Gerente(String nome, String cpf, LocalDate dataNascimento, LocalDate dataAdmissao, double salario, double bonificacao){
        super(nome, cpf, dataNascimento, dataAdmissao, salario);
        this.bonificacao = bonificacao;
    }

    void modificarEstoque(Estoque estoque){
        System.out.println("Selecione a opcao desejada: \n" +
                "1 - Adicionar Jogo Tabuleiro\n" +
                "2 - Adicionar Jogo Digital\n" +
                "3 - Remover Tabuleiro\n" +
                "4 - Remover Digital\n");

    }

}
