// A package named classes
package classes;

// Importing necessary classes
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

// A class named StudentManager
public class StudentManager {

	private ConnectDB db; // instance used to connect to database

	// constructor that establishes a connection to the database
	public StudentManager() {
		db = new ConnectDB();
	}

	// receives tblStudents model and adds all students to the model
	public void addDataStudents(DefaultTableModel model) {
		model.setRowCount(0); // clears the model
		String sql = "select studentid, firstname, surname, email from students order by surname, firstname";
		ResultSet rs = db.getResults(sql);
		try {
			while (rs.next()) {
				// adds a new row containing student data to the model
				model.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4) });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// receives a Student object, adds the data from the object to the database
	public void addStudent(Student student) {
		String sql = "insert into students(firstname, surname, email, password) values(?,?,?,?)";
		try {
			PreparedStatement st = db.getConn().prepareStatement(sql);
			st.setString(1, student.getFirstname());
			st.setString(2, student.getSurname());
			st.setString(3, student.getEmail());
			st.setString(4, "1234"); // default password
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// receives a Student object, edits the student's details in the database
	public void editStudent(Student student) {
		String sql = "update students set firstname = ? , surname = ? , email = ? where studentid = ?";
		try {
			PreparedStatement st = db.getConn().prepareStatement(sql);
			st.setString(1, student.getFirstname());
			st.setString(2, student.getSurname());
			st.setString(3, student.getEmail());
			st.setInt(4, student.getStudentID());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// deletes a student from the database based on their student ID
	public void deleteStudent(int studentID) {
		String sql = "delete from students where studentID = ?";

		try {
			PreparedStatement st = db.getConn().prepareStatement(sql);
			st.setInt(1, studentID);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// adds student IDs to a combo box
	public void addStudentIdsComboBox(JComboBox combo) {
		String sql = "select studentID from students";
		ResultSet rs = db.getResults(sql);
		try {
			while (rs.next()) {
				combo.addItem(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// verifies a student's login credentials
	public Student getStudentLoggedIn(String email, String password) {
		String sql = "select * from students where email = '" + email + "' AND password = '" + password + "'";
		ResultSet rs = db.getResults(sql);
		try {
			while (rs.next()) {
				return new Student(rs.getInt("StudentID"), rs.getString("Firstname"), rs.getString("Surname"),
						rs.getString("Email"), rs.getString("Password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // returns null if username and password are not found in Students
	}
	
	// receives a Student object, changes the students password
		public void changePassword(Student student) {
			String sql = "update students set password = ? where studentid = ?";
			try {
				PreparedStatement st = db.getConn().prepareStatement(sql);
				st.setString(1, student.getPassword());
				st.setInt(2, student.getStudentID());
				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	

}
