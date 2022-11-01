/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class ProxyshowGame {
private showGame ShowGame;
    public ProxyshowGame() {
    }
    
    public void checkchoice(){
       if (JOptionPane.showConfirmDialog( null,"are you want to play the game ","monoply",
            JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
           ShowGame = new showGame(); 
           ShowGame.displayGame();
    }
    
}
