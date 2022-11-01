/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

import javax.swing.JFrame;
import src.view.GameBoard;

/**
 *
 * @author DELL
 */
public class showGame {

    public showGame() {
    }
    public void displayGame(){
        JFrame window = new JFrame();
                window.setSize(300, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Monopoly");

		var game = new GameBoard(window);
		game.init();
                
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);
    }
}
