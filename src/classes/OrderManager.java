package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import screens.NewOrderScreen;

public class OrderManager {

	private ConnectDB db; // used to connect to database

	public OrderManager() {
		db = new ConnectDB(); // establishes a connection
	}

	// Adds all orders to the given DefaultTableModel.
	public void addDataOrder(DefaultTableModel model) {
		model.setRowCount(0);
		String sql = "SELECT OrderID, Orders.StudentID, Firstname, Surname, OrderDate, Collected "
				+ "FROM Orders INNER JOIN Students ON (Orders.StudentID = Students.StudentID) "
				+ "AND (Orders.StudentID = Students.StudentID)";
		ResultSet rs = db.getResults(sql);
		try {
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getBoolean(6) });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Adds all orders to the given DefaultTableModel.
	public void addDataStudentsOrder(DefaultTableModel model, int studentID) {
		model.setRowCount(0);
		String sql = "SELECT OrderID, Orders.StudentID, Firstname, Surname, OrderDate, Collected "
				+ "FROM Orders INNER JOIN Students ON (Orders.StudentID = Students.StudentID) "
				+ "AND (Orders.StudentID = Students.StudentID) WHERE Orders.StudentID = '" + studentID + "'";
		ResultSet rs = db.getResults(sql);
		try {
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getBoolean(6) });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Adds an order to the database with the given student ID.
	public void addOrder(int studentID) {
		LocalDate today = LocalDate.now();
		String sql = "INSERT INTO orders(studentid, orderdate, collected) VALUES(?,?,?)";
		try {
			PreparedStatement st = db.getConn().prepareStatement(sql);
			st.setInt(1, studentID);
			st.setString(2, today.toString());
			st.setBoolean(3, false);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Edits the details of an order with the given Order object.
	public void editOrder(Order order) {
		String sql = "UPDATE order SET studentid = ? , orderdate = ? , collected = ? WHERE orderid = ?";
		try {
			PreparedStatement st = db.getConn().prepareStatement(sql);
			st.setString(1, order.getStudentID());
			st.setString(2, order.getOrderDate().toString());
			st.setBoolean(3, order.getCollected());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Deletes the order with the given order ID.
	public void deleteOrder(int orderID) {
		String sql = "DELETE FROM orders WHERE orderID = ?";
		try {
			PreparedStatement st = db.getConn().prepareStatement(sql);
			st.setInt(1, orderID);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addItemToCart(String itemData, int qty, JTable tblCart, JLabel lblCartTotal) {
		DefaultTableModel model = (DefaultTableModel) tblCart.getModel();
		ItemManager im = new ItemManager();
		Item item = im.getItemForCart(itemData);
		if (hasEnoughStock(item.getItemName(), qty)) {
			double cost = qty * item.getPrice();
			int rowAffected = getCartRowAffected(item.getItemName(), model);
			if (rowAffected != -1) {
				int cartQty = Integer.parseInt(model.getValueAt(rowAffected, 3).toString());
				double cartCost = Double.parseDouble(model.getValueAt(rowAffected, 5).toString());
				NewOrderScreen.totalCartCost -= cartCost;
				int newQty = qty + cartQty;
				cost = newQty * item.getPrice();
				model.setValueAt(newQty, rowAffected, 3);
				model.setValueAt(cost, rowAffected, 5);
			} else {
				model.addRow(new Object[] { item.getItemName(), item.getSize(), item.getColour(), qty, item.getPrice(),
						cost });
			}
			NewOrderScreen.totalCartCost += cost;
			lblCartTotal.setText("Cart Total:  R" + NewOrderScreen.totalCartCost);
		} else {
			JOptionPane.showMessageDialog(null, "Not enough stock");
		}

	}

	private int getCartRowAffected(String itemName, DefaultTableModel model) {
		int row = -1;
		for (int i = 0; i < model.getRowCount(); i++) {
			String cartItem = model.getValueAt(i, 0).toString();
			if (cartItem.equalsIgnoreCase(itemName)) {
				row = i;
				break;
			}
		}
		return row;
	}

	public boolean hasEnoughStock(String itemName, int qty) {
		boolean enough = true;
		String sql = "SELECT Quantity FROM Items WHERE ItemName = '" + itemName + "'";
		ResultSet rs = db.getResults(sql);
		try {
			if (rs.next()) {
				int qtyFromTable = rs.getInt(1);
				if (qtyFromTable < qty) {
					enough = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enough;
	}

	public int getLastOrderID() {
		int orderID = 0;
		String sql = "SELECT MAX(OrderID) FROM Orders";
		ResultSet rs = db.getResults(sql);
		try {
			if (rs.next()) {
				orderID = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderID;
	}

	public void upDateCollected(JTable tblOrders) {
		DefaultTableModel model = (DefaultTableModel) tblOrders.getModel();
		for (int i = 0; i < model.getRowCount(); i++) {
			int orderID = Integer.parseInt(model.getValueAt(i, 0).toString());
			boolean collected = Boolean.parseBoolean(model.getValueAt(i, 5).toString());
			String sql = "UPDATE Orders SET Collected = ? WHERE OrderID = ?";
			try {
				PreparedStatement st = db.getConn().prepareStatement(sql);
				st.setBoolean(1, collected);
				st.setInt(2, orderID);
				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
