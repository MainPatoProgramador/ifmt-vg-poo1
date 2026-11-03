package cap13;

import cap12.modelo.Produto;
import cap12.regras.Cantina;

/** Cap. 13: testa as REGRAS sem abrir tela nenhuma. */
public class TesteCantina {
    public static void main(String[] args) {
        Cantina c = new Cantina();
        c.cadastrar(new Produto("Coxinha", 6.0, 3));

        Verificador.verificar(c.vender("coxinha", 2) == 12.0, "venda cobra preço x quantidade");
        Verificador.verificar(c.buscar("Coxinha").getEstoque() == 1, "venda baixa o estoque");
        Verificador.verificar(c.getCaixa() == 12.0, "valor entra no caixa");

        try {
            c.vender("Coxinha", 5);
            Verificador.verificar(false, "estoque insuficiente deve ser recusado");
        } catch (IllegalArgumentException e) {
            Verificador.verificar(true, "estoque insuficiente deve ser recusado");
        }
        Verificador.verificar(c.getCaixa() == 12.0, "venda recusada não mexe no caixa");

        Verificador.resumo();
    }
}
