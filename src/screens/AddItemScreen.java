package screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.DataValidation;
import classes.Item;
import classes.ItemManager;
import classes.Student;
import classes.StudentManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;

public class AddItemScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtItemname;
	private JTextField txtColour;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddItemScreen frame = new AddItemScreen();
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
	public AddItemScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 505);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddStudent = new JLabel("Add Item");
		lblAddStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddStudent.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lblAddStudent.setBounds(0, 0, 651, 32);
		contentPane.add(lblAddStudent);

		JLabel lblItemName = new JLabel("Item Name:");
		lblItemName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblItemName.setBounds(136, 62, 92, 16);
		contentPane.add(lblItemName);

		txtItemname = new JTextField();
		txtItemname.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtItemname.setColumns(10);
		txtItemname.setBounds(264, 58, 213, 26);
		contentPane.add(txtItemname);

		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSize.setBounds(136, 110, 79, 16);
		contentPane.add(lblSize);

		JLabel lblColour = new JLabel("Colour:");
		lblColour.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblColour.setBounds(136, 160, 61, 16);
		contentPane.add(lblColour);

		JLabel lblItemNameError = new JLabel("");
		lblItemNameError.setForeground(Color.RED);
		lblItemNameError.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblItemNameError.setBounds(513, 66, 245, 16);
		contentPane.add(lblItemNameError);

		JLabel lblSizeError = new JLabel("");
		lblSizeError.setForeground(Color.RED);
		lblSizeError.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSizeError.setBounds(513, 114, 245, 16);
		contentPane.add(lblSizeError);

		JLabel lblQuantityError = new JLabel("");
		lblQuantityError.setForeground(Color.RED);
		lblQuantityError.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblQuantityError.setBounds(513, 215, 245, 16);
		contentPane.add(lblQuantityError);

		JLabel lblColourError = new JLabel("");
		lblColourError.setForeground(Color.RED);
		lblColourError.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblColourError.setBounds(513, 160, 245, 16);
		contentPane.add(lblColourError);

		JLabel lblPriceError = new JLabel("");
		lblPriceError.setForeground(Color.RED);
		lblPriceError.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPriceError.setBounds(513, 265, 245, 16);
		contentPane.add(lblPriceError);

		txtColour = new JTextField();
		txtColour.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtColour.setColumns(10);
		txtColour.setBounds(264, 156, 213, 26);
		contentPane.add(txtColour);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblQuantity.setBounds(136, 208, 79, 16);
		contentPane.add(lblQuantity);

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtPrice.setColumns(10);
		txtPrice.setBounds(264, 260, 213, 26);
		contentPane.add(txtPrice);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPrice.setBounds(136, 259, 61, 16);
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

		JComboBox comboBoxSize = new JComboBox();
		comboBoxSize.setMaximumRowCount(12);
		comboBoxSize.setModel(new DefaultComboBoxModel(new String[] { "XS", "S", "M", "L", "XL" }));
		comboBoxSize.setBounds(335, 107, 52, 26);
		contentPane.add(comboBoxSize);

		JSpinner spnQuantity = new JSpinner();
		spnQuantity.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spnQuantity.setBounds(357, 209, 30, 20);
		contentPane.add(spnQuantity);

		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String itemname = txtItemname.getText();
				String size = comboBoxSize.getSelectedItem().toString();
				String colour = txtColour.getText();
				int quantity = Integer.parseInt(spnQuantity.getValue().toString());
				String sPrice = txtPrice.getText();

				// data validation
				DataValidation dV = new DataValidation();
				lblItemNameError.setText(dV.checkString(itemname));
				lblColourError.setText(dV.checkString(colour));
				lblPriceError.setText(dV.checkPrice(sPrice));

				if (dV.isValid()) {

					double price = Double.parseDouble(sPrice);

					Item item = new Item(itemname, size, colour, quantity, price);

					ItemManager im = new ItemManager();
					im.addItem(item);
					JOptionPane.showMessageDialog(contentPane, "Item has been addded", "Add Item",
							JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});

		btnAddItem.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnAddItem.setBounds(266, 313, 138, 29);
		contentPane.add(btnAddItem);

	}
}
