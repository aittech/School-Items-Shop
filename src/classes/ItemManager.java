package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class ItemManager {

	private ConnectDB db; // used to connect to database

	public ItemManager() {
		db = new ConnectDB(); // establishes a connection
	}

	// receives tblItems model and adds all items to the model
	public void addDataItems(DefaultTableModel model) {
		model.setRowCount(0);
		String sql = "select itemid, itemname, size, color, quantity, price from items";
		ResultSet rs = db.getResults(sql);
		try {
			while (rs.next()) {
				model.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6) });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// receives a Item object, adds the data from the object to the database
	public void addItem(Item item) {
		String sql = "insert into items(itemname, size, color, quantity, price) values(?,?,?,?,?)";
		try {
			PreparedStatement st = db.getConn().prepareStatement(sql);
			st.setString(1, item.getItemName());
			st.setString(2, item.getSize());
			st.setString(3, item.getColour());
			st.setInt(4, item.getQantity());
			st.setDouble(5, item.getPrice());
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// receives a Item object, edits the item details
	public void editItem(Item item) {
		String sql = "update items set itemname = ? , size = ? , color = ?, quantity = ?, price = ? where itemid = ?";
		try {
			PreparedStatement st = db.getConn().prepareStatement(sql);
			st.setString(1, item.getItemName());
			st.setString(2, item.getSize());
			st.setString(3, item.getColour());
			st.setInt(4, item.getQantity());
			st.setDouble(5, item.getPrice());
			st.setInt(6, item.getItemID());
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteItem(int itemID) {
		String sql = "delete from items where itemID = ?";

		try {
			PreparedStatement st = db.getConn().prepareStatement(sql);
			st.setInt(1, itemID);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getItemsForComboBox(JComboBox combo) {
		String sql = "select itemName, size, color from items";
		ResultSet rs = db.getResults(sql);
		try {
			while (rs.next()) {
				combo.addItem(rs.getString(1) + "-" + rs.getString(2) + "-" + rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Olly-Sorour-Blue //.split("") splits into different positions into an array
	public Item getItemForCart(String itemSelected) {
		String[] data = itemSelected.split("-");
		String sql = "select * from items where itemName = '" + data[0] + "' and " + "size = '" + data[1]
				+ "' and color = '" + data[2] + "'";
		ResultSet rs = db.getResults(sql);
		try {
			while (rs.next()) {
				return new Item(rs.getInt("ItemID"), rs.getString("ItemName"), rs.getString("Size"),
						rs.getString("Color"), rs.getInt("Quantity"), rs.getDouble("Price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getItemIDByName(String itemName) {
		int itemID = -1;
		String sql = "select itemID from items where ItemName = '" + itemName + "'";
		ResultSet rs = db.getResults(sql);
		try {
			if (rs.next()) {
				itemID = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemID;
	}

	public void decreaseItemQuantity(int itemID, int quantity) {
		String sql = "update items set quantity = quantity - ? where itemID = ?";
		try {
			PreparedStatement st = db.getConn().prepareStatement(sql);
			st.setInt(1, quantity);
			st.setInt(2, itemID);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
