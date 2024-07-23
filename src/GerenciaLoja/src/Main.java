import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

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
    }

    public static void gravaVendedor(Vendedor vendedor) throws IOException {
        boolean append = new File("Funcs.txt").exists();
        FileOutputStream fx = new FileOutputStream("Funcs.txt", true);
        ObjectOutputStream ox = append ? new AppendingObjectOutputStream(fx) : new ObjectOutputStream(fx);

        ox.writeObject(vendedor);
        ox.close();
        fx.close();
    }

    public static ArrayList<Vendedor> instanciaFuncs() throws IOException {
        ArrayList<Vendedor> v = new ArrayList<>();
        Vendedor vend;
        FileInputStream fx = new FileInputStream("Funcs.txt");
        ObjectInputStream ox = new ObjectInputStream(fx);

        while (true) {
            try {
                vend = (Vendedor) ox.readObject();
                v.add(vend);
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        ox.close();
        fx.close();

        return v;
    }

    public static void main(String[] args) throws IOException {

        gravaGerente(new Gerente("Cleber", "9845", 6000));
        Gerente gerente = instanciaGerente();
        gerente.setMatricula(1000);

        gravaVendedor(new Vendedor("Bruna", "1234", 2000));
        gravaVendedor(new Vendedor("Mário", "3654", 2240));
        gravaVendedor(new Vendedor("Paulo", "8745", 3000));
        gravaVendedor(new Vendedor("Joaquina", "0987", 3700));

        ArrayList<Vendedor> vendedores = instanciaFuncs();

        System.out.println(gerente.getNome() +"-"+ gerente.matricula);

        for (Vendedor vendedor : vendedores) {
            System.out.println(vendedor.getNome() +"-"+ vendedor.matricula);
        }

        int funcionario = 0;
        String commonPassword = "password";
        int managerUser = 1000;
        String managerPassword = "senhagerente";

        // Mostrar tela de login
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
                "Username:", usernameField,
                "Password:", passwordField
        };

        while(true) {
            int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                int username = Integer.parseInt(usernameField.getText());
                String password = new String(passwordField.getPassword());
                if (username == funcionario && password.equals(commonPassword)) {
                    //Logando no modo de usuário Funcionário
                    JOptionPane.showMessageDialog(null, "Bem-vindo, funcionário comum!");
                    visaoFuncionario();
                    break;
                } else if (username == managerUser && password.equals(managerPassword)) {
                    //Logando no modo de usuário Gerente
                    JOptionPane.showMessageDialog(null, "Bem-vindo, gerente!");
                    visaogerente();
                    break;
                } else {
                    // Credenciais inválidas
                    JOptionPane.showMessageDialog(null, "Login ou senha inválidos.");
                    continue;
                }
            }else{
                break;
            }
        }

        JOptionPane.showMessageDialog(null, "Dou meu toba!!!");
    }

    private static void visaoFuncionario() {

        JOptionPane.showMessageDialog(null, "Tela do Funcionário Comum");
    }

    private static void visaogerente() {
        // Definir cores personalizadas
        UIManager.put("OptionPane.background", new ColorUIResource(255, 255, 255));
        UIManager.put("Panel.background", new ColorUIResource(255, 255, 255));
        UIManager.put("Button.background", new ColorUIResource(240, 240, 240));
        UIManager.put("Button.foreground", new ColorUIResource(22, 24, 66));
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 12));

        String opcoes[] = {
                "Cadastrar Jogo", "Excluir Jogo", "Cadastrar Funcionário"
        };

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout(10, 10));
        painel.setPreferredSize(new Dimension(300, 150));

        JLabel titulo = new JLabel("Escolha uma opção!", JLabel.CENTER);
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

        if (opcaoSelecionada != JOptionPane.CLOSED_OPTION) {
            System.out.println("Opção selecionada: " + opcoes[opcaoSelecionada]);
        }
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
