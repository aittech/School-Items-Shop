package screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.ConnectDB;
import classes.Student;
import classes.StudentManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LoginScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pwfPassword;
	public static Student studentLoggedIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
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

	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 435);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblHeading = new JLabel("User Login");
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblHeading.setBounds(6, 20, 502, 16);
		contentPane.add(lblHeading);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblEmail.setBounds(44, 82, 111, 16);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPassword.setBounds(44, 130, 90, 16);
		contentPane.add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtUsername.setBounds(147, 78, 188, 26);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		pwfPassword = new JPasswordField();
		pwfPassword.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		pwfPassword.setBounds(146, 126, 189, 26);
		contentPane.add(pwfPassword);

		JCheckBox chckbxAdmin = new JCheckBox("Admin?");
		chckbxAdmin.setBounds(194, 182, 93, 21);
		contentPane.add(chckbxAdmin);

		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get user input
				String email = txtUsername.getText();
				String password = pwfPassword.getText();
				if (chckbxAdmin.isSelected()) {

					// check if admin email and password is correct
					ConnectDB db = new ConnectDB();
					String sql = "select email, password from admins";
					ResultSet rs = db.getResults(sql);
					try {
						while (rs.next()) {

							if (rs.getString("email").equals(email) && rs.getString("password").equals(password)) {

								new DashboardScreen().setVisible(true);
								dispose();

							} else {

								JOptionPane.showMessageDialog(contentPane, "Invalid username or password", "login",
										JOptionPane.ERROR_MESSAGE);

							}

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {// this is a student login

					StudentManager students = new StudentManager();
					studentLoggedIn = students.getStudentLoggedIn(email, password);
					if (studentLoggedIn == null) {

						JOptionPane.showMessageDialog(contentPane, "Invalid username or password", "login",
								JOptionPane.ERROR_MESSAGE);

					} else {

						new StudentDashboardScreen().setVisible(true);
						dispose();

					}

				}

			}

		});
		btnLogin.setBounds(178, 220, 117, 29);
		contentPane.add(btnLogin);

	}
}
