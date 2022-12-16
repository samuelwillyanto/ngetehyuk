
public class MilkTea extends Drink {

	private String milkType;
	public MilkTea(String drinkID, String drinkName, String drinkType, Integer drinkPrice, String milkType) {
		super(drinkID, drinkName, drinkType, drinkPrice);
		// TODO Auto-generated constructor stub
		this.milkType = milkType;
	}

	@Override
	public String typeOfDrink() {
		// TODO Auto-generated method stub
		return milkType;
	}

	@Override
	public Integer tax() {
		// TODO Auto-generated method stub
		return 3500;
	}
	
	public static Integer getTax() {
		return 3500;
	}
	

}
