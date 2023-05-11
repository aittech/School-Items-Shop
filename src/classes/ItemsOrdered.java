package classes;

public class ItemsOrdered {

	private int orderID; // variable to store the order ID
	private int itemID; // variable to store the item ID
	private int quanity; // variable to store the quantity of items ordered

	public ItemsOrdered(int orderID, int itemID, int quanity) {
		this.orderID = orderID; // initialize order ID
		this.itemID = itemID; // initialize item ID
		this.quanity = quanity; // initialize quantity
	}

	public int getOrderID() {
		return orderID; // returns the order ID
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID; // sets the order ID
	}

	public int getItemID() {
		return itemID; // returns the item ID
	}

	public void setItemID(int itemID) {
		this.itemID = itemID; // sets the item ID
	}

	public int getQuanity() {
		return quanity; // returns the quantity of items ordered
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity; // sets the quantity of items ordered
	}

}
