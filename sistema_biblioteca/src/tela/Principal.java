package tela;

import dados.ArquivoCsv;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Path;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import regras.Biblioteca;

/**
 * Ponto de partida: carrega os dados, abre a janela e salva ao fechar.
 * Os dados ficam na pasta do usuário (ex.: C:\Users\aluno\poo1_dados_biblioteca),
 * onde qualquer usuário pode gravar, mesmo sem ser administrador.
 */
public class Principal {
    public static void main(String[] args) {
        Path pasta = Path.of(System.getProperty("user.home"), "poo1_dados_biblioteca");
        ArquivoCsv arquivo = new ArquivoCsv(pasta);
        System.out.println("Dados da biblioteca em: " + pasta.toAbsolutePath());

        Biblioteca carregada;
        String aviso = null;
        try {
            carregada = arquivo.carregar("Biblioteca do Campus VG");
        } catch (Exception e) {
            e.printStackTrace();   // o motivo aparece no Output do NetBeans
            aviso = "Não consegui ler os dados (" + e.getMessage() + ").\nO sistema começou vazio.";
            carregada = new Biblioteca("Biblioteca do Campus VG");
        }

        final Biblioteca b = carregada;
        final String avisoFinal = aviso;
        SwingUtilities.invokeLater(() -> {
            JanelaBiblioteca janela = new JanelaBiblioteca(b);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            janela.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        arquivo.salvar(b);
                        System.out.println("Dados salvos em: " + pasta.toAbsolutePath());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Não consegui salvar: " + ex.getMessage());
                    }
                    System.exit(0);
                }
            });
            janela.setVisible(true);
            // O aviso só aparece DEPOIS da janela, por cima dela: nunca fica escondido.
            if (avisoFinal != null) {
                JOptionPane.showMessageDialog(janela, avisoFinal);
            }
        });
    }
}
