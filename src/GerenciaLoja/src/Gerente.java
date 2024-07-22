import java.time.LocalDate;
import java.util.Scanner;

public class Gerente extends Funcionario {

    protected double comissao;

    Gerente(String nome, String cpf, LocalDate dataNascimento, LocalDate dataAdmissao, double salario){
        super(nome, cpf, dataNascimento, dataAdmissao, salario);
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public void modificarSalario(){
        this.salario += comissao;
    }

    void modificarEstoque(Estoque estoque){
        System.out.println("Selecione a opcao desejada: \n" +
                "1 - Adicionar Jogo Tabuleiro\n" +
                "2 - Adicionar Jogo Digital\n" +
                "3 - Remover Tabuleiro\n" +
                "4 - Remover Digital\n");

        Scanner ent = new Scanner(System.in);
        int opcao = ent.nextInt();

        if(opcao == 1){
            System.out.println("Digite os dados do  jogo de tabuleiro");
            System.out.println("Nome: ");
            String nome = ent.nextLine();
            System.out.println("Quantidade em estoque: ");
            int quantidade = ent.nextInt();
            System.out.println("Valor: ");
            double valor = ent.nextDouble();
            System.out.println("Genêro: ");
            String genero = ent.nextLine();
            System.out.println("Empresa: ");
            String empresa = ent.nextLine();
            System.out.println("Quantidade de Jogadores: ");
            int qnt_jogadores = ent.nextInt();

            Tabuleiro jogoTabNovo = new Tabuleiro(nome, quantidade, valor, genero, empresa, qnt_jogadores);
            estoque.adicionarJogoTab(jogoTabNovo);
        } else if(opcao == 2){
            System.out.println("Digite os dados do  jogo digital");
            System.out.println("Nome: ");
            String nome = ent.nextLine();

            System.out.println("Quantidade em estoque: ");
            int quantidade = ent.nextInt();

            System.out.println("Valor: ");
            double valor = ent.nextDouble();

            System.out.println("Genêro: ");
            String genero = ent.nextLine();

            System.out.println("Empresa: ");
            String empresa = ent.nextLine();

            System.out.println("Plataforma: ");
            String plataforma = ent.nextLine();

            System.out.println("Conectividade: ");
            String conectividade = ent.nextLine();

            Digital jogoDigNovo = new Digital(nome, quantidade, valor, genero,
                    empresa, plataforma, conectividade);
            estoque.adicionarJogoDig(jogoDigNovo);
        } else if (opcao == 3){
            System.out.println("Digite o codigo do jogo de tabuleiro: ");
            int codigo = ent.nextInt();
            estoque.removerJogoTab(codigo);
        } else if(opcao == 4){
            System.out.println("Digite o codigo do jogo digital");
            int codigo = ent.nextInt();
            estoque.removerJogoDig(codigo);
        }

        ent.close();
    }
}
