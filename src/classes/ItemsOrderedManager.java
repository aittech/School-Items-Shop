// A package named classes
package classes;

//Importing necessary classes
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// A class named ItemsOrderedManager
public class ItemsOrderedManager {

	private ConnectDB db; // used to connect to database

	public ItemsOrderedManager() {
		db = new ConnectDB(); // establishes a connection
	}

	// receives tblItemsOrdered model and adds all itemsOrdereds to the model
	public void addDataItemsOrdered(DefaultTableModel model) {
		model.setRowCount(0);
		String sql = "select orderid, itemid, quantity from itemsOrdereds";
		ResultSet rs = db.getResults(sql);
		try {
			while (rs.next()) {
				model.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5) });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addItemsForOrder(JTable tblCart) {
		OrderManager orderManager = new OrderManager();
		int orderID = orderManager.getLastOrderID();
		ItemManager itemManager = new ItemManager();
		DefaultTableModel model = (DefaultTableModel) tblCart.getModel();
		for (int i = 0; i < model.getRowCount(); i++) {
			String itemName = model.getValueAt(i, 0).toString();
			int itemID = itemManager.getItemIDByName(itemName);
			int quantity = Integer.parseInt(model.getValueAt(i, 3).toString());
			String sql = "insert into itemsOrdered(orderID, itemID, quantity) values(?,?,?)";
			try {
				PreparedStatement st = db.getConn().prepareStatement(sql);
				st.setInt(1, orderID);
				st.setInt(2, itemID);
				st.setInt(3, quantity);
				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			itemManager.decreaseItemQuantity(itemID, quantity);
		}
	}

	// receives an ItemsOrdered object, edits the itemsOrdered details
	public void editItemsOrdered(ItemsOrdered itemsOrdered) {
		String sql = "update itemsOrdered set itemid = ? , quantity = ? where orderID = ?";
		try {
			PreparedStatement st = db.getConn().prepareStatement(sql);
			st.setInt(1, itemsOrdered.getItemID());
			st.setInt(2, itemsOrdered.getQuanity());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteItemsOrdered(int itemsOrderedID) {
		String sql = "delete from itemsOrdered where OrderID = ?";
		try {
			PreparedStatement st = db.getConn().prepareStatement(sql);
			st.setInt(1, itemsOrderedID);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getItemsByOrderID(DefaultTableModel model, int orderID) {
		model.setRowCount(0);
		String sql = "SELECT ItemName, Size, Color, ItemsOrdered.Quantity, (ItemsOrdered.Quantity * Price) "
				+ "FROM (Items INNER JOIN ItemsOrdered ON Items.ItemID = ItemsOrdered.ItemID) "
				+ "INNER JOIN Orders ON ItemsOrdered.OrderID = Orders.OrderID WHERE ItemsOrdered.OrderID = '" + orderID
				+ "'";
		ResultSet rs = db.getResults(sql);
		try {
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getDouble(5) });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
