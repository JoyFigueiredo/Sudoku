
# Sudoku (Java)

Implementação do clássico jogo **Sudoku** em **Java**, adotando o padrão arquitetural **MVC** (Model–View–Controller).  
Desenvolvido como projeto acadêmico na disciplina de Álgebra Linear.

---

## 🎯 Visão geral

- **Padrão:** MVC (separação clara entre Model, View e Controller).
- **Linguagem:** Java.
- **Uso:** Interface gráfica para resolver tabuleiros 9×9.
- Possível extensão para geração, verificação e solução automática via backtracking.

---

## 🗂️ Estrutura do repositório
Sudoku/
├─ Controller/      # Classes que lidam com interação e lógica de controle
├─ Model/           # Representação de tabuleiro, validações e estado do jogo
├─ View/            # Interface gráfica (Swing, JavaFX ou console)
├─ README.md        # Este arquivo
└─ pom.xml / build/ # Scripts de build (se aplicáveis)

---

## 🚀 Como executar

### 📦 Pré-requisitos

- Java JDK 11+ instalado.
- (Opcional) IDE de sua preferência: IntelliJ IDEA, Eclipse ou VS Code com suporte Java.

### Execução via linha de comando

```bash
cd Sudoku
javac Controller/*.java Model/*.java View/*.java
java Controller.Main
```
### Execução via IDE

- Importe o projeto como **Java Project**.
- Localize a classe principal (`Main` ou outra que inicialize o jogo).
- Execute-a diretamente na IDE.

---

## ⌨️ Controles (exemplo genérico)

- Interface gráfica/Swing:
  - Clique em células vazias e digite o número.
  - Botões: **Reset** / **Check** / **Solve** (caso implementados).
- Console:
  - Digite coordenadas (linha, coluna, valor).
  - Comandos adicionais como `reset`, `solve`, `exit`.

*Ajuste essas instruções conforme seu uso real.*

---

## 🧠 Lógica de funcionamento (nível alto)

1. **Model**: mantém o estado do tabuleiro (9×9), checagens de linha, coluna e subgrade.
2. **Controller**: processa entradas do usuário e coordena Model + View.
3. **View**: renderiza o tabuleiro e atualiza conforme interações.
4. **Possível Solver**: algoritmo backtracking que resolve o tabuleiro automaticamente.

---

## 📸 Capturas de tela / GIF

docs/screenshot-inicio.png
docs/screenshot-complete.png
docs/demo-sudoku.gif

---

## 🛠️ Desenvolvimento

- Use **VS Code**, **IntelliJ** ou **Eclipse**.
- Estrutura leve: apenas `javac` e `java` são suficientes para compilação e execução.

---

## 📚 Referências & Créditos

- Implementação com base em conceitos de Álgebra Linear (disciplina na qual foi criado).

---

## ✅ Status

Projeto concluído para fins acadêmicos. Pode ser expandido com:
- Geração de tabuleiros aleatórios.
- Validador/sistema de dica.
- Solver com visualização de backtracking.

---

## 📄 Licença

Esse projeto está licenciado sob a **MIT License**. Veja o arquivo `LICENSE` para mais detalhes.

---

## 🗺️ Roadmap (sugestões)

- [ ] Gerador de tabuleiros com níveis de dificuldade.
- [ ] Validador automático de jogadas.
- [ ] Solver visual (backtracking com animação).
- [ ] Export/import de tabuleiros (e.g. formato CSV/JSON).
