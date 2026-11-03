# Apostila de POO I e seus exemplos

- **A apostila:** [`Apostila_POO_I_Java_Aluno.pdf`](Apostila_POO_I_Java_Aluno.pdf) (para ler) e [`.docx`](Apostila_POO_I_Java_Aluno.docx) (editável).
- **Os exemplos:** um pacote por capítulo (`cap01` a `cap16`) em `src`. Cada exemplo com `main` roda sozinho.

## Como abrir

1. NetBeans: **File > Open Project** e escolha esta pasta (`apostila`).
2. Abra o arquivo do exemplo, clique com o botão direito > **Run File** (Shift+F6).

Há muitos `main`; o botão de rodar o projeto inteiro não sabe qual você quer.

## Plano B, pelo terminal (dentro desta pasta)

```powershell
New-Item -ItemType Directory -Force -Path build\classes | Out-Null
javac -encoding UTF-8 -d build\classes -sourcepath src src\cap08\ExemploPlaylist.java
java -cp build\classes cap08.ExemploPlaylist
```

## Capítulos

| Pacote | Assunto | Rode |
|---|---|---|
| `cap01` | Por que OO | `CantinaSemObjetos`, `CantinaComObjetos` |
| `cap02` | Java essencial | `MediaDaTurma`, `LeituraTeclado` |
| `cap03` | Classes, objetos e referências | `ExemploReferencias` |
| `cap04` | Encapsulamento | `ExemploContaCantina` |
| `cap05` | UML | `ExemploUml` |
| `cap06` | Construtores e `this(...)` | `ExemploConstrutores` |
| `cap07` | `static`, `toString`, `equals` | `ExemploStaticToString` |
| `cap08` | `ArrayList` | `ExemploArrayList`, `ExemploPlaylist` |
| `cap09` | Associação e composição | `ExemploRelacoes` |
| `cap10` | Exceções | `ExemploExcecoes` |
| `cap11` | Prévia de herança | `ExemploHeranca` |
| `cap12` | Camadas (modelo, regras, tela) | `tela/MenuCantina` |
| `cap13` | Testes | `TesteCantina` |
| `cap14` | Arquivos CSV | `ExemploArquivo` |
| `cap15` | Primeira janela (Swing) | `PrimeiraJanela` |
| `cap16` | Tela + modelo | `JanelaPlaylist` |

Os capítulos 17 e 18 usam o projeto `../sistema_biblioteca`.
