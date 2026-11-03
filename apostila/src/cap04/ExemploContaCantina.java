package cap04;

public class ExemploContaCantina {
    public static void main(String[] args) {
        ContaCantina conta = new ContaCantina("Bruno");
        conta.depositar(15.0);
        System.out.println("Pagou o suco? " + conta.pagar(5.0));      // true
        System.out.println("Pagou o almoço? " + conta.pagar(18.0));   // false
        System.out.println("Saldo: " + conta.getSaldo());             // 10.0
        // conta.saldo = 1000;   // não compila: saldo é private
    }
}
