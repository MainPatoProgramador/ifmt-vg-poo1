package cap02;

import java.util.Scanner;

/** Cap. 2: lendo dados do teclado com Scanner. */
public class LeituraTeclado {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Seu nome: ");
        String nome = teclado.nextLine();

        System.out.print("Sua idade: ");
        int idade = teclado.nextInt();

        if (idade >= 16) {
            System.out.println(nome + ", você já pode votar.");
        } else {
            System.out.println(nome + ", faltam " + (16 - idade) + " anos para votar.");
        }
        teclado.close();
    }
}
