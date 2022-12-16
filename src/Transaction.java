import java.util.Vector;

public class Transaction {

	private String transactionID, drinkID, buyerName;
	private Integer quantity;
	public Transaction(String transactionID, String drinkID, String buyerName, Integer quantity) {
		super();
		this.transactionID = transactionID;
		this.drinkID = drinkID;
		this.buyerName = buyerName;
		this.quantity = quantity;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public String getDrinkID() {
		return drinkID;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public Integer getQuantity() {
		return quantity;
	}
		 
}
