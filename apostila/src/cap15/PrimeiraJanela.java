package cap15;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/** Cap. 15: a primeira janela. Um campo, um botão e uma resposta. */
public class PrimeiraJanela extends JFrame {
    private JTextField campoNome;
    private JLabel rotuloResposta;

    public PrimeiraJanela() {
        super("Minha primeira janela");          // título da janela
        setDefaultCloseOperation(EXIT_ON_CLOSE); // fechar a janela encerra o programa
        setLayout(new FlowLayout());             // componentes um ao lado do outro

        add(new JLabel("Seu nome:"));
        campoNome = new JTextField(12);
        add(campoNome);

        JButton botao = new JButton("Cumprimentar");
        add(botao);

        rotuloResposta = new JLabel(" ");
        add(rotuloResposta);

        // Ligação do botão ao método: "quando clicar, chame cumprimentar()".
        botao.addActionListener(e -> cumprimentar());

        setSize(420, 120);
        setLocationRelativeTo(null);             // centraliza na tela
    }

    private void cumprimentar() {
        String nome = campoNome.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um nome.");
            return;
        }
        rotuloResposta.setText("Olá, " + nome + "!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PrimeiraJanela().setVisible(true));
    }
}
