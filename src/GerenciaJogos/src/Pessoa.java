import java.io.Serializable;

public  class Pessoa implements Serializable {
    protected static int codigoAtual = 0;
    protected int codigo;
    protected String nome;
    protected String cpf;

    Pessoa(String nome, String cpf) {
        this.codigo = ++codigoAtual;
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

    public int getCodigo(){
        return codigo;
    }



    // Métodos Sets

    public static void setCodigoAtual(int c){
        codigoAtual = c;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
