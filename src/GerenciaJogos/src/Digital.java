

/**
 * Classe para os jogos digitais, que herdam da classe JOGO
 */
public class Digital extends Jogo{
    protected String plataforma;
    protected String conectividade;


    /**
     * 
     * @param nome
     * @param quantidade
     * @param valor
     * @param genero
     * @param empresa
     * @param plataforma
     * @param conectividade
     */
    Digital(String nome, int quantidade, double valor, String genero, String empresa,
            String plataforma, String conectividade){
        super(nome, quantidade, valor, genero, empresa);
        this.conectividade = conectividade;
        this.plataforma = plataforma;
    }

    /**
     * Mostra as informações do jogo digital
     */
    public String mostraInfo(){
        String s = String.format("""
                Código:%d
                Nome:%s
                Tipo: Digital
                Plataforma: %s
                Conectividade: %s
                Gênero: %s
                Empresa: %s
                Quantidade: %d
                Valor:%.2f""",getCodigo(), getNome(), getPlataforma(), getConectividade(), getGenero(), getEmpresa(), getQuantidade(), getValor());

        return(s);
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getConectividade() {
        return conectividade;
    }
}
