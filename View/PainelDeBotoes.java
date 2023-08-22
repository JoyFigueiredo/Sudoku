package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import Model.Botoes;
import Controller.BotoesControle;
import java.awt.Color;

/**
 *  Classe que desenha os botoes e as reaçoes do usuario sobre eles
 * @author Joice
 */
public class PainelDeBotoes extends JPanel implements Observer { //Observer usado para notificar outros objetos alguma alteração
    
    JButton botaoNovo, botaoCheck, botaoSair;   // Botoes a serem usados
    JCheckBox caixaAjuda;                   // Caixa de seleção da ajuda
    ButtonGroup grupoNumeros;              // agrupamento debotoes
    JToggleButton[] botoesNumeros;         // Botoes dos numeros
    
    //Construtor
    public PainelDeBotoes() {
        super(new BorderLayout()); //Divide o painel em norte, sul, leste, oeste e centro

        JPanel painelAlinhado = new JPanel();
        painelAlinhado.setLayout(new BoxLayout(painelAlinhado, BoxLayout.PAGE_AXIS)); //deixa os botoes juntos em uma caixa.
        add(painelAlinhado, BorderLayout.NORTH);

        JPanel painelOpcao = new JPanel(new FlowLayout(FlowLayout.LEADING)); // Layout de fluxo, deixando os componentes direcionais
        painelOpcao.setBorder(BorderFactory.createTitledBorder(" Options "));
        painelOpcao.setBackground( Color.LIGHT_GRAY );
        painelAlinhado.add(painelOpcao);

        botaoNovo = new JButton("Novo");
        botaoNovo.setFocusable(false);
        botaoNovo.setForeground(new Color(128, 0, 0));
        botaoNovo.setBackground( Color.BLACK );
        painelOpcao.add(botaoNovo);

        botaoCheck = new JButton("Check");
        botaoCheck.setFocusable(false);
        botaoCheck.setForeground(new Color(128, 0, 0));
        botaoCheck.setBackground( Color.BLACK );
        painelOpcao.add(botaoCheck);

        botaoSair = new JButton("Sair");
        botaoSair.setFocusable(false);
        botaoSair.setForeground(new Color(128, 0, 0));
        botaoSair.setBackground( Color.BLACK );
        painelOpcao.add(botaoSair);

        JPanel painelNumeros = new JPanel();
        painelNumeros.setLayout(new BoxLayout(painelNumeros, BoxLayout.PAGE_AXIS));
        painelNumeros.setBorder(BorderFactory.createTitledBorder(" Números "));
        painelNumeros.setBackground( Color.LIGHT_GRAY );
        painelAlinhado.add(painelNumeros);

        JPanel painelNumerosAjuda = new JPanel(new FlowLayout(FlowLayout.LEADING));
        painelNumeros.add(painelNumerosAjuda);
        
        caixaAjuda = new JCheckBox("Ajuda on", true);
        caixaAjuda.setFocusable(false);
        painelNumerosAjuda.add(caixaAjuda);

        JPanel painelDeNumeros = new JPanel(new FlowLayout(FlowLayout.LEADING));
        painelDeNumeros.setBackground( Color.LIGHT_GRAY );
        painelNumeros.add(painelDeNumeros);

        grupoNumeros = new ButtonGroup();
        botoesNumeros = new JToggleButton[9];//botoes com os numeros
        for (int i = 0; i < 9; i++) {
            botoesNumeros[i] = new JToggleButton("" + (i + 1));
            botoesNumeros[i].setPreferredSize(new Dimension(40, 40));
            botoesNumeros[i].setFocusable(false);
            grupoNumeros.add(botoesNumeros[i]);
            painelDeNumeros.add(botoesNumeros[i]);
        }
        
    }
    
    /*
    * Metodo da classe Observable
    * Metodo será chamado quando for enviado atualizações.
    */
    public void update(Observable o, Object arg) {
        switch ((Botoes)arg) {
            case NOVO_JOGO: //cria nome jogo
            case CHECK: //verificar jogo
                grupoNumeros.clearSelection(); //metodo para apagar os itens selecionados
                break;
        }
    }
    
    /*
    * setControle adiciona controladores aos componentes que no caso são os botoes
    */
    public void setControle(BotoesControle botoesDeControle) {
        //addAction cria um evento de cliques
        botaoNovo.addActionListener(botoesDeControle);
        botaoCheck.addActionListener(botoesDeControle);
        botaoSair.addActionListener(botoesDeControle);
        caixaAjuda.addActionListener(botoesDeControle);
        for (int i = 0; i < 9; i++){
            botoesNumeros[i].addActionListener(botoesDeControle);
        }
    }
}
