package screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import classes.Item;
import classes.ItemManager;
import classes.Student;
import classes.StudentManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class ItemScreen extends JFrame {

	private JPanel contentPane;
	private JTable tblItems;
	private JLabel lblSearch;
	private JTextField txtSearch;
	private JLabel lblItems;
	private JButton btnAddItem;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnBack;

	public static Item selectedItem;

	/**
	 * Launch the application.
	 */

	public void searchTable(String searchItem, JTable table) {// makes use of reqular expressions to filter the table
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(
				(DefaultTableModel) table.getModel());
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter("(?i)" + searchItem)); // ?i makes ignores case with filter
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemScreen frame = new ItemScreen();
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
	public ItemScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 442);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 143, 538, 174);
		contentPane.add(scrollPane);
		
		tblItems = new JTable();
		scrollPane.setViewportView(tblItems);
		tblItems.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		
		
		tblItems.setModel(new javax.swing.table.DefaultTableModel(
	            new String [][] {

	            },
	            new String [] {
	                "Item ID", "Item name", "Size", "Colour", "Quantity", "Price"
	            }
	        ));
		
		lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSearch.setBounds(30, 111, 61, 16);
		contentPane.add(lblSearch);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String text = txtSearch.getText();
				searchTable(text, tblItems);
			}
		});
		txtSearch.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtSearch.setColumns(10);
		txtSearch.setBounds(91, 107, 213, 26);
		contentPane.add(txtSearch);
		
		lblItems = new JLabel("Items");
		lblItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblItems.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lblItems.setBounds(10, 26, 769, 23);
		contentPane.add(lblItems);
		
		btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new AddItemScreen().setVisible(true);
				//closes the current screen
				dispose();
			}
		});
		btnAddItem.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnAddItem.setBounds(575, 140, 138, 29);
		contentPane.add(btnAddItem);
		
		
		
		btnDelete = new JButton("Delete Item");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = tblItems.getSelectedRow();
				if(row == -1) {
					
					JOptionPane.showMessageDialog(null,"Please select a item", "Delete item", JOptionPane.ERROR_MESSAGE);
				}
			else {
				int id = Integer.parseInt(tblItems.getValueAt(row, 0).toString());
				ItemManager sm = new ItemManager();
				sm.deleteItem(id);
				JOptionPane.showMessageDialog(null, "Item deleted", "Delete item", JOptionPane.INFORMATION_MESSAGE);
				sm.addDataItems((DefaultTableModel)tblItems.getModel());
			}
			}
			});
		
				
				
		btnDelete.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnDelete.setBounds(575, 213, 138, 29);
		contentPane.add(btnDelete);
		
		
		
		btnEdit = new JButton("Edit Items");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = tblItems.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(contentPane, "Please select an item","Edit Item", JOptionPane.ERROR_MESSAGE);
				}
				else {
					int itemID = Integer.parseInt(tblItems.getValueAt(row, 0).toString());
					String itemName = tblItems.getValueAt(row, 1).toString();
					String size = tblItems.getValueAt(row, 2).toString();
					String colour = tblItems.getValueAt(row, 3).toString();
					int quantity = Integer.parseInt(tblItems.getValueAt(row, 4).toString());
					double price = Double.parseDouble(tblItems.getValueAt(row, 5).toString());
					selectedItem = new Item(itemID,itemName, size, colour, quantity, price);
					new EditItemScreen().setVisible(true);
					dispose();
				}
				
			}
		});
		btnEdit.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnEdit.setBounds(575, 296, 138, 29);
		contentPane.add(btnEdit);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new DashboardScreen().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnBack.setBounds(26, 366, 138, 29);
		contentPane.add(btnBack);
		
		//add data to tblItems
		ItemManager im = new ItemManager();
		im.addDataItems((DefaultTableModel) tblItems.getModel());
	}
}
