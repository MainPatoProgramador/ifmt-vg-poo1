package tela;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import modelo.Emprestimo;
import modelo.ItemBiblioteca;
import modelo.Leitor;
import modelo.Livro;
import modelo.Revista;
import regras.Biblioteca;
import regras.RegraException;

/**
 * Camada de TELA. Três abas: Acervo, Leitores e Empréstimos.
 * Regra de ouro: esta classe NÃO decide nada. Ela lê os campos, chama a
 * Biblioteca e mostra o resultado (ou a mensagem de erro).
 */
public class JanelaBiblioteca extends JFrame {
    private Biblioteca biblioteca;

    // ----- Aba Acervo
    private JComboBox<String> comboTipo = new JComboBox<>(new String[] {"Livro", "Revista"});
    private JTextField campoTitulo = new JTextField(16);
    private JTextField campoAutor = new JTextField(12);
    private JTextField campoNumero = new JTextField(5);
    private JTextField campoBusca = new JTextField(14);
    private DefaultListModel<ItemBiblioteca> modeloAcervo = new DefaultListModel<>();
    private JList<ItemBiblioteca> listaAcervo = new JList<>(modeloAcervo);

    // ----- Aba Leitores
    private JTextField campoNomeLeitor = new JTextField(16);
    private DefaultListModel<Leitor> modeloLeitores = new DefaultListModel<>();
    private JList<Leitor> listaLeitores = new JList<>(modeloLeitores);

    // ----- Aba Empréstimos
    private DefaultComboBoxModel<Leitor> modeloComboLeitor = new DefaultComboBoxModel<>();
    private JComboBox<Leitor> comboLeitor = new JComboBox<>(modeloComboLeitor);
    private DefaultComboBoxModel<ItemBiblioteca> modeloComboItem = new DefaultComboBoxModel<>();
    private JComboBox<ItemBiblioteca> comboItem = new JComboBox<>(modeloComboItem);
    private DefaultListModel<ItemBiblioteca> modeloDoLeitor = new DefaultListModel<>();
    private JList<ItemBiblioteca> listaDoLeitor = new JList<>(modeloDoLeitor);
    private DefaultListModel<Emprestimo> modeloHistorico = new DefaultListModel<>();

    // ----- Rodapé
    private JLabel rotuloResumo = new JLabel(" ");

    public JanelaBiblioteca(Biblioteca biblioteca) {
        super(biblioteca.getNome());
        this.biblioteca = biblioteca;

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Acervo", criarAbaAcervo());
        abas.addTab("Leitores", criarAbaLeitores());
        abas.addTab("Empréstimos", criarAbaEmprestimos());
        add(abas, BorderLayout.CENTER);

        rotuloResumo.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        add(rotuloResumo, BorderLayout.SOUTH);

        setSize(760, 460);
        setLocationRelativeTo(null);
        atualizarTudo();
    }

    // =====================================================================
    // Montagem das abas (pronta: você não precisa mexer aqui)
    // =====================================================================

    private JPanel criarAbaAcervo() {
        JPanel cadastro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cadastro.setBorder(BorderFactory.createTitledBorder("Novo item"));
        cadastro.add(comboTipo);
        cadastro.add(new JLabel("Título:"));
        cadastro.add(campoTitulo);
        cadastro.add(new JLabel("Autor:"));
        cadastro.add(campoAutor);
        cadastro.add(new JLabel("Ano/Edição:"));
        cadastro.add(campoNumero);
        JButton botaoAdicionar = new JButton("Adicionar");
        cadastro.add(botaoAdicionar);

        JPanel busca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        busca.add(new JLabel("Buscar título:"));
        busca.add(campoBusca);
        JButton botaoBuscar = new JButton("Buscar");
        JButton botaoTodos = new JButton("Mostrar todos");
        JButton botaoRemover = new JButton("Remover selecionado");
        busca.add(botaoBuscar);
        busca.add(botaoTodos);
        busca.add(botaoRemover);

        JPanel topo = new JPanel(new GridLayout(2, 1));
        topo.add(cadastro);
        topo.add(busca);

        JPanel aba = new JPanel(new BorderLayout());
        aba.add(topo, BorderLayout.NORTH);
        aba.add(new JScrollPane(listaAcervo), BorderLayout.CENTER);

        // Cada botão chama um método desta classe.
        botaoAdicionar.addActionListener(e -> adicionarItem());
        botaoBuscar.addActionListener(e -> buscar());
        botaoTodos.addActionListener(e -> atualizarTudo());
        botaoRemover.addActionListener(e -> removerItem());
        comboTipo.addActionListener(e -> campoAutor.setEnabled(comboTipo.getSelectedIndex() == 0));
        return aba;
    }

