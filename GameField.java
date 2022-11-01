package src.model;

import java.awt.Graphics2D;

public abstract class GameField {
	
	public final int celSize = 75;
	
	protected int x;
	protected int y;
	protected String field;
	protected String type;
	
	public GameField(int x, int y, String field, String type) {
		this.x = x;
		this.y = y;
		this.field = field;
		this.type = type;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public String getField() {
		return field;
	}
	public String getType() {
		return type;
	}
	
	public abstract void render(Graphics2D g2);
	
	@Override
	public String toString() {
		return "("+y+", "+x+") Cell name: "+field;
	}
}
