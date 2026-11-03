# Sistema da biblioteca (Cap. 17 e 18 da apostila)

Sistema de empréstimos com janela Swing, regras, testes e dados em CSV.

| Pacote | Camada | Classes |
|---|---|---|
| `modelo` | modelo | `ItemBiblioteca`, `Livro`, `Revista`, `Leitor`, `Emprestimo` |
| `regras` | regras | `Biblioteca`, `RegraException` |
| `dados` | dados | `ArquivoCsv` |
| `tela` | tela | `JanelaBiblioteca`, `Principal` |
| `testes` | testes | `TesteBiblioteca` |

## Como usar

1. NetBeans: **File > Open Project** nesta pasta.
2. Complete os `TODO` 1 a 10 em `regras/Biblioteca.java`. Rode `testes/TesteBiblioteca.java` (Shift+F6) até ver `18 passaram, 0 falharam`.
3. Complete os `TODO` 11 a 13 em `tela/JanelaBiblioteca.java`.
4. Rode `tela/Principal.java`. Os dados ficam na pasta `poo1_dados_biblioteca`, dentro da sua pasta de usuário (criada ao fechar a janela; o caminho aparece no Output).

## Plano B, pelo terminal (dentro desta pasta)

```powershell
New-Item -ItemType Directory -Force -Path build\classes | Out-Null
javac -encoding UTF-8 -d build\classes -sourcepath src src\tela\Principal.java src\testes\TesteBiblioteca.java
java -cp build\classes testes.TesteBiblioteca
java -cp build\classes tela.Principal
```
