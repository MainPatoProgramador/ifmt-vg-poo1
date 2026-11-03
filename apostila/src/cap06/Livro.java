package cap06;

/** Cap. 6: construtores sobrecarregados e this(...). */
public class Livro {
    private String titulo;
    private String autor;
    private int ano;

    // Construtor completo: é o único que valida e guarda.
    public Livro(String titulo, String autor, int ano) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título obrigatório");
        }
        if (ano < 0) {
            throw new IllegalArgumentException("Ano inválido");
        }
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    // Sem ano: chama o completo, com ano 0 ("não informado").
    public Livro(String titulo, String autor) {
        this(titulo, autor, 0);
    }

    // Só o título: autor desconhecido.
    public Livro(String titulo) {
        this(titulo, "Autor desconhecido");
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAno() { return ano; }

    public String ficha() {
        if (ano == 0) {
            return titulo + " (" + autor + ")";
        }
        return titulo + " (" + autor + ", " + ano + ")";
    }
}
