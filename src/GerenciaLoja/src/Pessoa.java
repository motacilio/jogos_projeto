import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public  class Pessoa implements Serializable {
    protected static int countCodigo = 0;
    protected int codigo;
    protected String nome;
    protected String cpf;
    ArrayList<Endereco> enderecos = new ArrayList<Endereco>();

    Pessoa(String nome, String cpf) {
        this.codigo = ++countCodigo;
        this.nome = nome;
        this.cpf = cpf;
    }

    Pessoa(){}

    // Métodos Gets
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }



    // Métodos Sets

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
