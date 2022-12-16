import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	
	private Connect con;
	private Scanner sc;

	public Main() {
		// TODO Auto-generated constructor stub
		con = Connect.getConnection();
		sc = new Scanner(System.in);
		
		mainMenu();
	}
	
	public void mainMenu() {
		Integer input;
		
		do {
			System.out.println();
			System.out.println("Ngeteh Yuk!");
			System.out.println("===========");
			System.out.println("1. Buy Tea");
			System.out.println("2. View Transaction");
			System.out.println("3. Delete Transaction");
			System.out.println("4. Exit");
			System.out.print(">> ");
			input = sc.nextInt(); sc.nextLine();
			
			switch (input) {
				case 1:
					buyTea();
					break;
				case 2:
					viewTransaction();
					break;
				case 3:
					deleteTransaction();
					break;
			}
		} while (input != 4);
	}

	public void deleteTransaction() {
		// TODO Auto-generated method stub
		viewTransaction();
		Integer i = 0, input;
		
		ResultSet rs = con.executeQuery("select * from transaction");
		
		try {
			while(rs.next()) {
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		do {
			System.out.print("Input the number of index you want to delete [1 .. "+i+"] : TR00");
			input = sc.nextInt(); sc.nextLine();
		} while (input < 0 || input > i);
		
		con.executeUpdate("DELETE FROM transaction WHERE transactionid = 'TR00"+input+"'");
		System.out.println("Successfully delete transaction!");
		
	}

	public void viewTransaction() {
		// TODO Auto-generated method stub
		String transactionID, buyerName, drinkName, drinkType;
		Integer id = 1, drinkPrice, quantity, tax, totalPrice;
		System.out.println(" ID - Transaction ID - Buyer Name - Drink Name - "
				+ "Drink Type - Drink Price - Quantity - Tax - Total Price");
		
		ResultSet rs = con.executeQuery("select * from transaction tr join "
				+ "(select * from tea union select * from milktea) tea on tr.drinkid = tea.drinkid");
		
		try {
			while(rs.next()) {
				transactionID = rs.getString("transactionid");
				buyerName = rs.getString("buyername");
				drinkName = rs.getString("drinkname");
				drinkPrice = rs.getInt("drinkprice");
				quantity = rs.getInt("quantity");
				if(rs.getString(2).startsWith("TE")) {
					tax = 2000;
				} else {
					tax = 3500;				}
				totalPrice = (drinkPrice * quantity) + tax;
						
				
				System.out.println(id + " - " + transactionID + " - " + buyerName + " - " 
				+ drinkName + " - " + drinkPrice + " - " + quantity + " - " + tax + " - " + totalPrice);
				id++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void buyTea() {
		// TODO Auto-generated method stub
		displayTea();
		Integer drinkInput, qty, i = 1, totalPrice;
		String name, transactionID = null;
		String drinkName = null, drinkId = null;
		Integer drinkPrice = null, tax;
		
		do {
			System.out.print("Input the number of index you want to buy [1 .. 10] : ");
			drinkInput = sc.nextInt(); sc.nextLine();
		} while (drinkInput < 1 || drinkInput > 10);
		
		do {
			System.out.print("Input quantity [>0] : ");
			qty = sc.nextInt(); sc.nextLine();
		} while (qty < 1);
		
		do {
			System.out.print("Input your name [must be 2 words] : ");
			name = sc.nextLine();
		} while (!name.contains(" "));
		
		
		ResultSet rs = con.executeQuery("select * from transaction");
		
		try {			
			while(rs.next()) {
				i++;
				transactionID = "TR00" + i;
				if(rs.getString(1).equals(transactionID)) {
					i++;
				}
			}
			transactionID = "TR00" + i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(drinkInput >= 1 && drinkInput <= 5) {
			tax = Tea.getTax();
			ResultSet rsTea = con.executeQuery("select * from tea");
			try {
				while(rsTea.next()) {
					if(rsTea.getString(1).equals("TE00"+drinkInput+"")) {
						drinkId = rsTea.getString(1);
						drinkName = rsTea.getString(2);
						drinkPrice = rsTea.getInt(4);
					}
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			tax = MilkTea.getTax();
			ResultSet rsMilk = con.executeQuery("select * from milktea");
			Integer milkTeaCheck = drinkInput - 5;
			try {
				while(rsMilk.next()) {
					if(rsMilk.getString(1).equals("MT00"+milkTeaCheck+"")) {
						drinkId = rsMilk.getString(1);
						drinkName = rsMilk.getString(2);
						drinkPrice = rsMilk.getInt(4);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		totalPrice = (drinkPrice * qty) + tax;
		
		Transaction t = new Transaction(transactionID, drinkId, name, qty);
		
		con.executeUpdate("insert into transaction values ('"+transactionID+"', '"+drinkId+"', '"+name+"', "+qty+")");
				
		System.out.println("Detail Transaction");
		System.out.println("Transaction ID: " + transactionID);
		System.out.println("Buyer Name: " + name);
		System.out.println("Drink Name: " + drinkName);
		System.out.println("Drink Price: " + drinkPrice);
		System.out.println("Drink Quantity: " + qty);
		System.out.println("Tax: " + tax);
		System.out.println("Total Price: " + totalPrice);
		
		System.out.println("Successfully inserted new transaction!");
	
		
	}
	
	public void displayTea() {
		System.out.println("No - Drink ID - Drink Name - Drink Type - Drink Price - Sugar Type - Milk Type");
		
		ResultSet rs = con.executeQuery("select * from tea");
		Integer i = 1, drinkPrice;
		String drinkID, drinkName, drinkType, sugarType, milkType;
		try {
			while(rs.next()) {
				drinkID = rs.getString(1);
				drinkName = rs.getString(2);
				drinkType = rs.getString(3);
				drinkPrice = rs.getInt(4);
				sugarType = rs.getString(5);
				Tea t = new Tea(drinkID, drinkName, drinkType, drinkPrice, sugarType);
				System.out.println(i + " - " + drinkID + " - " + drinkName + " - " + drinkType + " - " + drinkPrice + " - " + sugarType + " - " + " - ");
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rsMilkTea = con.executeQuery("select * from milktea");
		try {
			while(rsMilkTea.next()) {
				drinkID = rsMilkTea.getString(1);
				drinkName = rsMilkTea.getString(2);
				drinkType = rsMilkTea.getString(3);
				drinkPrice = rsMilkTea.getInt(4);
				milkType = rsMilkTea.getString(5);
				MilkTea m = new MilkTea(drinkID, drinkName, drinkType, drinkPrice, milkType);
				System.out.println(i + " - " + drinkID + " - " + drinkName + " - " + drinkType + " - " + drinkPrice + " - " + " - " + " - " + milkType);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
