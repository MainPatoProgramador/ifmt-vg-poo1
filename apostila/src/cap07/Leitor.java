package cap07;

/** Cap. 7: static, toString e equals. */
public class Leitor {
    public static final int LIMITE = 2;      // constante da classe
    private static int proximaMatricula = 1; // um contador para todos os leitores

    private int matricula;
    private String nome;

    public Leitor(String nome) {
        this.nome = nome;
        this.matricula = proximaMatricula;   // pega o número da vez
        proximaMatricula++;                  // e avança para o próximo leitor
    }

    public int getMatricula() { return matricula; }
    public String getNome() { return nome; }

    public static int totalCriados() {
        return proximaMatricula - 1;
    }

    @Override
    public String toString() {
        return matricula + " - " + nome;
    }

    // Dois leitores são "iguais" se têm a mesma matrícula.
    @Override
    public boolean equals(Object outro) {
        if (this == outro) return true;
        if (!(outro instanceof Leitor)) return false;
        Leitor l = (Leitor) outro;
        return matricula == l.matricula;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(matricula);
    }
}
