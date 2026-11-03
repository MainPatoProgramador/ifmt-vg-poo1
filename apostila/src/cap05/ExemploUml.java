package cap05;

public class ExemploUml {
    public static void main(String[] args) {
        Leitor ana = new Leitor("Ana");
        ana.registrarEmprestimo();
        ana.registrarEmprestimo();
        System.out.println(ana.getNome() + " pode pegar mais? " + ana.podePegar()); // false
    }
}
