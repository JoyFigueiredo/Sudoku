<h1 align="center">Sudoku (Java)</h1>

<p align="center">
    Implementação do clássico jogo <strong>Sudoku </strong> em <strong>Java</strong>, adotando o padrão arquitetural <strong>MVC</strong> (Model–View–Controller).  
Desenvolvido como projeto acadêmico na disciplina de Álgebra Linear.
</p>


> **Stack principal:** Java (JDK 11+)  
> **Padrão:** MVC (Model–View–Controller)  
> **Plataforma alvo:** Windows/Linux/macOS (execução via JDK)

---

## 🎮 Como jogar

- Preencha os números de **1 a 9** em cada célula do tabuleiro 9×9.
- Cada linha, coluna e subgrade 3×3 deve conter todos os números **sem repetição**.
- Utilize botões ou comandos para **Reset**, **Check** ou **Solve** (se implementados).

---

## ✨ Funcionalidades

- Interface gráfica (Swing ou JavaFX).
- Validação automática de jogadas.
- Estrutura modular (MVC) para fácil manutenção.
- Possibilidade de extensão para:
  - Geração de tabuleiros aleatórios.
  - Solver automático via backtracking.

---

## 🗂️ Estrutura do repositório
```
Sudoku/
    ├─ Controller/      # Classes que lidam com interação e lógica de controle
    ├─ Model/           # Representação de tabuleiro, validações e estado do jogo
    ├─ View/            # Interface gráfica (Swing, JavaFX ou console)
    ├─ README.md        # Este arquivo
    └─ pom.xml / build/ # Scripts de build (se aplicáveis)
```
---

## ▶️ Executar

### Opção 1 — Linha de comando
```bash
cd Sudoku
javac Controller/*.java Model/*.java View/*.java
java Controller.Main
```
### Opção 2 - Execução via IDE

- Importe o projeto como **Java Project**.
- Localize a classe principal (`Main` ou outra que inicialize o jogo).
- Execute-a diretamente na IDE.

---

## ⌨️ Controles

- Interface gráfica/Swing:
  - Clique em células vazias e digite o número.
  - Botões: **Reset** / **Check** / **Solve** (caso implementados).
- Console:
  - Digite coordenadas (linha, coluna, valor).
  - Comandos adicionais como `reset`, `solve`, `exit`.

*Ajuste essas instruções conforme seu uso real.*

---

## 🧩 Lógica de funcionamento (nível alto)

1. **Model**: mantém o estado do tabuleiro (9×9), checagens de linha, coluna e subgrade.
2. **Controller**: processa entradas do usuário e coordena Model + View.
3. **View**: renderiza o tabuleiro e atualiza conforme interações.
4. **Possível Solver**: algoritmo backtracking que resolve o tabuleiro automaticamente.

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
