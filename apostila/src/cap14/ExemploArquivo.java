package cap14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Cap. 14: gravar e ler um arquivo CSV. */
public class ExemploArquivo {
    public static void main(String[] args) {
        Path arquivo = Path.of("placar.csv");   // criado na pasta onde o programa roda

        // 1) Gravar
        ArrayList<Jogador> placar = new ArrayList<>();
        placar.add(new Jogador("Ana", 120));
        placar.add(new Jogador("Bruno", 95));
        ArrayList<String> linhas = new ArrayList<>();
        for (Jogador j : placar) {
            linhas.add(j.paraCsv());
        }
        try {
            Files.write(arquivo, linhas);
            System.out.println("Gravado em " + arquivo.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Não consegui gravar: " + e.getMessage());
        }

        // 2) Ler de volta
        try {
            List<String> lidas = Files.readAllLines(arquivo);
            for (String linha : lidas) {
                System.out.println("Lido: " + Jogador.deCsv(linha));
            }
        } catch (IOException e) {
            System.out.println("Não consegui ler: " + e.getMessage());
        }
    }
}
