

public class Endereco {

    protected String estado;
    protected String cidade;
    protected String logradouro;
    protected String setor;
    protected String complemento;
    protected String cep;

    Endereco(String estado, String cidade, String logradouro, String setor, String cep){
        this.estado = estado;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.setor = setor;
        this.cep = cep;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String toString() {
        String info = "Logradouro: " + this.logradouro + "\n";
        info += "Setor: " + this.setor + "\n";
        info += "Cidade: " + this.cidade + "\n";
        info += "Estado: " + this.estado + "\n";
        info += "Cep: " + this.cep + "\n";

        return info;
    }
}
