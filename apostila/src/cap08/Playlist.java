package cap08;

import java.util.ArrayList;

/** Cap. 8: uma classe que guarda uma lista de objetos. */
public class Playlist {
    private String nome;
    private ArrayList<Musica> musicas;

    public Playlist(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();   // a lista nasce no construtor
    }

    public void adicionar(Musica m) {
        musicas.add(m);
    }

    public int quantidade() {
        return musicas.size();
    }

    public int duracaoTotal() {
        int total = 0;
        for (Musica m : musicas) {
            total = total + m.getSegundos();
        }
        return total;
    }

    public Musica maisLonga() {
        if (musicas.isEmpty()) {
            return null;
        }
        Musica maior = musicas.get(0);
        for (Musica m : musicas) {
            if (m.getSegundos() > maior.getSegundos()) {
                maior = m;
            }
        }
        return maior;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    @Override
    public String toString() {
        return nome + ": " + quantidade() + " músicas";
    }
}
