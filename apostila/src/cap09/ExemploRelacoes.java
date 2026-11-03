package cap09;

public class ExemploRelacoes {
    public static void main(String[] args) {
        Aluno ana = new Aluno("Ana");
        Produto coxinha = new Produto("Coxinha", 6.0);
        Produto suco = new Produto("Suco", 4.5);

        Pedido p1 = new Pedido(ana);
        p1.adicionar(coxinha, 2);
        p1.adicionar(suco, 1);
        System.out.println(p1.resumo());

        Pedido p2 = new Pedido(ana);   // o mesmo aluno em outro pedido: associação
        p2.adicionar(suco, 2);
        System.out.println(p2.resumo());
    }
}
