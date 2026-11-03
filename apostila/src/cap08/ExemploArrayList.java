package cap08;

import java.util.ArrayList;

/** Cap. 8: as operações que você mais vai usar numa ArrayList. */
public class ExemploArrayList {
    public static void main(String[] args) {
        ArrayList<String> fila = new ArrayList<>();   // nasce vazia

        fila.add("Ana");
        fila.add("Bruno");
        fila.add("Carla");
        System.out.println("Tamanho: " + fila.size());          // 3
        System.out.println("Primeira: " + fila.get(0));         // Ana
        System.out.println("Tem Bruno? " + fila.contains("Bruno")); // true

        fila.remove("Bruno");
        System.out.println("Depois de remover: " + fila);       // [Ana, Carla]

        for (String nome : fila) {
            System.out.println("Atendendo " + nome);
        }
    }
}
