package cap11;

import java.util.ArrayList;

public class ExemploHeranca {
    public static void main(String[] args) {
        ArrayList<ItemBiblioteca> acervo = new ArrayList<>();
        acervo.add(new Livro("Vidas Secas", "Graciliano Ramos"));
        acervo.add(new Revista("Revista do Campus", 12));
        acervo.add(new Livro("Dom Casmurro", "Machado de Assis"));

        acervo.get(1).emprestar();   // método herdado: Revista não escreveu emprestar()

        for (ItemBiblioteca item : acervo) {
            // Cada objeto responde com a SUA descricao().
            System.out.println(item.descricao() + " -> disponível: " + item.isDisponivel());
        }
    }
}
