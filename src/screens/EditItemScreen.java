package screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.ItemManager;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import java.awt.Color;

public class EditItemScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtItemName;
	private JTextField txtSize;
	private JTextField txtColour;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditItemScreen frame = new EditItemScreen();
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
	public EditItemScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 432);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtItemName = new JTextField();
		txtItemName.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtItemName.setColumns(10);
		txtItemName.setBounds(238, 62, 213, 26);
		contentPane.add(txtItemName);
		
		txtSize = new JTextField();
		txtSize.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtSize.setColumns(10);
		txtSize.setBounds(238, 110, 213, 26);
		contentPane.add(txtSize);
		
		txtColour = new JTextField();
		txtColour.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtColour.setColumns(10);
		txtColour.setBounds(238, 160, 213, 26);
		contentPane.add(txtColour);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtPrice.setColumns(10);
		txtPrice.setBounds(238, 266, 213, 26);
		contentPane.add(txtPrice);
		
		JSpinner spnQuantity = new JSpinner();
		spnQuantity.setBounds(315, 215, 30, 20);
		contentPane.add(spnQuantity);
		
		JLabel lblItemName = new JLabel("Item Name:");
		lblItemName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblItemName.setBounds(136, 62, 92, 16);
		contentPane.add(lblItemName);
		
		JLabel lblEditItem = new JLabel("Edit Item");
		lblEditItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditItem.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lblEditItem.setBounds(0, 0, 636, 32);
		contentPane.add(lblEditItem);
		
		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSize.setBounds(136, 110, 79, 16);
		contentPane.add(lblSize);
		
		JLabel lblColour = new JLabel("Colour:");
		lblColour.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblColour.setBounds(136, 160, 61, 16);
		contentPane.add(lblColour);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblQuantity.setBounds(136, 214, 79, 16);
		contentPane.add(lblQuantity);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPrice.setBounds(136, 266, 61, 16);
		contentPane.add(lblPrice);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new ItemScreen().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnBack.setBounds(10, 355, 138, 29);
		contentPane.add(btnBack);
		
		JButton btnEditItem = new JButton("Edit Item");
		btnEditItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String itemName = txtItemName.getText();
				String colour = txtColour.getText();
				String size = txtSize.getText();
				int quantity = Integer.parseInt(spnQuantity.getValue().toString());
				double price = Double.parseDouble(txtPrice.getText());
				ItemScreen.selectedItem.setItemName(itemName);
				ItemScreen.selectedItem.setColour(colour);
				ItemScreen.selectedItem.setSize(size);
				ItemScreen.selectedItem.setQantity(quantity);
				ItemScreen.selectedItem.setPrice(price);
				ItemManager im = new ItemManager();
				im.editItem(ItemScreen.selectedItem);
				JOptionPane.showMessageDialog(contentPane, "Details have been updated", "Edit item", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnEditItem.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnEditItem.setBounds(276, 313, 138, 29);
		contentPane.add(btnEditItem);
		
		//auto-fill components with selected items 
		txtItemName.setText(ItemScreen.selectedItem.getItemName());
		txtSize.setText(ItemScreen.selectedItem.getSize());
		txtColour.setText(ItemScreen.selectedItem.getColour());
		txtPrice.setText("" + ItemScreen.selectedItem.getPrice());
		spnQuantity.setValue(ItemScreen.selectedItem.getQantity());
		
		JLabel lblItemNameError = new JLabel("");
		lblItemNameError.setForeground(Color.RED);
		lblItemNameError.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblItemNameError.setBounds(513, 67, 245, 16);
		contentPane.add(lblItemNameError);
		
		JLabel lblSizeError = new JLabel("");
		lblSizeError.setForeground(Color.RED);
		lblSizeError.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSizeError.setBounds(513, 115, 245, 16);
		contentPane.add(lblSizeError);
		
		JLabel lblColourError = new JLabel("");
		lblColourError.setForeground(Color.RED);
		lblColourError.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblColourError.setBounds(513, 161, 245, 16);
		contentPane.add(lblColourError);
		
		JLabel lblQuantityError = new JLabel("");
		lblQuantityError.setForeground(Color.RED);
		lblQuantityError.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblQuantityError.setBounds(513, 216, 245, 16);
		contentPane.add(lblQuantityError);
		
		JLabel lblPriceError = new JLabel("");
		lblPriceError.setForeground(Color.RED);
		lblPriceError.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPriceError.setBounds(513, 266, 245, 16);
		contentPane.add(lblPriceError);
		
		
	}
}
