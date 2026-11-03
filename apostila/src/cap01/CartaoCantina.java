package cap01;

/**
 * Cap. 1: o mesmo problema com um objeto. Os dados e as regras
 * ficam juntos, e só a própria classe mexe no saldo.
 */
public class CartaoCantina {
    private String dono;
    private double saldo;

    public CartaoCantina(String dono, double saldoInicial) {
        this.dono = dono;
        this.saldo = saldoInicial;
    }

    // Mensagem: "pague este valor". O cartão decide se pode.
    public boolean pagar(double valor) {
        if (valor > saldo) {
            return false;
        }
        saldo = saldo - valor;
        return true;
    }

    public String getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }
}
