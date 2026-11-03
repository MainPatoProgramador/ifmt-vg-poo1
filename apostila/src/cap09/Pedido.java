package cap09;

import java.util.ArrayList;

/**
 * Cap. 9:
 * - ASSOCIAÇÃO com Aluno: o pedido só conhece o aluno; o aluno existe sem o pedido.
 * - COMPOSIÇÃO com ItemPedido: o pedido cria e é dono dos seus itens.
 */
public class Pedido {
    private Aluno cliente;                   // associação
    private ArrayList<ItemPedido> itens;     // composição

    public Pedido(Aluno cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    // Quem cria o ItemPedido é o próprio pedido.
    public void adicionar(Produto produto, int quantidade) {
        itens.add(new ItemPedido(produto, quantidade));
    }

    public double total() {
        double soma = 0;
        for (ItemPedido item : itens) {
            soma = soma + item.subtotal();
        }
        return soma;
    }

    public String resumo() {
        String texto = "Pedido de " + cliente.getNome();
        for (ItemPedido item : itens) {
            texto = texto + "\n  " + item;
        }
        return texto + "\n  Total: R$ " + total();
    }
}
