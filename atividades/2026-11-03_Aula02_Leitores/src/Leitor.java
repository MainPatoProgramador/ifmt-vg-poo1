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
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("ta em branco ou null, pode n");
        }
        // TODO: guardar o nome.
        this.nome = nome;
        // TODO: criar a lista vazia: emprestados = new ArrayList<>();
        this.emprestados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int quantidadeEmprestados() {
        // TODO: retornar o tamanho da lista (size).
        return emprestados.size();
    }

    public boolean pegar(ItemBiblioteca item) {
        // TODO: se o leitor já tiver LIMITE itens, retornar false.
        if (this.LIMITE == 2){
        return false;
        }
        // TODO: mandar a mensagem item.emprestar(). Se ela retornar false, retornar false.
        else if (item.emprestar() == false){
        return false;
        }
        // TODO: senão, adicionar o item na lista (add) e retornar true.
        else{
        emprestados.add(item);
        return true;
        }
    }

    public boolean devolver(ItemBiblioteca item) {
        // TODO: se o item não estiver na lista (contains), retornar false.
        if (emprestados.contains(item)== false){
        return false;
        }
        // TODO: senão, remover da lista (remove), mandar item.devolver() e retornar true.
        emprestados.remove(item);
        item.devolver();
        return true;
    }

    public String listarEmprestados() {
        // TODO: montar o texto: nome + " está com:" e, para cada item,
        //       uma linha "\n- " + item.descricao(). Use for (ItemBiblioteca item : emprestados).
        String lista = "";
        for (ItemBiblioteca var :emprestados ){
        lista = lista + var.getTitulo() + " esta com: " + nome + "\n- " + var.descricao();
        }
        return lista;
    }
}
