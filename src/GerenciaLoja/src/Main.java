import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static Gerente instanciaGerente(){
        try{
            Gerente gerente;
            FileInputStream fx = new FileInputStream("Gerente.txt");
            ObjectInputStream os = new ObjectInputStream(fx);

            gerente = (Gerente)os.readObject();
            os.close();
            fx.close();

            return gerente;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void gravaGerente(Gerente gerente) throws IOException {
        FileOutputStream fx = new FileOutputStream("Gerente.txt");
        ObjectOutputStream ox = new ObjectOutputStream(fx);

        ox.writeObject(gerente);

        ox.close();
        fx.close();
    }

    public static void gravaVendedor(Vendedor vendedor) throws IOException {
        try{
            boolean append = new File("vend.txt").exists();
            FileOutputStream fx = new FileOutputStream("vend.txt", true);
            ObjectOutputStream ox = append ? new AppendingObjectOutputStream(fx) : new ObjectOutputStream(fx);

            ox.writeObject(vendedor);
            ox.close();
            fx.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public static ArrayList<Vendedor> instanciaFuncs() throws IOException {
        ArrayList<Vendedor> v = new ArrayList<>();
        Vendedor vend;
            FileInputStream fx = new FileInputStream("vend.txt");
            ObjectInputStream ox = new ObjectInputStream(fx);

            while (true) {
                try {
                    vend = (Vendedor) ox.readObject();
                    v.add(vend);
                } catch (EOFException | ClassNotFoundException e) {
                    return null;
                }

                return v;
            }
    }

    public static Cliente encontraCliente(String cpf, List<Cliente> clientes){
        for(Cliente cliente: clientes){
            if(cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {

        Estoque estoque = Estoque.getInstance();
        List<Cliente> clientes = new ArrayList<>();


        gravaGerente(new Gerente("Bruno", "9876", 6000));
        Gerente gerente = instanciaGerente();
        gerente.setMatricula(1);

        //ArrayList<Vendedor> vendedores = instanciaFuncs();
        // lista temporaria enquanto nao arruma a porra dos arquivos
        ArrayList<Vendedor> vendedores = new ArrayList<>();

        vendedores.add(new Vendedor("Julio", "123", 2000));
        System.out.println("Gerente\n"+gerente.getNome() +"-"+ gerente.matricula + "\n");

        System.out.println("Vendedores\n");
        for (Vendedor vendedor : vendedores) {
            System.out.println(vendedor.getNome() +"-"+ vendedor.matricula);
        }
        System.out.println();

        String senhaComum = "senha";
        int gerenteMatricula = 1;
        String gerenteSenha = "admin";

        // Mostrar tela de login
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
                "Matrícula:", usernameField,
                "Senha:", passwordField
        };

        while(true) {
            int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                int matricula = Integer.parseInt(usernameField.getText());
                String senha = new String(passwordField.getPassword());

                if (matricula == gerenteMatricula && senha.equals(gerenteSenha)) {
                    //Logando no modo de usuário Gerente
                    JOptionPane.showMessageDialog(null, "Bem-vindo Gerente!");
                    visaogerente(gerente, estoque);
                    break;
                } else  if(senha.equals(senhaComum)){
                    Vendedor vendedorLogado = null;

                    for(Vendedor vendedor: vendedores){
                        if(vendedor.getMatricula() == matricula){
                            vendedorLogado = vendedor;
                            break;
                        }
                    }
                    if(vendedorLogado != null){
                        JOptionPane.showMessageDialog(null, "Bem-Vindo " + vendedorLogado.getNome() + "!");
                        visaoVendedor(vendedorLogado, estoque, clientes);
                    }else {
                        // Credenciais inválidas
                        JOptionPane.showMessageDialog(null, "Login ou senha inválidos.");
                        continue;
                    }
                }

            }else{
                break;
            }
        }

        JOptionPane.showMessageDialog(null, estoque.mostrarJogos());
        JOptionPane.showMessageDialog(null, "Finalizando Programa");
    }

    private static void visaoVendedor(Vendedor vendedorLogado, Estoque estoque, List<Cliente> clientes) {

        loopExterno:
        do{
            String opcoes[] = new String[]{
                    "Vender", "Alugar", "Cadastrar Cliente", "Renovar Aluguel", "Voltar"
            };


            JPanel painel = new JPanel();
            painel.setLayout(new BorderLayout(5, 5));
            painel.setPreferredSize(new Dimension(80, 80));

            JLabel titulo = new JLabel("Vendas", JLabel.CENTER);
            titulo.setFont(new Font("Arial", Font.BOLD, 18));
            painel.add(titulo, BorderLayout.NORTH);

            int opcaoSelecionada = JOptionPane.showOptionDialog(
                    null,
                    painel,
                    "Comércio",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            while (true) {
                if(opcaoSelecionada != JOptionPane.CLOSED_OPTION){
                    switch (opcaoSelecionada) {
                        case 0:
                            JTextField cpf = new JTextField();

                            Object[] campos = {
                                    "CPF do Cliente:", cpf
                            };

                            JOptionPane.showConfirmDialog(null,
                                    campos,
                                    "Procurar Cliente",
                                    JOptionPane.OK_CANCEL_OPTION);

                            Cliente clienteAtual = encontraCliente(cpf.getText(), clientes);
                            if(clienteAtual != null){
                                JTextField codigo = new JTextField();
                                JTextField quantidade = new JTextField();

                                Object[] dadosVenda = {
                                        "Código do jogo:", codigo, "Quantidade:", quantidade
                                };

                                JOptionPane.showConfirmDialog(
                                        null,
                                        dadosVenda,
                                        "Dados da Venda",
                                        JOptionPane.OK_CANCEL_OPTION
                                );

                                boolean venda = vendedorLogado.vender(clienteAtual, Integer.parseInt(codigo.getText()), Integer.parseInt(quantidade.getText()), estoque);
                                if(venda){
                                    JOptionPane.showMessageDialog(null, "Venda Realizada!");
                                    continue loopExterno;
                                } else{
                                    JOptionPane.showMessageDialog(null, "Jogo não encontrado");
                                    continue loopExterno;
                                }
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Cliente não encontrado");
                                continue loopExterno;
                            }

                        case 1:
                            JTextField cpfAluguel = new JTextField();
                            JTextField codigoJogoAlugar = new JTextField();

                            Object[] camposAluguel = {
                                    "CPF:", cpfAluguel, "Código do Jogo:", codigoJogoAlugar
                            };

                            JOptionPane.showConfirmDialog(
                                    null,
                                    camposAluguel,
                                    "Dados Aluguel",
                                    JOptionPane.OK_CANCEL_OPTION
                            );

                            if(cpfAluguel.getText().isBlank() || codigoJogoAlugar.getText().isBlank()){
                                JOptionPane.showMessageDialog(null, "Insira dados");
                                continue loopExterno;
                            }

                            Cliente clienteAluguel = encontraCliente(cpfAluguel.getText(), clientes);
                            Jogo jogoAlugar = estoque.getJogo(Integer.parseInt(codigoJogoAlugar.getText()));
                            if(clienteAluguel != null || jogoAlugar != null){
                                boolean resultadoAluguel = vendedorLogado.processarAluguel(clienteAluguel, Integer.parseInt(codigoJogoAlugar.getText()), estoque);
                                if(resultadoAluguel){
                                    JOptionPane.showMessageDialog(null, "Aluguel realizado com sucesso!");
                                    continue loopExterno;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Falha ao processar aluguel!");
                                    continue loopExterno;
                                }
                            }

                        case 2:
                            JTextField nome = new JTextField();
                            JTextField cpfNovo = new JTextField();

                            Object[] camposCadastroCliente = {
                                    "Nome:", nome, "CPF: ", cpfNovo
                            };

                            JOptionPane.showConfirmDialog(
                                    null,
                                    camposCadastroCliente,
                                    "Cadastro de Cliente",
                                    JOptionPane.OK_CANCEL_OPTION
                            );
                            if(nome.getText().isBlank() || cpfNovo.getText().isBlank()){
                                JOptionPane.showMessageDialog(null, "Digite dados válidos");
                                continue loopExterno;
                            }
                            clientes.add(new Cliente(nome.getText(), cpfNovo.getText()));
                            JOptionPane.showMessageDialog(null, "Cliente Cadastrado!");
                            continue loopExterno;

                        case 4:
                            return;
                        default:
                            break;
                    }
                }
            }




        } while(true);


    }

    private static void visaogerente(Gerente gerente, Estoque estoque) throws IOException {

        loopExterno:
        do{
            String opcoes[] = new String[]{
                "Cadastrar Jogo", "Excluir Jogo", "Cadastrar Funcionário", "Mostra Estoque", "Voltar"
            };

            JPanel painel = new JPanel();
            painel.setLayout(new BorderLayout(5, 5));
            painel.setPreferredSize(new Dimension(80, 80));

            JLabel titulo = new JLabel("Gerência Lojas MIHL", JLabel.CENTER);
            titulo.setFont(new Font("Arial", Font.BOLD, 18));
            painel.add(titulo, BorderLayout.NORTH);

            int opcaoSelecionada = JOptionPane.showOptionDialog(
                    null,
                    painel,
                    "Gerenciamento",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            while(true){
                if (opcaoSelecionada != JOptionPane.CLOSED_OPTION) {
                    switch (opcaoSelecionada) {
                        case 0:
                            opcoes = new String[] {
                                "Jogo digital", "Jogo de Tabuleiro", "Voltar"
                            };
        
                            opcaoSelecionada = JOptionPane.showOptionDialog(
                                            null,
                                            painel,
                                            "Gerenciamento",
                                            JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            opcoes,
                                            opcoes[0]
                                            );

                            switch (opcaoSelecionada) {
                                case 0:
                                    gerente.cadstrarJogo(estoque, 2);
                                    continue loopExterno;
                                case 1:
                                    gerente.cadstrarJogo(estoque, 1);
                                    continue loopExterno;
                                case 2:
                                    continue loopExterno;
                                default:
                                    break;
                            }
                            continue loopExterno;
                        
                        case 1:
                            if(estoque.mostrarJogos().equals("")){
                                JOptionPane.showMessageDialog(null,
                                                            "Não tem jogos cadastrados!");
                                continue loopExterno;
                            }    

                            String cod = JOptionPane.showInputDialog(null, 
                                                        estoque.mostrarJogos() + 
                                                        "\n Digite o codigo do Jogo?"); 

                            gerente.excluirJogo(estoque, Integer.parseInt(cod));
                            
                            continue loopExterno;

                        case 2:
                            JTextField nome = new JTextField();
                            JTextField cpf = new JTextField();
                            JTextField salario = new JTextField();

                            Object[] campos = {
                                "Nome:", nome, "CPF:", cpf, "Salário", salario
                            };

                            JOptionPane.showConfirmDialog(
                                            null, 
                                            campos, 
                                            "Adicionar Vendedor", 
                                            JOptionPane.OK_CANCEL_OPTION);

                            Vendedor vendedor = new Vendedor(nome.getText(), cpf.getText(), Double.parseDouble(salario.getText()));
                            gravaVendedor(vendedor);
                            continue loopExterno;

                        case 3:
                            if(estoque.mostrarJogos().equals("")){
                                JOptionPane.showMessageDialog(null,
                                                            "Não tem jogos cadastrados!");
                                continue loopExterno;
                            }else{
                                JOptionPane.showMessageDialog(null,estoque.mostrarJogos());
                                continue loopExterno;
                            }

                        default:
                            break;
                    }
                }
            }

        }while(true);
    }

    public static class AppendingObjectOutputStream extends ObjectOutputStream {
        public AppendingObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }
}
