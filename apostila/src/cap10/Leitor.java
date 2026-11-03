package cap10;

import java.util.ArrayList;

/** Cap. 10: em vez de devolver false, o leitor avisa O MOTIVO com uma exceção. */
public class Leitor {
    public static final int LIMITE = 2;
    private String nome;
    private ArrayList<String> titulos;

    public Leitor(String nome) {
        this.nome = nome;
        this.titulos = new ArrayList<>();
    }

    public void pegar(String titulo) {
        if (titulos.size() >= LIMITE) {
            throw new RegraException(nome + " já atingiu o limite de " + LIMITE + " itens");
        }
        if (titulos.contains(titulo)) {
            throw new RegraException(nome + " já está com " + titulo);
        }
        titulos.add(titulo);
    }

    public int quantidade() {
        return titulos.size();
    }
}
