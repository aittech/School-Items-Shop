// declare that this class belongs to the package "classes"
package classes;

// define the public class "Item"
public class Item {

	// private member variables for an item's ID, name, size, color, quantity, and
	// price
	private int itemID;
	private String itemName;
	private String size;
	private String colour;
	private int quantity;
	private double price;

	// constructor that takes in all member variables including item ID
	public Item(int itemID, String itemName, String size, String colour, int quantity, double price) {
		// set the member variables to the values passed in as arguments
		this.itemID = itemID;
		this.itemName = itemName;
		this.size = size;
		this.colour = colour;
		this.quantity = quantity;
		this.price = price;
	}

	// constructor that takes in all member variables except item ID
	public Item(String itemName, String size, String colour, int quantity, double price) {
		// set the member variables to the values passed in as arguments
		this.itemName = itemName;
		this.size = size;
		this.colour = colour;
		this.quantity = quantity;
		this.price = price;
	}

	// getter method for item ID
	public int getItemID() {
		return itemID;
	}

	// setter method for item ID
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	// getter method for item name
	public String getItemName() {
		return itemName;
	}

	// setter method for item name
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	// getter method for item size
	public String getSize() {
		return size;
	}

	// setter method for item size
	public void setSize(String size) {
		this.size = size;
	}

	// getter method for item color
	public String getColour() {
		return colour;
	}

	// setter method for item color
	public void setColour(String colour) {
		this.colour = colour;
	}

	// getter method for item quantity
	public int getQantity() {
		return quantity;
	}

	// setter method for item quantity
	public void setQantity(int quantity) {
		this.quantity = quantity;
	}

	// getter method for item price
	public double getPrice() {
		return price;
	}

	// setter method for item price
	public void setPrice(double price) {
		this.price = price;
	}
}