    private JPanel criarAbaLeitores() {
        JPanel cadastro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cadastro.setBorder(BorderFactory.createTitledBorder("Novo leitor"));
        cadastro.add(new JLabel("Nome:"));
        cadastro.add(campoNomeLeitor);
        JButton botaoCadastrar = new JButton("Cadastrar");
        cadastro.add(botaoCadastrar);

        JPanel aba = new JPanel(new BorderLayout());
        aba.add(cadastro, BorderLayout.NORTH);
        aba.add(new JScrollPane(listaLeitores), BorderLayout.CENTER);

        botaoCadastrar.addActionListener(e -> cadastrarLeitor());
        return aba;
    }

    private JPanel criarAbaEmprestimos() {
        JPanel emprestar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emprestar.setBorder(BorderFactory.createTitledBorder("Emprestar"));
        comboLeitor.setPreferredSize(new Dimension(150, 26));
        comboItem.setPreferredSize(new Dimension(330, 26));
        emprestar.add(new JLabel("Leitor:"));
        emprestar.add(comboLeitor);
        emprestar.add(new JLabel("Item disponível:"));
        emprestar.add(comboItem);
        JButton botaoEmprestar = new JButton("Emprestar");
        emprestar.add(botaoEmprestar);

        JPanel comLeitor = new JPanel(new BorderLayout());
        comLeitor.setBorder(BorderFactory.createTitledBorder("Com o leitor escolhido"));
        comLeitor.add(new JScrollPane(listaDoLeitor), BorderLayout.CENTER);
        JButton botaoDevolver = new JButton("Devolver selecionado");
        comLeitor.add(botaoDevolver, BorderLayout.SOUTH);

        JPanel historico = new JPanel(new BorderLayout());
        historico.setBorder(BorderFactory.createTitledBorder("Histórico"));
        historico.add(new JScrollPane(new JList<>(modeloHistorico)), BorderLayout.CENTER);

        JPanel centro = new JPanel(new GridLayout(1, 2));
        centro.add(comLeitor);
        centro.add(historico);

        JPanel aba = new JPanel(new BorderLayout());
        aba.add(emprestar, BorderLayout.NORTH);
        aba.add(centro, BorderLayout.CENTER);

        botaoEmprestar.addActionListener(e -> emprestar());
        botaoDevolver.addActionListener(e -> devolver());
        comboLeitor.addActionListener(e -> mostrarItensDoLeitor());
        return aba;
    }

    // =====================================================================
    // Ações dos botões: aqui mora o seu trabalho no Cap. 17
    // =====================================================================

    private void adicionarItem() {
        try {
            String titulo = campoTitulo.getText();
            String numero = campoNumero.getText().trim();
            ItemBiblioteca novo;
            if (comboTipo.getSelectedIndex() == 0) {
                int ano = numero.isEmpty() ? 0 : Integer.parseInt(numero);
                novo = new Livro(titulo, campoAutor.getText(), ano);
            } else {
                novo = new Revista(titulo, Integer.parseInt(numero));
            }
            biblioteca.adicionarItem(novo);
            limpar(campoTitulo, campoAutor, campoNumero);
            atualizarTudo();
        } catch (NumberFormatException e) {
            avisar("Ano/Edição precisa ser um número inteiro.");
        } catch (IllegalArgumentException | RegraException e) {
            avisar(e.getMessage());
        }
    }

