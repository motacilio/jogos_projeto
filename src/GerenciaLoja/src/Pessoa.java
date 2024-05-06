import java.time.LocalDate;
import java.util.ArrayList;

public class Pessoa {
    protected static int countCodigo = 0;
    protected int codigo;
    protected String nome;
    protected String cpf;
    protected LocalDate dataNascimento;
    ArrayList<Endereco> enderecos = new ArrayList<Endereco>();

    Pessoa(String nome, String cpf, LocalDate dataNascimento) {
        this.codigo = ++countCodigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    // Métodos Gets
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }


    // Métodos Sets

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Métodos Extra
    public void addEndereco(String estado, String cidade, String logradouro, String setor, String cep) {
        enderecos.add(new Endereco(estado, cidade, logradouro, setor, cep));
    }

    public void mostraEndereco() {
        for(Endereco endereco: enderecos){
            System.out.println(endereco);
        }
    }

}
