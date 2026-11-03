package cap12.regras;

import cap12.modelo.Produto;
import java.util.ArrayList;

/** Camada de REGRAS: o que a cantina faz. Também não sabe nada de tela. */
public class Cantina {
    private ArrayList<Produto> produtos;
    private double caixa;

    public Cantina() {
        produtos = new ArrayList<>();
        caixa = 0;
    }

    public void cadastrar(Produto p) {
        if (buscar(p.getNome()) != null) {
            throw new IllegalArgumentException("Produto já cadastrado: " + p.getNome());
        }
        produtos.add(p);
    }

    public Produto buscar(String nome) {
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) return p;
        }
        return null;
    }

    /** Vende e devolve o valor cobrado. */
    public double vender(String nome, int quantidade) {
        Produto p = buscar(nome);
        if (p == null) {
            throw new IllegalArgumentException("Produto não encontrado: " + nome);
        }
        if (!p.baixarEstoque(quantidade)) {
            throw new IllegalArgumentException("Estoque insuficiente de " + nome);
        }
        double valor = p.getPreco() * quantidade;
        caixa = caixa + valor;
        return valor;
    }

    public double getCaixa() { return caixa; }

    public ArrayList<Produto> getProdutos() { return produtos; }
}
