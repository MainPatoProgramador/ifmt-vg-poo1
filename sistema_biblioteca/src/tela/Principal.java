package tela;

import dados.ArquivoCsv;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Path;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import regras.Biblioteca;

/** Ponto de partida: carrega os dados, abre a janela e salva ao fechar. */
public class Principal {
    public static void main(String[] args) {
        ArquivoCsv arquivo = new ArquivoCsv(Path.of("dados_biblioteca"));

        Biblioteca biblioteca;
        try {
            biblioteca = arquivo.carregar("Biblioteca do Campus VG");
        } catch (IOException | RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Não consegui ler os dados: " + e.getMessage()
                    + "\nO sistema vai começar vazio.");
            biblioteca = new Biblioteca("Biblioteca do Campus VG");
        }

        final Biblioteca b = biblioteca;
        SwingUtilities.invokeLater(() -> {
            JanelaBiblioteca janela = new JanelaBiblioteca(b);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            // Ao fechar a janela: salvar tudo.
            janela.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        arquivo.salvar(b);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Não consegui salvar: " + ex.getMessage());
                    }
                }
            });
            janela.setVisible(true);
        });
    }
}
