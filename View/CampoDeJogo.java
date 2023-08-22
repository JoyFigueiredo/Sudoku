package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;


/**
 *  Classe representa o campo do sudoku
 * @author Joice
 */
public class CampoDeJogo extends JLabel{ //JLabel classe Biblioteca Java, usada para exibir textos
    Color numerosInser = new Color(255, 69, 0);
    private int x;      // Posição X no jogo.
    private int y;      // Posição Y no jogo.
    
    /*
    * Contrutor da classe ao qual rotula e define as posições X e Y no Jogo
    */
    public CampoDeJogo(int x, int y) {
        super("", CENTER); //centralizando o campo
        this.x = x;
        this.y = y;
        
        setPreferredSize(new Dimension(40, 40)); //Definindo um tamanho para o campo
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); //Bordas cinza escuro
        setFont(new Font(Font.SERIF, Font.CENTER_BASELINE, 22)); //Fonte                                              
        setOpaque(true); // Fundo opaco e translucido
    }
    
    /*
    * Metodo setNumero
    * Metodo usado para definir a cor do primeiro plano diacordo com o userInput.
    * Variavel numero ao qual sera definido
    * UserInput um booleano que indica se o numero foi ou não inserido pelo usuario.
    */
    public void setNumero(int numero, boolean userInput) {
        setForeground(userInput ? numerosInser : Color.BLACK); // cor dos numeros inseridos, e dos numeros ja colocados
        setText(numero > 0 ? numero + "" : "");
    }
    
    //Retorna a posição X no jogo.
    public int getCampoX() {
        return x;
    }
    
    //Retorna a posição Y no jogo.
    public int getCampoY() {
        return y;
    }
}
