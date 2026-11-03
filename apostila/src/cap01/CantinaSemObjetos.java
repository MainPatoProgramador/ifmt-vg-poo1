package cap01;

/**
 * Cap. 1: o jeito estruturado. Os dados ficam soltos e qualquer parte
 * do programa pode mexer neles.
 */
public class CantinaSemObjetos {
    public static void main(String[] args) {
        String nomeAluno = "Ana";
        double saldo = 20.0;

        saldo = saldo - 6.5;   // comprou um salgado
        saldo = saldo - 30.0;  // ninguém impediu: o saldo ficou negativo!

        System.out.println(nomeAluno + " tem R$ " + saldo);
    }
}
