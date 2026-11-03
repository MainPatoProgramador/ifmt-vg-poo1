package testes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import modelo.ItemBiblioteca;
import modelo.Leitor;
import modelo.Livro;
import modelo.Revista;
import dados.ArquivoCsv;
import regras.Biblioteca;
import regras.RegraException;

/**
 * Rode este arquivo (Shift+F6) para conferir as REGRAS sem abrir a janela.
 * Tudo [OK] = a camada de regras está certa.
 */
public class TesteBiblioteca {
    private static int passou = 0;
    private static int falhou = 0;

    public static void main(String[] args) throws IOException {
        Biblioteca b = new Biblioteca("Teste");
        Livro dom = new Livro("Dom Casmurro", "Machado de Assis", 1899);
        Livro vidas = new Livro("Vidas Secas", "Graciliano Ramos");
        Revista rev = new Revista("Revista do Campus", 12);
        b.adicionarItem(dom);
        b.adicionarItem(vidas);
        b.adicionarItem(rev);
        Leitor ana = b.cadastrarLeitor("Ana");
        Leitor bruno = b.cadastrarLeitor("Bruno");

        verificar(ana.getMatricula() != bruno.getMatricula(), "matrículas diferentes");
        verificar(falhaRegra(() -> b.adicionarItem(new Livro("dom casmurro", "X"))), "título repetido é recusado");
        verificar(b.buscarPorTitulo("VIDAS SECAS") == vidas, "busca ignora maiúsculas");
        verificar(b.buscarPorTrecho("cas").size() == 2, "busca por trecho");

        b.emprestar(ana, dom);
        verificar(!dom.isDisponivel() && ana.estaCom(dom), "empréstimo marca item e leitor");
        verificar(b.emprestimosAtivos().size() == 1, "empréstimo vira registro (composição)");
        verificar(falhaRegra(() -> b.emprestar(bruno, dom)), "item emprestado não sai de novo");
        b.emprestar(ana, vidas);
        verificar(falhaRegra(() -> b.emprestar(ana, rev)), "limite de " + Leitor.LIMITE + " itens");
        verificar(rev.isDisponivel(), "empréstimo recusado não mexe no item");
        verificar(falhaRegra(() -> b.devolver(bruno, vidas)), "não devolve o que não pegou");
        verificar(falhaRegra(() -> b.removerItem(dom)), "não remove item emprestado");

        b.devolver(ana, dom);
        verificar(dom.isDisponivel() && !ana.estaCom(dom), "devolução libera o item");
        verificar(b.emprestimosAtivos().size() == 1 && b.getHistorico().size() == 2, "histórico guarda tudo");
        b.emprestar(bruno, dom);
        verificar(bruno.estaCom(dom), "outro leitor pega depois da devolução");

        // Gravar e ler de volta
        Path pasta = Files.createTempDirectory("biblioteca");
        new ArquivoCsv(pasta).salvar(b);
        Biblioteca lida = new ArquivoCsv(pasta).carregar("Lida");
        verificar(lida.getAcervo().size() == 3 && lida.getLeitores().size() == 2, "CSV: acervo e leitores");
        verificar(lida.emprestimosAtivos().size() == 2, "CSV: empréstimos em aberto");
        verificar(!lida.buscarPorTitulo("Dom Casmurro").isDisponivel(), "CSV: situação dos itens");
        Leitor novo = lida.cadastrarLeitor("Carla");
        verificar(novo.getMatricula() > bruno.getMatricula(), "CSV: matrícula nova não repete");

        System.out.println(passou + " passaram, " + falhou + " falharam.");
    }

    private static void verificar(boolean condicao, String descricao) {
        if (condicao) {
            passou++;
            System.out.println("[OK]    " + descricao);
        } else {
            falhou++;
            System.out.println("[FALHA] " + descricao);
        }
    }

    /** true se a ação lançar RegraException. */
    private static boolean falhaRegra(Runnable acao) {
        try {
            acao.run();
            return false;
        } catch (RegraException e) {
            return true;
        }
    }
}
