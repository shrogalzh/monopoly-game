package src;

import javax.swing.JFrame;

import src.view.GameBoard;

public class Main {
	public static void main(String[] args) {
		/*JFrame window = new JFrame();
                window.setSize(300, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Monopoly");

		var game = new GameBoard(window);
		game.init();
                
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);*/
	ProxyshowGame proxy = new ProxyshowGame();
        proxy.checkchoice();
	}
}