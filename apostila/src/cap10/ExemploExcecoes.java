package cap10;

public class ExemploExcecoes {
    public static void main(String[] args) {
        // 1) Exceção do próprio Java: texto que não é número.
        try {
            int idade = Integer.parseInt("dezesseis");
            System.out.println("Idade: " + idade);
        } catch (NumberFormatException e) {
            System.out.println("Isso não é um número inteiro.");
        }

        // 2) A nossa exceção de regra.
        Leitor ana = new Leitor("Ana");
        String[] pedidos = {"Dom Casmurro", "Vidas Secas", "Iracema"};
        for (String titulo : pedidos) {
            try {
                ana.pegar(titulo);
                System.out.println("OK: " + titulo);
            } catch (RegraException e) {
                System.out.println("Negado: " + e.getMessage());
            }
        }
        System.out.println("O programa continua. Ana está com " + ana.quantidade());
    }
}
