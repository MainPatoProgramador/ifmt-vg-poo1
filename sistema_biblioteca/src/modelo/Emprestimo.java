package modelo;

import java.time.LocalDate;

/**
 * Registro de um empréstimo. Quem cria é a Biblioteca (composição):
 * ele liga um leitor a um item numa data.
 */
public class Emprestimo {
    private Leitor leitor;
    private ItemBiblioteca item;
    private LocalDate data;
    private boolean ativo;

    public Emprestimo(Leitor leitor, ItemBiblioteca item, LocalDate data) {
        this.leitor = leitor;
        this.item = item;
        this.data = data;
        this.ativo = true;
    }

    public Leitor getLeitor() { return leitor; }
    public ItemBiblioteca getItem() { return item; }
    public LocalDate getData() { return data; }
    public boolean isAtivo() { return ativo; }

    public void encerrar() {
        ativo = false;
    }

    @Override
    public String toString() {
        String situacao = ativo ? "em aberto" : "devolvido";
        return data + " | " + leitor.getNome() + " | " + item.getTitulo() + " | " + situacao;
    }
}
