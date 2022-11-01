package src.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.StringTokenizer;

public class PropertyUtility extends GameField{

	protected Player owner;
	protected int rent;
	protected int price;
	
	public PropertyUtility(int x, int y, String field,
			String type, int price, int rent) {
		super(x, y, field, type);
		this.price = price;
		this.rent = rent;
	}
	
	public boolean buyProperty(Player p) {
		this.owner = p;
		if(p.getBalance()<price)
			return false;
		p.withDraw(price);
		p.buyProperty(this);
		return true;
	}
	
	public void payRent(Player p) {
		this.owner.deposit(p.withDraw(200));
	}
	
	public boolean isOwned() {
		if (owner == null)
			return false;
		return true;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public int getPrice() {
		return price;
	}
	
	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.drawRect(x*celSize, y*celSize, celSize, celSize);
		g2.setFont(new Font("Arial", Font.BOLD, 12));
		StringTokenizer token = new StringTokenizer(field);
		int z = 30;
		while(token.hasMoreTokens()) {
			g2.drawString(token.nextToken(), x*celSize+20, y*celSize+z);
			z+=20;
		}
	}
	
	
}
