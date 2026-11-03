package cap11;

/** Cap. 11: a superclasse guarda o que é comum a todo item. */
public class ItemBiblioteca {
    private String titulo;
    private boolean disponivel;

    public ItemBiblioteca(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título obrigatório");
        }
        this.titulo = titulo;
        this.disponivel = true;
    }

    public String getTitulo() { return titulo; }
    public boolean isDisponivel() { return disponivel; }

    public boolean emprestar() {
        if (!disponivel) return false;
        disponivel = false;
        return true;
    }

    public void devolver() {
        disponivel = true;
    }

    public String descricao() {
        return "Item: " + titulo;
    }
}
