package cap12.modelo;

/** Camada de MODELO: o que é um produto. Não sabe nada de tela. */
public class Produto {
    private String nome;
    private double preco;
    private int estoque;

    public Produto(String nome, double preco, int estoque) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome obrigatório");
        if (preco <= 0) throw new IllegalArgumentException("Preço deve ser positivo");
        if (estoque < 0) throw new IllegalArgumentException("Estoque não pode ser negativo");
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getEstoque() { return estoque; }

    public boolean baixarEstoque(int quantidade) {
        if (quantidade <= 0 || quantidade > estoque) return false;
        estoque = estoque - quantidade;
        return true;
    }

    @Override
    public String toString() {
        return nome + " - R$ " + preco + " (" + estoque + " no estoque)";
    }
}
