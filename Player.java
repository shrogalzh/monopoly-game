package src.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameField{

	private String name;
	private double balance;
	private GameField currentCell;
	private List<GameField> lis;
	private Color color;
  private boolean completedRound = false;
	
	public Player(String name,
			GameField currentCell, Color color, int x, int y) {
		super(x, y, name, "Player");
		this.name = name;
		this.balance = 1500;
		this.currentCell = currentCell;
		this.color = color;
		lis = new ArrayList<>();
	}
  
  public boolean hasCompleted(){ return completedRound; }
	
	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}

	public GameField getCurrentCell() {
		return currentCell;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void deposit(double balance) {
		this.balance += balance;
	}
	
	public double withDraw(double balance) {
		this.balance -= balance;
		return balance;
	}


	public double payTax(int i) {
		double per = balance * i / 100;
		this.balance -= per;
		return per;
	}

	public void move(GameField cell, boolean passedGo) {
		this.currentCell = cell;
    if(passedGo && !this.completedRound) this.completedRound = true;
	}
	
	public void buyProperty(GameField field) {
		lis.add(field);
	}
	
	public List<GameField> getCheapProperty(){
		List<GameField> list = new ArrayList<GameField>();
		int size = lis.size();
		for(int i=size/2; i<size; i++) {
			list.add(lis.remove(i));
		}
		return list;
	}
	
	public List<GameField> getAllProperties(){
		List<GameField> list = new ArrayList<GameField>();
		int size = lis.size();
		for(int i=0; i<size; i++) {
			list.add(lis.remove(i));
		}
		return list;
	}
  
  public List<PropertyUtility> getProperties(){
    List<PropertyUtility> properties = new ArrayList<>();
    for(GameField gf : lis){
      if(!(gf instanceof PropertyUtility)) continue;
      properties.add((PropertyUtility) gf);
    }
    return properties;
  }
	
	public void render(Graphics2D g2) {
		g2.setColor(color);
		g2.fillOval(currentCell.x*celSize+x,
				currentCell.y*celSize+y, 25, 25);
	}
       
}
