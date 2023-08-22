package View;

import Controller.SudokuControle;
import Model.Botoes;
import Model.Jogo;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Classe para desenhar o painel e atualizar açoes nele
 * @author Joice
 */
public class SudokuPainel extends JPanel implements Observer{

    // Colorir as casas canidatas do determinado numero.
    private static final Color COLOR_CANDIDATE = new Color(102,205,170);
    private static final Color FUNDO_CAMPO = new Color(255,228,196);
    private static final Color ERRADO = new Color(255,99,71);
    private static final Color CERTO = new Color(144,238,144);
    
    private CampoDeJogo[][] campo; // Matriz do campo
    private JPanel[][] painel;      // Painel da matriz do jogo

    //Construtor do painel de jogo
    public SudokuPainel() {
        super(new GridLayout(3, 3));// cria o campo 3x3 

        painel = new JPanel[3][3];
        for (int y = 0; y < 3; y++) { // percorre todo o campo do jogo adicionando em matrizes 3x3
            for (int x = 0; x < 3; x++) {
                painel[y][x] = new JPanel(new GridLayout(3, 3));
                painel[y][x].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(painel[y][x]);
            }
        }

        campo = new CampoDeJogo[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                campo[y][x] = new CampoDeJogo(x, y);
                painel[y / 3][x / 3].add(campo[y][x]);
            }
        }
    }

    //Definir os campos do Jogo
    public void setJogo(Jogo jogo) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                campo[y][x].setBackground(FUNDO_CAMPO);//colore cada posição da matriz
                campo[y][x].setNumero(jogo.getNumero(x, y), false); // setar com um valor
            }
        }
    }
    
    //Diacordo com o jogo é apresentado candidatos diferentes.
    private void setCandidatos(Jogo jogo) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                campo[y][x].setBackground(FUNDO_CAMPO);
                if (jogo.isAjuda() && jogo.isSelecionaNumeroCandidato(x, y)){
                    campo[y][x].setBackground(COLOR_CANDIDATE);
                }
            }
        }
    }
     
    //Adiciona controladores aos subpaineis passando por parametro a ação do usuario
    public void setControle(SudokuControle sudokuControle) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++){
                painel[y][x].addMouseListener(sudokuControle);
            }
        }
    }
    
     //Valida os campos de acordo com o jogo
    private void setValidaJogo(Jogo jogo) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                campo[y][x].setBackground(FUNDO_CAMPO);
                if (campo[y][x].getForeground().equals(new Color(255, 69, 0)))
                    campo[y][x].setBackground(jogo.isCheckValido(x, y) ? CERTO : ERRADO);
            }
        }
    }
    
    /*
    * Metodo da Classe Observable 
    * Metodo chamado para envia notificação de atualização
    */
    @Override
    public void update(Observable o, Object arg) {
        switch ((Botoes)arg) {
            case NOVO_JOGO:
                setJogo((Jogo)o);
                break;
            case CHECK:
                setValidaJogo((Jogo)o);
                break;
            case SELECIONA_NUMERO:
            case CANDIDATO:
            case AJUDA:
                setCandidatos((Jogo)o);
                break;
        }
    }
}
