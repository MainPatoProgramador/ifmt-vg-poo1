package modelo;

import java.util.ArrayList;

/** Leitor com matrícula automática e a lista do que está levando (associação). */
public class Leitor {
    public static final int LIMITE = 2;
    private static int proximaMatricula = 1;

    private int matricula;
    private String nome;
    private ArrayList<ItemBiblioteca> itens;

    /** Leitor novo: recebe a próxima matrícula. */
    public Leitor(String nome) {
        this(proximaMatricula, nome);
    }

    /** Leitor que já existia (lido do arquivo): mantém a matrícula. */
    public Leitor(int matricula, String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome obrigatório");
        }
        if (matricula <= 0) {
            throw new IllegalArgumentException("Matrícula inválida");
        }
        this.matricula = matricula;
        this.nome = nome.trim();
        this.itens = new ArrayList<>();
        if (matricula >= proximaMatricula) {
            proximaMatricula = matricula + 1;   // nunca repete um número
        }
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public int quantidadeItens() {
        return itens.size();
    }

    public boolean atingiuLimite() {
        return itens.size() >= LIMITE;
    }

    public boolean estaCom(ItemBiblioteca item) {
        return itens.contains(item);
    }

    /** Uma cópia da lista: quem está fora não consegue mexer na original. */
    public ArrayList<ItemBiblioteca> getItens() {
        return new ArrayList<>(itens);
    }

    // Quem decide QUANDO chamar estes dois é a Biblioteca (camada de regras).
    // A tela nunca chama receber/entregar diretamente.
    public void receber(ItemBiblioteca item) {
        itens.add(item);
    }

    public void entregar(ItemBiblioteca item) {
        itens.remove(item);
    }

    @Override
    public String toString() {
        return matricula + " - " + nome + " (" + itens.size() + "/" + LIMITE + ")";
    }

    @Override
    public boolean equals(Object outro) {
        if (this == outro) return true;
        if (!(outro instanceof Leitor)) return false;
        return matricula == ((Leitor) outro).matricula;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(matricula);
    }
}
