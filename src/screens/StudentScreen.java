package screens;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import classes.Student;
import classes.StudentManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StudentScreen extends JFrame {

	private JPanel contentPane;
	private JTable tblStudents;
	private JTextField txtSearch;

	public static Student selectedStudent; // stores details for the selected student from the tblStudents

	/**
	 * Launch the application.
	 */

	public void searchTable(String searchItem, JTable table) {// makes use of regular expressions to filter the table
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(
				(DefaultTableModel) table.getModel());
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter("(?i)" + searchItem)); // ?i makes ignores case with filter
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentScreen frame = new StudentScreen();
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
	public StudentScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 421);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblHeading = new JLabel("Students");
		lblHeading.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setBounds(6, 34, 769, 23);
		contentPane.add(lblHeading);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 122, 516, 180);
		contentPane.add(scrollPane);

		tblStudents = new JTable();
		tblStudents.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		scrollPane.setViewportView(tblStudents);

		tblStudents.setModel(new javax.swing.table.DefaultTableModel(new String[][] {

		}, new String[] { "ID", "Firstname", "Surname", "Email" }));

		JButton btnDelete = new JButton("Delete Student");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int row = tblStudents.getSelectedRow();
				if (row == -1) {

					JOptionPane.showMessageDialog(null, "Please select a student", "Delete student",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int id = Integer.parseInt(tblStudents.getValueAt(row, 0).toString());
					StudentManager sm = new StudentManager();
					sm.deleteStudent(id);
					JOptionPane.showMessageDialog(null, "Student deleted", "Delete student",
							JOptionPane.INFORMATION_MESSAGE);
					sm.addDataStudents((DefaultTableModel) tblStudents.getModel());
				}
			}
		});

		btnDelete.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnDelete.setBounds(571, 190, 138, 29);
		contentPane.add(btnDelete);

		JButton btnAdd = new JButton("Add Student");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// opens the AddStudentScreen
				new AddStudentScreen().setVisible(true);
				// closes the current screen
				dispose();
			}
		});
		btnAdd.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnAdd.setBounds(571, 117, 138, 29);
		contentPane.add(btnAdd);

		JButton btnEdit = new JButton("Edit Student");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = tblStudents.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(contentPane, "Please select a student", "Delete Student",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int studentID = Integer.parseInt(tblStudents.getValueAt(row, 0).toString());
					String firstname = tblStudents.getValueAt(row, 1).toString();
					String surname = tblStudents.getValueAt(row, 2).toString();
					String email = tblStudents.getValueAt(row, 3).toString();
					selectedStudent = new Student(studentID, firstname, surname, email);
					new EditStudentScreen().setVisible(true);
					dispose();
				}

			}
		});
		btnEdit.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnEdit.setBounds(571, 273, 138, 29);
		contentPane.add(btnEdit);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new DashboardScreen().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnBack.setBounds(22, 343, 138, 29);
		contentPane.add(btnBack);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSearch.setBounds(26, 88, 61, 16);
		contentPane.add(lblSearch);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				String text = txtSearch.getText();
				searchTable(text, tblStudents);

			}
		});
		txtSearch.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtSearch.setBounds(87, 84, 213, 26);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);

		// format table header

		JTableHeader header = tblStudents.getTableHeader();
		header.setBackground(Color.black);
		header.setForeground(Color.white);
		Font headerFont = new Font("Verdana", Font.BOLD, 14);
		header.setFont(headerFont);

		// change column width
		tblStudents.getColumnModel().getColumn(0).setPreferredWidth(5);
		tblStudents.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblStudents.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblStudents.getColumnModel().getColumn(3).setPreferredWidth(150);

		// add data to tblStudents

		StudentManager sm = new StudentManager();
		sm.addDataStudents((DefaultTableModel) tblStudents.getModel());

	}
}
