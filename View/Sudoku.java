package View;

import Controller.BotoesControle;
import Controller.SudokuControle;
import Model.Jogo;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Joice
 */
public class Sudoku extends JFrame {
    
    //Construtor iniciar jogo;
     public Sudoku() {
        super("Sudoku");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        Jogo jogo = new Jogo();

        BotoesControle buttonController = new BotoesControle(jogo);
        PainelDeBotoes buttonPanel = new PainelDeBotoes();
        buttonPanel.setControle(buttonController);
        add(buttonPanel, BorderLayout.EAST);

        SudokuPainel sudokuPanel = new SudokuPainel();
        SudokuControle sudokuController = new SudokuControle(sudokuPanel, jogo);
        sudokuPanel.setJogo(jogo);
        sudokuPanel.setControle(sudokuController);
        add(sudokuPanel, BorderLayout.CENTER);

        jogo.addObserver(buttonPanel);
        jogo.addObserver(sudokuPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
    public static void main(String args[]) {
        
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ex) { ex.printStackTrace(); }
        new Sudoku();
        
    }
}
