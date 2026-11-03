package dados;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Emprestimo;
import modelo.ItemBiblioteca;
import modelo.Leitor;
import modelo.Livro;
import modelo.Revista;
import regras.Biblioteca;

/**
 * Camada de DADOS: grava e lê a biblioteca em três arquivos CSV (separador ;).
 *   acervo.csv       LIVRO;titulo;autor;ano   ou   REVISTA;titulo;edicao
 *   leitores.csv     matricula;nome
 *   emprestimos.csv  matricula;titulo;data      (só os em aberto)
 */
public class ArquivoCsv {
    private Path pasta;

    public ArquivoCsv(Path pasta) {
        this.pasta = pasta;
    }

    public void salvar(Biblioteca b) throws IOException {
        Files.createDirectories(pasta);

        ArrayList<String> acervo = new ArrayList<>();
        for (ItemBiblioteca item : b.getAcervo()) {
            if (item instanceof Livro) {
                Livro l = (Livro) item;
                acervo.add("LIVRO;" + l.getTitulo() + ";" + l.getAutor() + ";" + l.getAno());
            } else if (item instanceof Revista) {
                Revista r = (Revista) item;
                acervo.add("REVISTA;" + r.getTitulo() + ";" + r.getEdicao());
            }
        }
        Files.write(pasta.resolve("acervo.csv"), acervo);

        ArrayList<String> leitores = new ArrayList<>();
        for (Leitor l : b.getLeitores()) {
            leitores.add(l.getMatricula() + ";" + l.getNome());
        }
        Files.write(pasta.resolve("leitores.csv"), leitores);

        ArrayList<String> emprestimos = new ArrayList<>();
        for (Emprestimo e : b.emprestimosAtivos()) {
            emprestimos.add(e.getLeitor().getMatricula() + ";" + e.getItem().getTitulo() + ";" + e.getData());
        }
        Files.write(pasta.resolve("emprestimos.csv"), emprestimos);
    }

    /** Carrega o que existir. Se for a primeira vez, devolve a biblioteca vazia. */
    public Biblioteca carregar(String nomeBiblioteca) throws IOException {
        Biblioteca b = new Biblioteca(nomeBiblioteca);

        for (String linha : ler("acervo.csv")) {
            String[] p = linha.split(";");
            if (p[0].equals("LIVRO")) {
                b.adicionarItem(new Livro(p[1], p[2], Integer.parseInt(p[3])));
            } else if (p[0].equals("REVISTA")) {
                b.adicionarItem(new Revista(p[1], Integer.parseInt(p[2])));
            }
        }
        for (String linha : ler("leitores.csv")) {
            String[] p = linha.split(";");
            b.adicionarLeitor(new Leitor(Integer.parseInt(p[0]), p[1]));
        }
        for (String linha : ler("emprestimos.csv")) {
            String[] p = linha.split(";");
            Leitor leitor = b.buscarLeitor(Integer.parseInt(p[0]));
            ItemBiblioteca item = b.buscarPorTitulo(p[1]);
            b.emprestar(leitor, item, LocalDate.parse(p[2]));
        }
        return b;
    }

    private ArrayList<String> ler(String arquivo) throws IOException {
        Path caminho = pasta.resolve(arquivo);
        ArrayList<String> linhas = new ArrayList<>();
        if (!Files.exists(caminho)) {
            return linhas;
        }
        for (String linha : Files.readAllLines(caminho)) {
            if (!linha.isBlank()) {
                linhas.add(linha);
            }
        }
        return linhas;
    }
}
