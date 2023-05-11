package screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.StudentManager;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePasswordScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtPassword1;
	private JTextField txtPassword2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePasswordScreen frame = new ChangePasswordScreen();
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
	public ChangePasswordScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 439);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Email:");
		lblUsername.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblUsername.setBounds(116, 131, 79, 16);
		contentPane.add(lblUsername);
		
		JLabel lblNewPassword = new JLabel("New password:");
		lblNewPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNewPassword.setBounds(116, 194, 106, 16);
		contentPane.add(lblNewPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblConfirmPassword.setBounds(116, 257, 129, 16);
		contentPane.add(lblConfirmPassword);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(255, 131, 197, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPassword1 = new JTextField();
		txtPassword1.setColumns(10);
		txtPassword1.setBounds(255, 194, 197, 19);
		contentPane.add(txtPassword1);
		
		txtPassword2 = new JTextField();
		txtPassword2.setColumns(10);
		txtPassword2.setBounds(255, 257, 197, 19);
		contentPane.add(txtPassword2);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String password1 = txtPassword1.getText();
				String password2 = txtPassword2.getText();
				
				if (password1.equals(password2)){
					
					LoginScreen.studentLoggedIn.setPassword(password1);
					StudentManager students = new StudentManager();
					students.changePassword(LoginScreen.studentLoggedIn);
					JOptionPane.showMessageDialog(null, "Your password has been changed");
					new StudentDashboardScreen().setVisible(true);
					dispose();
					
				}else {
					
					JOptionPane.showMessageDialog(null, "passwords do not match");
				}
				
			}
		});
		btnConfirm.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnConfirm.setBounds(291, 310, 138, 29);
		contentPane.add(btnConfirm);
		
		JLabel lblNewPassword_1 = new JLabel("New password");
		lblNewPassword_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPassword_1.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lblNewPassword_1.setBounds(10, 10, 718, 32);
		contentPane.add(lblNewPassword_1);
		
		//set email to email textfield
		txtEmail.setText(LoginScreen.studentLoggedIn.getEmail());
		txtEmail.setEnabled(false);
		
	}
}
