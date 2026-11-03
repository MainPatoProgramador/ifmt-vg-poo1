package cap02;

/** Cap. 2: tipos, vetor, laço, decisão e método. */
public class MediaDaTurma {
    public static void main(String[] args) {
        double[] notas = {7.5, 4.0, 9.0, 6.0, 8.5};

        System.out.println("Média: " + media(notas));
        System.out.println("Maior nota: " + maior(notas));

        int abaixo = 0;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] < 6.0) {
                abaixo++;
            }
        }
        System.out.println("Abaixo de 6,0: " + abaixo);
    }

    static double media(double[] valores) {
        double soma = 0;
        for (double v : valores) {
            soma = soma + v;
        }
        return soma / valores.length;
    }

    static double maior(double[] valores) {
        double maior = valores[0];
        for (double v : valores) {
            if (v > maior) {
                maior = v;
            }
        }
        return maior;
    }
}
