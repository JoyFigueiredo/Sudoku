/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Jogo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

/**
 *
 * @author Joice
 */
public class BotoesControle implements ActionListener {

    private Jogo jogo;
    
    //Construtor dos botoes do painel do jogo
    public BotoesControle(Jogo jogo) {
        this.jogo = jogo;
    }
    
    
    // Executa a ação depois que o usuario pressionar o botão.        
    @Override
    public void actionPerformed(ActionEvent arg0) {
       if (arg0.getActionCommand().equals("Novo"))
            jogo.novoJogo();
        else if (arg0.getActionCommand().equals("Check"))
            jogo.checkJogo();
        else if (arg0.getActionCommand().equals("Sair"))
            System.exit(0);
        else if (arg0.getActionCommand().equals("Ajuda on"))
            jogo.setAjuda(((JCheckBox)arg0.getSource()).isSelected());
        else
            jogo.setNumeroSelecionado(Integer.parseInt(arg0.getActionCommand()));
    }
    
    
    
}
