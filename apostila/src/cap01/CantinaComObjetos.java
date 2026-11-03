package cap01;

public class CantinaComObjetos {
    public static void main(String[] args) {
        CartaoCantina cartao = new CartaoCantina("Ana", 20.0);

        System.out.println("Salgado: " + cartao.pagar(6.5));   // true
        System.out.println("Combo: " + cartao.pagar(30.0));    // false
        System.out.println(cartao.getDono() + " tem R$ " + cartao.getSaldo());
        // Ana tem R$ 13.5
    }
}
