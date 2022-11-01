package src.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;
import java.util.StringTokenizer;

public class Swap extends GameField{

	public Swap(int x, int y, String field, String type) {
		super(x, y, field, type);
	}
	
	public void swap(Player p1, Player p2) {
		if(p2 != null) {
			List<GameField> p1Properties = p1.getCheapProperty();
			List<GameField> p2Properties = p2.getAllProperties();
			for(GameField field: p1Properties)
				p2.buyProperty(field);
			for(GameField field: p2Properties)
				p1.buyProperty(field);
		}
	}
	
	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.drawRect(x*celSize, y*celSize, celSize, celSize);
		g2.setFont(new Font("Arial", Font.PLAIN, 14));
		StringTokenizer token = new StringTokenizer(field);
		int z = 30;
		while(token.hasMoreTokens()) {
			g2.drawString(token.nextToken(), x*celSize+20, y*celSize+z);
			z+=15;
		}
	}
	
}
