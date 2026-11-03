package cap16;

import cap08.Musica;
import cap08.Playlist;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Cap. 16: a tela usa a Playlist do Cap. 8 SEM mudar nada nela.
 * A JList mostra o toString() de cada Musica.
 */
public class JanelaPlaylist extends JFrame {
    private Playlist playlist = new Playlist("Minha playlist");   // o modelo
    private DefaultListModel<Musica> itensDaLista = new DefaultListModel<>();
    private JTextField campoTitulo = new JTextField(12);
    private JTextField campoSegundos = new JTextField(4);
    private JLabel rotuloTotal = new JLabel("Total: 0 s");

    public JanelaPlaylist() {
        super("Playlist");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel formulario = new JPanel(new FlowLayout());
        formulario.add(new JLabel("Música:"));
        formulario.add(campoTitulo);
        formulario.add(new JLabel("Segundos:"));
        formulario.add(campoSegundos);
        JButton botaoAdicionar = new JButton("Adicionar");
        formulario.add(botaoAdicionar);

        add(formulario, BorderLayout.NORTH);
        add(new JScrollPane(new JList<>(itensDaLista)), BorderLayout.CENTER);
        add(rotuloTotal, BorderLayout.SOUTH);

        botaoAdicionar.addActionListener(e -> adicionar());

        setSize(520, 300);
        setLocationRelativeTo(null);
    }

    private void adicionar() {
        try {
            int segundos = Integer.parseInt(campoSegundos.getText().trim());
            Musica m = new Musica(campoTitulo.getText().trim(), segundos);
            playlist.adicionar(m);          // 1) a regra fica no modelo
            itensDaLista.addElement(m);     // 2) a tela só mostra
            rotuloTotal.setText("Total: " + playlist.duracaoTotal() + " s");
            campoTitulo.setText("");
            campoSegundos.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Segundos deve ser um número inteiro.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JanelaPlaylist().setVisible(true));
    }
}
