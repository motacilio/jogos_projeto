import java.io.Serializable;
import java.time.LocalDate;

public class Funcionario extends Pessoa implements Serializable {

    private static int matriculaAtual = 0;
    protected int matricula;
    protected double salario;

    Funcionario (String nome, String cpf, double salario) {
        super(nome, cpf);
        this.salario = salario;
        matricula = matriculaAtual++;
    }

    Funcionario(){}

    public void mostrarInfo(){
        System.out.println("Nome: " + this.getNome() + "\nCPF: " + this.getCpf()+
                "\nSalario: " + this.getSalario());
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
}
