
public class Tea extends Drink {

	private String sugarType;
	public Tea(String drinkID, String drinkName, String drinkType, Integer drinkPrice, String sugarType) {
		super(drinkID, drinkName, drinkType, drinkPrice);
		// TODO Auto-generated constructor stub
		this.sugarType = sugarType;
	}

	@Override
	public String typeOfDrink() {
		// TODO Auto-generated method stub
		return sugarType;
	}

	@Override
	public Integer tax() {
		// TODO Auto-generated method stub
		return 2000;
	}
	
	public static Integer getTax() {
		return 2000;
	}

	
}
