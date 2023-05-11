package screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import classes.Item;
import classes.ItemManager;
import classes.ItemsOrderedManager;
import classes.OrderManager;
import classes.StudentManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class NewOrderScreen extends JFrame {

	private JPanel contentPane;
	private JTable tblCart;
	public static double totalCartCost = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewOrderScreen frame = new NewOrderScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewOrderScreen() {
		totalCartCost = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 931, 438);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblStudentID = new JLabel("Select Student By ID");
		lblStudentID.setBounds(19, 71, 167, 17);
		lblStudentID.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		contentPane.add(lblStudentID);

		JComboBox comboStudentID = new JComboBox();
		comboStudentID.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		comboStudentID.setBounds(19, 100, 237, 27);
		contentPane.add(comboStudentID);

		// add studentID's to comboBox
		StudentManager students = new StudentManager();
		students.addStudentIdsComboBox(comboStudentID);
		
		//check for student login, sets student id in combobox, disables combobox
		if(LoginScreen.studentLoggedIn != null) {
			int id = LoginScreen.studentLoggedIn.getStudentID();
			for(int i = 0; i < comboStudentID.getItemCount(); i++) {
				int comboID = (int) comboStudentID.getItemAt(i);
				if(comboID == id) {
					comboStudentID.setSelectedIndex(i);
					break;
				}
			}
			comboStudentID.setEnabled(false);
		}

		JLabel lblNewOrder = new JLabel("New Order");
		lblNewOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewOrder.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lblNewOrder.setBounds(6, 6, 882, 23);
		contentPane.add(lblNewOrder);

		JLabel lblSelectItem = new JLabel("Select Item");
		lblSelectItem.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectItem.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSelectItem.setBounds(19, 139, 237, 17);
		contentPane.add(lblSelectItem);

		JComboBox comboItems = new JComboBox();
		comboItems.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		comboItems.setBounds(19, 168, 248, 27);
		contentPane.add(comboItems);

		// add items to combobox
		ItemManager items = new ItemManager();
		items.getItemsForComboBox(comboItems);

		JLabel lblSelectQuantity = new JLabel("Select Quantity");
		lblSelectQuantity.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectQuantity.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSelectQuantity.setBounds(19, 217, 150, 17);
		contentPane.add(lblSelectQuantity);

		SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 50, 1);
		JSpinner spnQty = new JSpinner(spinnerModel);
		spnQty.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		spnQty.setBounds(19, 254, 77, 26);
		contentPane.add(spnQty);

		JLabel lblCartTotal = new JLabel("Cart Total");
		lblCartTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCartTotal.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCartTotal.setBounds(329, 259, 581, 17);
		contentPane.add(lblCartTotal);

		JButton btnAddToCart = new JButton("Add to cart");
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemData = comboItems.getSelectedItem().toString();
				int qty = (int) spnQty.getValue();
				OrderManager orders = new OrderManager();
				orders.addItemToCart(itemData, qty, tblCart, lblCartTotal);
			}
		});
		btnAddToCart.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnAddToCart.setBounds(147, 254, 138, 29);
		contentPane.add(btnAddToCart);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(329, 119, 581, 122);
		contentPane.add(scrollPane);

		tblCart = new JTable();
		scrollPane.setViewportView(tblCart);
		tblCart.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));

		tblCart.setModel(new javax.swing.table.DefaultTableModel(new String[][] {

		}, new String[] { "Item", "Size", "Color", "Qty", "Price", "Cost" }));

		JLabel lblCart = new JLabel("Cart");
		lblCart.setHorizontalAlignment(SwingConstants.CENTER);
		lblCart.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCart.setBounds(329, 90, 581, 17);
		contentPane.add(lblCart);

		JButton btnConfirmOrder = new JButton("Confirm Order");
		btnConfirmOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int studentID = Integer.parseInt(comboStudentID.getSelectedItem().toString());
				OrderManager orders = new OrderManager();
				orders.addOrder(studentID);
				ItemsOrderedManager itemsOrdered = new ItemsOrderedManager();
				itemsOrdered.addItemsForOrder(tblCart);
				JOptionPane.showMessageDialog(null, "Order has been captured");
			}
		});
		btnConfirmOrder.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnConfirmOrder.setBounds(546, 303, 155, 29);
		contentPane.add(btnConfirmOrder);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OrderScreen().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnBack.setBounds(6, 344, 138, 29);
		contentPane.add(btnBack);

		JButton btnRemoveFromCart = new JButton("Remove From Cart");
		btnRemoveFromCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblCart.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Please select a row to remove");
				} else {
					DefaultTableModel model = (DefaultTableModel) tblCart.getModel();
					double cost = Double.parseDouble(tblCart.getValueAt(row, 5).toString());
					model.removeRow(row);
					totalCartCost -= cost;
					lblCartTotal.setText("Cart Total: R" + totalCartCost);
				}

			}
		});
		btnRemoveFromCart.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnRemoveFromCart.setBounds(666, 78, 194, 29);
		contentPane.add(btnRemoveFromCart);

	}
}
