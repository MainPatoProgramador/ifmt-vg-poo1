import java.util.ArrayList;

/**
 * Leitor da biblioteca. Complete os TODO a partir do UML da ficha.
 * O leitor guarda as referências dos itens que estão com ele (associação).
 */
public class Leitor {
    // Constante: todo leitor pode ficar com no máximo 2 itens.
    public static final int LIMITE = 2;

    private String nome;
    private ArrayList<ItemBiblioteca> emprestados;

    public Leitor(String nome) {
        // TODO: se nome for null ou em branco, lançar IllegalArgumentException.
        // TODO: guardar o nome.
        // TODO: criar a lista vazia: emprestados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int quantidadeEmprestados() {
        // TODO: retornar o tamanho da lista (size).
        return 0;
    }

    public boolean pegar(ItemBiblioteca item) {
        // TODO: se o leitor já tiver LIMITE itens, retornar false.
        // TODO: mandar a mensagem item.emprestar(). Se ela retornar false, retornar false.
        // TODO: senão, adicionar o item na lista (add) e retornar true.
        return false;
    }

    public boolean devolver(ItemBiblioteca item) {
        // TODO: se o item não estiver na lista (contains), retornar false.
        // TODO: senão, remover da lista (remove), mandar item.devolver() e retornar true.
        return false;
    }

    public String listarEmprestados() {
        // TODO: montar o texto: nome + " está com:" e, para cada item,
        //       uma linha "\n- " + item.descricao(). Use for (ItemBiblioteca item : emprestados).
        return "";
    }
}
