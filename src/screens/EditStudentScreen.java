package screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class EditStudentScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtFirstname;
	private JTextField txtSurname;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditStudentScreen frame = new EditStudentScreen();
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
	public EditStudentScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 852, 386);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEditStudent = new JLabel("Edit Student");
		lblEditStudent.setBounds(6, 22, 608, 30);
		lblEditStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditStudent.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		contentPane.add(lblEditStudent);
		
		JLabel lblFirstname = new JLabel("Firstname:");
		lblFirstname.setBounds(67, 97, 83, 17);
		lblFirstname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		contentPane.add(lblFirstname);
		
		txtFirstname = new JTextField();
		txtFirstname.setBounds(191, 92, 219, 27);
		txtFirstname.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtFirstname.setColumns(10);
		contentPane.add(txtFirstname);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(67, 148, 72, 17);
		lblSurname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		contentPane.add(lblSurname);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(191, 143, 219, 27);
		txtSurname.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtSurname.setColumns(10);
		contentPane.add(txtSurname);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(67, 202, 72, 17);
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(188, 197, 219, 27);
		txtEmail.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		contentPane.add(txtEmail);
		
		JButton btnEditStudent = new JButton("Edit Student");
		btnEditStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//get user input
				String firstname = txtFirstname.getText();
				String surname = txtSurname.getText();
				String email = txtEmail.getText();
				
				//change selected students details
				StudentScreen.selectedStudent.setFirstname(firstname);
				StudentScreen.selectedStudent.setSurname(surname);
				StudentScreen.selectedStudent.setEmail(email);
				
				StudentManager sm = new StudentManager();
				sm.editStudent(StudentScreen.selectedStudent);
				JOptionPane.showMessageDialog(contentPane, "Details have been changed.","Edit Student",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnEditStudent.setBounds(238, 254, 132, 29);
		btnEditStudent.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		contentPane.add(btnEditStudent);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StudentScreen().setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(32, 303, 97, 29);
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		contentPane.add(btnBack);
		
		//auto fill components with selected students details
		txtFirstname.setText(StudentScreen.selectedStudent.getFirstname());
		txtSurname.setText(StudentScreen.selectedStudent.getSurname());
		txtEmail.setText(StudentScreen.selectedStudent.getEmail());
		
		JLabel lblFirstnameError = new JLabel("");
		lblFirstnameError.setForeground(Color.RED);
		lblFirstnameError.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblFirstnameError.setBounds(469, 97, 245, 16);
		contentPane.add(lblFirstnameError);
		
		JLabel lblSurnameError = new JLabel("");
		lblSurnameError.setForeground(Color.RED);
		lblSurnameError.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSurnameError.setBounds(469, 145, 245, 16);
		contentPane.add(lblSurnameError);
		
		JLabel lblEmailError = new JLabel("");
		lblEmailError.setForeground(Color.RED);
		lblEmailError.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblEmailError.setBounds(469, 195, 245, 16);
		contentPane.add(lblEmailError);
		
		
	}
}
