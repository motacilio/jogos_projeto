
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Classe que representa o Estoque contendo uma lista de jogos.
 */
public class Estoque {
    private static Estoque instance;

    private int qtdeTab;
    private int qtdeDig;
    ArrayList<Jogo> jogos = new ArrayList<>();

    /**
     * Construtor que cria o Estoque vazio.
     */
    private Estoque() {
        this.qtdeTab = 0;
        this.qtdeDig = 0;
    }

    /**
     * Retorna a instância do Estoque.
     *
     * @return a instância do Estoque
     */
    public static synchronized Estoque getInstance() {
        if (instance == null) {
            instance = new Estoque();
        }
        return instance;
    }

    /**
     * Lê os arquivos dos jogos para preencher o estoque.
     */
    public void lerArquivos() {
        lerArquivoTab();
        lerArquivoDig();
    }

    /**
     * Lê o arquivo de jogos de tabuleiro e preenche o estoque.
     */
    private void lerArquivoTab() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("EstoqueTab.txt"))) {
            while (true) {
                try {
                    Tabuleiro jogo = (Tabuleiro) ois.readObject();
                    jogos.add(jogo);
                    qtdeTab++;
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo EstoqueTab.txt não encontrado.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lê o arquivo de jogos digitais e preenche o estoque.
     */
    private void lerArquivoDig() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("EstoqueDig.txt"))) {
            while (true) {
                try {
                    Digital jogo = (Digital) ois.readObject();
                    jogos.add(jogo);
                    qtdeDig++;
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo EstoqueDig.txt não encontrado.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adiciona um jogo de tabuleiro ao estoque.
     *
     * @param jogo o jogo de tabuleiro a ser adicionado
     */
    public void adicionarJogoTab(Jogo jogo) {
        this.jogos.add(jogo);
        System.out.println("Código do jogo:" + jogo.getCodigo() + ", static:" + Jogo.getCodigoAtual());
        this.qtdeTab += jogo.getQuantidade();
    }

    /**
     * Adiciona um jogo digital ao estoque.
     *
     * @param jogo o jogo digital a ser adicionado
     */
    public void adicionarJogoDig(Jogo jogo) {
        jogos.add(jogo);
        System.out.println("Código do jogo:" + jogo.getCodigo() + ", static:" + Jogo.getCodigoAtual());
        this.qtdeDig += jogo.getQuantidade();
    }

    /**
     * Remove um jogo do estoque.
     *
     * @param codigo o código do jogo a ser removido
     */
    public void removerJogo(int codigo) {
        Jogo jogoAux = getJogo(codigo);

        if (jogoAux == null) {
            JOptionPane.showMessageDialog(null, "Esse jogo não existe");
            return;
        } else {
            jogos.remove(jogoAux);
        }

        if (jogoAux instanceof Tabuleiro)
            qtdeTab -= jogoAux.getQuantidade();
        else
            qtdeDig -= jogoAux.getQuantidade();
    }

    /**
     * Atualiza os arquivos de estoque com os jogos atuais.
     *
     * @throws IOException se ocorrer um erro de entrada/saída
     */
    public void atualizaArquivos() throws IOException {
        FileOutputStream arqTabs = new FileOutputStream("EstoqueTab.txt");
        ObjectOutputStream tabs = new ObjectOutputStream(arqTabs);

        FileOutputStream arqDigs = new FileOutputStream("EstoqueDig.txt");
        ObjectOutputStream digs = new ObjectOutputStream(arqDigs);

        for (Jogo j : jogos) {
            if (j instanceof Tabuleiro) {
                tabs.writeObject(j);
            } else {
                digs.writeObject(j);
            }
        }

        tabs.flush();
        tabs.close();
        arqTabs.close();

        digs.flush();
        digs.close();
        arqDigs.close();
    }

    /**
     * Verifica a disponibilidade de um jogo no estoque.
     *
     * @param codigo o código do jogo
     * @param quantidade a quantidade desejada
     * @return true se o jogo está disponível, false caso contrário
     */
    public boolean verificarDisponibilidade(int codigo, int quantidade) {
        if (quantidade > 0) {
            for (Jogo jogo : jogos) {
                if (jogo.getCodigo() == codigo && jogo.getQuantidade() >= quantidade) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Adiciona quantidade ao estoque de um jogo.
     *
     * @param codigo o código do jogo
     * @param quantidade a quantidade a ser adicionada
     */
    public void adicionarQuantidadeEstoque(int codigo, int quantidade) {
        Jogo jogo = this.getJogo(codigo);
        if (jogo != null) {
            int novaQuantidade = jogo.getQuantidade() + quantidade;

            jogo.setQuantidade(novaQuantidade);
        }
    }

    /**
     * Remove quantidade do estoque de um jogo.
     *
     * @param codigo o código do jogo
     * @param quantidade a quantidade a ser removida
     */
    public void removerQuantidadeEstoque(int codigo, int quantidade) {
        Jogo jogo = this.getJogo(codigo);

        if (jogo != null) {
            int novaQuantidade = jogo.getQuantidade() - quantidade;

            if (novaQuantidade >= 0) {
                jogo.setQuantidade(novaQuantidade);
            } else {
                throw new IllegalArgumentException("Quantidade Insuficiente no Estoque!");
            }
        }
    }

    /**
     * Retorna o jogo correspondente ao código fornecido.
     *
     * @param codigo o código do jogo
     * @return o jogo correspondente ao código, ou null se não encontrado
     */
    public Jogo getJogo(int codigo) {
        for (Jogo jogo : jogos) {
            if (jogo.getCodigo() == codigo) {
                return jogo;
            }
        }
        return null;
    }

    /**
     * Mostra a lista de jogos no estoque.
     *
     * @return uma string contendo a lista de jogos no estoque
     */
    public String mostrarJogos() {
        StringBuilder s = new StringBuilder();
        s.append("Jogos Digitais:").append(this.qtdeDig).append("\n\n");
        for (Jogo jogo : jogos) {
            if (jogo instanceof Digital)
                s.append(jogo.mostraInfo()).append("\n\n");
        }

        s.append("\n\nJogos de Tabuleiro:").append(this.qtdeTab).append("\n\n");
        for (Jogo jogo : jogos) {
            if (jogo instanceof Tabuleiro)
                s.append(jogo.mostraInfo()).append("\n\n");
        }

        return s.toString();
    }

    /**
     * Retorna a lista de jogos.
     *
     * @return a lista de jogos
     */
    public ArrayList<Jogo> getJogos() {
        return jogos;
    }

    public int getQTab() {
        return qtdeTab;
    }

    public int getQDig() {
        return qtdeDig;
    }
}