    private void buscar() {
        modeloAcervo.clear();
        for (ItemBiblioteca item : biblioteca.buscarPorTrecho(campoBusca.getText())) {
            modeloAcervo.addElement(item);
        }
    }

    private void removerItem() {
        ItemBiblioteca item = listaAcervo.getSelectedValue();
        if (item == null) {
            avisar("Selecione um item na lista.");
            return;
        }
        try {
            biblioteca.removerItem(item);
            atualizarTudo();
        } catch (RegraException e) {
            avisar(e.getMessage());
        }
    }

    private void cadastrarLeitor() {
        // TODO 11: dentro de um try:
        //   - pedir à biblioteca para cadastrar o leitor com o texto de campoNomeLeitor;
        //   - limpar o campo, chamar atualizarTudo() e mostrar a matrícula gerada
        //     com JOptionPane.showMessageDialog(this, "...").
        // No catch (IllegalArgumentException e): avisar(e.getMessage()).
        // Modelo: veja como adicionarItem() faz.
    }

    private void emprestar() {
        Leitor leitor = (Leitor) comboLeitor.getSelectedItem();
        ItemBiblioteca item = (ItemBiblioteca) comboItem.getSelectedItem();
        // TODO 12: num try, pedir à biblioteca para emprestar e chamar atualizarTudo().
        //          No catch (RegraException e), avisar(e.getMessage()).
    }

    private void devolver() {
        Leitor leitor = (Leitor) comboLeitor.getSelectedItem();
        ItemBiblioteca item = listaDoLeitor.getSelectedValue();
        if (item == null) {
            avisar("Selecione na lista o item a devolver.");
            return;
        }
        // TODO 13: igual ao emprestar(), mas chamando biblioteca.devolver(leitor, item).
    }

    // =====================================================================
    // Atualização das listas (pronta)
    // =====================================================================

    /** Recarrega tudo que a tela mostra a partir da Biblioteca. */
    public void atualizarTudo() {
        Leitor leitorEscolhido = (Leitor) comboLeitor.getSelectedItem();

        preencher(modeloAcervo, biblioteca.getAcervo());
        preencher(modeloLeitores, biblioteca.getLeitores());
        preencher(modeloHistorico, biblioteca.getHistorico());

        modeloComboLeitor.removeAllElements();
        for (Leitor l : biblioteca.getLeitores()) {
            modeloComboLeitor.addElement(l);
        }
        if (leitorEscolhido != null && biblioteca.getLeitores().contains(leitorEscolhido)) {
            comboLeitor.setSelectedItem(leitorEscolhido);
        }

        modeloComboItem.removeAllElements();
        for (ItemBiblioteca item : biblioteca.itensDisponiveis()) {
            modeloComboItem.addElement(item);
        }

        mostrarItensDoLeitor();
        rotuloResumo.setText(biblioteca.relatorio().replace("\n", "  —  "));
    }

    private void mostrarItensDoLeitor() {
        modeloDoLeitor.clear();
        Leitor leitor = (Leitor) comboLeitor.getSelectedItem();
        if (leitor != null) {
            for (ItemBiblioteca item : leitor.getItens()) {
                modeloDoLeitor.addElement(item);
            }
        }
    }

    private <T> void preencher(DefaultListModel<T> modelo, ArrayList<T> dados) {
        modelo.clear();
        for (T d : dados) {
            modelo.addElement(d);
        }
    }

    private void limpar(JTextField... campos) {
        for (JTextField c : campos) {
            c.setText("");
        }
    }

    private void avisar(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Atenção", JOptionPane.WARNING_MESSAGE);
    }
}
