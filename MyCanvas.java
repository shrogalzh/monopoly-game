package src.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import src.model.GameField;

public class MyCanvas extends JPanel {

	private static final long serialVersionUID = 1L;
	private List<GameField> gameElements;
	private GameBoard gameBoard;

	public MyCanvas(GameBoard gameBoard, int width, int height) {
		this.gameBoard = gameBoard;
		gameElements = new ArrayList<GameField>();
		setBackground(Color.black);
		setPreferredSize(new Dimension(width, height));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		for (var e : gameElements) {
			e.render(g2);
		}

	}

	public List<GameField> getGameElements() {
		return gameElements;
	}
	
	public GameBoard getGameBoard() {
		return gameBoard;
	}

}
