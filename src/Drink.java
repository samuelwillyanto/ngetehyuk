
public abstract class Drink {

	private String drinkID, drinkName, drinkType;
	private Integer drinkPrice;
	
	public abstract String typeOfDrink();
	
	public abstract Integer tax();
	
	public Drink(String drinkID, String drinkName, String drinkType, Integer drinkPrice) {
		super();
		this.drinkID = drinkID;
		this.drinkName = drinkName;
		this.drinkType = drinkType;
		this.drinkPrice = drinkPrice;
	}

	public String getDrinkID() {
		return drinkID;
	}

	public String getDrinkName() {
		return drinkName;
	}

	public Integer getDrinkPrice() {
		return drinkPrice;
	}
	
	
}
