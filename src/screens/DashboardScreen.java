package screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class DashboardScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardScreen frame = new DashboardScreen();
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
	public DashboardScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 815, 515);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setHorizontalAlignment(SwingConstants.CENTER);
		lblDashboard.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lblDashboard.setBounds(-12, 0, 813, 32);
		contentPane.add(lblDashboard);

		JButton btnStudent = new JButton("Students");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new StudentScreen().setVisible(true);
				dispose();
			}
		});
		btnStudent.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnStudent.setBounds(539, 93, 138, 29);
		contentPane.add(btnStudent);

		JButton btnItems = new JButton("Items");
		btnItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new ItemScreen().setVisible(true);
				dispose();
			}
		});
		btnItems.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnItems.setBounds(539, 195, 138, 29);
		contentPane.add(btnItems);

		JButton btnOrders = new JButton("Orders");
		btnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new OrderScreen().setVisible(true);
				dispose();
			}
		});
		btnOrders.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnOrders.setBounds(539, 299, 138, 29);
		contentPane.add(btnOrders);

		JButton btnHelp = new JButton("Help");
		btnHelp.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnHelp.setBounds(539, 379, 138, 29);
		contentPane.add(btnHelp);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(
				new ImageIcon("C:\\Users\\olivi\\My Drive\\Olly PAT\\Olly PAT New\\MY PAT 2023\\src\\Images\\SMS Logo.jpg"));
		lblLogo.setBounds(52, 52, 400, 351);
		contentPane.add(lblLogo);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new LoginScreen().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnBack.setBounds(31, 439, 138, 29);
		contentPane.add(btnBack);
	}
}
