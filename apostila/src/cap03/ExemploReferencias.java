package cap03;

public class ExemploReferencias {
    public static void main(String[] args) {
        Lanche a = new Lanche();
        a.nome = "Pão de queijo";
        a.preco = 3.0;

        Lanche b = a;          // b NÃO é um lanche novo: aponta para o mesmo objeto
        b.preco = 3.5;
        a.mostrar();           // Pão de queijo custa R$ 3.5

        Lanche c = new Lanche();
        c.nome = "Pão de queijo";
        c.preco = 3.5;
        System.out.println("a == b? " + (a == b));   // true: mesma referência
        System.out.println("a == c? " + (a == c));   // false: objetos diferentes

        Lanche d = null;       // d não aponta para nenhum objeto
        System.out.println("d é null? " + (d == null));
        // d.mostrar();        // descomente: NullPointerException
    }
}
