import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public static void gravaCliente(Cliente cliente) throws IOException {
        try{
            boolean append = new File("cliente.txt").exists();
            FileOutputStream fx = new FileOutputStream("cliente.txt", true);
            ObjectOutputStream ox = append ? new AppendingObjectOutputStream(fx) : new ObjectOutputStream(fx);

            ox.writeObject(cliente);
            ox.flush();
            ox.close();
            fx.close();
        }catch(Exception e){
            e.printStackTrace();
        }   
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

    private static ArrayList<Vendedor> instanciaFuncs() {
        ArrayList<Vendedor> vends = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("vend.txt"))) {
            while (true) {
                try {
                    Vendedor vend = (Vendedor) ois.readObject();
                    vends.add(vend);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo vend.txt não encontrado.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vends;
    }

    private static ArrayList<Cliente> instanciaClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cliente.txt"))) {
            while (true) {
                try {
                    Cliente cliente = (Cliente) ois.readObject();
                    clientes.add(cliente);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo vend.txt não encontrado.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    private static ArrayList<Aluguel> instanciaAluguel(ArrayList<Cliente> clientes){
        ArrayList<Aluguel> alugueis = new ArrayList<>();
        
        for(Cliente c:clientes){
            if(c.getAlugueis() != null){
                for(Aluguel a : c.getAlugueis()){
                    alugueis.add(a);
                }
            }
        }

        return alugueis;
    }

    public static Cliente encontraCliente(String cpf, List<Cliente> clientes){
        for(Cliente cliente: clientes){
            if(cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        return null;
    }

    public static void atualizaArquivosFunc(ArrayList<Vendedor> vends) throws IOException{
        FileOutputStream arqVend = new FileOutputStream("vend.txt");
        ObjectOutputStream vend = new ObjectOutputStream(arqVend);

        for(Vendedor v : vends){
            vend.writeObject(v);
        }

        vend.flush();
        vend.close();
        arqVend.close();
    }

    public static void atualizaArquivosCliente(ArrayList<Cliente> clientes) throws IOException{
        FileOutputStream arqCli = new FileOutputStream("cliente.txt");
        ObjectOutputStream cli = new ObjectOutputStream(arqCli);

        for(Cliente c : clientes){
            cli.writeObject(c);
        }

        cli.flush();
        cli.close();
        arqCli.close();
    }

    public static void main(String[] args) throws IOException {

        Estoque estoque = Estoque.getInstance();
        estoque.lerArquivos();

        Gerente gerente = instanciaGerente();
        gerente.setMatricula(1000);

        ArrayList<Cliente> clientes = instanciaClientes();

        ArrayList<Aluguel> alugueis = instanciaAluguel(clientes);

        ArrayList<Vendedor> vendedores = instanciaFuncs();

        System.out.println("Gerente\n"+gerente.getNome() +"-"+ gerente.matricula + "\n");

        System.out.println("Vendedores");
        for (Vendedor vendedor : vendedores) {
            System.out.println(vendedor.getNome() +"-"+ vendedor.matricula);
        }
        System.out.println();

        System.out.println("Clientes");

        for (Cliente cliente : clientes){
            System.out.println(cliente.getNome() + "-" + cliente.codigo);
        }

        String senhaComum = "funcmihl";
        int gerenteMatricula = 1000;
        String gerenteSenha = "adminmihl";

        // Mostrar tela de login
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
                "Matrícula:", usernameField,
                "Senha:", passwordField
        };

        login:
        while(true) {
            int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                int matricula = Integer.parseInt(usernameField.getText());
                String senha = new String(passwordField.getPassword());

                if (matricula == gerenteMatricula && senha.equals(gerenteSenha)) {
                    //Logando no modo de usuário Gerente
                    JOptionPane.showMessageDialog(null, "Bem-vindo Gerente!");
                    visaogerente(gerente, estoque, vendedores);
                    continue login;
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
                        visaoVendedor(vendedorLogado, estoque, alugueis, clientes);
                    }else {
                        // Credenciais inválidas
                        JOptionPane.showMessageDialog(null, "Login ou senha inválidos.");
                        continue login;
                    }
                }

            }else{
               break;
            }
        }
        JOptionPane.showMessageDialog(null, "Finalizando Programa");
    }

    private static void visaoVendedor(Vendedor vendedorLogado, Estoque estoque, ArrayList<Aluguel> alugueis, ArrayList<Cliente> clientes) throws IOException {

        loopExterno:
        do{
            String[] opcoes = new String[]{
                    "Vender", "Alugar", "Cadastrar Cliente", "Renovar Aluguel", "Mostrar Aluguéis", "Devolver", "Voltar"
            };

            JTextArea textoEstoque = new JTextArea(10, 30);
            textoEstoque.setText("Lista de Jogos:\n" + estoque.mostrarJogos());
            textoEstoque.setEditable(false);
            JScrollPane scrollEstoque = new JScrollPane(textoEstoque);

            String s = "";
            for(Aluguel a : alugueis){
                s += a.mostraAluguel() + "\n";
            }

            JTextArea textoAluguel = new JTextArea(10, 30);
            textoAluguel.setText("Lista de aluguéis:\n" + s);
            textoAluguel.setEditable(false);
            JScrollPane scrollAluguel = new JScrollPane(textoAluguel);

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
                            JTextField cpfVenda = new JTextField();

                            Object[] campos = {
                                    "CPF do Cliente:", cpfVenda
                            };

                            int resultadoVenda = JOptionPane.showConfirmDialog(
                                    null,
                                    campos,
                                    "Procurar Cliente",
                                    JOptionPane.OK_CANCEL_OPTION);

                            if(resultadoVenda == JOptionPane.OK_OPTION){
                                Cliente clienteAtual = encontraCliente(cpfVenda.getText(), clientes);
                                if(clienteAtual != null){
                                    JTextField codigo = new JTextField();
                                    JTextField quantidade = new JTextField();
                                    
                                    Object[] dadosVenda = {
                                            scrollEstoque, "Código do jogo:", codigo, "Quantidade:", quantidade
                                    };

                                    int resultadoDadosVenda = JOptionPane.showConfirmDialog(
                                            null,
                                            dadosVenda,
                                            "Dados da Venda",
                                            JOptionPane.OK_CANCEL_OPTION
                                    );

                                    if(resultadoDadosVenda == JOptionPane.OK_OPTION){
                                        if(!codigo.getText().isBlank() || !quantidade.getText().isBlank()){
                                            try{
                                                int codigoInt = Integer.parseInt(codigo.getText());
                                                int quantidadeInt = Integer.parseInt(quantidade.getText());
                                                boolean venda = vendedorLogado.vender(clienteAtual, codigoInt, quantidadeInt, estoque);
                                                if(venda){
                                                    JOptionPane.showMessageDialog(null, "Venda Realizada!");
                                                } else{
                                                    JOptionPane.showMessageDialog(null, "Jogo não encontrado ou quantidade insuficiente");
                                                }
                                            } catch (NumberFormatException e){
                                                JOptionPane.showMessageDialog(null, "Digite valores numéricos válidos para o código e quantidade!");
                                            }
                                        } else{
                                            JOptionPane.showMessageDialog(null,"Digite dados válidos!");
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Cliente não encontrado");
                                }
                            }
                            continue loopExterno;

                        case 1:
                            JTextField cpfAluguel = new JTextField();
                            JTextField codigoJogoAlugar = new JTextField();

                            Object[] camposAluguel = {
                                    scrollEstoque, "CPF:", cpfAluguel, "Código do Jogo:", codigoJogoAlugar
                            };

                            int resultado = JOptionPane.showConfirmDialog(
                                    null,
                                    camposAluguel,
                                    "Dados do Aluguel",
                                    JOptionPane.OK_CANCEL_OPTION
                            );

                            if(resultado == JOptionPane.OK_OPTION) {
                                if (cpfAluguel.getText().isBlank() || codigoJogoAlugar.getText().isBlank()) {
                                    JOptionPane.showMessageDialog(null, "Insira dados");
                                    continue loopExterno;
                                }

                                Cliente clienteAluguel = encontraCliente(cpfAluguel.getText(), clientes);
                                if (clienteAluguel != null) {

                                    if(clienteAluguel.qtdeAtrasos() > 0){
                                        JOptionPane.showMessageDialog(null, "Esse cliente tem atrasos: \n"+clienteAluguel.qtdeAtrasos()+" atrasos. Multa de: " + clienteAluguel.valorMulta());
                                        continue loopExterno;
                                    }

                                    Aluguel resultadoAluguel = vendedorLogado.processarAluguel(clienteAluguel, Integer.parseInt(codigoJogoAlugar.getText()), estoque);
                                    alugueis.add(resultadoAluguel);
                                    if (resultadoAluguel != null) {
                                        JOptionPane.showMessageDialog(null, "Aluguel realizado com sucesso!");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Falha ao processar aluguel!");
                                        continue loopExterno;
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
                                }
                            }
                            continue loopExterno;
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

                            Pessoa.setCodigoAtual(clientes.getLast().getCodigo());
                            clientes.add(new Cliente(nome.getText(), cpfNovo.getText()));
                            JOptionPane.showMessageDialog(null, "Cliente Cadastrado!");
                            continue loopExterno;
                        case 4:
                            if(alugueis == null){
                                JOptionPane.showMessageDialog(null,"Não tem aluguéis...");
                            }else{
                                JOptionPane.showMessageDialog(null, scrollAluguel);
                            }
                            continue loopExterno;
                        case 5:
                        cpfAluguel = new JTextField();
                        JTextField codigo = new JTextField();
                        
                        camposAluguel = new Object[] {
                                "CPF:", cpfAluguel
                        };

                        resultado = JOptionPane.showConfirmDialog(
                                null,
                                camposAluguel,
                                "Devolver",
                                JOptionPane.OK_CANCEL_OPTION
                        );

                        Cliente clienteAluguel = encontraCliente(cpfAluguel.getText(), clientes);

                        s = "";
                        for(Aluguel a : clienteAluguel.getAlugueis()){
                            s+=a.mostraAluguel()+"\n";
                        }

                        textoAluguel = new JTextArea(10, 30);
                        textoAluguel.setText("Lista de aluguéis:\n" + s);
                        textoAluguel.setEditable(false);
                        scrollAluguel = new JScrollPane(textoAluguel);

                        campos = new Object[] {scrollAluguel, "Código do aluguel para devolver:", codigo};

                        JOptionPane.showConfirmDialog(null, campos, "Devolução", JOptionPane.OK_CANCEL_OPTION);

                        double devolução = clienteAluguel.devolver(estoque, Integer.parseInt(codigo.getText()));
                        if(devolução == 0){
                            JOptionPane.showMessageDialog(null, "Devolução realizada!");
                            continue loopExterno;
                        }else{
                            JOptionPane.showMessageDialog(null, "Esse cliente tem atrasos: \n"+clienteAluguel.qtdeAtrasos()+" atrasos. Multa de: " + clienteAluguel.valorMulta());
                            Object[] ops = {"Devolver", "Renovar", "Cancelar"};
                            
                            int o = JOptionPane.showOptionDialog(null, null, "Devolução", JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, ops, ops[0]);
                            if(o == 0){
                                JOptionPane.showMessageDialog(null,"Pagamento realizado! Como penalidade você deve devolver todos os jogos!");
                                for(Aluguel a : clienteAluguel.getAlugueis()){
                                    a.renovar();
                                    clienteAluguel.devolver(estoque, a.getCodigo());
                                }
                                continue loopExterno;
                            }else if(o == 1){
                                JOptionPane.showMessageDialog(null,"Pagamento realizado! Como penalidade você deve devolver todos os jogos!");
                                for(Aluguel a : clienteAluguel.getAlugueis()){
                                    a.renovar();
                                }
                            }else if(o == 2){
                                JOptionPane.showMessageDialog(null,"Sem aluguel pra você! Pague sua dívida!");
                                continue loopExterno;
                            }
                        }  
                        case 6:
                            estoque.atualizaArquivos();
                            atualizaArquivosCliente(clientes);
                            return;
                        default:
                            break;
                    }
                }
            }




        } while(true);


    }

    private static void visaogerente(Gerente gerente, Estoque estoque, ArrayList<Vendedor> vendedores) throws IOException {

        loopExterno:
        do{
            String opcoes[] = new String[]{
                "Cadastrar Jogo", "Excluir Jogo", "Cadastrar Funcionário", "Ver Estoque", "Atualizar Jogo", "Voltar"
            };

            JTextArea textoEstoque = new JTextArea(10, 30);
            textoEstoque.setText("Lista de Jogos:\n" + estoque.mostrarJogos());
            textoEstoque.setEditable(false);
            JScrollPane scrollEstoque = new JScrollPane(textoEstoque);

            JPanel painel = new JPanel();
            painel.setLayout(new BorderLayout(0, 0));
            painel.setPreferredSize(new Dimension(80, 80));

            JLabel titulo = new JLabel("Gerência Lojas MIHL", JLabel.CENTER);
            titulo.setFont(new Font("Roboto", Font.BOLD, 18));
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
            loopJogos:
            while(true){
                if (opcaoSelecionada != JOptionPane.CLOSED_OPTION) {
                    switch (opcaoSelecionada) {
                        //Adicionar Jogo
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
                                    boolean op = gerente.cadastrarJogo(estoque, 2);
                                    if(op){
                                        continue loopExterno;
                                    }else{
                                        continue loopJogos;
                                    }
                                case 1:
                                    op = gerente.cadastrarJogo(estoque, 1);
                                    if(op){
                                        continue loopExterno;
                                    }else{
                                        continue loopJogos;
                                    }
                                case 2:
                                    continue loopExterno;
                                default:
                                    break;
                            }
                            continue loopExterno;
                        //Excluir Jogo
                        case 1:
                            if(estoque.mostrarJogos().equals("")){
                                JOptionPane.showMessageDialog(null,
                                                            "Não tem jogos cadastrados!");
                                continue loopExterno;
                            }    

                            String cod = JOptionPane.showInputDialog(null, 
                                            scrollEstoque, "Digite o código", JOptionPane.OK_CANCEL_OPTION); 

                                                        if(cod == null){
                                                            continue loopExterno;
                                                        }
                        
                            int codigo = Integer.parseInt(cod);
                            System.out.println("Código jogo excluido:"+codigo);

                            gerente.excluirJogo(estoque, codigo);
                            
                            continue loopExterno;
                        //Cria Vendedor
                        case 2:
                            JTextField nome = new JTextField();
                            JTextField cpf = new JTextField();
                            JTextField salario = new JTextField();

                            Object[] campos = {
                                "Nome:", nome, "CPF:", cpf, "Salário", salario
                            };

                            int op = JOptionPane.showConfirmDialog(
                                            null, 
                                            campos, 
                                            "Adicionar Vendedor", 
                                            JOptionPane.OK_CANCEL_OPTION);

                                            if(op == JOptionPane.OK_CANCEL_OPTION){
                                                continue loopExterno;
                                            }

                            Funcionario.setMatriculaAtual(vendedores.getLast().getMatricula());

                            Vendedor vendedor = new Vendedor(nome.getText(), cpf.getText(), Double.parseDouble(salario.getText()));
                            vendedores.add(vendedor);
                            continue loopExterno;
                        //Mostra Estoque                    
                        case 3:
                            if(estoque.getJogos() == null){
                                JOptionPane.showMessageDialog(null,
                                                            "Não tem jogos cadastrados!");
                                continue loopExterno;
                            }else{
                                JOptionPane.showMessageDialog(null, scrollEstoque, "Estoque", JOptionPane.INFORMATION_MESSAGE);
                                continue loopExterno;
                            }
                        //Atualiza Jogo
                        case 4:
                            cod = JOptionPane.showInputDialog(null, scrollEstoque, null, JOptionPane.OK_CANCEL_OPTION); 

                            if(cod == null){
                                continue loopExterno;
                            }

                            codigo = Integer.parseInt(cod);
                            boolean o = gerente.atualizarJogo(estoque, codigo);
                            if(o == false){
                                continue loopExterno;
                            }else{
                                JOptionPane.showMessageDialog(null,"Mudanças salvas");
                                continue loopExterno;
                            }

                        case 5:
                            atualizaArquivosFunc(vendedores);
                            estoque.atualizaArquivos();
                            return;
                        default:
                            break;
                    }
                }
            }

        }while(true);
    }

    public static void telaJogos(Estoque estoque){
        String jogosTexto = estoque.mostrarJogos();
        
        // Cria um JFrame
        JFrame frame = new JFrame("Lista de Jogos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        // Cria um JTextArea para exibir o texto
        JTextArea textArea = new JTextArea(jogosTexto);
        textArea.setEditable(false); // Torna o JTextArea somente leitura
        textArea.setBackground(Color.WHITE);
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Cria um JScrollPane para adicionar a barra de rolagem
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        
        // Adiciona o JScrollPane ao JFrame
        frame.add(scrollPane);
        
        // Personaliza o fundo do JFrame
        JPanel panel = new JPanel();
        panel.setBackground(Color.RED);
        frame.add(panel, BorderLayout.NORTH);
        
        // Exibe o JFrame
        frame.setVisible(true);
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
