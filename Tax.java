package src.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.StringTokenizer;

public class Tax extends GameField{

	public Tax(int x, int y, String field, String type) {
		super(x, y, field, type);
	}
	
	public String payTax(Player p1, Player p2,Bank bank) {
		if(field.contains("Income")) {
			bank.desposit(p1.payTax(7));
			return p1.getName()+" has paid Income tax";
		}else if(field.contains("Luxury")) {
			bank.desposit(p1.payTax(12));
			return p1.getName()+" has paid Luxury tax";
		}else{
			double half = p1.payTax(50);
			if(p2 != null) {
				p2.deposit(half);
				return p1.getName()+" has Transfer half of balance to " + p2.getName();
			}
			return "There is only one player balnce can't be transfered";
		}
	}
	
	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.drawRect(x*celSize, y*celSize, celSize, celSize);
		g2.setFont(new Font("Arial", Font.BOLD, 14));
		StringTokenizer token = new StringTokenizer(field);
		int z = 30;
		while(token.hasMoreTokens()) {
			g2.drawString(token.nextToken(), x*celSize+10, y*celSize+z);
			z+=20;
		}
	}
	
}
