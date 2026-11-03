package cap07;

public class ExemploStaticToString {
    public static void main(String[] args) {
        Leitor ana = new Leitor("Ana");
        Leitor bruno = new Leitor("Bruno");

        System.out.println(ana);            // 1 - Ana   (chama toString sozinho)
        System.out.println(bruno);          // 2 - Bruno
        System.out.println("Criados: " + Leitor.totalCriados());  // 2
        System.out.println("Limite: " + Leitor.LIMITE);           // 2

        Leitor mesmaAna = ana;
        System.out.println(ana.equals(mesmaAna));  // true
        System.out.println(ana.equals(bruno));     // false
    }
}
