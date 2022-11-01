package src.model;

import java.awt.*;

public class TextDraw extends GameField {

	private int size;

	public TextDraw(int x, int y, String field, String type, int size) {
		super(x, y, field, type);
		this.size = size;
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("CENTURY SCHOOLBOOK", Font.BOLD, size));
		g2.drawString(field, x, y);

	}

}
