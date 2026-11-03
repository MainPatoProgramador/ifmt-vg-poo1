package cap08;

public class ExemploPlaylist {
    public static void main(String[] args) {
        Playlist p = new Playlist("Estudar");
        p.adicionar(new Musica("Chuva no Cerrado", 200));
        p.adicionar(new Musica("Rio Cuiabá", 245));
        p.adicionar(new Musica("Fim de Tarde", 180));

        System.out.println(p);                                   // Estudar: 3 músicas
        System.out.println("Total (s): " + p.duracaoTotal());    // 625
        System.out.println("Mais longa: " + p.maisLonga());      // Rio Cuiabá (4:05)
    }
}
