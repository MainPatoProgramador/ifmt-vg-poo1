package cap04;

/** Cap. 4: encapsulamento. O saldo é private e só muda pelas regras. */
public class ContaCantina {
    private String aluno;
    private double saldo;

    public ContaCantina(String aluno) {
        setAluno(aluno);
        this.saldo = 0;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        if (aluno == null || aluno.isBlank()) {
            throw new IllegalArgumentException("Nome do aluno obrigatório");
        }
        this.aluno = aluno;
    }

    public double getSaldo() {
        return saldo;          // só leitura: não existe setSaldo
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Depósito deve ser positivo");
        }
        saldo = saldo + valor;
    }

    public boolean pagar(double valor) {
        if (valor <= 0 || valor > saldo) {
            return false;
        }
        saldo = saldo - valor;
        return true;
    }
}
