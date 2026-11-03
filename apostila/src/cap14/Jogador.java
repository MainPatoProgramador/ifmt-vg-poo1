package cap14;

public class Jogador {
    private String nome;
    private int pontos;

    public Jogador(String nome, int pontos) {
        this.nome = nome;
        this.pontos = pontos;
    }

    public String getNome() { return nome; }
    public int getPontos() { return pontos; }

    /** Uma linha do CSV: nome;pontos */
    public String paraCsv() {
        return nome + ";" + pontos;
    }

    /** Monta um Jogador a partir de uma linha do CSV. */
    public static Jogador deCsv(String linha) {
        String[] partes = linha.split(";");
        return new Jogador(partes[0], Integer.parseInt(partes[1]));
    }

    @Override
    public String toString() {
        return nome + " (" + pontos + " pts)";
    }
}
