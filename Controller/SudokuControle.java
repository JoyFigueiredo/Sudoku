package Controller;

import Model.Botoes;
import Model.Jogo;
import View.CampoDeJogo;
import View.SudokuPainel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *  Classe que controla todas as ações do usuario
 * @author Joice
 */
public class SudokuControle implements MouseListener {
    
    private SudokuPainel sudokuPainel;    // Painel de Controle
    private Jogo jogo;                    // Jogo atual
    
    //CONSTRUTOR
    public SudokuControle(SudokuPainel sudokuPanel, Jogo game) {
        this.sudokuPainel = sudokuPanel;
        this.jogo = game;
    }
        
    /*
    * Cliques dentro do jogo, definindo na posição clicada o numero selecionado
    * e atualiza o campo clicado. 
    * Se o usuario clicar com o botão direito do mouse o número sera apagado no jogo,
    * e o campo será atualizado.
    */
    //Classe abstrata da classe MouseListener
    public void mousePressed(MouseEvent e) {
        JPanel panel = (JPanel)e.getSource(); // JPanel como o proprio nome sugere é o que vai gerar o painel do jogo
        Component componente = panel.getComponentAt(e.getPoint());
        if (componente instanceof CampoDeJogo) {
            CampoDeJogo campo = (CampoDeJogo)componente;
            int x = campo.getCampoX();
            int y = campo.getCampoY();

            if (e.getButton() == MouseEvent.BUTTON1 && 
                                (jogo.getNumero(x, y) == 0 || campo.getForeground().equals(new Color(255, 69, 0)))) {
                int numero = jogo.getNumeroSelecionado();
                if (numero == -1)
                    return;
                jogo.setNumero(x, y, numero);
                campo.setNumero(numero, true);
            } else if (e.getButton() == MouseEvent.BUTTON3 && !campo.getForeground().equals(Color.BLACK)) {
                jogo.setNumero(x, y, 0);
                campo.setNumero(0, false);
            }
            sudokuPainel.update(jogo, Botoes.CANDIDATO);
        }
    }
    
    public void mouseClicked(MouseEvent e){ }
    public void mouseEntered(MouseEvent e){ }
    public void mouseExited(MouseEvent e){ }
    public void mouseReleased(MouseEvent e){ }   
}
