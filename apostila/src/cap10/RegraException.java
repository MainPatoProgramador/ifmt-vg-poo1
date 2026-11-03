package cap10;

/**
 * Cap. 10: a nossa própria exceção, para regras de negócio.
 * "extends RuntimeException" quer dizer: ela é um tipo de exceção.
 * (o extends é explicado no Cap. 11)
 */
public class RegraException extends RuntimeException {
    public RegraException(String mensagem) {
        super(mensagem);
    }
}
