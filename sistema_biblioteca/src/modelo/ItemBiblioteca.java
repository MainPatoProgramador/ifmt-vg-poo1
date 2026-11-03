package modelo;

/** Tudo que a biblioteca empresta. O título identifica o item. */
public class ItemBiblioteca {
    private String titulo;
    private boolean disponivel;

    public ItemBiblioteca(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título obrigatório");
        }
        this.titulo = titulo.trim();
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public boolean emprestar() {
        if (!disponivel) {
            return false;
        }
        disponivel = false;
        return true;
    }

    public void devolver() {
        disponivel = true;
    }

    public String descricao() {
        return "Item: " + titulo;
    }

    /** É isto que aparece nas listas e caixas de seleção da tela. */
    @Override
    public String toString() {
        if (disponivel) {
            return "[disponível] " + descricao();
        }
        return "[emprestado] " + descricao();
    }
}
