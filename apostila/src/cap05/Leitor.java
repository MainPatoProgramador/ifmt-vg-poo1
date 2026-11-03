package cap05;

/**
 * Cap. 5: esta classe foi escrita olhando o diagrama:
 *   Leitor
 *   - nome : String
 *   - itensEmprestados : int
 *   + Leitor(nome : String)
 *   + getNome() : String
 *   + podePegar() : boolean
 *   + registrarEmprestimo() : void
 */
public class Leitor {
    private String nome;
    private int itensEmprestados;

    public Leitor(String nome) {
        this.nome = nome;
        this.itensEmprestados = 0;
    }

    public String getNome() {
        return nome;
    }

    public boolean podePegar() {
        return itensEmprestados < 2;
    }

    public void registrarEmprestimo() {
        itensEmprestados++;
    }
}
