package src.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.StringTokenizer;

public class ParkGardenMosque extends GameField{
	protected int rent;

	public ParkGardenMosque(int x, int y, String field, String type, int rent) {
		super(x, y, field, type);
		this.rent = rent;
	}
	
	public double payRent(Player p) {
		return p.withDraw(rent);
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.drawRect(x*celSize, y*celSize, celSize, celSize); 
		g2.setFont(new Font("Arial", Font.BOLD, 14));
		StringTokenizer token = new StringTokenizer(field);
		int z = 30;
		while(token.hasMoreTokens()) {
			g2.drawString(token.nextToken(), x*celSize+20, y*celSize+z);
			z+=20;
		}
	}
	
}
