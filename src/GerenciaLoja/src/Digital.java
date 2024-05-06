public class Digital extends Jogo{
    protected String plataforma;
    protected String conectividade;

    Digital(String nome, int quantidade, double valor, String genero, String empresa,
            String plataforma, String conectividade){
        super(nome, quantidade, valor, genero, empresa);
        this.conectividade = conectividade;
        this.plataforma = plataforma;
    }

    public void mostrarInfo(){
        String s = String.format("""
                Nome:%s
                Tipo: Digital
                Plataforma: %s
                Conectividade: %s
                Gênero: %s
                Empresa: %s
                Quantidade: %d
                Valor:%f""", getNome(), getPlataforma(), getConectividade(), getGenero(), getEmpresa(), getQuantidade(), getValor());

        System.out.println(s);
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getConectividade() {
        return conectividade;
    }
}
