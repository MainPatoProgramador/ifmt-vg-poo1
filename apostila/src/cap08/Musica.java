package cap08;

public class Musica {
    private String titulo;
    private int segundos;

    public Musica(String titulo, int segundos) {
        if (segundos <= 0) {
            throw new IllegalArgumentException("Duração inválida");
        }
        this.titulo = titulo;
        this.segundos = segundos;
    }

    public String getTitulo() { return titulo; }
    public int getSegundos() { return segundos; }

    @Override
    public String toString() {
        return titulo + " (" + segundos / 60 + ":" + String.format("%02d", segundos % 60) + ")";
    }
}
