package src.model;

import java.util.Random;

public class Dice {
	
	private static Dice dice;
	
	private Dice() {
		rand = new Random();
	}
	
	private Random rand;
	
	public int roll() {
		return (1+rand.nextInt(6))+(1+rand.nextInt(6));
	}
	
	public static Dice getDice() {
		if(dice == null)
			dice = new Dice();
		return dice;
	}

}
