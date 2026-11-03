package cap06;

public class ExemploConstrutores {
    public static void main(String[] args) {
        Livro a = new Livro("Vidas Secas", "Graciliano Ramos", 1938);
        Livro b = new Livro("Dom Casmurro", "Machado de Assis");
        Livro c = new Livro("Lendas do Pantanal");

        System.out.println(a.ficha());   // Vidas Secas (Graciliano Ramos, 1938)
        System.out.println(b.ficha());   // Dom Casmurro (Machado de Assis)
        System.out.println(c.ficha());   // Lendas do Pantanal (Autor desconhecido)
    }
}
