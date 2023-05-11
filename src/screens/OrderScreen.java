package screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import classes.OrderManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class OrderScreen extends JFrame {

	private JPanel contentPane;
	private JTable tblOrders;
	public static int selectedOrderID;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderScreen frame = new OrderScreen();
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

	public void searchTable(String searchItem, JTable table) {// makes use of reqular expressions to filter the table
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(
				(DefaultTableModel) table.getModel());
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter("(?i)" + searchItem)); // ?i makes ignores case with filter
	}

	public OrderScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 417);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblOrders = new JLabel("Orders");
		lblOrders.setBounds(6, 10, 672, 30);
		lblOrders.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrders.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		contentPane.add(lblOrders);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 102, 784, 150);
		contentPane.add(scrollPane);

		tblOrders = new JTable();
		tblOrders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int rowSelected = tblOrders.getSelectedRow();
				int orderID = Integer.parseInt(tblOrders.getValueAt(rowSelected, 0).toString());

			}
		});
		scrollPane.setViewportView(tblOrders);
		tblOrders.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));

		tblOrders.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "OrderID", "StudentID", "Firstname", "Surname", "Date", "Collected" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		JButton btnViewItemsOrder = new JButton("View Items");
		btnViewItemsOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSelected = tblOrders.getSelectedRow();
				if (rowSelected == -1) {
					JOptionPane.showMessageDialog(null, "Select order first.");
				} else {
					selectedOrderID = Integer.parseInt(tblOrders.getValueAt(rowSelected, 0).toString());
					new ViewItemsOrderScreen().setVisible(true);
					dispose();
				}
			}
		});
		btnViewItemsOrder.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnViewItemsOrder.setBounds(839, 102, 138, 29);
		contentPane.add(btnViewItemsOrder);

		JButton btnAddOrder = new JButton("Add Order");
		btnAddOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NewOrderScreen().setVisible(true);
				dispose();
			}
		});
		btnAddOrder.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnAddOrder.setBounds(839, 156, 138, 29);
		contentPane.add(btnAddOrder);

		JButton btnDeleteOrder = new JButton("Delete Order");
		btnDeleteOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSelected = tblOrders.getSelectedRow();
				if (rowSelected == -1) {
					JOptionPane.showMessageDialog(null, "Select order first.");
				} else {
					int orderID = Integer.parseInt(tblOrders.getValueAt(rowSelected, 0).toString());
					OrderManager order = new OrderManager();
					order.deleteOrder(orderID);
					JOptionPane.showMessageDialog(null, "Order deleted.");
					order.addDataOrder((DefaultTableModel) tblOrders.getModel());
				}
			}
		});
		btnDeleteOrder.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnDeleteOrder.setBounds(839, 208, 138, 29);
		contentPane.add(btnDeleteOrder);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new DashboardScreen().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnBack.setBounds(23, 290, 138, 29);
		contentPane.add(btnBack);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				String text = txtSearch.getText();
				searchTable(text, tblOrders);
			}
		});
		txtSearch.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtSearch.setColumns(10);
		txtSearch.setBounds(95, 66, 213, 26);
		contentPane.add(txtSearch);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSearch.setBounds(34, 70, 61, 16);
		contentPane.add(lblSearch);

		JButton btnUpdateCollected = new JButton("Update Collected");
		btnUpdateCollected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderManager orders = new OrderManager();
				orders.upDateCollected(tblOrders);
				orders.addDataOrder((DefaultTableModel) tblOrders.getModel());
			}
		});
		btnUpdateCollected.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnUpdateCollected.setBounds(621, 264, 179, 29);
		contentPane.add(btnUpdateCollected);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		btnExit.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnExit.setBounds(23, 343, 138, 29);
		contentPane.add(btnExit);

		OrderManager orders = new OrderManager();
		if(LoginScreen.studentLoggedIn != null) { //checks if a student has logged in and fills only their order
			orders.addDataStudentsOrder((DefaultTableModel) tblOrders.getModel(), LoginScreen.studentLoggedIn.getStudentID());
			//disable buttons for students
			btnBack.setEnabled(false);
			btnDeleteOrder.setEnabled(false);
			btnDeleteOrder.setEnabled(false);
			btnUpdateCollected.setEnabled(false);
		}
		else { //this is an admin logged in, shows all orders
			orders.addDataOrder((DefaultTableModel) tblOrders.getModel());
		}
		

		// format table header

		JTableHeader header = tblOrders.getTableHeader();
		header.setBackground(Color.black);
		header.setForeground(Color.white);
		Font headerFont = new Font("Comic Sans MS", Font.BOLD, 14);
		header.setFont(headerFont);

		// change column width
		tblOrders.getColumnModel().getColumn(0).setPreferredWidth(5);
		tblOrders.getColumnModel().getColumn(1).setPreferredWidth(10);
		tblOrders.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblOrders.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblOrders.getColumnModel().getColumn(4).setPreferredWidth(100);
		tblOrders.getColumnModel().getColumn(5).setPreferredWidth(10);

	}
}
