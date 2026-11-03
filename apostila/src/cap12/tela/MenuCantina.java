package cap12.tela;

import cap12.modelo.Produto;
import cap12.regras.Cantina;
import java.util.Scanner;

/** Camada de TELA (console): só conversa com o usuário e com a Cantina. */
public class MenuCantina {
    public static void main(String[] args) {
        Cantina cantina = new Cantina();
        cantina.cadastrar(new Produto("Coxinha", 6.0, 10));
        cantina.cadastrar(new Produto("Suco", 4.5, 5));

        Scanner teclado = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n1-Listar  2-Vender  0-Sair");
            opcao = Integer.parseInt(teclado.nextLine());
            if (opcao == 1) {
                for (Produto p : cantina.getProdutos()) {
                    System.out.println(p);
                }
                System.out.println("Caixa: R$ " + cantina.getCaixa());
            } else if (opcao == 2) {
                System.out.print("Produto: ");
                String nome = teclado.nextLine();
                System.out.print("Quantidade: ");
                int qtd = Integer.parseInt(teclado.nextLine());
                try {
                    double valor = cantina.vender(nome, qtd);
                    System.out.println("Cobrar R$ " + valor);
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }
        }
        teclado.close();
    }
}
