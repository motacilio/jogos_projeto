import java.time.LocalDate;

public class Funcionario extends Pessoa {

    private static int matriculaAtual = 0;
    protected int matricula;
    protected LocalDate dataAdmissao;
    protected double salario;

    Funcionario (String nome, String cpf, LocalDate dataNascimento, LocalDate dataAdmissao, double salario) {
        super(nome, cpf, dataNascimento);
        this.dataAdmissao = dataAdmissao;
        this.salario = salario;
        matricula = matriculaAtual++;
    }

    void modificarSalario(double salario) {
        this.salario = salario;
    }


}
