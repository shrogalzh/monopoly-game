package src.model;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Fields extends GameField{

	private List<GameField> allFields;
	
	public Fields() {
		super(-1, -1, "", "");
		GameField jail = new Jail(0, 9, "In Jail", "Jail", 10, null);
		allFields = new ArrayList<GameField>();
		allFields.add(new Go(0, 0, "Go", "Go"));
		allFields.add(new PropertyUtility(1, 0, "White House", "Property", 300, 200));
		allFields.add(new PropertyUtility(2, 0, "Chicago Avenue", "Property", 300, 200));
		allFields.add(new PropertyUtility(3, 0, "Texas Avuenue", "Property", 300, 200));
		allFields.add(new PropertyUtility(4, 0, "Utility", "Utility", 100, 50));
		allFields.add(new PropertyUtility(5, 0, "College Avenue", "Property", 300, 200));
		allFields.add(new PropertyUtility(6, 0, "Burger King", "Property", 300, 200));
		allFields.add(new NothingParkingRollAgain(7, 0, "Nothing", "Nothing"));
		allFields.add(new PropertyUtility(8, 0, "Holiday inn Hotel", "Property", 300, 200));
		allFields.add(new Jail(9, 0, "Go to Jail", "Jail",0, jail));
		allFields.add(new NothingParkingRollAgain(9, 1, "Roll again", "Roll again"));
		allFields.add(new ParkGardenMosque(9, 2, "Blue Mosque", "Mosque", 200));
		allFields.add(new PropertyUtility(9, 3, "Railroads", "Property", 100, 50));
		allFields.add(new Tax(9, 4, "Luxury tax", "Tax"));
		allFields.add(new ParkGardenMosque(9, 5, "City Park", "Park", 20));
		allFields.add(new NothingParkingRollAgain(9, 6, "Nothing", "Nothing"));
		allFields.add(new Tax(9, 7, "Give_Half of_your money", "Tax"));
		allFields.add(new ParkGardenMosque(9, 8, "Mosque", "Mosque", 20));
		allFields.add(new NothingParkingRollAgain(9, 9, "Free Parking", "Parking"));
		allFields.add(new PropertyUtility(8, 9, "New_York Avenue", "Property", 300, 200));
		allFields.add(new PropertyUtility(7, 9, "Colorado Avenue", "Property", 300, 200));
		allFields.add(new Tax(6, 9, "Income Tax", "Tax"));
		allFields.add(new ParkGardenMosque(5, 9, "Marvin Garden", "Garden", 20));
		allFields.add(new NothingParkingRollAgain(4, 9, "Nothing", "Nothing"));
		allFields.add(new PropertyUtility(3, 9, "Red House", "Property", 300, 200));
		allFields.add(new PropertyUtility(2, 9, "Blue House", "Property", 300, 200));
		allFields.add(new PropertyUtility(1, 9, "Hilton Hotel", "Property", 300, 200));
		allFields.add(jail);
		allFields.add(new ParkGardenMosque(0, 8, "Mosque", "Mosque", 200));
		allFields.add(new Swap(0, 7, "Swap", "Swap"));
		allFields.add(new NothingParkingRollAgain(0, 6, "Nothing", "Nothing"));
		allFields.add(new PropertyUtility(0, 5, "Sheraton Hotel", "Property", 300, 200));
		allFields.add(new PropertyUtility(0, 4, "Yellow House", "Property", 300, 200));
		allFields.add(new PropertyUtility(0, 3, "Washington Avenue", "Property", 300, 200));
		allFields.add(new NothingParkingRollAgain(0, 2, "Roll again", "Roll again"));
		allFields.add(new PropertyUtility(0, 1, "Mall_of Arabia", "Property", 300, 200));	
	}

	@Override
	public void render(Graphics2D g2) {
		for(GameField field:allFields) {
			field.render(g2);
		}
	}
	
	public GameField getGo() {
		return allFields.get(0);
	}
	
	public List<GameField> getAllFields() {
		return allFields;
	}
	
	
}
