package regras;

import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Emprestimo;
import modelo.ItemBiblioteca;
import modelo.Leitor;

/**
 * Camada de REGRAS. A tela só conversa com esta classe.
 * Toda regra violada vira RegraException com uma mensagem clara.
 */
public class Biblioteca {
    private String nome;
    private ArrayList<ItemBiblioteca> acervo;
    private ArrayList<Leitor> leitores;
    private ArrayList<Emprestimo> emprestimos;   // composição: só a Biblioteca cria

    public Biblioteca(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome obrigatório");
        }
        this.nome = nome;
        this.acervo = new ArrayList<>();
        this.leitores = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    // ---------------- Acervo ----------------

    public void adicionarItem(ItemBiblioteca item) {
        if (item == null) {
            throw new IllegalArgumentException("Item obrigatório");
        }
        if (buscarPorTitulo(item.getTitulo()) != null) {
            throw new RegraException("Já existe um item com o título \"" + item.getTitulo() + "\"");
        }
        acervo.add(item);
    }

    public void removerItem(ItemBiblioteca item) {
        // TODO 1: se o item NÃO estiver disponível, lançar
        //         RegraException("Não dá para remover um item emprestado").
        // TODO 2: senão, remover o item do acervo.
    }

    public ItemBiblioteca buscarPorTitulo(String titulo) {
        for (ItemBiblioteca item : acervo) {
            if (item.getTitulo().equalsIgnoreCase(titulo.trim())) {
                return item;
            }
        }
        return null;
    }

    /** Itens cujo título contém o trecho (para a busca da tela). */
    public ArrayList<ItemBiblioteca> buscarPorTrecho(String trecho) {
        ArrayList<ItemBiblioteca> achados = new ArrayList<>();
        String procurado = trecho.trim().toLowerCase();
        for (ItemBiblioteca item : acervo) {
            if (item.getTitulo().toLowerCase().contains(procurado)) {
                achados.add(item);
            }
        }
        return achados;
    }

    public ArrayList<ItemBiblioteca> getAcervo() {
        return new ArrayList<>(acervo);
    }

    public ArrayList<ItemBiblioteca> itensDisponiveis() {
        ArrayList<ItemBiblioteca> livres = new ArrayList<>();
        // TODO 3: percorrer o acervo e colocar em "livres" só os itens disponíveis.
        return livres;
    }

    // ---------------- Leitores ----------------

    public Leitor cadastrarLeitor(String nomeLeitor) {
        Leitor novo = new Leitor(nomeLeitor);
        leitores.add(novo);
        return novo;
    }

    /** Usado ao carregar do arquivo, quando a matrícula já existe. */
    public void adicionarLeitor(Leitor leitor) {
        if (leitores.contains(leitor)) {   // usa o equals (matrícula)
            throw new RegraException("Matrícula repetida: " + leitor.getMatricula());
        }
        leitores.add(leitor);
    }

    public Leitor buscarLeitor(int matricula) {
        for (Leitor l : leitores) {
            if (l.getMatricula() == matricula) {
                return l;
            }
        }
        return null;
    }

    public ArrayList<Leitor> getLeitores() {
        return new ArrayList<>(leitores);
    }

    // ---------------- Empréstimos ----------------

    public Emprestimo emprestar(Leitor leitor, ItemBiblioteca item) {
        return emprestar(leitor, item, LocalDate.now());
    }

    public Emprestimo emprestar(Leitor leitor, ItemBiblioteca item, LocalDate data) {
        if (leitor == null || item == null) {
            throw new RegraException("Escolha um leitor e um item");
        }
        // TODO 4: se o leitor atingiu o limite, lançar RegraException
        //         com a mensagem: <nome> já está com <LIMITE> itens
        // TODO 5: mandar a mensagem item.emprestar(). Se ela devolver false,
        //         lançar RegraException: "<título>" já está emprestado
        // TODO 6: o leitor recebe o item (leitor.receber).
        // TODO 7: criar o Emprestimo(leitor, item, data), guardar na lista
        //         "emprestimos" e devolvê-lo com return.
        return null;
    }

    public void devolver(Leitor leitor, ItemBiblioteca item) {
        if (leitor == null || item == null) {
            throw new RegraException("Escolha um leitor e um item");
        }
        // TODO 8: se o leitor NÃO está com o item (leitor.estaCom), lançar
        //         RegraException: <nome> não está com "<título>"
        // TODO 9: o leitor entrega o item e o item é devolvido.
        // TODO 10: percorrer "emprestimos" e encerrar o que estiver ativo
        //          para este item (e.isAtivo() && e.getItem() == item).
    }

    public ArrayList<Emprestimo> emprestimosAtivos() {
        ArrayList<Emprestimo> ativos = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.isAtivo()) {
                ativos.add(e);
            }
        }
        return ativos;
    }

    public ArrayList<Emprestimo> getHistorico() {
        return new ArrayList<>(emprestimos);
    }

    public String relatorio() {
        String texto = nome + "\nItens: " + acervo.size()
                + " | Disponíveis: " + itensDisponiveis().size()
                + " | Leitores: " + leitores.size()
                + " | Empréstimos em aberto: " + emprestimosAtivos().size();
        return texto;
    }
}
