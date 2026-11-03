package cap11;

public class Livro extends ItemBiblioteca {
    private String autor;

    public Livro(String titulo, String autor) {
        super(titulo);           // a superclasse cuida do título
        this.autor = autor;
    }

    public String getAutor() { return autor; }

    @Override
    public String descricao() {
        return "Livro: " + getTitulo() + " | Autor: " + autor;
    }
}
