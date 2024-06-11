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

    public void mostrarInfo(){
        System.out.println("Nome: " + this.getNome() + "\nCPF: " + this.getCpf()+
                "\nData de admissao: " + this.getDataAdmissao() + "\nSalario: " + this.getSalario());
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
