package screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import classes.ItemsOrderedManager;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ViewItemsOrderScreen extends JFrame {

	private JPanel contentPane;
	private JTable tblItemsOrdered;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewItemsOrderScreen frame = new ViewItemsOrderScreen();
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
	public ViewItemsOrderScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 373);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblHeading = new JLabel("Items Ordered");
		lblHeading.setBounds(6, 10, 623, 30);
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		contentPane.add(lblHeading);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 94, 512, 108);
		contentPane.add(scrollPane);

		tblItemsOrdered = new JTable();
		scrollPane.setViewportView(tblItemsOrdered);
		tblItemsOrdered.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));

		tblItemsOrdered.setModel(new javax.swing.table.DefaultTableModel(new String[][] {

		}, new String[] { "Item", "Size", "Color", "Qty", "Price" }));

		JLabel lblOrderID = new JLabel("Order ID");
		lblOrderID.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrderID.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblOrderID.setBounds(70, 52, 512, 16);
		contentPane.add(lblOrderID);

		JButton lblBack = new JButton("Back");
		lblBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OrderScreen().setVisible(true);
				dispose();
			}
		});
		lblBack.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblBack.setBounds(21, 237, 138, 29);
		contentPane.add(lblBack);

		// fill items ordered table
		ItemsOrderedManager im = new ItemsOrderedManager();
		im.getItemsByOrderID((DefaultTableModel) tblItemsOrdered.getModel(), OrderScreen.selectedOrderID);

		lblOrderID.setText("OrderID: " + OrderScreen.selectedOrderID);

	}

}
