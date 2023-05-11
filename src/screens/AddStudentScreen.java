package screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.DataValidation;
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

public class AddStudentScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtFirstname;
	private JTextField txtSurname;
	private JTextField txtEmail;
	private JButton btnAdd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudentScreen frame = new AddStudentScreen();
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

	public AddStudentScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 455);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddStudent = new JLabel("Add Student");
		lblAddStudent.setBounds(6, 10, 569, 30);
		lblAddStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddStudent.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		contentPane.add(lblAddStudent);

		JLabel lblFirstname = new JLabel("Firstname:");
		lblFirstname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblFirstname.setBounds(101, 80, 92, 16);
		contentPane.add(lblFirstname);

		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSurname.setBounds(101, 128, 79, 16);
		contentPane.add(lblSurname);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblEmail.setBounds(101, 178, 61, 16);
		contentPane.add(lblEmail);

		JLabel lblFirstnameError = new JLabel("");
		lblFirstnameError.setForeground(new Color(255, 0, 0));
		lblFirstnameError.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblFirstnameError.setBounds(483, 80, 245, 16);
		contentPane.add(lblFirstnameError);

		JLabel lblSurnameError = new JLabel("");
		lblSurnameError.setForeground(Color.RED);
		lblSurnameError.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSurnameError.setBounds(483, 128, 245, 16);
		contentPane.add(lblSurnameError);

		JLabel lblEmailError = new JLabel("");
		lblEmailError.setForeground(Color.RED);
		lblEmailError.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblEmailError.setBounds(483, 178, 245, 16);
		contentPane.add(lblEmailError);

		txtFirstname = new JTextField();
		txtFirstname.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtFirstname.setColumns(10);
		txtFirstname.setBounds(229, 76, 213, 26);
		contentPane.add(txtFirstname);

		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtSurname.setColumns(10);
		txtSurname.setBounds(229, 124, 213, 26);
		contentPane.add(txtSurname);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(229, 174, 213, 26);
		contentPane.add(txtEmail);

		btnAdd = new JButton("Add Student");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get user input
				String firstname = txtFirstname.getText();
				String surname = txtSurname.getText();
				String email = txtEmail.getText();

				DataValidation dV = new DataValidation();
				lblFirstnameError.setText(dV.checkString(firstname));
				lblSurnameError.setText(dV.checkString(surname));
				lblEmailError.setText(dV.checkEmail(email));

				if (dV.isValid()) {
					Student student = new Student(firstname, surname, email);

					StudentManager sm = new StudentManager();
					sm.addStudent(student);
					JOptionPane.showMessageDialog(contentPane, "Student has been added", "Add Student", 3);
				}
			}
		});
		btnAdd.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnAdd.setBounds(212, 262, 138, 29);
		contentPane.add(btnAdd);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StudentScreen().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnBack.setBounds(10, 310, 138, 29);
		contentPane.add(btnBack);

	}
}
